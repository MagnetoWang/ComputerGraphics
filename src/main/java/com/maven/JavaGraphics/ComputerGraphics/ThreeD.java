package com.maven.JavaGraphics.ComputerGraphics;



import java.util.Scanner;

import com.maven.JavaGraphics.ComputerGraphics.Xform.RotateOrder;
import com.sun.prism.shader.DrawCircle_LinearGradient_PAD_AlphaTest_Loader;

import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.VLineTo;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
/** 
*
* @ClassName : ThreeD.java
* @author : Magneto_Wang
* @date  2018年5月15日 下午1:33:04
* @Description  TODO
* 
*/
public class ThreeD extends Application{


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
	
	
    private void buildScene() {
        System.out.println("buildScene");
        root.getChildren().add(world);
    }
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
    public void buildObject(){
    	
//        	final PhongMaterial redMaterial = new PhongMaterial();
//        	redMaterial.setDiffuseColor(Color.DARKRED);
        	redMaterial.setSpecularColor(Color.RED);
        	Box Object_One = new Box(10, 10	, 10);
        	Object_One.setMaterial(redMaterial);
        	objectGroup.getChildren().add(Object_One);
//        	objectGroup.setScale(2);
//        	objectGroup.setTranslate(10, 10);
//        	objectGroup.setRotate(60);
        	
        	objectGroup.setVisible(true);
//        	world.getChildren().addAll(objectGroup);
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
    public void ObjectCoordinate(Xform Object ,double x,double y,double z){
    	Object.setTranslate(x, y, z);
    }
    public Translate translate3D=new Translate(10, 10, 10);

	public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        Application.launch(args);
	}
	
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
		buildObject(); 
		solveFirstQuestion();


        
        
		LinearSymmetry();
		
		handleKey(scene,world);
		
		world.getChildren().addAll(objectGroup);
		root.getChildren().addAll(world);
		primaryStage.setScene(scene);
		primaryStage.show();
		scene.setCamera(camera);
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
//    	Box Object_One = new Box(10, 10	, 10);
//    	objectGroup.setTranslate(x,y,0.0);
//    	Object_One.setMaterial(Material);
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
	public void solveFirstQuestion(){

		Xform OriginalObject =buildBox(10, 10	, 10, redMaterial); //=objectGroup.clone();
//		world.getChildren().add(OriginalObject);
		Xform ObjectTwo = buildBox(10, 10, 10, redMaterial);
		ObjectTwo.setTranslate(10.0,20.0,10.0);
		ObjectTwo.setScale(2.0,1.0,0.5);
		objectGroup.getChildren().add(OriginalObject);
    	objectGroup.getChildren().add(ObjectTwo);
    	objectGroup.setVisible(true);
//		Group cameraGroup = new Group();
//		cameraGroup.getChildren().add(camera);
//		root.getChildren().add(cameraGroup);
		
		
//		Box myBox = new Box(10, 10, 10);
//		Cylinder myCylinder = new Cylinder(10	, 10);
//
//        final PhongMaterial redMaterial = new PhongMaterial();
//        redMaterial.setDiffuseColor(Color.DARKRED);
//        redMaterial.setSpecularColor(Color.RED);
//        
//        
//        myBox.setMaterial(redMaterial);
//        myCylinder.setMaterial(redMaterial);
//        
//        final Xform world = new Xform();
//        world.getChildren().add(myBox);
//        world.getChildren().add(myCylinder);
	}

	
	//
	// The handleCameraViews file contains the handleMouse() and handleKeyboard() 
	// methods that are used in the MoleculeSampleApp application to handle the 
	// different 3D camera views.  These methods are used in the Getting Started with 
	// JavaFX 3D Graphics tutorial. 
	//

	
}
