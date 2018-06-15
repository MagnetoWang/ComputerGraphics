package Curve;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Chart3dSampleApp extends Application {

    final Group root = new Group();
    final Group axisGroup = new Group();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    final double cameraDistance = 1450;
    final Xform moleculeGroup = new Xform();
    private Timeline timeline;
    boolean timelinePlaying = false;
    double ONE_FRAME = 1.0 / 24.0;
    double DELTA_MULTIPLIER = 200.0;
    double CONTROL_MULTIPLIER = 10.1;
    double SHIFT_MULTIPLIER = 0.1;
    double ALT_MULTIPLIER = 0.5;
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

    private void buildScene() {
        root.getChildren().add(world);
    }

    private void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(0);

        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-cameraDistance);
        cameraXform.ry.setAngle(0);
        cameraXform.rx.setAngle(0);
    }

    private void buildAxes() {
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(300, 1, 300);
        final Box yAxis = new Box(1, 300, 300);
        final Box zAxis = new Box(300, 300, 1);

        yAxis.setTranslateY(-150);
        yAxis.setTranslateX(150);
        zAxis.setTranslateY(-150);
        zAxis.setTranslateZ(150);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        world.getChildren().addAll(axisGroup);
    }

    private void buildChart() {

        final PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.WHITE);
        whiteMaterial.setSpecularColor(Color.LIGHTBLUE);

        float h = 200;                    // Height
        float s = 200;                    // Side

        TriangleMesh pyramidMesh = new TriangleMesh();

        pyramidMesh.getTexCoords().addAll(0,0);
        pyramidMesh.getPoints().addAll(
                0,    0,    0,            // Point 0 - Top
                0,    h,    -s/2,         // Point 1 - Front
                -s/2, h,    0,            // Point 2 - Left
                s/2,  h,    0,            // Point 3 - Back
                0,    h,    s/2           // Point 4 - Right
        );

        pyramidMesh.getFaces().addAll(
                0,0,  2,0,  1,0,          // Front left face
                0,0,  1,0,  3,0,          // Front right face
                0,0,  3,0,  4,0,          // Back right face
                0,0,  4,0,  2,0,          // Back left face
                4,0,  1,0,  2,0,          // Bottom rear face
                4,0,  3,0,  1,0           // Bottom front face
        );


        MeshView pyramid = new MeshView(pyramidMesh);
        pyramid.setDrawMode(DrawMode.FILL);
        pyramid.setMaterial(whiteMaterial);
        pyramid.setTranslateY(-h);

        world.getChildren().addAll(pyramid);
    }


    private void handleMouse(Scene scene, final Node root) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
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
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * modifierFactor * modifier * 2.0);  // +
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * modifierFactor * modifier * 2.0);  // -
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
        final boolean moveCamera = true;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
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

    @Override
    public void start(Stage primaryStage) {
        buildScene();
        buildCamera();
        buildAxes();
        buildChart();

        Scene scene = new Scene(root, 1600, 900, true);
        scene.setFill(Color.GREY);
        handleKeyboard(scene, world);
        handleMouse(scene, world);

        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setCamera(camera);

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        launch(args);
    }

    public static class Xform extends Group {

        public enum RotateOrder {
            XYZ, XZY, YXZ, YZX, ZXY, ZYX
        }

        public Translate t  = new Translate();
        public Translate p  = new Translate();
        public Translate ip = new Translate();
        public Rotate rx = new Rotate();
        { rx.setAxis(Rotate.X_AXIS); }
        public Rotate ry = new Rotate();
        { ry.setAxis(Rotate.Y_AXIS); }
        public Rotate rz = new Rotate();
        { rz.setAxis(Rotate.Z_AXIS); }
        public Scale s = new Scale();

        public Xform() {
            super();
            getTransforms().addAll(t, rz, ry, rx, s);
        }

        public Xform(RotateOrder rotateOrder) {
            super();
            // choose the order of rotations based on the rotateOrder
            switch (rotateOrder) {
                case XYZ:
                    getTransforms().addAll(t, p, rz, ry, rx, s, ip);
                    break;
                case XZY:
                    getTransforms().addAll(t, p, ry, rz, rx, s, ip);
                    break;
                case YXZ:
                    getTransforms().addAll(t, p, rz, rx, ry, s, ip);
                    break;
                case YZX:
                    getTransforms().addAll(t, p, rx, rz, ry, s, ip);  // For Camera
                    break;
                case ZXY:
                    getTransforms().addAll(t, p, ry, rx, rz, s, ip);
                    break;
                case ZYX:
                    getTransforms().addAll(t, p, rx, ry, rz, s, ip);
                    break;
            }
        }

        public void setTranslate(double x, double y, double z) {
            t.setX(x);
            t.setY(y);
            t.setZ(z);
        }

        public void setTranslate(double x, double y) {
            t.setX(x);
            t.setY(y);
        }

        // Cannot override these methods as they are final:
        // public void setTranslateX(double x) { t.setX(x); }
        // public void setTranslateY(double y) { t.setY(y); }
        // public void setTranslateZ(double z) { t.setZ(z); }
        // Use these methods instead:
        public void setTx(double x) { t.setX(x); }
        public void setTy(double y) { t.setY(y); }
        public void setTz(double z) { t.setZ(z); }

        public void setRotate(double x, double y, double z) {
            rx.setAngle(x);
            ry.setAngle(y);
            rz.setAngle(z);
        }

        public void setRotateX(double x) { rx.setAngle(x); }
        public void setRotateY(double y) { ry.setAngle(y); }
        public void setRotateZ(double z) { rz.setAngle(z); }
        public void setRx(double x) { rx.setAngle(x); }
        public void setRy(double y) { ry.setAngle(y); }
        public void setRz(double z) { rz.setAngle(z); }

        public void setScale(double scaleFactor) {
            s.setX(scaleFactor);
            s.setY(scaleFactor);
            s.setZ(scaleFactor);
        }

        public void setScale(double x, double y, double z) {
            s.setX(x);
            s.setY(y);
            s.setZ(z);
        }

        // Cannot override these methods as they are final:
        // public void setScaleX(double x) { s.setX(x); }
        // public void setScaleY(double y) { s.setY(y); }
        // public void setScaleZ(double z) { s.setZ(z); }
        // Use these methods instead:
        public void setSx(double x) { s.setX(x); }
        public void setSy(double y) { s.setY(y); }
        public void setSz(double z) { s.setZ(z); }

        public void setPivot(double x, double y, double z) {
            p.setX(x);
            p.setY(y);
            p.setZ(z);
            ip.setX(-x);
            ip.setY(-y);
            ip.setZ(-z);
        }

        public void reset() {
            t.setX(0.0);
            t.setY(0.0);
            t.setZ(0.0);
            rx.setAngle(0.0);
            ry.setAngle(0.0);
            rz.setAngle(0.0);
            s.setX(1.0);
            s.setY(1.0);
            s.setZ(1.0);
            p.setX(0.0);
            p.setY(0.0);
            p.setZ(0.0);
            ip.setX(0.0);
            ip.setY(0.0);
            ip.setZ(0.0);
        }

        public void resetTSP() {
            t.setX(0.0);
            t.setY(0.0);
            t.setZ(0.0);
            s.setX(1.0);
            s.setY(1.0);
            s.setZ(1.0);
            p.setX(0.0);
            p.setY(0.0);
            p.setZ(0.0);
            ip.setX(0.0);
            ip.setY(0.0);
            ip.setZ(0.0);
        }
    }

}