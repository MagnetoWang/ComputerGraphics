package solorSystem;
/** 
*
* @ClassName : solor.java
* @author : Magneto_Wang
* @date  2018年6月25日 下午11:03:32
* @Description  模拟太阳公转
* 
*/


import java.util.Scanner;

import javax.xml.soap.SOAPException;

import com.maven.JavaGraphics.ComputerGraphics.Xform;
import com.maven.JavaGraphics.ComputerGraphics.Xform.RotateOrder;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *  Main class for the "Hello World" style example
 */
public class solor extends Application {
	RotateOrder axisOrder=RotateOrder.XYZ;
	final Group root = new Group();
	final Xform world = new Xform();
	final PerspectiveCamera camera = new PerspectiveCamera(true);
	final Xform cameraXform = new Xform(axisOrder);
	final Xform cameraXform2 = new Xform(axisOrder);
	final Xform cameraXform3 = new Xform(axisOrder);
	private static final double CAMERA_INITIAL_DISTANCE = -450;
	private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
	private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
	private static final double CAMERA_NEAR_CLIP = 0.1;
	private static final double CAMERA_FAR_CLIP = 10000.0;
	
	private static final double AXIS_LENGTH = 250.0;
	
	

    final double cameraDistance = 450;
    final Xform moleculeGroup = new Xform(axisOrder);
    private Timeline timeline;
    boolean timelinePlaying = false;
    double ONE_FRAME = 1.0 / 24.0;
    double DELTA_MULTIPLIER = 200.0;

    double ALT_MULTIPLIER = 0.5;
 
	
	private static final double CONTROL_MULTIPLIER = 0.1;
	private static final double SHIFT_MULTIPLIER = 10.0;
	private static final double MOUSE_SPEED = 0.1;
	private static final double ROTATION_SPEED = 2.0;
	private static final double TRACK_SPEED = 0.3;
	double mousePosX;
	double mousePosY;
	double mouseOldX;
	double mouseOldY;
	double mouseDeltaX;
	double mouseDeltaY;

	final Xform axisGroup = new Xform();
	
	final Xform objectGroup =new Xform();
	
	//Color Set
	final PhongMaterial redMaterial = new PhongMaterial(Color.RED);
	final PhongMaterial greenMaterial = new PhongMaterial(Color.GREEN);
	final PhongMaterial blueMaterial = new PhongMaterial(Color.BLUE);
	
	

    private void buildCamera() {
    	root.getChildren().add(cameraXform);
    	cameraXform.getChildren().add(cameraXform2);
    	cameraXform2.getChildren().add(cameraXform3);
    	
    	cameraXform3.getChildren().add(camera);
//    	cameraXform2.setRotateY(60.0);
//    	cameraXform3.setRotateZ(60.0);
    	camera.setNearClip(CAMERA_NEAR_CLIP);
    	camera.setFarClip(CAMERA_FAR_CLIP);
    	camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
//    	cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
//    	cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    	cameraXform.ry.setAngle(55);
    	cameraXform.rx.setAngle(-15);
    }
    private void buildAxes() {
    	PhongMaterial redMaterial = new PhongMaterial();
    	redMaterial.setDiffuseColor(Color.DARKRED);
    	redMaterial.setSpecularColor(Color.RED);
    	PhongMaterial greenMaterial = new PhongMaterial();
    	greenMaterial.setDiffuseColor(Color.DARKGREEN);
    	greenMaterial.setSpecularColor(Color.GREEN);
    	PhongMaterial blueMaterial = new PhongMaterial();
    	blueMaterial.setDiffuseColor(Color.DARKBLUE);
    	blueMaterial.setSpecularColor(Color.BLUE);
    	final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
    	final Box yAxis = new Box(1, AXIS_LENGTH, 1);
    	final Box zAxis = new Box(1, 1, AXIS_LENGTH);
    	xAxis.setMaterial(redMaterial);
    	yAxis.setMaterial(greenMaterial);
    	zAxis.setMaterial(blueMaterial);
    	axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
    	axisGroup.setVisible(true);
    	world.getChildren().addAll(axisGroup);
    	}
    private void handleMouse(Scene scene, final Node root) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                double modifier = 1.0;
                double modifierFactor = 0.1;

