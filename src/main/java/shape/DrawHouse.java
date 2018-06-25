package shape;

import java.util.logging.Logger;

/** 
*
* @ClassName : DrawHouse.java
* @author : Magneto_Wang
* @date  2018年6月24日 上午10:31:12
* @Description  用JOGL画简单的房子图案
* 
*/


import javax.swing.JFrame;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.*;
public class DrawHouse implements GLEventListener{
	   @Override
	   public void display( GLAutoDrawable drawable ) {
	      final GL2 gl = drawable.getGL().getGL2();
	      //drawing top
	      gl.glBegin ( GL2.GL_LINES );
	      gl.glVertex3f( -0.3f, 0.3f, 0 );
	      gl.glVertex3f( 0.3f,0.3f, 0 );
	      gl.glEnd();
	      //drawing bottom
	      gl.glBegin( GL2.GL_LINES );
	      gl.glVertex3f( -0.3f,-0.3f, 0 );
	      gl.glVertex3f( 0.3f,-0.3f, 0 );
	      gl.glEnd();
	      //drawing the right edge
	      gl.glBegin( GL2.GL_LINES );
	      gl.glVertex3f( -0.3f,0.3f, 0 );
	      gl.glVertex3f( -0.3f,-0.3f, 0 );
	      gl.glEnd();
	      //drawing the left edge
	      gl.glBegin( GL2.GL_LINES );
	      gl.glVertex3f( 0.3f,0.3f,0 );
	      gl.glVertex3f( 0.3f,-0.3f,0 );
	      gl.glEnd();
	      //building roof
	      //building lft dia 
	      gl.glBegin( GL2.GL_LINES );
	      gl.glVertex3f( 0f,0.6f, 0 );
	      gl.glVertex3f( -0.3f,0.3f, 0 );
	      gl.glEnd();
	      //building rt  dia 
	      gl.glBegin( GL2.GL_LINES );
	      gl.glVertex3f( 0f,0.6f, 0 );
	      gl.glVertex3f( 0.3f,0.3f, 0 );
	      gl.glEnd();
	      //building door
	      //drawing top
	      gl.glBegin ( GL2.GL_LINES );
	      gl.glVertex3f( -0.05f, 0.05f, 0 );
	      gl.glVertex3f( 0.05f, 0.05f, 0 );
	      gl.glEnd();
	      //drawing the left edge
	      gl.glBegin ( GL2.GL_LINES );
	      gl.glVertex3f( -0.05f, 0.05f, 0 );
	      gl.glVertex3f( -0.05f, -0.3f, 0 );
	      gl.glEnd();
	      //drawing the right edge
	      gl.glBegin ( GL2.GL_LINES );
	      gl.glVertex3f( 0.05f, 0.05f, 0 );
	      gl.glVertex3f( 0.05f, -0.3f, 0 );
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
	   public static void main( String[] args ) {
		   System.out.println("begin");
	      //getting the capabilities object of GL2 profile
	      final GLProfile profile = GLProfile.get( GLProfile.GL2 );
	      GLCapabilities capabilities = new GLCapabilities( profile );
	      // The canvas 
	      final GLCanvas glcanvas = new GLCanvas( capabilities );
	      DrawHouse house = new DrawHouse();
	      glcanvas.addGLEventListener( house );
	      System.out.println("");
	      glcanvas.setSize(400, 400);
	      //creating frame
	      final JFrame frame = new JFrame( "House" );
	      //adding canvas to frame
	      frame.getContentPane().add( glcanvas );
	      frame.setSize( frame.getContentPane().getPreferredSize() );
	      frame.setVisible( true );
	      System.out.println("end ");
	   }//end of main

}
