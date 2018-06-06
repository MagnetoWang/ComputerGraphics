/*
 * Copyright (c) 2008, 2014, Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package animation;

import javafx.animation.*;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;


public class Animation extends Application {
    
    private SequentialTransition sequentialTransition;
    private SequentialTransition parallelTransition;
    

   @Override public void start(Stage stage) {
        stage.setTitle("Transitions and Timeline Animation");
        final Scene scene = new Scene(new Group(), 600, 450);
        scene.setFill(Color.WHITE);
        
        final Rectangle rectBasicTimeline = new Rectangle(100, 50, 100, 50);
        rectBasicTimeline.setFill(Color.BROWN);
        
        final Rectangle rectFade = new Rectangle(10, 150, 100, 100);
        rectFade.setArcHeight(20);
        rectFade.setArcWidth(20);
        rectFade.setFill(Color.RED);
        
        final Rectangle rectFadeAll = new Rectangle(0, 0, 600, 450);
        rectFadeAll.setFill(Color.GREY);
        
        final Rectangle rectPath = new Rectangle (0, 0, 40, 40);
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.ORANGE);
        
        Rectangle rectParallel = new Rectangle(10,200,50, 50);
        rectParallel.setArcHeight(15);
        rectParallel.setArcWidth(15);
        rectParallel.setFill(Color.DARKBLUE);
        rectParallel.setTranslateX(50);
        rectParallel.setTranslateY(75);
        
        Rectangle rectSeq = new Rectangle(25,25,50, 50);
        rectSeq.setArcHeight(15);
        rectSeq.setArcWidth(15);
        rectSeq.setFill(Color.DARKBLUE);
        rectSeq.setTranslateX(50);
        rectSeq.setTranslateY(50);
        
           
        
        ((Group)scene.getRoot()).getChildren().addAll(rectBasicTimeline, rectFade, rectPath, rectParallel, rectSeq);
        stage.setScene(scene);
        stage.show();
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(rectBasicTimeline.xProperty(), 300, Interpolator.EASE_BOTH);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        
        FadeTransition ft = new FadeTransition(Duration.millis(3000), rectFade);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
        
        Path path = new Path();
        path.getElements().add(new MoveTo(20,20));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
        
         FadeTransition fadeTransition = 
            new FadeTransition(Duration.millis(1000), rectSeq);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.3f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(true);

        TranslateTransition translateTransition =
            new TranslateTransition(Duration.millis(2000), rectSeq);
        translateTransition.setFromX(50);
        translateTransition.setToX(375);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);

        RotateTransition rotateTransition = 
            new RotateTransition(Duration.millis(2000), rectSeq);
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);

        ScaleTransition scaleTransition = 
            new ScaleTransition(Duration.millis(2000), rectSeq);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        
        // create a sequential transition to do four transitions one after another
        sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(
                fadeTransition,
                translateTransition,
                rotateTransition,
                scaleTransition);
        sequentialTransition.setCycleCount(Timeline.INDEFINITE);
        sequentialTransition.setAutoReverse(true);
        sequentialTransition.play();        
        
        
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