                if (me.isControlDown()) {
                    modifier = 0.1;
                }
                if (me.isShiftDown()) {
                    modifier = 10.0;
                }
                if (me.isPrimaryButtonDown()) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() + mouseDeltaX * modifierFactor * modifier * 2.0);  // +
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() - mouseDeltaY * modifierFactor * modifier * 2.0);  // -
                } else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX * modifierFactor * modifier;
                    camera.setTranslateZ(newZ);
                } else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * modifierFactor * modifier * 0.3);  // -
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * modifierFactor * modifier * 0.3);  // -
                }
            }
        });
    }
    /***
     * @SEE 键盘控制函数。包装好的键盘SWTICH。后续添加的功能在switch中修改就可以了
     * 也可以当作一个模板。后面在改进
     * @param scene
     * @param root
     */
    private void handleKeyboard(Scene scene, final Node root) {
        @SuppressWarnings("unused")
		final boolean moveCamera = true;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @SuppressWarnings("incomplete-switch")
			public void handle(KeyEvent event) {
                @SuppressWarnings("unused")
				Duration currentTime;
                switch (event.getCode()) {
                    case Z:
                        if (event.isShiftDown()) {
                            cameraXform.ry.setAngle(0.0);
                            cameraXform.rx.setAngle(0.0);
                            camera.setTranslateZ(-300.0);
                        }
                        cameraXform2.t.setX(0.0);
                        cameraXform2.t.setY(0.0);
                        break;
                    case X:
//                        if (event.isControlDown()) {
//                            if (axisGroup.isVisible()) {
//                                axisGroup.setVisible(false);
//                            } else {
//                                axisGroup.setVisible(true);
//                            }
//                        }
                      if (axisGroup.isVisible()) {
                    	  axisGroup.setVisible(false);
                      } else {
                    	  axisGroup.setVisible(true);
                      }
                        break;
                    case S:
                        if (event.isControlDown()) {
                            if (moleculeGroup.isVisible()) {
                                moleculeGroup.setVisible(false);
                            } else {
                                moleculeGroup.setVisible(true);
                            }
                        }
                        break;
                    case SPACE:

                        break;
                    case UP:
                        if (event.isControlDown() && event.isShiftDown()) {
                            cameraXform2.t.setY(cameraXform2.t.getY() - 10.0 * CONTROL_MULTIPLIER);
                        } else if (event.isAltDown() && event.isShiftDown()) {
                            cameraXform.rx.setAngle(cameraXform.rx.getAngle() - 10.0 * ALT_MULTIPLIER);
                        } else if (event.isControlDown()) {
                            cameraXform2.t.setY(cameraXform2.t.getY() - 1.0 * CONTROL_MULTIPLIER);
                        } else if (event.isAltDown()) {
                            cameraXform.rx.setAngle(cameraXform.rx.getAngle() - 2.0 * ALT_MULTIPLIER);
                        } else if (event.isShiftDown()) {
                            double z = camera.getTranslateZ();
                            double newZ = z + 5.0 * SHIFT_MULTIPLIER;
                            camera.setTranslateZ(newZ);
                        }
                        break;
                    case DOWN:
                        if (event.isControlDown() && event.isShiftDown()) {
                            cameraXform2.t.setY(cameraXform2.t.getY() + 10.0 * CONTROL_MULTIPLIER);
                        } else if (event.isAltDown() && event.isShiftDown()) {
                            cameraXform.rx.setAngle(cameraXform.rx.getAngle() + 10.0 * ALT_MULTIPLIER);
                        } else if (event.isControlDown()) {
                            cameraXform2.t.setY(cameraXform2.t.getY() + 1.0 * CONTROL_MULTIPLIER);
                        } else if (event.isAltDown()) {
                            cameraXform.rx.setAngle(cameraXform.rx.getAngle() + 2.0 * ALT_MULTIPLIER);
                        } else if (event.isShiftDown()) {
                            double z = camera.getTranslateZ();
                            double newZ = z - 5.0 * SHIFT_MULTIPLIER;
                            camera.setTranslateZ(newZ);
                        }
                        break;
                    case RIGHT:
                        if (event.isControlDown() && event.isShiftDown()) {
                            cameraXform2.t.setX(cameraXform2.t.getX() + 10.0 * CONTROL_MULTIPLIER);
                        } else if (event.isAltDown() && event.isShiftDown()) {
                            cameraXform.ry.setAngle(cameraXform.ry.getAngle() - 10.0 * ALT_MULTIPLIER);
                        } else if (event.isControlDown()) {
                            cameraXform2.t.setX(cameraXform2.t.getX() + 1.0 * CONTROL_MULTIPLIER);
                        } else if (event.isAltDown()) {
                            cameraXform.ry.setAngle(cameraXform.ry.getAngle() - 2.0 * ALT_MULTIPLIER);
                        }
                        break;
                    case LEFT:
                        if (event.isControlDown() && event.isShiftDown()) {
                            cameraXform2.t.setX(cameraXform2.t.getX() - 10.0 * CONTROL_MULTIPLIER);
                        } else if (event.isAltDown() && event.isShiftDown()) {
                            cameraXform.ry.setAngle(cameraXform.ry.getAngle() + 10.0 * ALT_MULTIPLIER);  // -
                        } else if (event.isControlDown()) {
                            cameraXform2.t.setX(cameraXform2.t.getX() - 1.0 * CONTROL_MULTIPLIER);
                        } else if (event.isAltDown()) {
                            cameraXform.ry.setAngle(cameraXform.ry.getAngle() + 2.0 * ALT_MULTIPLIER);  // -
                        }
                        break;
                }
            }
        });
    }

    public Xform buildBox(double width, double height, double depth,PhongMaterial Material){
    	Xform ObjectBox=new Xform(axisOrder);
//    	Material.setSpecularColor(Color.RED);
    	Box Object_One = new Box(width, height	, depth);
    	Object_One.setMaterial(Material);

    	ObjectBox.getChildren().add(Object_One);
    	ObjectBox.setVisible(true);
    	world.getChildren().addAll(ObjectBox);
    	return ObjectBox;
    }

    public Translate translate3D=new Translate(10, 10, 10);

	public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        Application.launch(args);
	}

	private PathTransition pathTransitionEllipse;
	private PathTransition pathTransitionCircle;
	public void start(Stage primaryStage) {
		Scene scene = new Scene(root, 1024, 768, true);
		scene.setFill(Color.GREY);
		handleKeyboard(scene, world);
		handleMouse(scene, world);
		//设置相机
		buildCamera();
		//设置坐标轴
		buildAxes();
		//建立初始目标对象
		Sphere earth=buildEarth();
		Sphere moon=buildMoon();
		Sphere sun=buildSolor();
//		MoveCircle(earth);

		CircleEarth(earth,moon);
//		CircleSun(sun,earth,moon);
		animateSphere1(moon);



//		TranslateTransition translateTransition =
//	            new TranslateTransition(Duration.millis(2000), sun);
//	        translateTransition.setFromX(50);
//	        translateTransition.setToX(375);
//	        translateTransition.setCycleCount(1);
//	        translateTransition.setAutoReverse(true);

//
//	        RotateTransition rotateTransition =
//	                new RotateTransition(Duration.millis(2000), sun);
//	            rotateTransition.setByAngle(180f);
//	            rotateTransition.setCycleCount(4);
//	            rotateTransition.setAutoReverse(true);
//
//
//	        SequentialTransition sequentialTransition = new SequentialTransition();
//	        sequentialTransition.getChildren().addAll(
//
////	                translateTransition,
//	                rotateTransition);
//	        sequentialTransition.setCycleCount(Timeline.INDEFINITE);
//	        sequentialTransition.setAutoReverse(true);
//	        sequentialTransition.play();
	        
//	        double centerX=0;
//	        double centerY=0;
//	        double radius=60;
//	        final Circle pointPath = new Circle (10);
//	        centerY=-centerY;
//	        Circle circlePath = new Circle(centerX,centerY,1);

//	        pointPath.setFill(Color.ORANGE);
////	        circlePath.setStroke(Color.BLUE);
//	        circlePath.setFill(Color.BLUE);
//
//	        Path path = new Path();
//	        path.getElements().add(new MoveTo(centerX,centerY-radius));
//	        path.getElements().add(new CubicCurveTo(centerX+(radius)/2, centerY-radius
//	        										, centerX+radius, centerY-(radius/2)
//	        										, centerX+radius, centerY));
//	        path.getElements().add(new CubicCurveTo(centerX+(radius), centerY+(radius/2)
//													, centerX+(radius/2), centerY+radius
//													, centerX, centerY+radius));
//	        path.getElements().add(new MoveTo(centerX, centerY+radius));
//
//	        path.getElements().add(new CubicCurveTo(centerX-(radius)/2, centerY+radius
//													, centerX-radius, centerY+(radius/2)
//													, centerX-radius, centerY));
//	        path.getElements().add(new CubicCurveTo(centerX-radius, centerY-(radius/2)
//													, centerX-(radius/2), centerY-(radius)
//													, centerX, centerY-radius));
//
//	        path.setFill(Color.WHITE);
//	        root.getChildren().add(path);
//	        root.getChildren().addAll(pointPath,circlePath);
//
//	        PathTransition pathTransition = new PathTransition();
//	        pathTransition.setDuration(Duration.millis(4000));
//	        pathTransition.setPath(path);
//	        pathTransition.setNode(earth);
//	        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//	        pathTransition.setCycleCount(Timeline.INDEFINITE);
//	        pathTransition.setAutoReverse(true);
//	        pathTransition.play();
	        
	        
		handleKey(scene,world);
		
		world.getChildren().addAll(objectGroup,earth_moon,sun_earth_moon	);
		root.getChildren().addAll(world);
		primaryStage.setScene(scene);
		primaryStage.show();
		scene.setCamera(camera);
    }
    public static Group earth_moon=new Group();
	public static Group sun_earth_moon=new Group();
    public void CircleEarth(Sphere earth,Sphere moon){
		earth_moon.getChildren().addAll(earth,moon);
	}
	public void CircleSun(Sphere sun,Sphere earth,Sphere moon){
		sun_earth_moon.getChildren().addAll(sun,earth_moon);
	}
    public void MoveCircle(Sphere node){
		Path path2 = createEllipsePath(0, 0, 50, 50, 0);
		root.getChildren().add(path2);

		pathTransitionCircle = PathTransitionBuilder.create()
				.duration(Duration.seconds(2))
				.path(path2)
				.node(node)
				.orientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT)
				.cycleCount(Timeline.INDEFINITE)
				.autoReverse(false)
				.build();
		pathTransitionCircle.play();
	}
	public void MoveCircle1(Sphere node){
		Shape arrow=createShape();
		Shape circle=createCircle(10);
		root.getChildren().addAll(node,circle);
		Animation animation = new ParallelTransition(
				createTransition(circle, arrow),
				createTimeline(10 / 2));
		animation.play();

	}
	//https://stackoverflow.com/questions/40721341/javafx-8-3d-animation
	private static void animateSphere(Sphere sphere) {
		Rotate rot = new Rotate();
		Translate radiusTranslate = new Translate(50, 0, 0);
		Translate zMovement = new Translate();

		sphere.getTransforms().setAll(zMovement, rot, radiusTranslate);
		Timeline tl = new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(zMovement.zProperty(), 0d),
						new KeyValue(rot.angleProperty(), 0d)),
				new KeyFrame(Duration.seconds(4),
						new KeyValue(zMovement.zProperty(), 900d, Interpolator.LINEAR),
						new KeyValue(rot.angleProperty(), 720, Interpolator.LINEAR))
		);
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.play();
	}
	private static void animateSphere1(Sphere sphere){
		CylinderCoordinateAdapter adapter = new CylinderCoordinateAdapter(
			earth_moon.translateXProperty(),
			earth_moon.translateYProperty(),
			earth_moon.translateZProperty());

		adapter.setRadius(100);
		sphere.setTranslateX(20);


		CylinderCoordinateAdapter mooon_ada=new CylinderCoordinateAdapter(
				sphere.translateXProperty(),
				sphere.translateYProperty(),
				sphere.translateZProperty());

		mooon_ada.setRadius(20);
		Timeline t2 = new Timeline(

				new KeyFrame(Duration.seconds(4),
						new KeyValue(mooon_ada.hProperty(), 0d, Interpolator.LINEAR),
						new KeyValue(mooon_ada.thetaProperty(), Math.PI *6, Interpolator.LINEAR))

		);



		Timeline tl = new Timeline(
//				new KeyFrame(Duration.seconds(10),
//						new KeyValue(adapter.hProperty(), 0d),
//						new KeyValue(adapter.thetaProperty(), 0d))
				new KeyFrame(Duration.seconds(4),
						new KeyValue(earth_moon.translateXProperty(), 0d, Interpolator.LINEAR),
						new KeyValue(earth_moon.translateYProperty(), Math.PI *6, Interpolator.LINEAR))
//				new KeyValue(adapter.axisProperty(),20, Interpolator.LINEAR))
//				Interpolator.SPLINE(0.5,0.5,0.5,0.5)
		);

//		System.out.println(tl.toString());
		t2.setCycleCount(Timeline.INDEFINITE);
		t2.play();
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.play();
	}
	private Shape createShape() {
		Shape s = new Polygon(0, 0, -10, -10, 10, 0, -10, 10);
		s.setStrokeWidth(3);
		s.setStrokeLineCap(StrokeLineCap.ROUND);
		s.setStroke(Color.RED);
		s.setEffect(new Bloom());
		return s;
	}
	private static final Duration DURATION = Duration.seconds(4);
	private Transition createTransition(Shape path, Shape node) {
		PathTransition t = new PathTransition(DURATION, path, node);
		t.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		t.setCycleCount(Timeline.INDEFINITE);
		t.setInterpolator(Interpolator.LINEAR);
		return t;
	}
	private Circle createCircle(double size) {
		Circle c = new Circle(size / 4);
		c.setFill(Color.TRANSPARENT);
		c.setStroke(Color.ORANGE);
		return c;
	}

	private Timeline createTimeline(double size) {
		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		t.setAutoReverse(true);
		KeyValue keyX = new KeyValue(root.translateXProperty(), size);
		KeyValue keyY = new KeyValue(root.translateYProperty(), size);
		KeyValue keyZ = new KeyValue(root.translateZProperty(), -size);
		KeyFrame keyFrame = new KeyFrame(DURATION.divide(2), keyX, keyY, keyZ);
		t.getKeyFrames().add(keyFrame);
		return t;
	}
	private Path createEllipsePath(double centerX, double centerY, double radiusX, double radiusY, double rotate) {
		ArcTo arcTo = new ArcTo();
		arcTo.setX(centerX - radiusX + 1); // to simulate a full 360 degree celcius circle.
		arcTo.setY(centerY - radiusY);
		arcTo.setSweepFlag(false);
		arcTo.setLargeArcFlag(true);
		arcTo.setRadiusX(radiusX);
		arcTo.setRadiusY(radiusY);
		arcTo.setXAxisRotation(rotate);

		Path path = PathBuilder.create()
				.elements(
						new MoveTo(centerX - radiusX, centerY - radiusY),
						arcTo,
						new ClosePath()) // close 1 px gap.
				.build();
		path.setStroke(Color.DODGERBLUE);
		path.getStrokeDashArray().setAll(5d, 5d);
		return path;
	}
	public void KeyEventPaint(){
		final PhongMaterial Material = new PhongMaterial(Color.BLACK);
		Xform OriginalObject =buildBox(10, 10, 10, Material);
		

	}
	public static int Keyflag=0;
	public void handleKey(Scene scene, final Node root) {
        @SuppressWarnings("unused")
		final boolean moveCamera = true;
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @SuppressWarnings("incomplete-switch")
			public void handle(KeyEvent event) {
                @SuppressWarnings("unused")
				Duration currentTime;
                
                switch (event.getCode()) {
                    case SPACE:
                    	if(Keyflag==0){
                    		drawPoint();
                    	}
                    	
                    	if(Keyflag==1){
                    		drawLine();
                    	}
                    	
                    	if(Keyflag==2){
                    		drawTran();
                    	}
                    	if(Keyflag==3){
                    		drawTranAnimation();
                    	}
                    	Keyflag++;
                    	

                        break;

                }
            }
        });
    }
	/***
	 * 画点，画线,长度默认10
	 */
	public void drawPoint(){
		Xform Object =new Xform();
		final PhongMaterial Material = new PhongMaterial(Color.ORANGE);
		Sphere boll=new Sphere(10);
		boll.setMaterial(Material);
		
		Object.getChildren().add(boll);
		Object.setTranslate(-20, -20, -20);
		Object.setVisible(true);
		objectGroup.getChildren().add(Object);
	}
	public void drawLine(){
		Xform Object =new Xform();
		final PhongMaterial Material = new PhongMaterial(Color.ORANGE);
		Cylinder line_=new Cylinder(1,1000);
		line_.setMaterial(Material);
		
		Object.getChildren().add(line_);
		Object.setTranslate(-10, 15, 15);
		Object.setVisible(true);
		Object.setRotate(60);
		objectGroup.getChildren().add(Object);
	}
	public void drawTran(){
		Xform Object =new Xform();
		final PhongMaterial Material = new PhongMaterial(Color.ORANGE);
		Cylinder line_1=new Cylinder(0.5,100);
		Cylinder line_2=new Cylinder(0.5,100);
		Cylinder line_3=new Cylinder(0.5,100);
		line_1.setRotate(30);
		line_2.setRotate(-30);
		line_3.setRotate(90);
		line_1.setTranslateY(-25);
		line_1.setTranslateX(-25);
		line_2.setTranslateY(-25);
		line_2.setTranslateX(25);
		line_3.setTranslateY(18);
		Object.getChildren().add(line_1);
		Object.getChildren().add(line_2);
		Object.getChildren().add(line_3);
		Object.setTranslate(-20, 20, -40);
		Object.setVisible(true);
		objectGroup.getChildren().add(Object);
	}
	public void drawTranAnimation() {
		Xform Object =new Xform();
		final PhongMaterial Material = new PhongMaterial(Color.ORANGE);
		Cylinder line_1=new Cylinder(0.5,100);
		Cylinder line_2=new Cylinder(0.5,100);
		Cylinder line_3=new Cylinder(0.5,100);
		line_1.setRotate(30);
		line_2.setRotate(-30);
		line_3.setRotate(90);
		line_1.setTranslateY(-25);
		line_1.setTranslateX(-25);
		line_2.setTranslateY(-25);
		line_2.setTranslateX(25);
		line_3.setTranslateY(18);
		Object.getChildren().add(line_1);
		Object.getChildren().add(line_2);
		Object.getChildren().add(line_3);
		Object.setTranslate(-20, 20, -40);
		Object.setVisible(true);
		objectGroup.getChildren().add(Object);
		
		
		 RotateTransition rotateTransition = 
		            new RotateTransition(Duration.millis(2000), Object);
		        rotateTransition.setByAngle(180f);
		        rotateTransition.setCycleCount(4);
		        rotateTransition.setAutoReverse(true);
		
		SequentialTransition sequentialTransition;
        sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(rotateTransition);
        sequentialTransition.setCycleCount(Timeline.INDEFINITE);
        sequentialTransition.setAutoReverse(true);
        sequentialTransition.play();   

	}
	public void LinearSymmetry(){
		Scanner in = new Scanner(System.in);
		System.out.println("输入目标坐标 ");
		System.out.println("输入x:");
		int x = 50;//in.nextInt();
		System.out.println("输入y:");
		int y = 10;//in.nextInt();
		x=-x;
		y=-y;
		final PhongMaterial Material = new PhongMaterial(Color.BLUEVIOLET);
		Xform OriginalObject =buildBox(10, 10, 10, Material);
		OriginalObject.setTranslate(x,y,0.0);

    	objectGroup.getChildren().add(OriginalObject);
    	objectGroup.setVisible(true);
    	
    	int y0=x+5;
    	int x0=y-5;
    	Xform ObjectTwo =buildBox(10, 10, 10, Material);
    	ObjectTwo.setTranslate(x0,y0,0.0);
    	objectGroup.getChildren().add(ObjectTwo);
    	objectGroup.setVisible(true);
    	in.close();

	}


