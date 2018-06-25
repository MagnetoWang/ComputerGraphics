package Color;
/** 
*
* @ClassName : TriangleColor.java
* @author : Magneto_Wang
* @date  2018年6月24日 上午11:05:22
* @Description  画多彩的三角形
* 
*/
import javax.swing.JFrame;
import com.jogamp.opengl.*;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;

public class TriangleColor implements GLEventListener{
	   @Override
	   public void display( GLAutoDrawable drawable ) {
	      final GL2 gl = drawable.getGL().getGL2();
	      gl.glBegin( GL2.GL_TRIANGLES );         // Drawing Using Triangles
	      gl.glColor3f( 1.0f, 0.0f, 0.0f ); //Red
	      gl.glVertex3f( 0.5f,0.7f,0.0f ); // Top
	      gl.glColor3f( 0.0f,1.0f,0.0f ); //blue
	      gl.glVertex3f( -0.2f,-0.50f,0.0f );              // Bottom Left
	      gl.glColor3f( 0.0f,0.0f,1.0f ); //green
	      gl.glVertex3f( 0.5f,-0.5f,0.0f );              //Bottom Right
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
	      //getting the capabilities object of GL2 profile
	      final GLProfile profile = GLProfile.get( GLProfile.GL2 );
	      GLCapabilities capabilities = new GLCapabilities( profile );
	      // The canvas 
	      final GLCanvas glcanvas = new GLCanvas( capabilities );
	      TriangleColor triangle = new TriangleColor();
	      glcanvas.addGLEventListener( triangle );
	      glcanvas.setSize( 400, 400 );
	      //creating frame
	      final JFrame frame = new JFrame (" Colored Triangle");      
	      //adding canvas to it
	      frame.getContentPane().add( glcanvas );
	      frame.setSize( frame.getContentPane().getPreferredSize() );
	      frame.setVisible( true );
	   }//end of main
	}//end of class
