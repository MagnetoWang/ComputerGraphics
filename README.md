# 计算机图形学
## Java版
### 个人重新封装某些函数

maven工程项目

要test和java两类文件

Java文件中的Graphics是草稿类，没有其他功能

相关算法的实现都以算法名称命名类



## 文件说明

### [效果展示](https://github.com/MagnetoWang/ComputerGraphics/tree/master/%E6%95%88%E6%9E%9C%E5%B1%95%E7%A4%BA)[：包含所有必要代码执行的截图](https://github.com/MagnetoWang/ComputerGraphics/tree/master/%E6%95%88%E6%9E%9C%E5%B1%95%E7%A4%BA)





## 图形学课堂笔记整理

### 基本光栅图形学

#### 光栅图形显示器上图形的显示 

- 光栅图形显示器上的图形是由像素构成的，每一个像素可呈现不同的颜色。
- 图形的扫描转换（光栅化）：确定图形的像素集合，然后对像素进行写操作 
- 图形的区域填充：确定图形内部的像素集合，然后对像素进行写操作
- 图形的裁剪：确定一个图形在裁剪窗口内的像素集合，然后对像素进行写操作
- 反走样：至今所画图元有一个共同的问题，即都有锯齿形的边，减轻或除去走样情况的应用技术，称为反走样

#### 直线的扫描转换

- 参考代码实现：https://blog.csdn.net/zhangkaihang/article/details/6917852

- 定义
	- 直线的扫描转换是指在二维栅格上计算位于该直线上或充分靠近它的一串象素坐标(光栅化)，并以此像素集近似替代原连续直线段在屏幕上显示的过程。直线由两个端点坐标确定。
- 要求
	- 观感好,象素分布均匀 
	- 误差小,象素尽可能接近数学理想坐标 
	- 速度快--避免乘除法和浮点数运算
-----
##### 方法一-斜率法

- 假定线的宽度是一个像素
- 直线斜率|k|<=1
- 将 x 坐标每次递增1个单位, 计算对应的 y 值,点亮相应象素

![1531309888005](picture\基本光栅图形学-直线转换扫描1.png)

- 按直线从起点到终点的顺序计算直线与各垂直光栅网格线的交点，然后确定每列象素中与此交点最近的象素。 



如题：已知过端点P0 (x0, y0), P1(x1, y1)的直线段L：$y=kx+B $

- 计算直线斜率 
- ![1531310067483](picture\基本光栅图形学-直线转换扫描2.png)
- 从直线最左端点开始，x 每次递增1个单位，对 $x_i$  计算
- ![1531310132349](C:\Users\Magneto_Wang\Documents\JAVA\ComputerGraphics\picture\基本光栅图形学-直线转换扫描3.png)
- 显示坐标为   ![1531310155576](C:\Users\Magneto_Wang\Documents\JAVA\ComputerGraphics\picture\基本光栅图形学-直线转换扫描4.png)               的象素 



评价：这种方法直观，但效率太低，因为每一步需要用浮点计算一次乘法、一次加法和一次取整运算。 



----

##### 方法二-增量法

通过增量计算去除其中的乘法 

因为

![1531310294419](picture\基本光栅图形学-直线转换扫描5.png)

所以![1531310372355](picture\基本光栅图形学-直线转换扫描6.png)

即x y值可以根据前一点的值推算出来,由此消除了算法中的乘法，且不用算截距。





评价：增量算法：在一个迭代算法中，如果每一步的值是用前一步的值加上一个增量来获得，则称为增量算法。增量算法可以避免乘法运算。


##### 中点线算法（MidPoint） 
- 原理
  - 假定直线斜率0<k<1，且已确定点亮象素点$P（x_p  ,y_p ）$,则下一个与直线最接近的像素可能是其右边的第一个像素E(称为东像素)，也可能是其右上方的第一个像素NE(称为东北像素) 。假设Q是直线与栅格线   $x=x_p+1$ 的交点，M为E和NE的中点。
  - ![1531310683513](picture\基本光栅图形学-中点线算法2.png)
  - 选择E还是NE？
- 当M在Q的下方-> NE离直线更近->取NE; 
- 当M在Q的上方-> E离直线更近->取E;
- 当M与Q重合， E、NE任取一点。



假设直线方程为：$F(x,y)=ax+by+c=0$
则$a=y_0-y_1, b=x_1-x_0, c=x_0y_1-x_1y_0$
由常识知：

