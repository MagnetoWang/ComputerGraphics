package tutorial.threeD;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;

import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.universe.*;

public class DrawingExample extends Applet implements MouseListener, MouseMotionListener {
	 
	private static final long serialVersionUID = 1L;
	private MainFrame frame;
	private Box box;
	private int imageHeight = 256;
	private int imageWidth = 256;
	private Canvas3D canvas;
	private SimpleUniverse universe;
	private BranchGroup group = new BranchGroup();
	private PickCanvas pickCanvas;
	private BufferedImage frontImage;
	private Shape3D frontShape;
	private Texture texture;
	private Appearance appearance;
	private TextureLoader loader;
	private int lastX=-1;
	private int lastY=-1;
	private int mouseButton = 0;
	private TransformGroup boxTransformGroup;
	
	public static void main(String[] args) {
              System.setProperty("sun.awt.noerasebackground", "true");
		DrawingExample object = new DrawingExample();		 
		object.frame = new MainFrame(object, args, object.imageWidth, object.imageHeight);
		object.validate();
	}

	public void init() {
  		startDrawing();
  	}

	public Point3d getPosition(MouseEvent event) {
		Point3d eyePos = new Point3d();
		Point3d mousePos = new Point3d();
		canvas.getCenterEyeInImagePlate(eyePos);
		canvas.getPixelLocationInImagePlate(event.getX(), event.getY(), mousePos);
		Transform3D transform = new Transform3D();
		canvas.getImagePlateToVworld(transform);
		transform.transform(eyePos);
		transform.transform(mousePos);
		Vector3d direction = new Vector3d(eyePos);
		direction.sub(mousePos);
		// three points on the plane
		Point3d p1 = new Point3d(.5, -.5, .5);
		Point3d p2 = new Point3d(.5, .5, .5);
		Point3d p3 = new Point3d(-.5, .5, .5);
		Transform3D currentTransform = new Transform3D();
		box.getLocalToVworld(currentTransform);
		currentTransform.transform(p1);
		currentTransform.transform(p2);
		currentTransform.transform(p3);		
		Point3d intersection = getIntersection(eyePos, mousePos, p1, p2, p3);
		currentTransform.invert();
		currentTransform.transform(intersection);
		return intersection;		
	}
	
	/**
	 * Returns the point where a line crosses a plane  
	 */
	Point3d getIntersection(Point3d line1, Point3d line2, 
			Point3d plane1, Point3d plane2, Point3d plane3) {
		Vector3d p1 = new Vector3d(plane1);
		Vector3d p2 = new Vector3d(plane2);
		Vector3d p3 = new Vector3d(plane3);
		Vector3d p2minusp1 = new Vector3d(p2);
		p2minusp1.sub(p1);
		Vector3d p3minusp1 = new Vector3d(p3);
		p3minusp1.sub(p1);
		Vector3d normal = new Vector3d();
		normal.cross(p2minusp1, p3minusp1);
		// The plane can be defined by p1, n + d = 0
		double d = -p1.dot(normal);
		Vector3d i1 = new Vector3d(line1);
		Vector3d direction = new Vector3d(line1);
		direction.sub(line2);
		double dot = direction.dot(normal);
		if (dot == 0) return null;
		double t = (-d - i1.dot(normal)) / (dot);
		Vector3d intersection = new Vector3d(line1);
		Vector3d scaledDirection = new Vector3d(direction);
		scaledDirection.scale(t);
		intersection.add(scaledDirection);
		Point3d intersectionPoint = new Point3d(intersection);
		return intersectionPoint;
	}
	
