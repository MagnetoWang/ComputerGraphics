package com.maven.JavaGraphics.ComputerGraphics;



	/** 
	*
	* @ClassName : Xylophone.java
	* @author : Magneto_Wang
	* @date  2018年6月3日 下午11:23:57
	* @Description  TODO
	* 
	*/


/*
 * Copyright (c) 2010, 2014, Oracle and/or its affiliates.
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
 *  - Neither the name of Oracle nor the names of its
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

 
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
 
public class Xylophone extends Application {
 
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
 
    final Cam camOffset = new Cam();
    final Cam cam = new Cam();
    
    final Shear shear = new Shear();
 
    class Cam extends Group {
        Translate t  = new Translate();
        Translate p  = new Translate();
        Translate ip = new Translate();
        Rotate rx = new Rotate();
        { rx.setAxis(Rotate.X_AXIS); }
        Rotate ry = new Rotate();
        { ry.setAxis(Rotate.Y_AXIS); }
        Rotate rz = new Rotate();
        { rz.setAxis(Rotate.Z_AXIS); }
        Scale s = new Scale();
        public Cam() { super(); getTransforms().addAll(t, p, rx, rz, ry, s, ip); }
    }
 
    @Override public void start(final Stage stage) {
        stage.setTitle("Xylophone");
 
        camOffset.getChildren().add(cam);
        resetCam();
 
        final Scene scene = new Scene(camOffset, 800, 600, true);
        scene.setFill(new RadialGradient(225, 0.85, 300, 300, 500, false,
                                         CycleMethod.NO_CYCLE, new Stop[]
                                         { new Stop(0f, Color.BLUE),
                                           new Stop(1f, Color.LIGHTBLUE) }));
        scene.setCamera(new PerspectiveCamera());
 
        final AudioClip bar1Note =
            new AudioClip(Xylophone.class.getResource("audio/Note1.wav").toString());
        final AudioClip bar2Note =
            new AudioClip(Xylophone.class.getResource("audio/Note2.wav").toString());
        final AudioClip bar3Note =
            new AudioClip(Xylophone.class.getResource("audio/Note3.wav").toString());
        final AudioClip bar4Note =
            new AudioClip(Xylophone.class.getResource("audio/Note4.wav").toString());
        final AudioClip bar5Note =
            new AudioClip(Xylophone.class.getResource("audio/Note5.wav").toString());
        final AudioClip bar6Note =
            new AudioClip(Xylophone.class.getResource("audio/Note6.wav").toString());
        final AudioClip bar7Note =
            new AudioClip(Xylophone.class.getResource("audio/Note7.wav").toString());
        final AudioClip bar8Note =
            new AudioClip(Xylophone.class.getResource("audio/Note8.wav").toString());
 
        Group rectangleGroup = new Group();
        rectangleGroup.getTransforms().add(shear);
        rectangleGroup.setDepthTest(DepthTest.ENABLE);
 
        double xStart = 260.0;
        double xOffset = 30.0;
        double yPos = 300.0;
        double zPos = 0.0;
        double barWidth = 22.0;
        double barDepth = 7.0;
 
        // Base1
        Cube base1Cube = new Cube(1.0, new Color(0.2, 0.12, 0.1, 1.0), 1.0);
        base1Cube.setTranslateX(xStart + 135);
        base1Cube.setTranslateZ(yPos+20.0);
        base1Cube.setTranslateY(11.0);
        base1Cube.setScaleX(barWidth*11.5);
        base1Cube.setScaleZ(10.0);
        base1Cube.setScaleY(barDepth*2.0);
 
        // Base2
        Cube base2Cube = new Cube(1.0, new Color(0.2, 0.12, 0.1, 1.0), 1.0);
        base2Cube.setTranslateX(xStart + 135);
        base2Cube.setTranslateZ(yPos-20.0);
        base2Cube.setTranslateY(11.0);
        base2Cube.setScaleX(barWidth*11.5);
        base2Cube.setScaleZ(10.0);
        base2Cube.setScaleY(barDepth*2.0);
 
        // Bar1
        Cube bar1Cube = new Cube(1.0, Color.PURPLE, 1.0);
        bar1Cube.setTranslateX(xStart + 1*xOffset);
        bar1Cube.setTranslateZ(yPos);
        bar1Cube.setScaleX(barWidth);
        bar1Cube.setScaleZ(100.0);
        bar1Cube.setScaleY(barDepth);
 
        // Bar2
        Cube bar2Cube = new Cube(1.0, Color.BLUEVIOLET, 1.0);
        bar2Cube.setTranslateX(xStart + 2*xOffset);
        bar2Cube.setTranslateZ(yPos);
        bar2Cube.setScaleX(barWidth);
        bar2Cube.setScaleZ(95.0);
        bar2Cube.setScaleY(barDepth);
 
        // Bar3
        Cube bar3Cube = new Cube(1.0, Color.BLUE, 1.0);
        bar3Cube.setTranslateX(xStart + 3*xOffset);
        bar3Cube.setTranslateZ(yPos);
        bar3Cube.setScaleX(barWidth);
        bar3Cube.setScaleZ(90.0);
        bar3Cube.setScaleY(barDepth);
 
        // Bar4
        Cube bar4Cube = new Cube(1.0, Color.GREEN, 1.0);
        bar4Cube.setTranslateX(xStart + 4*xOffset);
        bar4Cube.setTranslateZ(yPos);
        bar4Cube.setScaleX(barWidth);
        bar4Cube.setScaleZ(85.0);
        bar4Cube.setScaleY(barDepth);
 
        // Bar5
        Cube bar5Cube = new Cube(1.0, Color.GREENYELLOW, 1.0);
        bar5Cube.setTranslateX(xStart + 5*xOffset);
        bar5Cube.setTranslateZ(yPos);
        bar5Cube.setScaleX(barWidth);
        bar5Cube.setScaleZ(80.0);
        bar5Cube.setScaleY(barDepth);
 
        // Bar6
        Cube bar6Cube = new Cube(1.0, Color.YELLOW, 1.0);
        bar6Cube.setTranslateX(xStart + 6*xOffset);
        bar6Cube.setTranslateZ(yPos);
        bar6Cube.setScaleX(barWidth);
        bar6Cube.setScaleZ(75.0);
        bar6Cube.setScaleY(barDepth);
 
        // Bar7
        Cube bar7Cube = new Cube(1.0, Color.ORANGE, 1.0);
        bar7Cube.setTranslateX(xStart + 7*xOffset);
        bar7Cube.setTranslateZ(yPos);
        bar7Cube.setScaleX(barWidth);
        bar7Cube.setScaleZ(70.0);
        bar7Cube.setScaleY(barDepth);
 
        // Bar8
        Cube bar8Cube = new Cube(1.0, Color.RED, 1.0);
        bar8Cube.setTranslateX(xStart + 8*xOffset);
        bar8Cube.setTranslateZ(yPos);
        bar8Cube.setScaleX(barWidth);
        bar8Cube.setScaleZ(65.0);
        bar8Cube.setScaleY(barDepth);
 
        bar1Cube.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent me) { bar1Note.play(); }
        });
        bar2Cube.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) { bar2Note.play(); }
        });
        bar3Cube.setOnMousePressed(new EventHandler<MouseEvent>() {
           
            public void handle(MouseEvent me) { bar3Note.play(); }
        });
        bar4Cube.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent me) { bar4Note.play(); }
        });
        bar5Cube.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent me) { bar5Note.play(); }
        });
        bar6Cube.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent me) { bar6Note.play(); }
        });
        bar7Cube.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent me) { bar7Note.play(); }
        });
        bar8Cube.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent me) { bar8Note.play(); }
        });
 
        rectangleGroup.getChildren().addAll(base1Cube, base2Cube,
                                            bar1Cube, bar2Cube, bar3Cube,
                                            bar4Cube, bar5Cube, bar6Cube,
                                            bar7Cube, bar8Cube);
        rectangleGroup.setScaleX(2.5);
        rectangleGroup.setScaleY(2.5);
        rectangleGroup.setScaleZ(2.5);
        cam.getChildren().add(rectangleGroup);
       
        frameCam(stage, scene);
 
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent me) {
                mousePosX = me.getX();
                mousePosY = me.getY();
                mouseOldX = me.getX();
                mouseOldY = me.getY();
                //System.out.println("scene.setOnMousePressed " + me);
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getX();
                mousePosY = me.getY();
                mouseDeltaX = mousePosX - mouseOldX;
                mouseDeltaY = mousePosY - mouseOldY;
                if (me.isAltDown() && me.isShiftDown() && me.isPrimaryButtonDown()) {
                    double rzAngle = cam.rz.getAngle();
                    cam.rz.setAngle(rzAngle - mouseDeltaX);
                }
                else if (me.isAltDown() && me.isPrimaryButtonDown()) {
                    double ryAngle = cam.ry.getAngle();
                    cam.ry.setAngle(ryAngle - mouseDeltaX);
                    double rxAngle = cam.rx.getAngle();
                    cam.rx.setAngle(rxAngle + mouseDeltaY);
                }
                else if (me.isShiftDown() && me.isPrimaryButtonDown()) {
                    double yShear = shear.getY();
                    shear.setY(yShear + mouseDeltaY/1000.0);
                    double xShear = shear.getX();
                    shear.setX(xShear + mouseDeltaX/1000.0);
                }
                else if (me.isAltDown() && me.isSecondaryButtonDown()) {
                    double scale = cam.s.getX();
                    double newScale = scale + mouseDeltaX*0.01;
                    cam.s.setX(newScale);
                    cam.s.setY(newScale);
                    cam.s.setZ(newScale);
                }
                else if (me.isAltDown() && me.isMiddleButtonDown()) {
                    double tx = cam.t.getX();
                    double ty = cam.t.getY();
                    cam.t.setX(tx + mouseDeltaX);
                    cam.t.setY(ty + mouseDeltaY);
                }                
            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            
            public void handle(KeyEvent ke) {
                if (KeyCode.A.equals(ke.getCode())) {
                    resetCam();
                    shear.setX(0.0);
                    shear.setY(0.0);
                }
                if (KeyCode.F.equals(ke.getCode())) {
                    frameCam(stage, scene);
                    shear.setX(0.0);
                    shear.setY(0.0);
                }
                if (KeyCode.SPACE.equals(ke.getCode())) {
                    if (stage.isFullScreen()) {
                        stage.setFullScreen(false);
                        frameCam(stage, scene);
                    } else {
                        stage.setFullScreen(true);
                        frameCam(stage, scene);
                    }
                }
            }
        });
 
        stage.setScene(scene);
        stage.show();
    }
 
    //=========================================================================
    // CubeSystem.frameCam
    //=========================================================================
    public void frameCam(final Stage stage, final Scene scene) {
        setCamOffset(camOffset, scene);
        // cam.resetTSP();
        setCamPivot(cam);
        setCamTranslate(cam);
        setCamScale(cam, scene);
    }
 
    //=========================================================================
    // CubeSystem.setCamOffset
    //=========================================================================
    public void setCamOffset(final Cam camOffset, final Scene scene) {
        double width = scene.getWidth();
        double height = scene.getHeight();
        camOffset.t.setX(width/2.0);
        camOffset.t.setY(height/2.0);
    }
 
    //=========================================================================
    // setCamScale
    //=========================================================================
    public void setCamScale(final Cam cam, final Scene scene) {
        final Bounds bounds = cam.getBoundsInLocal();
        final double pivotX = bounds.getMinX() + bounds.getWidth()/2;
        final double pivotY = bounds.getMinY() + bounds.getHeight()/2;
        final double pivotZ = bounds.getMinZ() + bounds.getDepth()/2;
 
        double width = scene.getWidth();
        double height = scene.getHeight();
 
        double scaleFactor = 1.0;
        double scaleFactorY = 1.0;
        double scaleFactorX = 1.0;
        if (bounds.getWidth() > 0.0001) {
            scaleFactorX = width / bounds.getWidth(); // / 2.0;
        }
        if (bounds.getHeight() > 0.0001) {
            scaleFactorY = height / bounds.getHeight(); //  / 1.5;
        }
        if (scaleFactorX > scaleFactorY) {
            scaleFactor = scaleFactorY;
        } else {
            scaleFactor = scaleFactorX;
        }
        cam.s.setX(scaleFactor);
        cam.s.setY(scaleFactor);
        cam.s.setZ(scaleFactor);
    }
 
    //=========================================================================
    // setCamPivot
    //=========================================================================
    public void setCamPivot(final Cam cam) {
        final Bounds bounds = cam.getBoundsInLocal();
        final double pivotX = bounds.getMinX() + bounds.getWidth()/2;
        final double pivotY = bounds.getMinY() + bounds.getHeight()/2;
        final double pivotZ = bounds.getMinZ() + bounds.getDepth()/2;
        cam.p.setX(pivotX);
        cam.p.setY(pivotY);
        cam.p.setZ(pivotZ);
        cam.ip.setX(-pivotX);
        cam.ip.setY(-pivotY);
        cam.ip.setZ(-pivotZ);
    }
 
    //=========================================================================
    // setCamTranslate
    //=========================================================================
    public void setCamTranslate(final Cam cam) {
        final Bounds bounds = cam.getBoundsInLocal();
        final double pivotX = bounds.getMinX() + bounds.getWidth()/2;
        final double pivotY = bounds.getMinY() + bounds.getHeight()/2;
        cam.t.setX(-pivotX);
        cam.t.setY(-pivotY);
    }
 
    public void resetCam() {
        cam.t.setX(0.0);
        cam.t.setY(0.0);
        cam.t.setZ(0.0);
        cam.rx.setAngle(45.0);
        cam.ry.setAngle(-7.0);
        cam.rz.setAngle(0.0);
        cam.s.setX(1.25);
        cam.s.setY(1.25);
        cam.s.setZ(1.25);
 
 
        cam.p.setX(0.0);
        cam.p.setY(0.0);
        cam.p.setZ(0.0);
 
        cam.ip.setX(0.0);
        cam.ip.setY(0.0);
        cam.ip.setZ(0.0);
 
        final Bounds bounds = cam.getBoundsInLocal();
        final double pivotX = bounds.getMinX() + bounds.getWidth() / 2;
        final double pivotY = bounds.getMinY() + bounds.getHeight() / 2;
        final double pivotZ = bounds.getMinZ() + bounds.getDepth() / 2;
 
        cam.p.setX(pivotX);
        cam.p.setY(pivotY);
        cam.p.setZ(pivotZ);
 
        cam.ip.setX(-pivotX);
        cam.ip.setY(-pivotY);
        cam.ip.setZ(-pivotZ);
    }
 
    public class Cube extends Group {
        final Rotate rx = new Rotate(0, Rotate.X_AXIS);
        final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
        final Rotate rz = new Rotate(0, Rotate.Z_AXIS);
        public Cube(double size, Color color, double shade) {
            getTransforms().addAll(rz, ry, rx);
            
//          back face
            Rectangle backFace = new Rectangle(size,size);
            backFace.setFill(color.deriveColor(0.0, 1.0, (1 - 0.5*shade), 1.0));
            backFace.setTranslateX(-0.5*size);
            backFace.setTranslateY(-0.5*size);
            backFace.setTranslateZ(-0.5*size);
            
            // bottom face
            Rectangle bottomFace = new Rectangle(size,size);
            bottomFace.setFill(color.deriveColor(0.0, 1.0, (1 - 0.4*shade), 1.0));
            bottomFace.setTranslateX(-0.5*size);
            bottomFace.setTranslateY(0);
            bottomFace.setRotationAxis(Rotate.X_AXIS);
            bottomFace.setRotate(90);
            
//          right face
            Rectangle rightFace = new Rectangle(size,size);
            rightFace.setFill(color.deriveColor(0.0, 1.0, (1 - 0.3*shade), 1.0));
            rightFace.setTranslateX(-1*size);
            rightFace.setTranslateY(-0.5*size);
            rightFace.setRotationAxis(Rotate.Y_AXIS);
            rightFace.setRotate(90);
            
//            leftFace
            Rectangle leftFace = new Rectangle(size,size);
            leftFace.setFill(color.deriveColor(0.0, 1.0, (1 - 0.2*shade), 1.0));
            leftFace.setTranslateX(0);
            leftFace.setTranslateY(-0.5*size);
            leftFace.setRotationAxis(Rotate.Y_AXIS);
            leftFace.setRotate(90);
            
//            topFace
            Rectangle topFace = new Rectangle(size,size);
            topFace.setFill(color.deriveColor(0.0, 1.0, (1 - 0.1*shade), 1.0));
            topFace.setTranslateX(-0.5*size);
            topFace.setTranslateY(-1*size);
            topFace.setRotationAxis(Rotate.X_AXIS);
            topFace.setRotate(90);
            
//          frontFace
            Rectangle frontFace = new Rectangle(size,size);
            frontFace.setFill(color);
            frontFace.setTranslateX(-0.5*size);
            frontFace.setTranslateY(-0.5*size);
            frontFace.setTranslateZ(-0.5*size);
            
            getChildren().addAll(backFace, bottomFace, rightFace, leftFace, topFace,frontFace);
                
        }
    }
 
 
    public static void main(String[] args) {
        Application.launch(args);
    }
}