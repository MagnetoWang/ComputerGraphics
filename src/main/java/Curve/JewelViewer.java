package Curve;
/** 
*
* @ClassName : JewelViewer.java
* @author : Magneto_Wang
* @date  2018年6月17日 上午1:04:33
* @Description  TODO
* 
*/




import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.File;

import com.interactivemesh.jfx.importer.stl.StlMeshImporter;

public class JewelViewer extends Application {

  private static final String MESH_FILENAME =
    "/Users/lilyshard/Downloads/Perfect Diamond/Perfect Diamond.STL";

  private static final double MODEL_SCALE_FACTOR = 400;
  private static final double MODEL_X_OFFSET = 0; // standard
  private static final double MODEL_Y_OFFSET = 0; // standard

  private static final int VIEWPORT_SIZE = 800;

  private static final Color lightColor = Color.rgb(244, 255, 250);
  private static final Color jewelColor = Color.rgb(0, 190, 222);

  private Group root;
  private PointLight pointLight;

  static MeshView[] loadMeshViews() {
    File file = new File(MESH_FILENAME);
    StlMeshImporter importer = new StlMeshImporter();
    importer.read(file);
    Mesh mesh = importer.getImport();

    return new MeshView[] { new MeshView(mesh) };
  }

  private Group buildScene() {
    MeshView[] meshViews = loadMeshViews();
    for (int i = 0; i < meshViews.length; i++) {
      meshViews[i].setTranslateX(VIEWPORT_SIZE / 2 + MODEL_X_OFFSET);
      meshViews[i].setTranslateY(VIEWPORT_SIZE / 2 + MODEL_Y_OFFSET);
      meshViews[i].setTranslateZ(VIEWPORT_SIZE / 2);
      meshViews[i].setScaleX(MODEL_SCALE_FACTOR);
      meshViews[i].setScaleY(MODEL_SCALE_FACTOR);
      meshViews[i].setScaleZ(MODEL_SCALE_FACTOR);

      PhongMaterial sample = new PhongMaterial(jewelColor);
      sample.setSpecularColor(lightColor);
      sample.setSpecularPower(16);
      meshViews[i].setMaterial(sample);

      meshViews[i].getTransforms().setAll(new Rotate(38, Rotate.Z_AXIS), new Rotate(20, Rotate.X_AXIS));
    }

    pointLight = new PointLight(lightColor);
    pointLight.setTranslateX(VIEWPORT_SIZE*3/4);
    pointLight.setTranslateY(VIEWPORT_SIZE/2);
    pointLight.setTranslateZ(VIEWPORT_SIZE/2);
    PointLight pointLight2 = new PointLight(lightColor);
    pointLight2.setTranslateX(VIEWPORT_SIZE*1/4);
    pointLight2.setTranslateY(VIEWPORT_SIZE*3/4);
    pointLight2.setTranslateZ(VIEWPORT_SIZE*3/4);
    PointLight pointLight3 = new PointLight(lightColor);
    pointLight3.setTranslateX(VIEWPORT_SIZE*5/8);
    pointLight3.setTranslateY(VIEWPORT_SIZE/2);
    pointLight3.setTranslateZ(0);

    Color ambientColor = Color.rgb(80, 80, 80, 0);
    AmbientLight ambient = new AmbientLight(ambientColor);

    root = new Group(meshViews);
    root.getChildren().add(pointLight);
    root.getChildren().add(pointLight2);
    root.getChildren().add(pointLight3);
    root.getChildren().add(ambient);

    return root;
  }

  private PerspectiveCamera addCamera(Scene scene) {
    PerspectiveCamera perspectiveCamera = new PerspectiveCamera();
    System.out.println("Near Clip: " + perspectiveCamera.getNearClip());
    System.out.println("Far Clip:  " + perspectiveCamera.getFarClip());
    System.out.println("FOV:       " + perspectiveCamera.getFieldOfView());

    scene.setCamera(perspectiveCamera);
    return perspectiveCamera;
  }

  @Override
  public void start(Stage primaryStage) {
    Group group = buildScene();
    group.setScaleX(2);
    group.setScaleY(2);
    group.setScaleZ(2);
    group.setTranslateX(50);
    group.setTranslateY(50);

    Scene scene = new Scene(group, VIEWPORT_SIZE, VIEWPORT_SIZE, true);
    scene.setFill(Color.rgb(10, 10, 40));
    addCamera(scene);
    primaryStage.setTitle("Jewel Viewer");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    System.setProperty("prism.dirtyopts", "false");
    launch(args);
  }
}
