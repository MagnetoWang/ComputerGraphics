//package animation;
//import javafx.animation.Animation;
//import javafx.animation.Interpolator;
//import javafx.animation.KeyFrame;
//import javafx.animation.KeyValue;
//import javafx.animation.ParallelTransition;
//import javafx.animation.PathTransition;
//import javafx.animation.PathTransition.OrientationType;
//import javafx.animation.Timeline;
//import javafx.animation.Transition;
//import javafx.application.Application;
//
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.Node;
//import javafx.scene.PerspectiveCamera;
//import javafx.scene.Scene;
//import javafx.scene.effect.Bloom;
//import javafx.scene.input.MouseEvent;
//
//import javafx.scene.paint.Color;
//import javafx.scene.paint.PhongMaterial;
//import javafx.scene.shape.*;
//import javafx.scene.transform.Rotate;
//import javafx.scene.transform.Translate;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//public class CubeDemo extends Application {
//
//    private static final double WIDTH = 700, HEIGHT = 500;
//    private PathTransition pathBackFaceTransition;
//    private PathTransition pathFrontFaceTransition;
//    private Timeline animation;
//
//    private void init(Stage primaryStage) {
//
//        Group root = new Group();
//        primaryStage.setTitle("JavaFX 3D");
//        primaryStage.setResizable(false);
//        Scene scene = new Scene(root, WIDTH, HEIGHT, true);
//        scene.setFill(Color.BLACK);
//        primaryStage.setScene(scene);
//        PerspectiveCamera camera = new PerspectiveCamera();
//        Translate translate = new Translate(WIDTH / 2, HEIGHT / 2);
//        Rotate rotate = new Rotate(180, Rotate.Y_AXIS);
//        primaryStage.getScene().setCamera(camera);
//        root.getTransforms().addAll(translate, rotate);
//
//        Node node = create3dContent();
//
//        root.getChildren().add(node);
//    }
//
//    public void play(){
//        pathBackFaceTransition.play();
//        pathFrontFaceTransition.play();
//    }
//
//    @Override
//    public void stop(){
//        pathBackFaceTransition.stop();
//        pathFrontFaceTransition.stop();
//    }
//
//    public Node create3dContent() {
//
//        final Face cube = new Face(250);
//
//        cube.rx.setAngle(0);
//        cube.ry.setAngle(0);
//        cube.rz.setAngle(0);
//
//        cube.setOnMouseMoved(new EventHandler(){
//            @Override
//            public void handle(final MouseEvent arg0) {
//
//                animation = new Timeline();
//
//                animation.getKeyFrames().addAll(
//                        new KeyFrame(new Duration(2000),
//                                new KeyValue(cube.rx.angleProperty(),arg0.getY()),
//                                new KeyValue(cube.ry.angleProperty(),-arg0.getX()),
//                                new KeyValue(cube.rz.angleProperty(),arg0.getY())
//                        ));
//                animation.play();
//            }
//        });
//
//        return new Group(cube);
//    }
//
//    public class Face extends Group {
//
//        final Rotate rx = new Rotate(0, Rotate.X_AXIS);
//        final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
//        final Rotate rz = new Rotate(0, Rotate.Z_AXIS);
//
//        RectangleBuilder frontFace;// front face
//        RectangleBuilder rightFace;// right face
//        RectangleBuilder leftFace;// left face
//        RectangleBuilder backFace;// back face
//
//        public Face(double size) {
//
//            Color[] colors = {Color.TRANSPARENT, Color.YELLOW};
//
//            backFace = RectangleBuilder.create()
//                    .strokeDashArray(1.0,6.0)
//                    .width(size)
//                    .height(size)
//                    .fill(colors[0])
//                    .strokeWidth(2)
//                    .stroke(Color.BLUE)
//                    .translateX(-0.5 * size)
//                    .translateY(-0.5 * size)
//                    .translateZ(-0.5*size)
//                    .rotationAxis(Rotate.Z_AXIS)
//                    .rotate(45);
//            rightFace = RectangleBuilder.create()
//                    .strokeDashArray(1.0,6.0)
//                    .width(size)
//                    .height(size)
//                    .fill(colors[0])
//                    .strokeWidth(2)
//                    .stroke(Color.BLUE)
//                    .translateX(-1 * size)
//                    .translateY(-0.5 * size)
//                    .rotationAxis(Rotate.Y_AXIS)
//                    .rotate(90);
//            leftFace = RectangleBuilder.create()
//                    .strokeDashArray(1.0,6.0)
//                    .width(size)
//                    .height(size)
//                    .fill(colors[0])
//                    .strokeWidth(2)
//                    .stroke(Color.BLUE)
//                    .translateX(0)
//                    .translateY(-0.5 * size)
//                    .rotationAxis(Rotate.Y_AXIS)
//                    .rotate(90);
//            frontFace = RectangleBuilder.create()
//                    .strokeDashArray(1.0,6.0)
//                    .width(size)
//                    .height(size)
//                    .fill(colors[0])
//                    .strokeWidth(2)
//                    .stroke(Color.BLUE)
//                    .translateX(-0.5 * size)
//                    .translateY(-0.5 * size)
//                    .translateZ(0.5*size)
//                    .rotationAxis(Rotate.Z_AXIS)
//                    .rotate(225);
//
//            Rectangle rectangleFrontFace = frontFace.build();
//            Rectangle rectangleRightFace = rightFace.build();
//            Rectangle ractangleLeftFace = leftFace.build();
//            Rectangle rectangleBackFace = backFace.build();
//
//            Bloom backFaceBloomEffect = new Bloom();
//            Circle backCircle = new Circle();
//            backCircle.setStrokeWidth(10);
//            backCircle.setRadius(10);
//            backCircle.setStrokeLineCap(StrokeLineCap.ROUND);
//            backCircle.setStroke(colors[1]);
//            backCircle.getStrokeDashArray().addAll(1.0, 20.0);
//            backCircle.setTranslateX(-0.5 * size);
//            backCircle.setTranslateY(-0.5 * size);
//            backCircle.setTranslateZ(-0.5 * size);
//            backCircle.setEffect(backFaceBloomEffect);
//
//            Bloom frontFaceBloomEffect = new Bloom();
//            Circle frontCircle = new Circle();
//            frontCircle.setStrokeWidth(10);
//            frontCircle.setRadius(10);
//            frontCircle.setStrokeLineCap(StrokeLineCap.ROUND);
//            frontCircle.setStroke(colors[1]);
//            frontCircle.getStrokeDashArray().addAll(1.0, 20.0);
//            frontCircle.setTranslateX(-0.5 * size);
//            frontCircle.setTranslateY(-0.5 * size);
//            frontCircle.setTranslateZ(0.5 * size);
//            frontCircle.setEffect(frontFaceBloomEffect);
//
//            pathBackFaceTransition = new PathTransition();
//            pathBackFaceTransition.setPath(rectangleBackFace);
//            pathBackFaceTransition.setNode(frontCircle);
//            pathBackFaceTransition.setDuration(Duration.seconds(4));
//            pathBackFaceTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
//            pathBackFaceTransition.setCycleCount(Timeline.INDEFINITE);
//
//            pathFrontFaceTransition = new PathTransition();
//            pathFrontFaceTransition.setPath(rectangleFrontFace);
//            pathFrontFaceTransition.setNode(backCircle);
//            pathFrontFaceTransition.setDuration(Duration.seconds(4));
//            pathFrontFaceTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
//            pathFrontFaceTransition.setCycleCount(Timeline.INDEFINITE);
//
//            getChildren().addAll(backCircle, frontCircle, rectangleBackFace, rectangleRightFace, ractangleLeftFace, rectangleFrontFace);
//            getTransforms().addAll(rz, ry, rx);
//
//        }
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        init(primaryStage);
//        primaryStage.show();
//        play();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}
