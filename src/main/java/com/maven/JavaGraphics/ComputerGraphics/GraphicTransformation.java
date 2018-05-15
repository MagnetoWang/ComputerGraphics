package com.maven.JavaGraphics.ComputerGraphics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;

/*
 * 
 *  1. 利用OpenGL实现一个立方体关于参考点（10.0,20.0,10.0）进行放缩变换，放缩因子为（2.0,1.0,0.5）。
2. 利用OpenGL实现一个矩形关于y=x+5对称的新图形。
3. 通过定义键盘回调函数，每按一次空格键，让三个点依次完成画点、画线、画三角形、让三角形平移和缩放，并让三角形沿三角形中心旋转起来。
 */
public class GraphicTransformation extends Application {

	/**
	 *
	 * @ClassName : GraphicTransformation.java
	 * @author : Magneto_Wang
	 * @date 2018年5月14日 上午11:26:36
	 * @Description
	 *
	 * 
	 */
	
	
	public GraphicsContext gc;
	public Stage stage = new Stage();
    public Canvas canvas;
    public VBox root=new VBox();
    public Scene scene;
	public BasicShape Draw = new BasicShape();
	
	
	
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage primaryStage) {
		canvas = new Canvas();
		gc=canvas.getGraphicsContext2D();
		stage.setTitle("立方体缩放");
		Transformation();
//		Rotation();
//		Cutting();
//		Zoom();
	}
	public void Transformation(){
		
	}

	public void TestTransformation() {
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Text Fonts");

		Group rectangleGroup = new Group();
		Scene scene = new Scene(rectangleGroup, 550, 250);

		Rectangle rect = new Rectangle();
		rect.setWidth(100);
		rect.setHeight(100);
		rect.setTranslateX(135);
		rect.setTranslateY(11.0);

		rectangleGroup.getChildren().add(rect);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void TestRotation() {
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Text Fonts");

		Group rectangleGroup = new Group();
		Scene scene = new Scene(rectangleGroup, 550, 250);

		Rectangle rect = new Rectangle();
		rect.setWidth(100);
		rect.setHeight(100);

		rect.setRotate(10);
		rectangleGroup.getChildren().add(rect);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void TestZoom() {
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Text Fonts");

		Group rectangleGroup = new Group();
		Scene scene = new Scene(rectangleGroup, 550, 250);

		Rectangle rect = new Rectangle();
		rect.setWidth(100);
		rect.setHeight(100);

		rect.setScaleY(2);
		rectangleGroup.getChildren().add(rect);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void TestCutting() {
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Text Fonts");

		Group rectangleGroup = new Group();
		Scene scene = new Scene(rectangleGroup, 550, 250);

		Rectangle rect = new Rectangle();
		rect.setWidth(100);
		rect.setHeight(100);

		Shear sh = new Shear();
		sh.setY(0.4);
		rect.getTransforms().add(sh);

		rectangleGroup.getChildren().add(rect);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