![1531310822460](picture\基本光栅图形学-中点线算法3.png)


评价：欲判断中点M点是在Q点上方还是在Q点下方，只需把M代入F(x,y),并检查它的符号。



![1531310879259](picture\基本光栅图形学-中点线算法4.png)

- 若$d>=0 ->M$在直线上方->取E;
- 此时再下一个象素的中点判别式为
$$
d_1=F(x_p+2, y_p+0.5)
       =a(x_p+2)+b(y_p+0.5)+c
     = a(x_p +1)+b(y_p +0.5)+c +a =d+a
$$
- 增量为a



- 若d < 0 - >M在直线下方->取NE;
- 此时再下一个象素的判别式为
$$
     d_2= F(x_p+2, y_p+1.5)
       =a(x_p+2)+b(y_p+1.5)+c
     = a(x_p +1)+b(y_p +0.5)+c +a +b =d+a+b ；
$$
- 增量为a＋b

![1531311477394](picture\基本光栅图形学-中点线算法5.png)





画线从(x0, y0)开始，d的初值
$$
d_0=F(x_0+1, y_0+0.5)= a(x_0 +1)+b(y_0 +0.5)+c
                 = F(x_0, y_0)+a+0.5b = a+0.5b
$$

评价：由于只用d 的符号作判断，为了只包含整数运算, 可以用2d代替d来摆脱小数，提高效率。同理，d1和d2的值也都被乘以2。

##### 算法具体实现

代码来源链接：https://blog.csdn.net/zhangkaihang/article/details/6917852

```c++

#include <tchar.h>
#include <GL/glut.h>
#include <cstdlib>
#include <iostream>
 
using namespace std;
 
//链接必要的库文件
#pragma comment( lib, "opengl32.lib" )	 
#pragma comment( lib, "glu32.lib" )	    
#pragma comment( lib, "glut32.lib" )	 
 
//画点
void print(float x,float y)
{
	glPointSize(2);
	glBegin (GL_POINTS);
	glColor3f (1.0f, 0.0f, 0.0f);
	glVertex2i ((int)x,(int)y);
	glEnd ();
}
 
void MidPointLine(float x0,float y0,float x1,float y1)
{	
	float a,b;
	float k,x,xmax,y,ymin,ymax;
	float d0,d1,d2;
 
	//直线和Y轴平行
	if(x0==x1)
	{
		x=x0;
		ymin=(y0<y1)?y0:y1;
		ymax=(y0>y1)?y0:y1;
		while(ymin<ymax)
		{
			print(x,ymin);
			ymin++;
		}
 
	}
	else
	{
		k=(y1-y0)/(x1-x0);
		xmax=(x0>x1)?x0:x1;
 
		//保证初始化x时取x0和x1中的最小值
		if(x1>x0)
		{
			a=y0-y1;
			b=x1-x0;
			x=x0;
			y=y0;
		}
		else
		{
			a=y1-y0;
			b=x0-x1;
			x=x1;
			y=y1;
		}
 
		//画区域一的直线
		if(k>=0&&k<=1)
		{
			d0=2*a+b;
			d1=2*a+2*b;
			d2=2*a;
			while(x<xmax)
			{
				if(d0<0)
				{
					x++;
					y++;
					d0+=d1;
				}
				else
				{
					x++;
					d0+=d2;
				}
				print(x,y);
			}
		}
				
		//画区域二的直线
		if(k>=-1&&k<0)
		{
			d0=2*a-b;
			d1=2*a-2*b;
			d2=2*a;
			while(x<xmax)
			{
				if(d0<0)
				{
					x++;
					d0+=d2;
				}
				else
				{
					x++;
					y--;
					d0+=d1;
				}
 
				print(x,y);
			}
		}
 
		//画区域三的直线
		if(k<-1)
		{
			d0=2*b-a;
			d1=2*b;
			d2=2*b-2*a;
			while(x<xmax)
			{
				if(d0<0)
				{
					y++;
					d0+=d1;
				}
				else
				{
					x--;
					y++;
					d0+=d2;
				}
				print(x,y);
			}
		}
 
		//画区域四的直线
		if(k>1)
		{
			d0=a+2*b;
			d1=2*b;
			d2=2*a+2*b;
			while(x<xmax)
			{
				if(d0<0)
				{
					y++;
					d0+=d1;
				}
				else
				{
					x++;
					y++;
					d0+=d2;
				}
				print(x,y);
			}
		}
	}
} 
 
void RenderScene(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	
	//画直线
	MidPointLine(150,80,50,50);
	MidPointLine(150,300,50,50);
	MidPointLine(50,50,-150,250);
	MidPointLine(50,50,-150,130);
 
	glFlush();
}
 
void ChangeSize(GLsizei w,GLsizei h)
{
	if(h==0)
	{
		h=1;
	}
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
 
	if(w<=h)
	{
		glOrtho(0.0f,250.0f,0.0f,250.0f*h/w,1.0f,-1.0f);
	}
	else
	{
		glOrtho(0.0f,250.0f*w/h,0.0f,250.0f,1.0f,-1.0f);
	}
 
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
 
}
 
void Init(void)
{
	glClearColor(1.0f,1.0f,1.0f,0.0f);
	glShadeModel(GL_FLAT);
}
 
int _tmain(int argc, _TCHAR* argv[])
{
	glutInitDisplayMode(GLUT_RGB | GLUT_SINGLE);
	glutCreateWindow("中点法画直线");
	Init();
	glutDisplayFunc(RenderScene);
	glutReshapeFunc(ChangeSize);
    glutMainLoop();
	return 0;
 
}
```



