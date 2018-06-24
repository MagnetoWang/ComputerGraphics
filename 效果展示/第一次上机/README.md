# 计算机图形学
## 第一次上机作业
#### 编程环境

编程语言：java1.8
编译器：eclipse
项目管理：maven
*PS:*
代码已经放在github网站。可以随时查阅代码细节。
网站地址：https://github.com/MagnetoWang/ComputerGraphics

#### 编程说明

为了更好描述DDA算法。我自定义了画板，并且封装了画点函数。很明显斜率绝对值越大，画线效果越好。


#### 一．简单图元的绘制

![BasicShapes](https://github.com/MagnetoWang/ComputerGraphics/blob/master/%E6%95%88%E6%9E%9C%E5%B1%95%E7%A4%BA/%E7%AC%AC%E4%B8%80%E6%AC%A1%E4%B8%8A%E6%9C%BA/BasicShapes.png)

![Paths](https://github.com/MagnetoWang/ComputerGraphics/blob/master/%E6%95%88%E6%9E%9C%E5%B1%95%E7%A4%BA/%E7%AC%AC%E4%B8%80%E6%AC%A1%E4%B8%8A%E6%9C%BA/Paths.png)

####　二．算法模拟题

##### 1.采用中点线算法在屏幕上画一条直线。

算法描述

```java
public void DDA_LINE_MoreThanOne(Point2D Begin,Point2D End){
		double k,d;

		k=(End.getY()-Begin.getY())/(End.getX()-Begin.getX());
		Point2D temp;
		if(k>1.0){
			if(End.getY()<Begin.getY()){
				temp=End;
				End=Begin;
				Begin=temp;
			}
			d=1-0.5*k;
			Point2D p=Begin;

			System.out.println(k);
			for( ;p.getY()<End.getY();  ){
				DrowPoint(p.getX(),p.getY(),10);
				
				if(d>=0){
					p=p.add(10, 0);
					d+=1-k;
				}else{
					d+=1;
				}
				p=p.add(0.0, 10.0);
			}
		}
		
	}
	public void DDA_LINE_BetweenZeroOne(Point2D Begin,Point2D End){
		double k,d;
		k=(End.getY()-Begin.getY())/(End.getX()-Begin.getX());
		Point2D temp;
		if(k<=1.0&&k>=0){
			if(End.getX()<Begin.getX()){
				temp=End;
				End=Begin;
				Begin=temp;
			}
			d=0.5-k;
			Point2D p=Begin;
			System.out.println(k);
			for( ;p.getX()<End.getX();  ){
				DrowPoint(p.getX(),p.getY(),10);
				
				if(d>=0){
					
					d=d-k;
				}else{
					p=p.add(0.0, 10.0);
					d=d+1-k;
				}
				p=p.add(10.0, 0.0);

				
			}
		}
	}
	public void DDA_LINE_BetweenZeroMinusOne(Point2D Begin,Point2D End){
		double k,d;
		k=(End.getY()-Begin.getY())/(End.getX()-Begin.getX());
		Point2D temp;
		if(k<0.0&&k>=-1){
			if(End.getX()<Begin.getX()){
				temp=End;
				End=Begin;
				Begin=temp;
			}
			d=-0.5-k;
			Point2D p=Begin;

			System.out.println(k);
			for( ;p.getX()<End.getX();  ){
				DrowPoint(p.getX(),p.getY(),10);
				
				if(d>0){
					p=p.add(0.0, -10.0);
					d=d-(1+k);
				}else{
					d=d-k;
				}
				p=p.add(10.0, 0.0);
			}
		}
	}

	public void DDA_LINE_LessThanMinusOne(Point2D Begin,Point2D End){
		double k,d;
		k=(End.getY()-Begin.getY())/(End.getX()-Begin.getX());
		Point2D temp;
		if(k<-1){
			if(End.getY()>Begin.getY()){
				temp=End;
				End=Begin;
				Begin=temp;
			}
			d=-1-0.5*k;
			Point2D p=Begin;
			System.out.println(k);
			for( ;p.getY()>End.getY();  ){
				DrowPoint(p.getX(),p.getY(),10);
				
				if(d<0){
					p=p.add(10.0, 0.0);
					d=d-1-k;
				}else{
					
					d=d-1;
				}
				p=p.add(0.0, -10.0);
			}
		}
	}
```

![DDA](https://github.com/MagnetoWang/ComputerGraphics/blob/master/%E6%95%88%E6%9E%9C%E5%B1%95%E7%A4%BA/%E7%AC%AC%E4%B8%80%E6%AC%A1%E4%B8%8A%E6%9C%BA/DDA.png)

![DDA1](https://github.com/MagnetoWang/ComputerGraphics/blob/master/%E6%95%88%E6%9E%9C%E5%B1%95%E7%A4%BA/%E7%AC%AC%E4%B8%80%E6%AC%A1%E4%B8%8A%E6%9C%BA/DDA1.png)
