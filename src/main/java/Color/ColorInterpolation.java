package Color;

import java.awt.Paint;
import java.util.Scanner;

import javax.swing.RootPaneContainer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** 
*
* @ClassName : ColorInterpolation.java
* @author : Magneto_Wang
* @date  2018年6月18日 下午8:57:22
* @Description  画一条线。类似于颜色条，每一个点的颜色都不一样
* 
*/
public class ColorInterpolation extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	static{

		
	}
	
    public Group root = new Group();
	public Canvas canvas ;// new Canvas(800, 600);
	GraphicsContext gc ;//= canvas.getGraphicsContext2D();
	public Scene scene=new Scene(root);
	@Override
	public void start(Stage primaryStage) throws Exception {

		root.getChildren().add(canvas);
		primaryStage.setScene(scene);
		DrawColorLine(primaryStage);
	}
	public void DrawColorLine(Stage primaryStage){
		double beginX=0,endX=200,beginY=0,endY=200,k;
		//左右移动的值和上下移动的值
		double leftRight=100;
		double upDown=100;
		beginX+=leftRight;
		endX+=leftRight;
		beginY+=upDown;
		endY+=upDown;
		k=1;//假设k=1
		double lineLength=endX-beginX;
		for(int i=(int)beginX;i<endX;i++){
			double lineBegin=i-beginX;
			Color c = new Color(lineBegin/lineLength,
					lineBegin/lineLength,
					lineBegin/lineLength,1.0);
			gc.setFill(c);
			DrawPoint(i, i, 0, 5);
			
		}
		primaryStage.show();
	}
	public void DrawPoint(double x, double y,double z, double radius) {

		gc.fillOval(x, y, radius, radius);
	}
}
