package Curve;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/** 
*
* @ClassName : MoveCurve.java
* @author : Magneto_Wang
* @date  2018年6月8日 下午11:40:38
* @Description  给出目标曲线，然后目标物体在曲线上运动
* 
*/
public class MoveCurve extends Application{
	
    public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        Application.launch(args);
    }
    
    public Group root = new Group();
	public Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	public Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Beizer 模拟曲线圆");
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));

//		MoveOnLine(primaryStage,100,50,400,450);
		RotateWithPoint(primaryStage,200,400,100);
 



		
		
		
	}
	/***
	 * @Description 使一物体沿着一条直线匀速移动
	 */
	public void MoveOnLine(Stage primaryStage,double begin_x,double begin_y,double end_x,double end_y){
	       final Rectangle rectBasicTimeline = new Rectangle(begin_x,begin_y , 10, 10);
	        rectBasicTimeline.setFill(Color.BLACK);
	        Line line=new Line(begin_x,begin_y,end_x,end_y);
	        line.setFill(Color.RED);
	        root.getChildren().addAll(rectBasicTimeline,line);	
	        primaryStage.show();
	        
	        final Timeline timeline = new Timeline();
	        timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.setAutoReverse(true);
	        final KeyValue kv = new KeyValue(rectBasicTimeline.xProperty(), end_x, Interpolator.EASE_BOTH);
	        final KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
	        final KeyValue kv_y = new KeyValue(rectBasicTimeline.yProperty(), end_y, Interpolator.EASE_BOTH);
	        final KeyFrame kf_y = new KeyFrame(Duration.millis(1000), kv_y);
	        timeline.getKeyFrames().addAll(kf,kf_y);
	        timeline.play();
	}
	/***
	 * @Description 使一物体围绕屏幕上一点匀速旋转
	 */
	public void RotateWithPoint(Stage primaryStage,double centerX,double centerY,double radius){
        final Circle pointPath = new Circle (10);
//        centerY=-centerY;
        Circle circlePath = new Circle(centerX,centerY,1);

        pointPath.setFill(Color.ORANGE);
//        circlePath.setStroke(Color.BLUE);
        circlePath.setFill(Color.BLUE);
        
        primaryStage.show();
        
        Path path = new Path();
        path.getElements().add(new MoveTo(centerX,centerY-radius));
        path.getElements().add(new CubicCurveTo(centerX+(radius)/2, centerY-radius
        										, centerX+radius, centerY-(radius/2)
        										, centerX+radius, centerY));
        path.getElements().add(new CubicCurveTo(centerX+(radius), centerY+(radius/2)
												, centerX+(radius/2), centerY+radius
												, centerX, centerY+radius));
        path.getElements().add(new MoveTo(centerX, centerY+radius));
        
        path.getElements().add(new CubicCurveTo(centerX-(radius)/2, centerY+radius
												, centerX-radius, centerY+(radius/2)
												, centerX-radius, centerY));
        path.getElements().add(new CubicCurveTo(centerX-radius, centerY-(radius/2)
												, centerX-(radius/2), centerY-(radius)
												, centerX, centerY-radius));
        path.setFill(Color.WHITE);
        root.getChildren().add(path);
        root.getChildren().addAll(pointPath,circlePath);

//        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(pointPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
        
        
	}


}