//	private  Group solorGroup = new Group();
//	private static final Duration DURATION = Duration.seconds(4);
//	private static final Color COLOR = Color.AQUA;
//	private static final double WIDTH = 3;
//	private final Group group = new Group();
//	private final Rotate rx = new Rotate(0, Rotate.X_AXIS);
//	private final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
//	private final Rotate rz = new Rotate(0, Rotate.Z_AXIS);
//	private final Box xAxis;
//	private final Box yAxis;
//	private final Box zAxis;
//	private final Shape circle;
//	private final Shape arrow;
//	private final Animation animation;
//	private solor(double size) {
//		xAxis = createBox(size, WIDTH, WIDTH);
//		yAxis = createBox(WIDTH, size, WIDTH);
//		zAxis = createBox(WIDTH, WIDTH, size);
//		circle = createCircle(size);
//		arrow = createShape();
//		animation = new ParallelTransition(
//				createTransition(circle, arrow),
//				createTimeline(size / 2));
//	}

//	private static  solor create(double size){
//		solor solorSystem=new solor(size);
//
//		solorSystem.solorGroup.getChildren().addAll(solorSystem.buildEarth()
//				,solorSystem.buildMoon(),
//				solorSystem.buildSolor());
//		solorSystem.solorGroup.getTransforms().addAll(solorSystem.rz,
//				solorSystem.ry,
//				solorSystem.rx);
//		return solorSystem;
//	}
//	private Circle createCircle(double size) {
//		Circle c = new Circle(size / 4);
//		c.setFill(Color.TRANSPARENT);
//		c.setStroke(COLOR);
//		return c;
//	}
//
//	private Box createBox(double w, double h, double d) {
//		Box b = new Box(w, h, d);
//		b.setMaterial(new PhongMaterial(COLOR));
//		return b;
//	}
//
//	private Shape createShape() {
//		Shape s = new Polygon(0, 0, -10, -10, 10, 0, -10, 10);
//		s.setStrokeWidth(WIDTH);
//		s.setStrokeLineCap(StrokeLineCap.ROUND);
//		s.setStroke(COLOR);
//		s.setEffect(new Bloom());
//		return s;
//	}
//
//	private Transition createTransition(Shape path, Shape node) {
//		PathTransition t = new PathTransition(DURATION, path, node);
//		t.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//		t.setCycleCount(Timeline.INDEFINITE);
//		t.setInterpolator(Interpolator.LINEAR);
//		return t;
//	}
//
//	private Timeline createTimeline(double size) {
//		Timeline t = new Timeline();
//		t.setCycleCount(Timeline.INDEFINITE);
//		t.setAutoReverse(true);
//		KeyValue keyX = new KeyValue(group.translateXProperty(), size);
//		KeyValue keyY = new KeyValue(group.translateYProperty(), size);
//		KeyValue keyZ = new KeyValue(group.translateZProperty(), -size);
//		KeyFrame keyFrame = new KeyFrame(DURATION.divide(2), keyX, keyY, keyZ);
//		t.getKeyFrames().add(keyFrame);
//		return t;
//	}

	private void buildScene() {
		System.out.println("buildScene");
		root.getChildren().add(world);
	}
	public Sphere buildEarth(){
		final PhongMaterial Material = new PhongMaterial(Color.BLUE);
		Sphere earth=new Sphere(10);
		earth.setMaterial(Material);
		Xform earth_form=new Xform();
		earth_form.getChildren().add(earth);
		earth_form.setTranslate(0, 0, 0 );
		objectGroup.getChildren().add(earth_form);
		objectGroup.setVisible(true);
		return earth;
	}
	public Sphere buildMoon(){
		final PhongMaterial Material = new PhongMaterial(Color.WHEAT);

		Sphere moon=new Sphere(5);
		moon.setMaterial(Material);
		Xform moon_form=new Xform();
		moon_form.setTranslate(0, 0, 0);
		moon_form.getChildren().add(moon);
		objectGroup.getChildren().add(moon_form);
		objectGroup.setVisible(true);
		return moon;
	}
	public Sphere buildSolor(){
		Sphere sun=new Sphere(5);
		final PhongMaterial Material = new PhongMaterial(Color.RED);

		sun.setMaterial(Material);
		Xform sun_form=new Xform();
		
		sun_form.getChildren().add(sun);
		
		objectGroup.getChildren().add(sun_form);
		objectGroup.setVisible(true);
		return sun;
	}

}

