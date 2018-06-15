package Curve;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * simple 3D viewer and mesh
 * Created by huson on 12/1/15.
 */
public class MeshViewerDemo  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // setup camera:
        final PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);

        final Rotate cameraRotateX = new Rotate(0, new Point3D(1, 0, 0));
        final Rotate cameraRotateY = new Rotate(0, new Point3D(0, 1, 0));
        final Translate cameraTranslate = new Translate(0, 0, -1000);
        camera.getTransforms().addAll(cameraRotateX, cameraRotateY, cameraTranslate);

        // setup world and subscene
        final Group world = new Group();
        final SubScene subScene = new SubScene(world, 1000, 1000, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(camera);

        // setup top pane and stacked pane
        final Pane topPane = new Pane();
        topPane.setPickOnBounds(false);

        final StackPane stackPane = new StackPane(subScene, topPane);
        StackPane.setAlignment(topPane, Pos.CENTER);
        StackPane.setAlignment(subScene, Pos.CENTER);

        // setup scene and stage:
        final Scene scene = new Scene(stackPane, 1000, 1000);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

        // add mouse handling
        addMouseHanderToScene(scene, cameraRotateX, cameraRotateY, cameraTranslate);

        // put some objects into the world:

        // triangle:
        if (false) {
            MeshView meshView = makeTriangle(true);
            meshView.setTranslateX(-50);
            meshView.setTranslateY(-5);
            meshView.setScaleX(10);
            meshView.setScaleY(10);
            //meshView.setDrawMode(DrawMode.LINE);
            world.getChildren().add(meshView);
        }


        // pentagon:
        if(true) {
            ArrayList<Point3D> points = new ArrayList<>();
            points.add(new Point3D(21.393, 16.960, 18.505));
            points.add(new Point3D(21.264, 16.229, 17.176));
            points.add(new Point3D(20.793, 17.368, 16.288));
            points.add(new Point3D(19.716, 17.901, 17.218));
            points.add(new Point3D(20.353, 17.952, 18.496));
            points = center(points);

            Node pentagon = makePentagon(points);
            world.getChildren().add(pentagon);
        }
    }

    /**
     * make a triangle
     *
     * @return triangle
     */
    private MeshView makeTriangle(boolean both) {
        float[] points = {50, 0, 0, 45, 10, 0, 55, 10, 0};
        float[] texCoords = {0.5f, 0, 0, 1, 1, 1};
        int[] faces;
        if (both)
            faces = new int[]{0, 0, 1, 1, 2, 2, 0, 0, 2, 2, 1, 1};
        else
            faces = new int[]{0, 0, 1, 1, 2, 2};

        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().addAll(points);
        mesh.getTexCoords().addAll(texCoords);
        mesh.getFaces().addAll(faces);
        return new MeshView(mesh);
    }

    /**
     * make a triangle
     *
     * @return triangle
     */
    private Group makePentagon(ArrayList<Point3D> input) {
        final Group group = new Group();
        if (input.size() != 5)
            throw new RuntimeException("Need 5 points for pentagon");

        float[] points = new float[3 * input.size()];
        for (int i = 0; i < input.size(); i++) {
            points[3 * i] = (float) (input.get(i).getX()) * 100;
            points[3 * i + 1] = (float) (input.get(i).getY()) * 100;
            points[3 * i + 2] = (float) (input.get(i).getZ()) * 100;
            Text text = new Text("V" + i);
            text.setTranslateX(points[3 * i]);
            text.setTranslateY(points[3 * i + 1]);
            text.setTranslateZ(points[3 * i + 2]);
            group.getChildren().add(text);
        }

        float[] texCoords = {0, 0, 0, 1, 1, 1};

        int[] faces = new int[]{
                0, 0, 1, 1, 2, 2,
                0, 0, 2, 1, 3, 2,
                0, 0, 3, 1, 4, 2};

        int[] smoothing = {1, 1, 1};

        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().addAll(points);
        mesh.getTexCoords().addAll(texCoords);
        mesh.getFaces().addAll(faces);
        mesh.getFaceSmoothingGroups().addAll(smoothing);
        MeshView meshView = new MeshView(mesh);
        //meshView.setDrawMode(DrawMode.LINE);

        group.getChildren().add(meshView);
        return group;
    }

    /**
     * center around original
     *
     * @param points
     */
    public static ArrayList<Point3D>  center(ArrayList<Point3D> points) {
        ArrayList<Point3D> result=new ArrayList<>(points.size());
        if (points.size() > 0) {
            double[] center = {0, 0, 0};

            for (Point3D point : points) {
                center[0] += point.getX();
                center[1] += point.getY();
                center[2] += point.getZ();
            }
            center[0] /= points.size();
            center[1] /= points.size();
            center[2] /= points.size();

            for (Point3D point : points) {
                result.add(point.subtract(new Point3D(center[0], center[1], center[2])));
            }
        }
        return result;
    }

    private double mouseDownX;
    private double mouseDownY;

    /**
     * handle mouse events
     *
     * @param scene
     * @param cameraTranslate
     */
    private void addMouseHanderToScene(Scene scene, Rotate cameraRotateX, Rotate cameraRotateY, Translate cameraTranslate) {
        scene.setOnMousePressed((me) -> {
            mouseDownX = me.getSceneX();
            mouseDownY = me.getSceneY();
        });
        scene.setOnMouseDragged((me) -> {
            double mouseDeltaX = me.getSceneX() - mouseDownX;
            double mouseDeltaY = me.getSceneY() - mouseDownY;

            if (me.isShiftDown()) {
                cameraTranslate.setZ(cameraTranslate.getZ() + mouseDeltaY);
            } else // rotate
            {
                cameraRotateY.setAngle(cameraRotateY.getAngle() + mouseDeltaX);
                cameraRotateX.setAngle(cameraRotateX.getAngle() - mouseDeltaY);
            }
            mouseDownX = me.getSceneX();
            mouseDownY = me.getSceneY();
        });
    }
}