	private void startDrawing() {
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse
				.getPreferredConfiguration();
		canvas = new Canvas3D(config);
		universe = new SimpleUniverse(canvas);
		add("Center", canvas);
		positionViewer();
		getScene();
		universe.addBranchGraph(group);
		pickCanvas = new PickCanvas(canvas, group);
		pickCanvas.setMode(PickInfo.PICK_BOUNDS);
		canvas.addMouseMotionListener(this);
		canvas.addMouseListener(this);
	}
	public void getScene() {
		addLights(group);
		Appearance ap = getAppearance(new Color3f(Color.blue));
		ap.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		ap.setCapability(Appearance.ALLOW_TEXGEN_WRITE);
		box = new Box(.5f, .5f, .5f, Primitive.GENERATE_TEXTURE_COORDS,
				getAppearance(new Color3f(Color.green)));		 
		box.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
		box.setCapability(Box.GEOMETRY_NOT_SHARED);		
		box.setCapability(Box.ALLOW_LOCAL_TO_VWORLD_READ);
		frontShape = box.getShape(Box.FRONT);
		frontShape.setAppearance(ap);
		
		 
		box.getShape(Box.TOP).setAppearance(getAppearance(Color.magenta));
		box.getShape(Box.BOTTOM).setAppearance(getAppearance(Color.orange)); ;
		box.getShape(Box.RIGHT).setAppearance(getAppearance(Color.red));
		box.getShape(Box.LEFT).setAppearance(getAppearance(Color.green)); 
		box.getShape(Box.BACK).setAppearance(getAppearance(new Color3f(Color.yellow))); ;
		
		frontImage = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)frontImage.getGraphics();
		g.setColor(new Color(70,70,140));
		g.fillRect(0, 0, imageWidth, imageHeight);
		addTexture(frontImage, frontShape);	
		MouseRotate behavior = new MouseRotate();
	    BoundingSphere bounds =
	        new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);	    
	    boxTransformGroup = new TransformGroup();
		boxTransformGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		boxTransformGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		behavior.setTransformGroup(boxTransformGroup);
		boxTransformGroup.addChild(behavior);

		
		behavior.setSchedulingBounds(bounds);	
		boxTransformGroup.addChild(box);
		group.addChild(boxTransformGroup);
		
	}
	public void addTexture(BufferedImage image, Shape3D shape) {
		frontShape.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
		appearance = shape.getAppearance();
		appearance.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		appearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
		changeTexture( texture,  image,  shape);		
		Color3f col = new Color3f(0.0f, 0.0f, 1.0f);
		ColoringAttributes ca = new ColoringAttributes(col,
				ColoringAttributes.NICEST);
		appearance.setColoringAttributes(ca);
		
		}
    public void changeTexture(Texture texture, BufferedImage image, Shape3D shape) {
    	loader = new TextureLoader(image, "RGB",
				TextureLoader.GENERATE_MIPMAP);
    	texture = loader.getTexture();
		texture.setBoundaryModeS(Texture.CLAMP_TO_BOUNDARY);
		texture.setBoundaryModeT(Texture.CLAMP_TO_BOUNDARY);
		texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.5f, 0f));
		// Set up the texture attributes
		// could be REPLACE, BLEND or DECAL instead of MODULATE
		
		// front = getAppearance(new Color3f(Color.YELLOW));
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		Color3f red = new Color3f(0.7f, .15f, .15f);
		appearance.setMaterial(new Material(red, black, red, white, 1.0f));		 
		TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.REPLACE);	
		appearance.setTextureAttributes(texAttr);
		appearance.setTexture(texture);
		shape.setAppearance(appearance);
	}
		
	BufferedImage getStartingImage(int i, int width, int height) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(new Color(70,70,140));
		g.fillRect(0, 0, width, height);
		return image;
	}
	
	public void positionViewer() {
		ViewingPlatform vp = universe.getViewingPlatform();
		TransformGroup tg1 = vp.getViewPlatformTransform();
		Transform3D t3d = new Transform3D();
		tg1.getTransform(t3d);
		vp.setNominalViewingTransform();

	}
	
	public static void addLights(BranchGroup group) {
		Color3f light1Color = new Color3f(0.7f, 0.8f, 0.8f);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
				100.0);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color,
				light1Direction);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);
		AmbientLight light2 = new AmbientLight(new Color3f(0.3f, 0.3f, 0.3f));
		light2.setInfluencingBounds(bounds);
		group.addChild(light2);
	}
	public static Appearance getAppearance(Color color) {
		return getAppearance(new Color3f(color));
	}
	public static Appearance getAppearance(Color3f color) {
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		Appearance ap = new Appearance();
		Texture texture = new Texture2D();
		TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.MODULATE);
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
		texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
		Material mat = new Material(color, black, color, white, 70f);
		ap.setTextureAttributes(texAttr);
		ap.setMaterial(mat);
		ap.setTexture(texture);	 
		ColoringAttributes ca = new ColoringAttributes(color,
				ColoringAttributes.NICEST);
		ap.setColoringAttributes(ca);
		return ap;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
		lastX=-1;
		lastY=-1;
		mouseButton = event.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}

	@Override
	public void mouseDragged(MouseEvent event) {		
		 if (mouseButton==MouseEvent.BUTTON1) return;
		 Point3d  intersectionPoint = getPosition(event);
		 if (Math.abs(intersectionPoint.x) < 0.5 && Math.abs(intersectionPoint.y) < 0.5)  {
			 double x = (0.5 + intersectionPoint.x) * imageWidth;
			 double y = (0.5 - intersectionPoint.y) * imageHeight;			 
			 Graphics2D g = (Graphics2D) frontImage.getGraphics();
			 g.setColor( Color.BLACK);
			 g.setStroke(new BasicStroke(3));
			 int iX = (int)(x + .5);
			 int iY = (int)(y + .5);
			 if (lastX < 0) {
				 lastX = iX;
				 lastY = iY;
			 }
			 g.drawLine(lastX, lastY, iX, iY);
			 lastX = iX;
			 lastY = iY;
             changeTexture(texture, frontImage, frontShape);
		 }	
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {	
	}
}
