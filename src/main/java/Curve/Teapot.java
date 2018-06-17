package Curve;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.VertexFormat;
import javafx.stage.Stage;
import utils.ThreeDTools;
/** 
*
* @ClassName : Teapot.java
* @author : Magneto_Wang
* @date  2018年6月17日 上午12:40:19
* @Description  TODO
* 
*/
public class Teapot extends Application{
	public static void main(String[] args) {
		Application.launch(args);
	}
    public Group root = new Group();
	public Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	
	public Scene scene;
	@Override
	public void start(Stage primaryStage) throws Exception {
		ThreeDTools tools =new ThreeDTools();
		
		root.getChildren().add(canvas);
		scene=new Scene(root);
		primaryStage.setScene(scene);
		tools.handleMouse(scene, root);
		DrawMesh(primaryStage);
		
		
		
	}
	public void DrawQuadricCurve(Stage primaryStage){
		QuadCurve quad = new QuadCurve();
		
		quad.setStartX(0.0f);
		quad.setStartY(50.0f);
		
		quad.setEndX(50.0f);
		quad.setEndY(50.0f);
		
		quad.setControlX(25.0f);
		quad.setControlY(0.0f);
		root.getChildren().add(quad);
		primaryStage.show();
	}
	public void DrawMesh(Stage primaryStage){
		VertexFormat vertexFormat=VertexFormat.POINT_NORMAL_TEXCOORD;
		TriangleMesh triangleMesh=new TriangleMesh(vertexFormat);
		root.getChildren().addAll();
		primaryStage.show();
	}

	
}
