package Curve;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/** 
*
* @ClassName : Hermite.java
* @author : Magneto_Wang
* @date  2018年6月7日 上午12:08:25
* @Description  TODO
* 
*/
public class Hermite extends Application{
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
		primaryStage.setTitle("Hermite");
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		
		DrawHermite(500,primaryStage);



		primaryStage.show();
		
		
	}
	/***
	 * hermite 有默认的公式直接调用即可。
	 * @param points
	 * @param primaryStage
	 */
	public void DrawHermite(int points,Stage primaryStage){
		Point2D p1=new Point2D(100,200);
		Point2D p4=new Point2D(100,200);
		Point2D r1=new Point2D(100,400);
		Point2D r4=new Point2D(-400,100);
//		Point2D tempC0=new Point2D(x, y);
		int n=points;
		//要想画理想图，都是要对缩放特别设置
		double scaler= 1.0/n;
		for(int i=0;i<n;i++){
			double u=i*scaler;
			Point2D MixedPoint = p1.multiply(Math.pow(u, 3)*2-3*Math.pow(u, 2)+1)
					.add(p4.multiply(Math.pow(u, 3)*(-2)+Math.pow(u, 2)*3)
							.add(r1.multiply(u*u*u-2*u*u+u))
							.add(r4.multiply(u*u*u-u*u)));
			DrawPoint(MixedPoint.getX(), MixedPoint.getY(), 1	);
		}
		
	}
	public void DrawPoint(double x, double y, double radius) {
		gc.fillOval(x, y, radius, radius);
	}


}
