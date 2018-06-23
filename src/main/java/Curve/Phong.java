package Curve;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.universe.*;
import java.awt.GraphicsConfiguration;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.j3d.*;
import javax.vecmath.*;

/**
 * !Diese Klasse wurde f�r das Laden �ber ein JAR-Archiv
 * oder Applet welches ein JAR - Archiv nutzt angepasst
 * Um das Programm als einfache Applikation �ber einen class-File
 * laufen zu lassen bitte auf den Code zum Einladen der OBJ Datei
 * im Tutorial zur�ckgreifen! 
 */
public class Phong extends Applet{

	/**
	 * init Methoden f�r die Darstellung als Applet
	 */
	public void init(){
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		canvas3D = new Canvas3D( config );
		add("Center", canvas3D);
		BranchGroup szene = macheSzene();
		szene.compile();
//		canvas3D.addMouseMotionListener();
//		canvas3D.addMouseListener(this);
		universe = new SimpleUniverse( canvas3D );
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(szene);
	}

	/**
	 * Erstellt den Szenegraphen
	 * @return BranchGroup
	 */
	public BranchGroup macheSzene(){
		BranchGroup objWurzel = new BranchGroup();
		// Transformation, 2 Rotationen:
		Transform3D drehung = new Transform3D();
		Transform3D drehung2 = new Transform3D();
		drehung.rotX(Math.PI / 4.0d);
		drehung2.rotY(Math.PI / 5.0d);
		drehung.mul(drehung2);
		TransformGroup objDreh =
						new TransformGroup(drehung);
//		Class cls =null;

//		System.out.println(Phong.class.getClassLoader().getResource("teapot.obj").getPath() );
		//Loader

		ClassLoader cLoader = Thread.currentThread().getContextClassLoader();
		ObjectFile file = new ObjectFile (ObjectFile.RESIZE);
		Object scene = null;
		try {
			System.out.println(cLoader.getSystemResource("teapot.obj"));
			scene = file.load("src/resourse/teapot.obj");
		} catch (FileNotFoundException | IncorrectFormatException | ParsingErrorException e) {
			
			e.printStackTrace();
		}
//		try {
//			// Applet Abfrage
//			if (getCodeBase()!=null){
//				try {
//					// Laden der Obj Datei als Applet mit jar
//					scene = file.load(new URL("jar:"+getCodeBase()+"Phong.jar!/teapot.obj"));
//				} catch (MalformedURLException e1) {
//					e1.printStackTrace();
//				}
//			}
//			// Laden der Obj Datei mittels jar
//			else scene = file.load(ClassLoader.getSystemResource("src/resourse/teapot.obj"));
//			
//		}
//		catch (FileNotFoundException e) {
//			System.err.println(e);
//			System.exit(1);
//		}
//		catch (ParsingErrorException e) {
//			System.err.println(e);
//			System.exit(1);
//		}
//		catch (IncorrectFormatException e) {
//			System.err.println(e);
//			System.exit(1);
//		}
		objDreh.addChild(((Scene)scene).getSceneGroup());
		
		DirectionalLight d_Licht = new DirectionalLight(
						 new Color3f (1.0f, 1.0f, 1.0f), new Vector3f (-1.0f, -1.0f, -1.0f));
		d_Licht.setInfluencingBounds (new BoundingSphere (new Point3d(0.0d,0.0d,0.0d), 100.0d));
		objDreh.addChild(d_Licht);
		objWurzel.addChild(objDreh);
		return objWurzel;
	}

	/**
	 * gibt speicher frei
	 */
	public void destroy(){
		universe.removeAllLocales();
	}

	public static void main(String[] args) {
		frame = new MainFrame(new Phong(),500,500);
		frame.setTitle("Phong");
	}

	//---- Attribute -----------------------
	private SimpleUniverse universe;
	private Canvas3D canvas3D;
	private static Frame frame;
	
	
	
	
	
	
	
	
	
	
	public void mouseClicked(MouseEvent arg0) {
	}

	
	public void mouseEntered(MouseEvent arg0) {
	}

	
	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent event) {
		lastX=-1;
		lastY=-1;
		mouseButton = event.getButton();
	}

	
	public void mouseReleased(MouseEvent arg0) {		
	}

	
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
	
	
	
	
	
	public void mouseMoved(MouseEvent arg0) {	
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
	
	private static final long serialVersionUID = 1L;
	
	private Box box;
	private int imageHeight = 256;
	private int imageWidth = 256;
	private Canvas3D canvas;
	
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
}
