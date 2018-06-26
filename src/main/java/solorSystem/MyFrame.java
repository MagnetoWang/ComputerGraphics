package solorSystem;
/** 
*
* @ClassName : MyFrame.java
* @author : Magneto_Wang
* @date  2018年6月26日 下午11:18:03
* @Description  TODO
* 
*/

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {
	private static final long serialVersionUID = 1L;
    protected MyFrame(String s){
    	super(s);
    }
	public void launchFrame() {
		this.setSize(Constant.FRAME_WIDETH, Constant.FRAME_HIGHTH);
		this.setLocation(100, 4);
		this.setVisible(true);
		//设置监听，关闭窗口
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		new FrameThread().start();//开启线程
	}
	/**
	 * 
	 * 重画！！
	 *
	 */
	class FrameThread extends Thread {
		public void run() {
			while (true) {//死循环，不断重画
				repaint();
				try {
					Thread.sleep(40);//间隔40ms画
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
 

}