#### 图元的区域填充 

- 共享边界如何处理 
- 原则：左闭右开，下闭上开 
- 该规则同样适用于线画矩形和多边形
- 对单个多边形区域，它损失了中心落在右、上边界的像素；
- 在扫描转换折线时，也可采用类似方法解决。

- 多边形的表示方法
	- 顶点表示
		- 用多边形顶点的序列来刻划多边形。直观、几何意义强、占内存少；不能直接用于显示。
	- 点阵表示
		- 用位于多边形内的象素的集合来刻划多边形。失去了许多重要的几何信息；但它是光栅显示系统显示时所需的表示形式，易于面着色。

- 多边形的扫描转换（多边形填充）
	- 把多边形的顶点表示转换为点阵表示，也就是从多边形的给定边界出发，求出位于其内部的各个象素，并给各个对应元素设置相应的灰度和颜色，通常称这种转换为多边形的扫描转换。
- 方法：逐点判断法；扫描线算法；边缘填充法
- 算法原理：逐个判断绘图窗口内的像素，确定是否在多边形区域内，从而求出位于多边形区域内的像素集合。
- 判断点在多边形的内外关系：射线法和累计角度法
	- 射线法
		- 从待判别点v发出射线
		- 求交点个数k
		- k的奇偶性决定了点与多边形的内外关系
	- 累计角度法
		- 从v点向多边形P顶点发出射线，形成有向角$\theta_i$
		- 计算有相交的和，得出结论![1531317654796](picture\基本光栅图形学-图元的区域填充1.png)

评价：逐点判断的算法虽然程序简单，但不可取。原因是速度太慢，主要是由于该算法割断了各象素之间的联系，孤立地考察各象素与多边形的内外关系，使得几十万甚至几百万个象素都要一一判别，每次判别又要多次求交点，需要做大量的乘除运算，花费很多时间。


##### 扫描线算法
- 参考链接：
  - https://www.jianshu.com/p/d9be99077c2b
  - https://blog.csdn.net/zjccoder/article/details/41146259
- 扫描线算法把几何图形在计算机中的顶点表示法转换成点阵表示法。需要注意的是转换成点阵表示法后其实是对多边形进行了填充，而不是只有轮廓。
- 扫描如图：
- ![基本光栅图形学-图元的区域填充2](picture\基本光栅图形学-图元的区域填充2.png)



- 从图中可以看出：
- 一条水平方向的扫描线 
- 一个多边形
- 动作
  - 扫描线从y=0开始向上移动
  - 得到交点
  - 从交点中得到线段
  - 判断线段是否在多边形内部
