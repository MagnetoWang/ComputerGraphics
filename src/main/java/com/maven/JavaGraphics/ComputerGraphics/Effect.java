/**
 * 
 */
package com.maven.JavaGraphics.ComputerGraphics;
import javafx.application.Application;  
import javafx.collections.ObservableList;  
import javafx.geometry.VPos;  
import javafx.scene.Group;  
import javafx.scene.Node;  
import javafx.scene.Scene;  
import javafx.scene.effect.BlendMode;  
import javafx.scene.effect.Bloom;  
import javafx.scene.effect.BoxBlur;  
import javafx.scene.effect.DropShadow;  
import javafx.scene.effect.GaussianBlur;  
import javafx.scene.effect.Light.Distant;  
import javafx.scene.effect.Lighting;  
import javafx.scene.effect.MotionBlur;  
import javafx.scene.effect.PerspectiveTransform;  
import javafx.scene.effect.Reflection;  
import javafx.scene.paint.Color;  
import javafx.scene.shape.Circle;  
import javafx.scene.shape.Rectangle;  
import javafx.scene.text.Font;  
import javafx.scene.text.FontWeight;  
import javafx.scene.text.Text;  
import javafx.stage.Stage;  

/** 
*
* @ClassName : Effect.java
* @author : Magneto_Wang
* @date  2018年5月7日 下午12:22:52
* @Description  展示一些图像的操作
* 
*/
public class Effect extends Application{


	  
	    Stage stage;  
	    Scene scene;  
	      
	    public static void main(String[] args) {  
	        launch(args);  
	  
	    }  
	  
	    @Override  
	    public void start(Stage stage) throws Exception {  
	        stage.show();  
	        scene = new Scene(new Group(), 650, 300);  
	        ObservableList<Node> content = ((Group)scene.getRoot()).getChildren();  
	          
	        content.add(blendMode());  
	        content.add(bloom());  
	        content.add(boxBlur());  
	        content.add(motionBlur());  
	        content.add(gaussianBlur());  
	        content.add(dropShadow());  
	        content.add(reflection());  
	        content.add(lighting());  
	        content.add(perspective());  
	        content.add(chainEffects());  
	          
	        stage.setScene(scene);  
	    }  
	  
	    static Node blendMode(){  
	        Rectangle rect = new Rectangle();  
	          
	        rect.setX(590);  
	        rect.setY(50);  
	        rect.setWidth(50);  
	        rect.setHeight(50);  
	        rect.setFill(Color.BLUE);  
	          
	        Circle c = new Circle();  
	        c.setFill(Color.rgb(255, 0, 0, 0.5f));  
	        c.setCenterX(590);  
	        c.setCenterY(50);  
	        c.setRadius(25);  
	          
	        Group g = new Group();  
	        g.setBlendMode(BlendMode.MULTIPLY);//blendMode混合模式//multiply：按比例增大  
	        g.getChildren().add(rect);  
	        g.getChildren().add(c);  
	          
	        return g;  
	    }  
	      
	    static Node bloom(){//bloom：开花、盛开  
	        Group g = new Group();  
	          
	        Rectangle r = new Rectangle();  
	        r.setX(10);  
	        r.setY(10);  
	        r.setWidth(160);  
	        r.setHeight(80);  
	        r.setFill(Color.DARKBLUE);  
	          
	        Text t = new Text();  
	        t.setText("Bloom!");  
	        t.setFill(Color.YELLOW);  
	        t.setFont(Font.font("null", FontWeight.BOLD,36));  
	        t.setX(25);  
	        t.setY(65);  
	          
	        g.setCache(true);//Cache:快取、缓存  
	          
	        Bloom bloom = new Bloom();  
	        bloom.setThreshold(1.0);//threshold:界限,限度   
	          
	        g.setEffect(bloom);  
	        g.setTranslateX(50);  
	          
	        g.getChildren().add(r);  
	        g.getChildren().add(t);  
	        return g;  
	    }  
	      
	    static Node boxBlur(){  
	        Text t = new Text();  
	        t.setText("Blurry Text!");  
	        t.setFill(Color.RED);  
	        t.setFont(Font.font("null", FontWeight.BOLD, 36));  
	        t.setX(10);  
	        t.setY(40);  
	          
	        BoxBlur bb = new BoxBlur();//blur变模糊  
	        bb.setWidth(5);  
	        bb.setHeight(5);  
	        bb.setIterations(3);//Iterations:反复、迭代  
	          
	        t.setTranslateX(300);  
	        t.setTranslateY(100);  
	        t.setEffect(bb);  
	          
	        return t;  
	    }  
	      
