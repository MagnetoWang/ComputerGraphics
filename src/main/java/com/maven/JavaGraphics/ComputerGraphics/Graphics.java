/**
 * 
 */
package com.maven.JavaGraphics.ComputerGraphics;

import java.awt.font.GlyphVector;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.SSLException;
import javax.swing.plaf.synth.SynthScrollBarUI;


import org.omg.CORBA.RepositoryIdHelper;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

/**
 * @author Magneto_Wang 模拟DDA算法
 */
public class Graphics extends Application {

	public Group root = new Group();
	public Canvas canvas = new Canvas(1200, 1000);
	// public Parent root;
	public Scene scene;

	public void start(Stage primaryStage) throws InterruptedException, IOException {
		// URL location = getClass().getResource("Scene.fxml");
		// FXMLLoader fxmlLoader = new FXMLLoader();
		// fxmlLoader.setLocation(location);
		//// fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		// root = fxmlLoader.load();
		// //如果使用 Parent root = FXMLLoader.load(...) 静态读取方法，无法获取到Controller的实例对象
		// primaryStage.setTitle("Hello World");
		// scene = new Scene(root);
		// //加载css样式
		// //scene.getStylesheets().add(getClass().getResource("style1.css").toExternalForm());
		// primaryStage.setScene(scene);
		//
		// primaryStage.show();

		primaryStage.setTitle("Drawing Operations Test");

		GraphicsContext gc = canvas.getGraphicsContext2D();

		// drawShapes(gc);
		drawLine(gc, primaryStage);

	}

	public void drawLine(GraphicsContext gc, Stage primaryStage) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		// System.out.println("输入 起点 x 和 y");
		// int StartX =in.nextInt();
		// int StartY = in.nextInt();
		// System.out.println("输入 终点 x 和 y");
		// int EndX = in.nextInt();
		// int EndY = in.nextInt();

		// DDA(gc,StartX,StartY,EndX,EndY);
		// 斜率 有限制 -1<=k<=1
		DDA(gc, 10, 10, 500, 500, primaryStage);

		NormalDDA(gc, 10, 30, 500, 530, primaryStage);

		primaryStage.show();

	}

	public void NormalDDA(GraphicsContext gc, int StartX, int StartY, int EndX, int EndY, Stage primaryStage) {
		gc.setFill(Color.BLUE);
		gc.setStroke(Color.YELLOW);
		double dY = (EndY - StartY);
		double dX = (EndX - StartX);
		double slope = (EndY - StartY) / (EndX - StartX);
		double d;
		int len, flag;
		int radius = 5;
		if (slope >= 1.0) {
			if (StartY > EndY) {
				int t = StartY;
				StartY = EndY;
				EndY = t;
			}
			d = 1 - 0.5 * slope;
			for (int i = StartY; i < EndY; i++) {
				if (i % 20 == 0)
					DrawPoint(gc, StartX, i, radius);
				if (d >= 0) {
					StartX++;
					d += (1 - slope);
				} else {
					d++;
				}

			}
		}
		if (slope <= 1.0 && slope >= 0.0) {

		}

	}

	/*
	 * x,y,width,height.x和y表示坐标位置，width=height时为圆 gc.fillOval(100, 100, 30, 30);
	 * 
	 */
	public void DDA(GraphicsContext gc, int StartX, int StartY, int EndX, int EndY, Stage primaryStage)
			throws InterruptedException {
		gc.setFill(Color.BLUE);
		gc.setStroke(Color.RED);
		double slope = (EndY - StartY) / (EndX - StartX);
		double y = StartY;

		// primaryStage.centerOnScreen();
		for (int i = StartX; i < EndX; i += 10) {
			DrawPoint(gc, i, y, 5);
			// Thread.sleep(2000);
			y += (slope * 10);
		}
		// gc.strokeLine(10, 10, 500, 500);
		gc.strokeLine(10, 20, 500, 510);

		// primaryStage.close();

	}

	public void DrawPoint(GraphicsContext gc, double x, double y, double radius) {
		gc.fillOval(x, y, radius, radius);
	}

	private void drawShapes(GraphicsContext gc) {

		gc.setFill(Color.BLUE);
		// gc.setStroke(Color.BLUE);
		gc.fillOval(1, 6, 30, 30);

		// gc.setLineWidth(5);
		// gc.strokeLine(40, 10, 10, 40);
		// gc.fillOval(1, 6, 30, 30);
		// gc.strokeOval(60, 60, 30, 30);
		// gc.fillRoundRect(110, 60, 30, 30, 10, 10);
		// gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
		// gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
		// gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
		// gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
		// gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
		// gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
		// gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
		// gc.fillPolygon(new double[]{10, 40, 10, 40},
		// new double[]{210, 210, 240, 240}, 4);
		// gc.strokePolygon(new double[]{60, 90, 60, 90},
		// new double[]{210, 210, 240, 240}, 4);
		// gc.strokePolyline(new double[]{110, 140, 110, 140},
		// new double[]{210, 210, 240, 240}, 4);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
