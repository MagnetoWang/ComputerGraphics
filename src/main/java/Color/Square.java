package Color;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/** 
*
* @ClassName : Square.java
* @author : Magneto_Wang
* @date  2018年6月18日 下午9:40:01
* @Description  显示方块的图形界面
* 
*/
public class Square extends Application{
	public static void main(String[] args) {
		Application.launch(args);
	}


    public Group root = new Group();
	public Canvas canvas ;//= new Canvas(800, 600);
	GraphicsContext gc ;//= canvas.getGraphicsContext2D();
	public Scene scene=new Scene(root);
	
	public void start(Stage primaryStage) throws Exception {
		Scanner in =new Scanner(System.in);
		int n=in.nextInt();
		canvas=new Canvas(n*10,n*10);
		gc=canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		
		primaryStage.setScene(scene);
		Rectangle rectangle=new Rectangle(n,n);
		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, n*10, n*10);
		gc.setFill(Color.WHITE);
		gc.fillRect(n*1, n*1, n, n);
		
		gc.setFill(Color.BLACK);
		gc.fillRect(n*2+n, n*1, n, n);
		
		gc.setFill(Color.GREEN);
		gc.fillRect(n*3+2*n, n*1, n, n);
		
		gc.setFill(Color.RED);
		gc.fillRect(n*1, n*3, n, n);
		
		gc.setFill(Color.ORANGERED);
		gc.fillRect(n*2+n, n*3, n, n);
		
		gc.setFill(Color.BLUE);
		gc.fillRect(n*3+2*n, n*3, n, n);
		
		gc.setFill(Color.GOLD);
		gc.fillRect(n*1, n*5, n, n);
		
		
		gc.setFill(Color.PINK);
		gc.fillRect(n*2+n, n*5, n, n);
		
		gc.setFill(Color.YELLOW);
		gc.fillRect(n*3+2*n, n*5, n, n);
		

			
		
		
		primaryStage.show();
		
		
	}

}
