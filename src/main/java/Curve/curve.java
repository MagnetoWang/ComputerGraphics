package Curve;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;

public class curve extends Application {
    public void start(Stage primaryStage) {
        BeizerCurve(primaryStage);
    }
    public void BeizerCurve(Stage primaryStage){
        Pane pane = new Pane();

        CubicCurve cubicCurve = new CubicCurve() ;
        cubicCurve.setStartX(50);
        cubicCurve.setStartY(75);
        cubicCurve.setControlX1(80);
        cubicCurve.setControlY1(-25);
        cubicCurve.setControlX2(110);
        cubicCurve.setControlY2(175);
        cubicCurve.setEndX(140);
        cubicCurve.setEndY(75) ;
        cubicCurve.setStroke(Color.BLACK);
        cubicCurve.setStrokeWidth(3);
        cubicCurve.setFill(Color.ORANGE);
        pane.getChildren().add(cubicCurve);

        Scene scene =new Scene(pane,200,150);
        primaryStage.setTitle("CubicBezierCurve");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        Application.launch(args);
    }
}