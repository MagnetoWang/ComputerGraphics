package Curve;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/** 
*
* @ClassName : Bezier.java
* @author : Magneto_Wang
* @date  2018年6月10日 下午6:52:40
* @Description  针对第四次上机作业，更深刻理解bezier曲线的应用
* 构造茶壶，颜色渐变线，以及鼠标填充表格
* 
*/
public class Bezier extends Application{
	//绘画基本工具
    public Group root = new Group();
	public Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	public Scene scene;
	
	//绘画启动函数
    public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        Application.launch(args);
    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("B-spline");
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		
		DrawBSpline(90,primaryStage);


		primaryStage.show();	
	}
    
	/***
	 * @Description 绘制bezier曲线，和b样条曲线。已知曲线控制点，来绘制。
	 * @ReferencesWebsite https://blog.csdn.net/yangtrees/article/details/9026411
	 * https://www.cnblogs.com/caster99/p/4746652.html?utm_source=tuicool
	 * 
	 * @param primaryStage
	 */
	public void BezierAndBSimple(Stage primaryStage){
		
	}
	/***
	 * P1(0,0,0)、P2(1,1,1)、 P3(2,-1,-1)、P4(3,0,0)
	 * @param points 画点的数量
	 * @param primaryStage 画布
	 */
	public void DrawBSpline(int points,Stage primaryStage){
		double ThreeTimes=1.0/6.0;
		double deltaT =1.0/points;
		double[][] functionCoefficient = { {1,4,1,0}, {-3,0,3,0},{3,-6,3,0},{-1,3,-3,1}};
		RealMatrix Coefficient = MatrixUtils.createRealMatrix(functionCoefficient);
		
		double[][] FourPoint ={{0,0,0},
							   {1,1,1},
							   {2,-1,-1},
							   {3,0,0}
							   };
		RealMatrix Points = MatrixUtils.createRealMatrix(FourPoint);
		double t=0;
		double radius=5;
		for(int i=0;i<=points;i++){
			t=i*deltaT;

			double[][] BasicFuntion ={{1,t,t*t,t*t*t}};
			RealMatrix basicFuntion = MatrixUtils.createRealMatrix(BasicFuntion);
			RealMatrix result = basicFuntion.multiply(
					        	Coefficient.multiply(Points))
								.scalarMultiply(ThreeTimes);
			Double T=new Double(t);
			if(T.equals(0d)){
				System.out.println(T);
				System.out.println(result);
			}
			
			if(Math.abs((t-1/3d))<=0.000001){
				System.out.println(T);
				System.out.println(result);
			}
			if(Math.abs((t-2/3d))<=0.000001){
				System.out.println(T);
				System.out.println(result);
			}
			if(T.equals(1d)){
				System.out.println(T);
				System.out.println(result);
			}
			result=result.scalarMultiply(300).scalarAdd(200);
//			System.out.println(result);
			double[][] resultData=result.getData();

			DrawPoint(resultData[0][0]-100,
					  resultData[0][1],
					  resultData[0][2],
					  radius);
			
		}
//		System.out.println(Points);
	}
	public void DrawPoint(double x, double y,double z, double radius) {
		gc.fillOval(x, y, radius, radius);
	}


}
