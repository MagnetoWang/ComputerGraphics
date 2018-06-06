package Curve;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** 
*
* @ClassName : MathCurve.java
* @author : Magneto_Wang
* @date  2018年6月6日 下午11:03:47
* @Description  根据数学公式画出对应曲线
* 
*/
public class MathCurve extends Application{
    public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        Application.launch(args);
    }
    
    public Group root = new Group();
	public Canvas canvas = new Canvas(600, 500);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	// public Parent root;
	public Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Formula one");
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		

		
//		formula1(primaryStage);
//		formula2(primaryStage);
//		primaryStage.show();
		
		
//		primaryStage=new Stage();
//		primaryStage.setTitle("Formula two");
		formula2(primaryStage);
		primaryStage.show();
//		
		
	}
	
	public void DrawPoint(double x, double y, double radius) {
		gc.fillOval(x, y, radius, radius);
	}
	/***
	 * x=t^2-2t+1
	 * y=t^3-2t^2+t	
	 * 
	 * (sqrt(x))^3-2*(sqrt(x)+1)^2+sqrt(x)+1
	 */
	public void formula1(Stage primaryStage){
		
		
		gc.setFill(Color.BLUE);
//		DrawPoint(40,40,5);
//		DrawPoint(80,80,5);
		for(double i=1000;i<1000000;){
			double t=i/100000;
			double x=t*t-2*t+1;
			double y =t*t*t-2*t*t+t;
			x=x*0.5+100;
			y=y*0.5+100;
			y=500-y;
			System.out.println(" x = "+x+" y = "+y);
			DrawPoint(x,y,5);
			i+=10007;
		}
		System.out.println("end");
		
		
		
		
		
		
		
		
		
	}
	
	/***
	 * x=t^2+1
	 * y=t^3
	 * 
	 * (sqrt(x-1))^3
	 * 
	 */
	public void formula2(Stage primaryStage){
		
		gc.setFill(Color.RED);

		for(double i=1000;i<1000000;){
			double t=i/100000;
			double x=t*t+1;
			double y =t*t*t;
			x=x*1+100;
			y=y*1+100;
			y=500-y;
			System.out.println(" x = "+x+" y = "+y);
			DrawPoint(x,y,5);
			i+=10007;
		}
		System.out.println("end");
		
		
		
	}


}
