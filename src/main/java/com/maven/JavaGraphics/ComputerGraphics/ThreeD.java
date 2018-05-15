package com.maven.JavaGraphics.ComputerGraphics;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class ThreeD extends Application{

	/** 
	*
	* @ClassName : ThreeD.java
	* @author : Magneto_Wang
	* @date  2018年5月15日 下午1:33:04
	* @Description  TODO
	* 
	*/
    final Group root = new Group();
    final Xform world = new Xform();

    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    final double cameraDistance = 450;
 
    private void buildScene() {
        System.out.println("buildScene");
        root.getChildren().add(world);
    }
    private void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);
 
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-cameraDistance);
        cameraXform.ry.setAngle(320.0);
        cameraXform.rx.setAngle(40);
    }
	public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        Application.launch(args);
	}
	public void start(Stage primaryStage) {
        System.out.println("start");
        buildScene();
        buildCamera();
        Scene scene = new Scene(root, 1024, 768, true);
        scene.setFill(Color.GREY);
 
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setCamera(camera);
 
    }
	//
	// This buildMolecule file contains the buildMolecule() method that is used in 
	// the MoleculeSampleApp application that you can build using the Getting Started with 
	// JavaFX 3D Graphics tutorial.
	//
	private void buildMolecule() {
	      
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.WHITE);
        whiteMaterial.setSpecularColor(Color.LIGHTBLUE);
        
        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.DARKGREY);
        greyMaterial.setSpecularColor(Color.GREY);
        
        // Molecule Hierarchy
        // [*] moleculeXform
        //     [*] oxygenXform
        //         [*] oxygenSphere
        //     [*] hydrogen1SideXform
        //         [*] hydrogen1Xform
        //             [*] hydrogen1Sphere
        //         [*] bond1Cylinder
        //     [*] hydrogen2SideXform
        //         [*] hydrogen2Xform
        //             [*] hydrogen2Sphere
        //         [*] bond2Cylinder
        
        Xform moleculeXform = new Xform();
        Xform oxygenXform = new Xform();
        Xform hydrogen1SideXform = new Xform();
        Xform hydrogen1Xform = new Xform();
        Xform hydrogen2SideXform = new Xform();
        Xform hydrogen2Xform = new Xform();
        
        Sphere oxygenSphere = new Sphere(40.0);
        oxygenSphere.setMaterial(redMaterial);
        
        Sphere hydrogen1Sphere = new Sphere(30.0);
        hydrogen1Sphere.setMaterial(whiteMaterial);
        hydrogen1Sphere.setTranslateX(0.0);
        
        Sphere hydrogen2Sphere = new Sphere(30.0);
        hydrogen2Sphere.setMaterial(whiteMaterial);
        hydrogen2Sphere.setTranslateZ(0.0);
        
        Cylinder bond1Cylinder = new Cylinder(5, 100);
        bond1Cylinder.setMaterial(greyMaterial);
        bond1Cylinder.setTranslateX(50.0);
        bond1Cylinder.setRotationAxis(Rotate.Z_AXIS);
        bond1Cylinder.setRotate(90.0);
        
        Cylinder bond2Cylinder = new Cylinder(5, 100);
        bond2Cylinder.setMaterial(greyMaterial);
        bond2Cylinder.setTranslateX(50.0);
        bond2Cylinder.setRotationAxis(Rotate.Z_AXIS);
        bond2Cylinder.setRotate(90.0);
        
        moleculeXform.getChildren().add(oxygenXform);
        moleculeXform.getChildren().add(hydrogen1SideXform);
        moleculeXform.getChildren().add(hydrogen2SideXform);        
        oxygenXform.getChildren().add(oxygenSphere);
        hydrogen1SideXform.getChildren().add(hydrogen1Xform);
        hydrogen2SideXform.getChildren().add(hydrogen2Xform);
        hydrogen1Xform.getChildren().add(hydrogen1Sphere);
        hydrogen2Xform.getChildren().add(hydrogen2Sphere);
        hydrogen1SideXform.getChildren().add(bond1Cylinder);
        hydrogen2SideXform.getChildren().add(bond2Cylinder); 
        
        hydrogen1Xform.setTx(100.0);
        hydrogen2Xform.setTx(100.0);
        hydrogen2SideXform.setRotateY(104.5);
        

    }
	//
	// The handleCameraViews file contains the handleMouse() and handleKeyboard() 
	// methods that are used in the MoleculeSampleApp application to handle the 
	// different 3D camera views.  These methods are used in the Getting Started with 
	// JavaFX 3D Graphics tutorial. 
	//

	
}
