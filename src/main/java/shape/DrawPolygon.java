package shape;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

/** 
*
* @ClassName : DrawPolygon.java
* @author : Magneto_Wang
* @date  2018年6月24日 上午10:52:10
* @Description  画多边形
* 
*/
public class DrawPolygon implements GLEventListener{
	 public static void main( String[] args ) {
//		   System.out.println("begin");
	      //getting the capabilities object of GL2 profile
	      final GLProfile profile = GLProfile.get( GLProfile.GL2 );
	      GLCapabilities capabilities = new GLCapabilities( profile );
	      // The canvas 
	      final GLCanvas glcanvas = new GLCanvas( capabilities );
	      DrawPolygon shape = new DrawPolygon();
	      glcanvas.addGLEventListener( shape );
//	      System.out.println("");
	      glcanvas.setSize(400, 400);
	      //creating frame
	      final JFrame frame = new JFrame( "shape" );
	      //adding canvas to frame
	      frame.getContentPane().add( glcanvas );
	      frame.setSize( frame.getContentPane().getPreferredSize() );
	      frame.setVisible( true );
//	      System.out.println("end ");
	   }//end of main

	 @Override
	   public void display( GLAutoDrawable drawable ) {
		   final GL2 gl = drawable.getGL().getGL2();
		   gl.glBegin(GL2.GL_POLYGON);
		   gl.glVertex3f(0f,0.5f,0f);
		   gl.glVertex3f(-0.5f,0.2f,0f);
		   gl.glVertex3f(-0.5f,-0.2f,0f);
		   gl.glVertex3f(0f,-0.5f,0f);
		   gl.glVertex3f(0f,0.5f,0f);
		   gl.glVertex3f(0.5f,0.2f,0f);
		   gl.glVertex3f(0.5f,-0.2f,0f);
		   gl.glVertex3f(0f,-0.5f,0f);
		   gl.glEnd();
	   }
	   @Override
	   public void dispose( GLAutoDrawable arg0 ) {
	      //method body
	   }
	   @Override
	   public void init( GLAutoDrawable arg0 ) {
	      // method body
	   }
	   @Override
	   public void reshape( GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4 ) {
	      // method body
	   }

}
