package com.maven.JavaGraphics.ComputerGraphics;



import com.maven.JavaGraphics.ComputerGraphics.Xform.RotateOrder;

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
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ThreeD extends Application{

	/** 
	*
	* @ClassName : ThreeD.java
	* @author : Magneto_Wang
	* @date  2018年5月15日 下午1:33:04
	* @Description  TODO
	* 
	*/
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
                        if (event.isControlDown()) {
                            if (axisGroup.isVisible()) {
                                axisGroup.setVisible(false);
                            } else {
                                axisGroup.setVisible(true);
                            }
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
                        if (timelinePlaying) {
                            timeline.pause();
                            timelinePlaying = false;
                        } else {
                            timeline.play();
                            timelinePlaying = true;
                        }
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
        	world.getChildren().addAll(objectGroup);
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
		
		
		
		
		buildCamera();
		buildAxes();
		buildObject(); 
		Xform OriginalObject =buildBox(10, 10	, 10, redMaterial); //=objectGroup.clone();
//		world.getChildren().add(OriginalObject);
   		objectGroup.setTranslate(10.0,20.0,10.0);
		objectGroup.setScale(2.0,1.0,0.5);
		
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
        
        
        
		
		
		root.getChildren().addAll(world);
		primaryStage.setScene(scene);
		primaryStage.show();
		scene.setCamera(camera);
    }

	
	//
	// The handleCameraViews file contains the handleMouse() and handleKeyboard() 
	// methods that are used in the MoleculeSampleApp application to handle the 
	// different 3D camera views.  These methods are used in the Getting Started with 
	// JavaFX 3D Graphics tutorial. 
	//

	
}
