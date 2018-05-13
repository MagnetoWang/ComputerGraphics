package com.maven.JavaGraphics.ComputerGraphics;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class BasicShape {

	/** 
	*
	* @ClassName : BasicShape.java
	* @author : Magneto_Wang
	* @date  2018年5月13日 上午11:50:05
	* @Description  TODO
	* 
	*/
	
	public GraphicsContext gc;
	public Stage stage;
	public Canvas canvas;
	public GraphicsContext getGc() {
		return gc;
	}
	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	public void DrawLine(Point2D Begin,Point2D End){
		double k=(End.getY()-Begin.getY())/(End.getX()-Begin.getX());
		System.out.println(k);
		System.out.println(Begin.getX()+" "+Begin.getY());
		
		System.out.println(End.getX()+" "+End.getY());
		if(k>1.0){
			DDA_LINE_MoreThanOne(Begin,End);
		}
		if(k<=1.0&&k>=0.0){
			DDA_LINE_BetweenZeroOne(Begin,End);
		}
		if(k<0.0&&k>=-1){
			DDA_LINE_BetweenZeroMinusOne(Begin,End);
		}
		if(k<-1.0){
			DDA_LINE_LessThanMinusOne(Begin,End);
		}
		
		
		
	}
	public void DDA_LINE_MoreThanOne(Point2D Begin,Point2D End){
		double k,d;

		k=(End.getY()-Begin.getY())/(End.getX()-Begin.getX());
		Point2D temp;
		if(k>1.0){
			if(End.getY()<Begin.getY()){
				temp=End;
				End=Begin;
				Begin=temp;
			}
			d=1-0.5*k;
			Point2D p=Begin;

			System.out.println(k);
			for( ;p.getY()<End.getY();  ){
				DrowPoint(p.getX(),p.getY(),10);
				
				if(d>=0){
					p=p.add(10, 0);
					d+=1-k;
				}else{
					d+=1;
				}
				p=p.add(0.0, 10.0);
//				System.out.println(p.getX()+" "+p.getY());
				
			}
		}
		
	}
	public void DDA_LINE_BetweenZeroOne(Point2D Begin,Point2D End){
		double k,d;
		k=(End.getY()-Begin.getY())/(End.getX()-Begin.getX());
		Point2D temp;
		if(k<=1.0&&k>=0){
			if(End.getX()<Begin.getX()){
				temp=End;
				End=Begin;
				Begin=temp;
			}
			d=0.5-k;
			Point2D p=Begin;
//			System.out.println(Begin.getX()+" "+Begin.getY());
//			
//			System.out.println(End.getX()+" "+End.getY());
			System.out.println(k);
			for( ;p.getX()<End.getX();  ){
				DrowPoint(p.getX(),p.getY(),10);
				
				if(d>=0){
					
					d=d-k;
				}else{
					p=p.add(0.0, 10.0);
					d=d+1-k;
				}
				p=p.add(10.0, 0.0);
//				System.out.println(p.getX()+" "+p.getY());
				
			}
		}
	}
	public void DDA_LINE_BetweenZeroMinusOne(Point2D Begin,Point2D End){
		double k,d;
		k=(End.getY()-Begin.getY())/(End.getX()-Begin.getX());
		Point2D temp;
		if(k<0.0&&k>=-1){
			if(End.getX()<Begin.getX()){
				temp=End;
				End=Begin;
				Begin=temp;
			}
			d=-0.5-k;
			Point2D p=Begin;

			System.out.println(k);
			for( ;p.getX()<End.getX();  ){
				DrowPoint(p.getX(),p.getY(),10);
				
				if(d>0){
					p=p.add(0.0, -10.0);
					d=d-(1+k);
				}else{
					d=d-k;
					
				}
				p=p.add(10.0, 0.0);
//				System.out.println(p.getX()+" "+p.getY());
				
			}
		}
	}

	public void DDA_LINE_LessThanMinusOne(Point2D Begin,Point2D End){
		double k,d;
		k=(End.getY()-Begin.getY())/(End.getX()-Begin.getX());
		Point2D temp;
		if(k<-1){
			if(End.getY()>Begin.getY()){
				temp=End;
				End=Begin;
				Begin=temp;
			}
			d=-1-0.5*k;
			Point2D p=Begin;
			System.out.println(k);
//			System.out.println(d);
//			System.out.println(Begin.getX()+" "+Begin.getY());
//			
//			System.out.println(End.getX()+" "+End.getY());
			for( ;p.getY()>End.getY();  ){
				DrowPoint(p.getX(),p.getY(),10);
				
				if(d<0){
					p=p.add(10.0, 0.0);
					d=d-1-k;
				}else{
					
					d=d-1;
				}
				p=p.add(0.0, -10.0);
//				System.out.println(p.getX()+" "+p.getY());
				
			}
		}
	}
	public void DrowPoint(double x, double y, double radius){
		gc.fillOval(x, y, radius, radius);
	}
	

}
