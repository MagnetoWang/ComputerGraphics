/**
 * 
 */
package com.maven.JavaGraphics.ComputerGraphics;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;



//import junit.framework.TestCase;
import javafx.application.Application;

/**
 * @author Magneto_Wang
 *
 */
public class TestGraphics extends Application {

	/** 
	*
	* @ClassName : TestGraphics.java
	* @author : Magneto_Wang
	* @date  2018年5月7日 上午10:55:11
	* @Description  TODO
	* 
	*/
	

	public void start(Stage stage) {
        VBox box = new VBox();
        final Scene scene = new Scene(box,300, 250);
        scene.setFill(null);

        Line line = new Line();
        line.setStartX(0.0f);
        line.setStartY(0.0f);
        line.setEndX(100.0f);
        line.setEndY(100.0f);

        box.getChildren().add(line);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
