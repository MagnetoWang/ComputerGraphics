package Curve;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.*;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import java.awt.GraphicsConfiguration;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.j3d.*;
import javax.vecmath.*;

/**
 * !Diese Klasse wurde f�r das Laden �ber ein JAR-Archiv
 * oder Applet welches ein JAR - Archiv nutzt angepasst
 * Um das Programm als einfache Applikation �ber einen class-File
 * laufen zu lassen bitte auf den Code zum Einladen der OBJ Datei
 * im Tutorial zur�ckgreifen! 
 */
public class Phong extends Applet{

	/**
	 * init Methoden f�r die Darstellung als Applet
	 */
	public void init(){
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		canvas3D = new Canvas3D( config );
		add("Center", canvas3D);
		BranchGroup szene = macheSzene();
		szene.compile();
		universe = new SimpleUniverse( canvas3D );
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(szene);
	}

	/**
	 * Erstellt den Szenegraphen
	 * @return BranchGroup
	 */
	public BranchGroup macheSzene(){
		BranchGroup objWurzel = new BranchGroup();
		// Transformation, 2 Rotationen:
		Transform3D drehung = new Transform3D();
		Transform3D drehung2 = new Transform3D();
		drehung.rotX(Math.PI / 4.0d);
		drehung2.rotY(Math.PI / 5.0d);
		drehung.mul(drehung2);
		TransformGroup objDreh =
						new TransformGroup(drehung);
		//Loader
		ObjectFile file = new ObjectFile (ObjectFile.RESIZE);
		Scene scene = null;
		try {
			scene = file.load(ClassLoader.getSystemResource("src/resourse/teapot.obj"));
		} catch (FileNotFoundException | IncorrectFormatException | ParsingErrorException e) {
			
			e.printStackTrace();
		}
//		try {
//			// Applet Abfrage
//			if (getCodeBase()!=null){
//				try {
//					// Laden der Obj Datei als Applet mit jar
//					scene = file.load(new URL("jar:"+getCodeBase()+"Phong.jar!/teapot.obj"));
//				} catch (MalformedURLException e1) {
//					e1.printStackTrace();
//				}
//			}
//			// Laden der Obj Datei mittels jar
//			else scene = file.load(ClassLoader.getSystemResource("src/resourse/teapot.obj"));
//			
//		}
//		catch (FileNotFoundException e) {
//			System.err.println(e);
//			System.exit(1);
//		}
//		catch (ParsingErrorException e) {
//			System.err.println(e);
//			System.exit(1);
//		}
//		catch (IncorrectFormatException e) {
//			System.err.println(e);
//			System.exit(1);
//		}
		objDreh.addChild(scene.getSceneGroup());
		
		DirectionalLight d_Licht = new DirectionalLight(
						 new Color3f (1.0f, 1.0f, 1.0f), new Vector3f (-1.0f, -1.0f, -1.0f));
		d_Licht.setInfluencingBounds (new BoundingSphere (new Point3d(0.0d,0.0d,0.0d), 100.0d));
		objDreh.addChild(d_Licht);
		objWurzel.addChild(objDreh);
		return objWurzel;
	}

	/**
	 * gibt speicher frei
	 */
	public void destroy(){
		universe.removeAllLocales();
	}

	public static void main(String[] args) {
		frame = new MainFrame(new Phong(),500,500);
		frame.setTitle("Phong");
	}

	//---- Attribute -----------------------
	private SimpleUniverse universe;
	private Canvas3D canvas3D;
	private static Frame frame;
}
