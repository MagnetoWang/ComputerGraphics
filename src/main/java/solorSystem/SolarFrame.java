package solorSystem;
/** 
*
* @ClassName : SolarFrame.java
* @author : Magneto_Wang
* @date  2018年6月26日 下午11:16:37
* @Description  TODO
* 
*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class SolarFrame extends MyFrame {
	private Image iBuffer;
	private Graphics gBuffer;
	SolarFrame(String s) {
		super(s);
	}
 
	private static final long serialVersionUID = 1L;
 
	Image bg = GameUtil.getImage("Picture\\Background_image.jpg");// 背景图片
	Star Sun = new Star("Picture\\sun.jpg", Constant.FRAME_WIDETH / 2, Constant.FRAME_HIGHTH / 2);
	Planet mercury = new Planet(Sun, "Picture\\Mercury.jpg", 110, 50, 0.3);// 水星
	Planet venus = new Planet(Sun, "Picture\\Venus.jpg", 150, 90, 0.4);// 金星
	Planet earth = new Planet(Sun, "Picture\\earth.jpg", 190, 130, 0.1);// 地球
	Planet mars = new Planet(Sun, "Picture\\Mars.jpg", 230, 170, 0.3);// 火星
	Planet jupiter = new Planet(Sun, "Picture\\Jupiter.jpg", 270, 210, 0.4);// 木星
	Planet saturn = new Planet(Sun, "Picture\\Saturn.jpg", 310, 250, 0.3);// 土星
	Planet uranus = new Planet(Sun, "Picture\\Uranus.jpg", 340, 290, 0.2);// 天王星
	Planet neptune = new Planet(Sun, "Picture\\Neptune.jpg", 380,330, 0.1);// 海王星
	Planet moon = new Planet(earth, "Picture\\moon.jpg", 30, 20, 0.2, true);// 月球
 
	public void paint(Graphics g) {
		Color c = g.getColor();
	    g.drawImage(bg,0,0,null);
		Sun.draw(g);
	    mercury.draw(g);
	    venus.draw(g);
	    earth.draw(g);
	    mars.draw(g); 
		jupiter.draw(g); 
	    saturn.draw(g); 
		uranus.draw(g);
	    neptune.draw(g);
	    moon.draw(g);
		this.setVisible(true);
	   g.setColor(c);
	}
	//JAVA双缓冲  ，防止屏幕闪烁
	public void update(Graphics scr)
	{
	    if(iBuffer==null)
	    {
	       iBuffer=createImage(this.getSize().width,this.getSize().height);
	       gBuffer=iBuffer.getGraphics();
	    }
	       gBuffer.setColor(getBackground());
	       gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
	       paint(gBuffer);
	       scr.drawImage(iBuffer,0,0,this);
	}
	public static void main(String[] args) {
		new SolarFrame("太阳系模型").launchFrame();
	}

}