- 从动作中看
- 难点：如何判断线段是否在内部
- 找规律
- 偶数点一般有内部线段包含
- 对于极值点作特殊处理
- 把所有极值顶点当成两个点，就可以保证扫描线与多边形的交点总是偶数
- 专门处理扫描边的数据结构
- **边表（Edge Table）** 
- ![基本光栅图形学-图元的区域填充3](picture\基本光栅图形学-图元的区域填充3.png)
- **链表成员：**
- ![基本光栅图形学-图元的区域填充4](picture\基本光栅图形学-图元的区域填充4.png)
- **活动边表（Active Edge Table）** 
- ![基本光栅图形学-图元的区域填充5](picture\基本光栅图形学-图元的区域填充5.png)
- **链表成员：**
- ![基本光栅图形学-图元的区域填充6](picture\基本光栅图形学-图元的区域填充6.png)

##### 算法具体实现

代码来源链接：https://www.jianshu.com/p/d9be99077c2b

```C++
#include <iostream>
#include <vector>
#include "GL/glut.h"

using namespace std;

//定义用于边表ET和活动边表AET的通用类Edge
class Edge
{
public:
  int ymax;
  float x;
  float dx;
  Edge* next;
};

//定义用于表示像素点坐标的类Point
class Point
{
public:
  int x;
  int y;
  Point(int x, int y)
  {
    this->x = x;
    this->y = y;
  }
};
/////////////////////请使用对应Demo/////////////////////
//窗体宽高
//Demo1
//const int windowWidth = 18;
//const int windowHeight = 12;
//Demo2
//const int windowWidth = 180;
//const int windowHeight = 120;
//Demo3、Demo4、Demo5
const int windowWidth = 1800;
const int windowHeight = 1200;
//多边形顶点
//Demo1
//vector<Point> vertices = { Point(2, 5), Point(2, 10), Point(9, 6), Point(16, 11), Point(16, 4), Point(12, 2), Point(7, 2) };
//Demo2
//vector<Point> vertices = { Point(20, 50), Point(20, 100), Point(90, 60), Point(160, 110), Point(160, 40), Point(120, 20), Point(70, 20) };
//Demo3 多边形
//vector<Point> vertices = { Point(200, 500), Point(200, 1000), Point(900, 600), Point(1600, 1100), Point(1600, 400), Point(1200, 200), Point(700, 200) };
//Demo4 箭头
//vector<Point> vertices = { Point(395, 887), Point(479, 998), Point(1199, 433), Point(1101, 867), Point(1294, 715), Point(1417, 171), Point(857, 163), Point(668, 314), Point(1111, 321) };
//Demo5 闪电
vector<Point> vertices = { Point(566, 970), Point(685, 1020), Point(754, 683), Point(985, 768), Point(1037, 481), Point(1208, 546), Point(1233, 179), Point(1140, 440), Point(951, 386), Point(899, 662), Point(668, 562) };
//边表
Edge *ET[windowHeight];
//活动边表
Edge *AET;

void init(void)
{
    glClearColor(1.0, 1.0, 1.0, 0.0);
    glMatrixMode(GL_PROJECTION);
    gluOrtho2D(0.0, windowWidth, 0.0, windowHeight);    
}

void polygonScan()
{
  //计算最高点的y坐标
  int maxY = 0;
  for (int i=0; i<vertices.size(); i++)
  {
    if (vertices[i].y > maxY)
    {
      maxY = vertices[i].y;
    }
  }
  //初始化ET和AET
  Edge *pET[windowHeight];
  for (int i=0; i<maxY; i++)
  {
    pET[i] = new Edge();
    pET[i]->next = nullptr;
  }
  AET = new Edge();
  AET->next = nullptr;
  
  //清空显示窗口并设置画点颜色为红色
  glClear(GL_COLOR_BUFFER_BIT);
  glColor3f(1.0, 0.0, 0.0);
  glBegin(GL_POINTS);
  
  //建立边表ET
  for (int i=0; i<vertices.size(); i++)
  {
    //取出当前点1前后相邻的共4个点，点1与点2的连线作为本次循环处理的边，另外两个点点0和点3用于计算奇点
    int x0 = vertices[(i - 1 + vertices.size()) % vertices.size()].x;
    int x1 = vertices[i].x;
    int x2 = vertices[(i + 1) % vertices.size()].x;
    int x3 = vertices[(i + 2) % vertices.size()].x;
    int y0 = vertices[(i - 1 + vertices.size()) % vertices.size()].y;
    int y1 = vertices[i].y;
    int y2 = vertices[(i + 1) % vertices.size()].y;
    int y3 = vertices[(i + 2) % vertices.size()].y;
    //水平线直接舍弃
    if (y1 == y2)
      continue;
    //分别计算下端点y坐标、上端点y坐标、下端点x坐标和斜率倒数
    int ymin = y1 > y2 ? y2 : y1;
    int ymax = y1 > y2 ? y1 : y2;
    float x = y1 > y2 ? x2 : x1;
    float dx = (x1 - x2) * 1.0f / (y1 - y2);
    //奇点特殊处理，若点2->1->0的y坐标单调递减则y1为奇点，若点1->2->3的y坐标单调递减则y2为奇点
    if (((y1 < y2) && (y1 > y0)) || ((y2 < y1) && (y2 > y3)))
    {
      ymin++;
      x += dx;
    }
    //创建新边，插入边表ET
    Edge *p = new Edge();
    p->ymax = ymax;
    p->x = x;
    p->dx = dx;
    p->next = pET[ymin]->next;
    pET[ymin]->next = p;
  }
  
  //扫描线从下往上扫描，y坐标每次加1
  for (int i=0; i<maxY; i++)
  {
    //取出ET中当前扫描行的所有边并按x的递增顺序（若x相等则按dx的递增顺序）插入AET
    while (pET[i]->next)
    {
      //取出ET中当前扫描行表头位置的边
      Edge *pInsert = pET[i]->next;
      Edge *p = AET;
      //在AET中搜索合适的插入位置
      while (p->next)
      {
        if (pInsert->x > p->next->x)
        {
          p = p->next;
          continue;
        }
        if (pInsert->x == p->next->x && pInsert->dx > p->next->dx)
        {
          p = p->next;
          continue;
        }
        //找到位置
        break;
      }
      //将pInsert从ET中删除，并插入AET的当前位置
      pET[i]->next = pInsert->next;
      pInsert->next = p->next;
      p->next = pInsert;
    }
    
    //AET中的边两两配对并填色
    Edge *p = AET;
    while (p->next && p->next->next)
    {
      for (int x = p->next->x; x < p->next->next->x; x++)
      {
        glVertex2i(x, i);
      }
      p = p->next->next;
    }
    
    //删除AET中满足y=ymax的边
    p = AET;
    while (p->next)
    {
      if (p->next->ymax == i)
      {
        Edge *pDelete = p->next;
        p->next = pDelete->next;
        pDelete->next = nullptr;
        delete pDelete;
      }
      else
      {
        p = p->next;
      }
    }
    
    //更新AET中边的x值，进入下一循环
    p = AET;
    while (p->next)
    {
      p->next->x += p->next->dx;
      p = p->next;
    }
        
  }
  glEnd();
  glFlush();
}

int main(int argc, char **argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowPosition(50, 100);
    glutInitWindowSize(windowWidth, windowHeight);
    glutCreateWindow("Polygon Scan Demo");
    init();
    glutDisplayFunc(polygonScan);
    glutMainLoop();
    return 0;
}

```

