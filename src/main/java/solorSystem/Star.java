package solorSystem;

import java.awt.Graphics;
import java.awt.Image;

/** 
	*
	* @ClassName : Star.java
	* @author : Magneto_Wang
	* @date  2018年6月26日 下午11:04:28
	* @Description  TODO
	* 
	*/
	
	
	
	

public class Star {
	public Image img;// 星球的图片
	public double x, y;
	public int width, height;
 
	public Star() {
	}
 
	public Star(Image img, double x, double y) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
 
	public Star(String imgpath, double x, double y) {
		this(GameUtil.getImage(imgpath), x, y);
	}
 
	public Star(Image img) {
		this.img = img;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
 
	public void draw(Graphics g) {
		g.drawImage(img, (int) x, (int) y, null);
	}

}
