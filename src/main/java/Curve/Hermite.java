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
	// public Parent root;
	public Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Formula one");
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		
		DrawHermite(200,primaryStage);



		primaryStage.show();
		
		
	}
	public void DrawHermite(int points,Stage primaryStage){
		Point2D p1=new Point2D(0, 1);
		Point2D p4=new Point2D(3,0);
		Point2D r1=new Point2D(0, 1);
		Point2D r4=new Point2D(3,0);
//		Point2D tempC0=new Point2D(x, y);
		
	}


}
