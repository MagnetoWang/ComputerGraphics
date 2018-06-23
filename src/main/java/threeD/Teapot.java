package threeD;
/** 
*
* @ClassName : Teapot.java
* @author : Magneto_Wang
* @date  2018年6月23日 下午11:06:49
* @Description  画茶壶，可拖动观察
* 
*/

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.pickfast.behaviors.PickRotateBehavior;
import com.sun.j3d.utils.pickfast.behaviors.PickTranslateBehavior;
import com.sun.j3d.utils.pickfast.behaviors.PickZoomBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

import tutorial.threeD.M3DMax3dsfileLoader;



public class Teapot extends Applet {
	BranchGroup scenceBranchGroupRoot =null;
	BoundingSphere bounds=null;
	TransformGroup sceneTG=null;
	TransformGroup objTG=null;
	Transform3D t3d1 =null;
	TransformGroup tg1=null;
	BranchGroup loaderBG =null;


	public Teapot(){
		this.setLayout(new BorderLayout());
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(gc);
		this.add("Center",canvas);
		BranchGroup scence = createScenceGraph(canvas);
		SimpleUniverse u = new SimpleUniverse(canvas);
		u.addBranchGraph(scence);
		u.getViewingPlatform().setNominalViewingTransform();
}

	
	public BranchGroup createScenceGraph(Canvas3D canvas){
	
scenceBranchGroupRoot = new BranchGroup();

	bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);
	sceneTG = new TransformGroup();
	sceneTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	scenceBranchGroupRoot.addChild(sceneTG);


	MouseRotate mouserotate = new MouseRotate();
	mouserotate.setTransformGroup(sceneTG);
	scenceBranchGroupRoot.addChild(mouserotate);
	mouserotate.setSchedulingBounds(bounds);


	MouseWheelZoom mouseWheelZoom = new MouseWheelZoom();
	mouseWheelZoom.setTransformGroup(sceneTG);
	scenceBranchGroupRoot.addChild(mouseWheelZoom);
	mouseWheelZoom.setSchedulingBounds(bounds);


	MouseTranslate mouseTranslate = new MouseTranslate();
	mouseTranslate.setTransformGroup(sceneTG);
	scenceBranchGroupRoot.addChild(mouseTranslate);
	mouseTranslate.setSchedulingBounds(bounds);


	Color3f bgColor = new Color3f(0.0f,0.0f,0.0f);
	Background bg = new Background(bgColor);
	bg.setApplicationBounds(bounds);
	scenceBranchGroupRoot.addChild(bg);


	Color3f light1Color = new Color3f(0.5f,0.5f,0.5f);
	Vector3f light1Direction = new Vector3f(4.0f,-7.0f,-12.0f);
	Color3f light2Color = new Color3f(0.0f,1.0f,0.5f);
	Vector3f light2Direction = new Vector3f(-6.0f,-2.0f,-1.0f);
	Color3f ambientColor = new Color3f(0.0f,0.0f,0.0f);


	AmbientLight ambientLight = new AmbientLight(ambientColor);
	ambientLight.setInfluencingBounds(bounds);
	scenceBranchGroupRoot.addChild(ambientLight);


	DirectionalLight light1 = new DirectionalLight(light1Color,light1Direction);
	light1.setInfluencingBounds(bounds);
	scenceBranchGroupRoot.addChild(light1);

	DirectionalLight light2 = new DirectionalLight(light2Color,light2Direction);
	light2.setInfluencingBounds(bounds);
	scenceBranchGroupRoot.addChild(light2);
	/**
	 * /**
	　　　　* TransformGroup特点：
	　　　　* 1.在三维空间中放置任何形体，灯光，声音都要要到该对象。
	　　　　* 2.该对象用来定义一个通过设置，可以移动、旋转和放大缩小的局部坐标系。
	　　　　* 3.该对象有两个flags,其中ALLOW_TRANSFORM_WRITE用来将最新的数据（即坐标变化后的数据写入到数据结构中），
	　　　　* 允许程序在运行的时候修改该节点上的场景。ALLOW_TRANSFORM_READ用来读取位置变化前的数据，从而进行判断和处理。
	　　　　* 允许程序在运行的时候读取该节点上的场景。
	　　　　* 4.通过设置ALLOW_TRANSFORM_WRITE来使坐标系运动（此时在不要读取值时使用，如读取移动的距离，选旋转的角度，
	　　　　* 缩放的比例等值。若要读取这些值，则要再使用ALLOW_TRANSFORM_READ）
	　　　　* 5.要在程序中通过鼠标，移动、旋转、比例放大所指定的局部坐标系，则需要同时设置ALLOW_TRANSFORM_WRITE和ALLOW_TRANSFORM_READ。
	　　　　* 6.Java虚拟机会为这两个flags创建单独的线程(或者进程)来负责接收场景的反馈，在控制场景，避免了用户不必要的开销。
	　　　　
	 */
	
	objTG = new TransformGroup();
	sceneTG.addChild(objTG);

//	　　　　//定义存放3DMax文件的TransformGroup
	tg1 = new TransformGroup();
//	　　　　/**
//	　　　　* Transform3D的特点
//	　　　　* 1.表示所指定的坐标的坐标变换，如旋转、放大缩小、平移等
//	　　　　* 2.Quat4f(x,y,z,w)的参数介绍：x:x坐标 y:y坐标 z:z坐标 w:物体旋转的角度 如：
//	　　　　* Quat4f(0.0f,1.0f,0.0f,1.57f) 表示物体绕Y轴旋转90度，当Y轴的只越大则旋转的幅度越大。
//	　　　　* 当x,y,z的值不为0时，其值的大小即表示转动幅度的大小，值越大，表示向该方向转动的幅度也越大。
//	　　　　* 3.Transform3D有四个方法rotx（）,roty（）, rotz（）,setTranslation（）.当这四个方法一起使用的时候，只有最后一个有作用。
//	　　　　* 为了能够让rotx,roty, rotz都显示出来，则可以使用setRotation(new Quat4())来代替，
//	　　　　* 此时不论setTranslation（）在setRotation(new Quat4())前还是后，都可以显示出效果
//	　　　　*/
	t3d1 = new Transform3D();
	t3d1.setScale(0.005);//初始化大小

	t3d1.setTranslation(new Vector3f(0.0f,0.0f,-0.4f));
	tg1 = new TransformGroup(t3d1);

//	　　　　//定义3dmax文件以及所有相关材质文件及所在目录。
	String modelDir = "";
	String fileName = "src/resourse/teapot.obj";
	loaderBG = new BranchGroup();
	
	try{
		ObjectFile file = new ObjectFile (ObjectFile.RESIZE);
		Scene loaderScene = file.load(fileName);
		if(loaderScene!=null){
//			loaderBG.addChild((loaderScene).getSceneGroup());
			loaderBG = loaderScene.getSceneGroup();
	
		}
	}catch(Exception e){
		e.printStackTrace();
	}

//	tg1.addChild(loaderBG);	
	objTG.addChild(loaderBG);
//	scenceBranchGroupRoot.addChild(objTG);
	return scenceBranchGroupRoot;

	
	}

	public static void main(String[] args){
	new MainFrame(new Teapot(), 900,720);
	}


	}