#### 裁剪

- 参考链接
  - https://blog.csdn.net/zl908760230/article/details/53957800
  - 
- 确定图形中哪些部分落在显示区之内，哪些落在显示区之外,以便只显示落在显示区内的那部分图形。这个选择过程称为裁剪。
- 图形显示前需要
  - 扫描转换
  - 裁剪
  - 扫描转换 ---〉裁剪：算法简单，但效率不高。
  - 裁剪---〉扫描转换：裁剪---〉扫描转换。

#####　直线段裁剪

- 直线段裁剪就是保留给定线段在窗口内的部分 
- ![基本光栅图形学-裁剪-直线段裁剪1](picture\基本光栅图形学-裁剪-直线段裁剪1.png)
- 直线段两个端点在窗口内（线段c）
- 直线段两个端点在窗口外，且与窗口不相交（线段d和e）；
- 直线段两个端点在窗口外，但与窗口相交（线段b）；
- 直线段一个端点在窗口内，一个端点在窗口外（线段a）。



#####  编码裁剪算法（也称Cohen-Sutherland算法）
- 该算法基于以下考虑：每一线段或者整个位于窗口内部，或者被窗口分割而使其中的一部分舍弃。
- 算法步骤
	- 确定一条线段是否整个位于窗口内，若是，则取之。
	- 确定该线段是否整个位于窗口外，若是，则弃之。
	- 若第1、2步判断均不成立，则通过窗口边界所在的直线将线段分成两部分，再对每一部分进行第1、2步的测式。






