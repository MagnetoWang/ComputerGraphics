package solorSystem;
/** 
*
* @ClassName : Planet.java
* @author : Magneto_Wang
* @date  2018年6月26日 下午11:07:59
* @Description  TODO
* 
*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
public class Planet  extends Star{
	 
	double longAxis;// 长轴
	double shortAxis;// 短轴
	double speed;// 速度
	double degree;// 角度
	Star center;// 绕着那个星球飞
	boolean satellite;//是否是行星，默认是，true表示不是行星
	private double x,y;
 
	public Planet(String Imgpath, double longAxis, double shortAxis, double speed, double degree, Star center) {
 
		this.longAxis = longAxis;
		this.shortAxis = shortAxis;
		this.speed = speed;
		this.degree = degree;
		this.center = center;
	}
 
	public Planet() {
 
	}
 
	public Planet(Star center, String imgpath, double longAxis, double shortAxis, double speed) {

		this.center = center;
		this.y = center.y;
		this.x = center.x + longAxis;
		
 
		this.longAxis = longAxis;
		this.shortAxis = shortAxis;
		this.speed = speed;
 
		
		/*this.width = img.getWidth(null); this.height = img.getHeight(null);*/
		 
	}
 
	public Planet(Star center, String imgpath, double longAxis, double shortAxis, double speed, boolean satellite) {
 
		this(center, imgpath, longAxis, shortAxis, speed);
		this.satellite = satellite;
	}
 
	public void draw(Graphics g) {
		super.draw(g);
		starmove();
		if (!satellite) {
			this.drawTrace(g);
		}
	}
 
	public Planet(Image img, double x, double y) {
		super(img, x, y);
	}
 
	public Planet(String imgpath, double x, double y) {
		super(imgpath, x, y);
	}
 
	private void drawTrace(Graphics g) {
		double ovalX, ovalY, ovalWidth, ovalHeight;
		ovalWidth = longAxis * 2;
		ovalHeight = shortAxis * 2;
		ovalX = (center.x + center.width / 2) - longAxis;
		ovalY = (center.y + center.height / 2) - shortAxis;
		Color c = g.getColor();
		g.setColor(Color.BLUE);
		g.drawOval((int) ovalX, (int) ovalY, (int) ovalWidth, (int) ovalHeight);
		g.setColor(c);
	}
 
	public void starmove() {
		// 沿着椭圆轨迹飞行
		x = center.x + center.width / 2 + longAxis * Math.cos(degree);
		y = center.y + center.height / 2 + shortAxis * Math.sin(degree);
		degree += speed;
	}


}
