//package solorSystem;
//
//import animation.Helix;
//import javafx.animation.*;
//import javafx.scene.Group;
//import javafx.scene.effect.Bloom;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.PhongMaterial;
//import javafx.scene.shape.*;
//import javafx.scene.transform.Rotate;
//import javafx.util.Duration;
//
//public class Sun {
//    private static final class Content {
//
//        private static final Duration DURATION = Duration.seconds(4);
//        private static final Color COLOR = Color.AQUA;
//        private static final double WIDTH = 3;
//        private final Group group = new Group();
//        private final Rotate rx = new Rotate(0, Rotate.X_AXIS);
//        private final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
//        private final Rotate rz = new Rotate(0, Rotate.Z_AXIS);
//        private final Box xAxis;
//        private final Box yAxis;
//        private final Box zAxis;
//        private final Shape circle;
//        private final Shape arrow;
//        private final Animation animation;
//
//        private static Content create(double size) {
//            Content c = new Content(size);
//            c.group.getChildren().addAll(c.arrow, c.circle,
//                    c.xAxis, c.yAxis, c.zAxis);
//            c.group.getTransforms().addAll(c.rz, c.ry, c.rx);
//            c.group.setTranslateX(-size / 2);
//            c.group.setTranslateY(-size / 2);
//            c.group.setTranslateZ(size / 2);
//            c.rx.setAngle(35);
//            c.ry.setAngle(-45);
//            return c;
//        }
//
//        private Content(double size) {
//            xAxis = createBox(size, WIDTH, WIDTH);
//            yAxis = createBox(WIDTH, size, WIDTH);
//            zAxis = createBox(WIDTH, WIDTH, size);
//            circle = createCircle(size);
//            arrow = createShape();
//            animation = new ParallelTransition(
//                    createTransition(circle, arrow),
//                    createTimeline(size / 2));
//        }
//
//        private Circle createCircle(double size) {
//            Circle c = new Circle(size / 4);
//            c.setFill(Color.TRANSPARENT);
//            c.setStroke(COLOR);
//            return c;
//        }
//
//        private Box createBox(double w, double h, double d) {
//            Box b = new Box(w, h, d);
//            b.setMaterial(new PhongMaterial(COLOR));
//            return b;
//        }
//
//        private Shape createShape() {
//            Shape s = new Polygon(0, 0, -10, -10, 10, 0, -10, 10);
//            s.setStrokeWidth(WIDTH);
//            s.setStrokeLineCap(StrokeLineCap.ROUND);
//            s.setStroke(COLOR);
//            s.setEffect(new Bloom());
//            return s;
//        }
//
//        private Transition createTransition(Shape path, Shape node) {
//            PathTransition t = new PathTransition(DURATION, path, node);
//            t.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//            t.setCycleCount(Timeline.INDEFINITE);
//            t.setInterpolator(Interpolator.LINEAR);
//            return t;
//        }
//
//        private Timeline createTimeline(double size) {
//            Timeline t = new Timeline();
//            t.setCycleCount(Timeline.INDEFINITE);
//            t.setAutoReverse(true);
//            KeyValue keyX = new KeyValue(group.translateXProperty(), size);
//            KeyValue keyY = new KeyValue(group.translateYProperty(), size);
//            KeyValue keyZ = new KeyValue(group.translateZProperty(), -size);
//            KeyFrame keyFrame = new KeyFrame(DURATION.divide(2), keyX, keyY, keyZ);
//            t.getKeyFrames().add(keyFrame);
//            return t;
//        }
//    }
//}
