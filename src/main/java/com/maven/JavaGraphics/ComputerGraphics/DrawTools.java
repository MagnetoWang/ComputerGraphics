package com.maven.JavaGraphics.ComputerGraphics;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

/**
 *
 * @ClassName : DrawTools.java
 * @author : Magneto_Wang
 * @date 2018年5月7日 下午11:19:25
 * @Description 重新封装java画图对象
 * 
 */
public class DrawTools extends Application {

	@Override
	public void start(Stage stage) {
		Stage primaryStage = new Stage();

//		primaryStage = new Stage();
//		DrawLine(primaryStage);
//
//		primaryStage = new Stage();
//		DrawRectangle(primaryStage);
//		primaryStage = new Stage();
//		Drawrect(primaryStage);
//		primaryStage = new Stage();
//		DrawEllipse(primaryStage);

	}



	public void TestDrawEllipse(Stage primaryStage) {
		primaryStage.setTitle("椭圆示例");
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250, Color.WHITE);

		Group g = new Group();

		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0);
		ds.setColor(Color.color(0.4, 0.4, 0.4));

		Ellipse ellipse = new Ellipse();
		ellipse.setCenterX(50.0f);
		ellipse.setCenterY(50.0f);
		ellipse.setRadiusX(50.0f);
		ellipse.setRadiusY(25.0f);
		ellipse.setEffect(ds);

		g.getChildren().add(ellipse);

		root.getChildren().add(g);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void TestRedLine(Stage primaryStage) {
		primaryStage.setTitle("Drawing Lines");

		Group root = new Group();
		Scene scene = new Scene(root, 300, 150, Color.GRAY);

		Line redLine = new Line(10, 10, 200, 10);

		redLine.setStroke(Color.RED);
		redLine.setStrokeWidth(10);
		redLine.setStrokeLineCap(StrokeLineCap.BUTT);

		redLine.getStrokeDashArray().addAll(15d, 5d, 15d, 15d, 20d);
		redLine.setStrokeDashOffset(10);

		root.getChildren().add(redLine);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void TestDrawLine(Stage stage) {
		VBox box = new VBox();
		Scene scene2 = new Scene(box, 300, 250);
		scene2.setFill(null);

		Line line = new Line();
		line.setStartX(0.0f);
		line.setStartY(0.0f);
		line.setEndX(100.0f);
		line.setEndY(100.0f);

		box.getChildren().add(line);

		stage.setScene(scene2);
		stage.show();
	}

	public void TestDrawrect(Stage primaryStage) {
		primaryStage.setTitle("圆角矩形示例");
		Group group = new Group();

		Rectangle rect = new Rectangle(20, 20, 200, 200);

		rect.setArcHeight(15);
		rect.setArcWidth(15);

		rect.setStroke(Color.BLACK);
		group.getChildren().add(rect);

		Scene scene = new Scene(group, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void TestDrawRectangle(Stage primaryStage) {
		primaryStage.setTitle("矩形示例");
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250, Color.WHITE);

		Rectangle r = new Rectangle();
		r.setX(50);
		r.setY(50);
		r.setWidth(200);
		r.setHeight(100);

		root.getChildren().add(r);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
