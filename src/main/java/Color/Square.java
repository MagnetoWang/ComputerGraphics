package Color;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
/** 
*
* @ClassName : Square.java
* @author : Magneto_Wang
* @date  2018年6月18日 下午9:40:01
* @Description  显示方块的图形界面
* 
*/
public class Square {
	public static void main(String[] args) {
		Application.launch(args);
	}


    public Group root = new Group();
	public Canvas canvas ;// new Canvas(800, 600);
	GraphicsContext gc ;//= canvas.getGraphicsContext2D();
	public Scene scene=new Scene(root);
	
	public void start(Stage primaryStage) throws Exception {

		root.getChildren().add(canvas);
		primaryStage.setScene(scene);
		
	}

}