	    static Node motionBlur() {  
	        Text t = new Text();  
	        t.setX(20.0f);  
	        t.setY(80.0f);  
	        t.setText("Motion Blur");  
	        t.setFill(Color.RED);  
	        t.setFont(Font.font("null", FontWeight.BOLD, 60));  
	   
	        MotionBlur mb = new MotionBlur();//motion移动   模糊  
	        mb.setRadius(15.0f);  
	        mb.setAngle(45.0f);  
	   
	        t.setEffect(mb);  
	   
	        t.setTranslateX(300);  
	        t.setTranslateY(150);  
	   
	        return t;  
	    }  
	      
	    static Node gaussianBlur() {//gaussian:高斯  模糊  
	        Text t2 = new Text();  
	        t2.setX(10.0f);  
	        t2.setY(140.0f);  
	        t2.setCache(true);  
	        t2.setText("Gaussian Blur");  
	        t2.setFill(Color.RED);  
	        t2.setFont(Font.font("null", FontWeight.BOLD, 36));  
	        t2.setEffect(new GaussianBlur());  
	        return t2;  
	    }  
	      
	    static Node dropShadow(){  
	        Group g = new Group();  
	          
	        DropShadow ds1 = new DropShadow();  
	        ds1.setOffsetY(4.0f);  
	        ds1.setOffsetX(4.0f);  
	        ds1.setColor(Color.CORAL);  
	   
	        Circle c = new Circle();  
	        c.setEffect(ds1);  
	        c.setCenterX(50.0f);  
	        c.setCenterY(225.0f);  
	        c.setRadius(30.0f);  
	        c.setFill(Color.RED);  
	        c.setCache(true);  
	   
	        g.getChildren().add(c);  
	          
	        return g;  
	    }  
	      
	    static Node reflection() {  
	        Text t = new Text();  
	        t.setX(10.0f);  
	        t.setY(50.0f);  
	        t.setCache(true);  
	        t.setText("Reflection in JavaFX...");  
	        t.setFill(Color.RED);  
	        t.setFont(Font.font("null", FontWeight.BOLD, 30));  
	   
	        Reflection r = new Reflection();  
	        r.setFraction(0.9);  
	   
	        t.setEffect(r);  
	   
	        t.setTranslateY(400);  
	        return t;  
	    }  
	      
	    static Node lighting() {  
	        Distant light = new Distant();  
	        light.setAzimuth(-135.0f);  
	   
	        Lighting l = new Lighting();  
	        l.setLight(light);  
	        l.setSurfaceScale(5.0f);  
	   
	        Text t = new Text();  
	        t.setText("JavaFX"+"\n"+"Lighting!");  
	        t.setFill(Color.RED);  
	        t.setFont(Font.font("null", FontWeight.BOLD, 70));  
	        t.setX(10.0f);  
	        t.setY(10.0f);  
	        t.setTextOrigin(VPos.TOP);  
	   
	        t.setEffect(l);  
	   
	        t.setTranslateX(350);  
	        t.setTranslateY(320);  
	   
	        return t;  
	    }  
	      
	    static Node perspective() {  
	        Group g = new Group();  
	        PerspectiveTransform pt = new PerspectiveTransform();  
	        pt.setUlx(10.0f);  
	        pt.setUly(10.0f);  
	        pt.setUrx(210.0f);  
	        pt.setUry(40.0f);  
	        pt.setLrx(210.0f);  
	        pt.setLry(60.0f);  
	        pt.setLlx(10.0f);  
	        pt.setLly(90.0f);  
	   
	        g.setEffect(pt);  
	        g.setCache(true);  
	   
	        Rectangle r = new Rectangle();  
	        r.setX(10.0f);  
	        r.setY(10.0f);  
	        r.setWidth(280.0f);  
	        r.setHeight(80.0f);  
	        r.setFill(Color.DARKBLUE);  
	   
	        Text t = new Text();  
	        t.setX(400.0f);  
	        t.setY(465.0f);  
	        t.setText("Perspective");  
	        t.setFill(Color.RED);  
	        t.setFont(Font.font("null", FontWeight.BOLD, 36));  
	   
	        g.getChildren().add(r);  
	        g.getChildren().add(t);  
	        return g;  
	    }  
	      
	static Node chainEffects() {  
	          
	        Rectangle rect = new Rectangle();  
	        rect.setFill(Color.RED);  
	        rect.setWidth(200);  
	        rect.setHeight(100);  
	        rect.setX(60.0f);  
	        rect.setY(550.0f);  
	   
	        DropShadow ds = new DropShadow();  
	        ds.setOffsetY(5.0);  
	        ds.setOffsetX(5.0);  
	        ds.setColor(Color.GRAY);  
	          
	          
	        Reflection reflection = new Reflection();  
	   
	        ds.setInput(reflection);      
	        rect.setEffect(ds);  
	   
	        return rect;  
	    }  
	}

