package 3d;
import com.sun.j3d.utils.geometry.*;

import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;

import javax.vecmath.*;

public class Ball {

public Ball() {

   // Create the universe

   SimpleUniverse universe = new SimpleUniverse();

   // Create a structure to contain objects

   BranchGroup group = new BranchGroup();

   // Create a ball and add it to the group of objects

   Sphere sphere = new Sphere(0.5f);

   group.addChild(sphere);

   // Create a red light that shines for 100m from the origin

   Color3f light1Color = new Color3f(1.8f, 0.1f, 0.1f);

   BoundingSphere bounds =

   new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);

   DirectionalLight light1

      = new DirectionalLight(light1Color, light1Direction);

   light1.setInfluencingBounds(bounds);

   group.addChild(light1);

   // look towards the ball

   universe.getViewingPlatform().setNominalViewingTransform();

   // add the group of objects to the Universe

   universe.addBranchGraph(group);

}

public static void main(String[] args) {

   System.setProperty("sun.awt.noerasebackground", "true");

   new Ball();

}

}