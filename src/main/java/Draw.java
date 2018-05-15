
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Draw {
	public static void main(String[] args) {
		new MainFrame();
	}
}

@SuppressWarnings("serial")
class MainFrame extends JFrame implements ActionListener {
	JPanel pane = new JPanel();
	// JLabel zuobiao;
	JTextField T_x0, T_y0, T_x1, T_y1;
	JButton Draw, Show;
	JLabel L_x0, L_y0, L_x1, L_y1;
	int x0, y0, x1, y1;

	MainFrame() {
		super("DrawLine Window");
		Container con = this.getContentPane();
		con.setLayout(null);

		pane.setBounds(20, 20, 850, 550);
		pane.setBackground(new Color(100, 156, 200));
		con.add(pane);

		L_x0 = new JLabel("X0");
		L_x0.setBounds(230, 580, 30, 20);
		con.add(L_x0);

		L_x1 = new JLabel("X1");
		L_x1.setBounds(370, 580, 30, 20);
		con.add(L_x1);

		L_y0 = new JLabel("Y0");
		L_y0.setBounds(230, 630, 30, 20);
		con.add(L_y0);

		L_y1 = new JLabel("Y1");
		L_y1.setBounds(370, 630, 30, 20);
		con.add(L_y1);

		T_x0 = new JTextField();
		T_x0.setBounds(270, 580, 50, 20);
		con.add(T_x0);

		T_y0 = new JTextField();
		T_y0.setBounds(270, 630, 50, 20);
		con.add(T_y0);

		T_x1 = new JTextField();
		T_x1.setBounds(400, 580, 50, 20);
		con.add(T_x1);

		T_y1 = new JTextField();
		T_y1.setBounds(400, 630, 50, 20);
		con.add(T_y1);

		Draw = new JButton("画线");
		Draw.setBounds(550, 580, 90, 30);
		Draw.addActionListener(this);
		con.add(Draw);

		Show = new JButton("展示坐标");
		Show.setBounds(550, 620, 90, 30);
		Show.addActionListener(this);
		con.add(Show);

		this.addWindowListener(new CloseWindow());
		this.setBounds(20, 20, 900, 700);
		this.setVisible(true);
		this.setResizable(false);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Draw) {
			x0 = Integer.parseInt(T_x0.getText().trim());
			y0 = Integer.parseInt(T_y0.getText().trim());
			x1 = Integer.parseInt(T_x1.getText().trim());
			y1 = Integer.parseInt(T_y1.getText().trim());

			float K = (float) (y1 - y0) / (x1 - x0);

			Line line = new Line(this);
			if (K >= 0 && K <= 1)
				line.draw0(x0, y0, x1, y1);
			else if (K > 1)
				line.draw1(x0, y0, x1, y1);
			else if (K <= 0 && K >= -1)
				line.draw2(x0, y0, x1, y1);
			else if (K < -1)
				line.draw3(x0, y0, x1, y1);
		}
		if (e.getSource() == Show) {
			Graphics g1 = this.pane.getGraphics();
			g1.setColor(Color.PINK);
			g1.drawLine(0, 300, 920, 300);// ----x---
			g1.drawLine(410, 0, 410, 720);// ----y---
			g1.dispose();
		}

	}/* method actionPerformed */
}

class Line {
	MainFrame jb;

	Line(MainFrame jb) {
		this.jb = jb;
	}

	// *************0<K<1*********************************
	public void draw0(int x0, int y0, int x1, int y1)// 0<K<1
	{
		int dx, dy, d, UpIncre, DownIncre, x, y;
		if (x0 > x1) {
			x = x1;
			x1 = x0;
			x0 = x;
			y = y1;
			y1 = y0;
			y0 = y;
		}
		x = x0;
		y = y0;
		dx = x1 - x0;
		dy = y1 - y0;
		d = dx - 2 * dy;
		UpIncre = 2 * dx - 2 * dy;
		DownIncre = -2 * dy;
		while (x <= x1) {
			try {
				Graphics g = jb.pane.getGraphics();
				g.setColor(Color.red);
				g.drawLine(x + 410, y + 300, x + 412, y + 302);
				g.dispose();
				Thread.sleep(30);
			} catch (Exception e) {
			}
			x++;
			if (d < 0) {
				y++;
				d += UpIncre;
			} else
				d += DownIncre;
		}

	}

	// *************0<K<1*********************************
	// ----------K>1------------------------------------
	public void draw1(int x0, int y0, int x1, int y1)// K>1
	{
		int dx, dy, d, UpIncre, DownIncre, x, y;
		if (x0 > x1) {
			x = x1;
			x1 = x0;
			x0 = x;
			y = y1;
			y1 = y0;
			y0 = y;
		}
		x = x0;
		y = y0;
		dx = x1 - x0;
		dy = y1 - y0;
		d = 2 * dx - dy;
		UpIncre = 2 * dx;
		DownIncre = 2 * dx - 2 * dy;
		while (x <= x1) {
			try {
				Graphics g = jb.pane.getGraphics();
				g.setColor(Color.red);
				g.drawLine(x + 410, y + 300, x + 412, y + 302);
				g.dispose();
				Thread.sleep(30);
			} catch (Exception e) {
			}
			y++;
			if (d >= 0) {
				x++;
				d += DownIncre;
			} else
				d += UpIncre;
		}
	}

	// ----------K>1------------------------------------
	// ***********-1<k<0*************************************
	public void draw2(int x0, int y0, int x1, int y1)// -1<K<0
	{
		int dx, dy, d, UpIncre, DownIncre, x, y;
		if (x0 > x1) {
			x = x1;
			x1 = x0;
			x0 = x;
			y = y1;
			y1 = y0;
			y0 = y;
		}
		x = x0;
		y = y0;
		dx = x1 - x0;
		dy = y1 - y0;
		d = -dx - 2 * dy;
		UpIncre = -2 * dy;
		DownIncre = -2 * dx - 2 * dy;
		while (x <= x1) {
			try {
				Graphics g = jb.pane.getGraphics();
				g.setColor(Color.red);
				g.drawLine(x + 410, y + 300, x + 412, y + 302);
				g.dispose();
				Thread.sleep(30);
			} catch (Exception e) {
			}
			x++;
			if (d >= 0) {
				y--;
				d += DownIncre;

			} else
				d += UpIncre;
		}
	}

	// ***********-1<k<0*************************************
	// ------------K>-1--------------------------------------
	public void draw3(int x0, int y0, int x1, int y1)// K<-1
	{
		int dx, dy, d, UpIncre, DownIncre, x, y;
		if (x0 > x1) {
			x = x1;
			x1 = x0;
			x0 = x;
			y = y1;
			y1 = y0;
			y0 = y;
		}
		x = x0;
		y = y0;
		dx = x1 - x0;
		dy = y1 - y0;
		d = -2 * dx - dy;
		UpIncre = -2 * dx;
		DownIncre = -2 * dx - 2 * dy;
		while (x <= x1) {
			try {
				Graphics g = jb.pane.getGraphics();
				g.setColor(Color.red);
				g.drawLine(x + 410, y + 300, x + 412, y + 302);
				g.dispose();
				Thread.sleep(30);
			} catch (Exception e) {
			}
			y--;
			if (d < 0) {
				x++;
				d += DownIncre;
			} else
				d += UpIncre;
		}
	}
	// ------------K>-1----------------------------------
}

class CloseWindow extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}