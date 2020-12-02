import processing.core.PApplet;
import processing.opengl.*;
import gzf.gui.CameraController;

import igeo.*;
import igeo.io.IRhino3dm.Mesh;

import wblut.math.*;
import wblut.processing.*;
import wblut.hemesh.*;
import wblut.geom.*;


public class  HelloWorld extends PApplet
{
    public static void main(String[] args)
    {
        PApplet.main("HelloWorld");//双引号中间的名字必须和类名一致
    }


    public void settings()  //Processing3中要求窗口的初始化在setting函数中运行，所以size或者fullScreen函数放在这里面，剩下的就完全跟Processing中一致了
    {
//		size(800, 600,IG.GL);
        size(100,100,P3D);

    }

//	CameraController aaa ;

    public void setup()
    {
//		IG.open("test01.3dm");
        HE_Mesh mesh = new HE_Mesh(new HEC_Cube().setEdge(100));
        long key=0;
        HE_Vertex v= mesh.getVertexWithKey(key);
        HE_Face f=  mesh.getFaceWithKey(key);
        HE_Halfedge e=mesh.getEdgeWithKey(key);
        HE_Halfedge he= mesh.getHalfedgeWithKey(key);
        println("# vertices: "+mesh.getNumberOfVertices());

        v= mesh.getVertexWithIndex(0);
        println("vertex neighbors of vertex:"+v);
        HE_VertexVertexCirculator vvCrc = v.vvCrc();
        HE_Vertex vneighbor;
        while(vvCrc.hasNext()) {
            vneighbor = vvCrc.next();
            println(vneighbor);
        }
    }

    public void draw()
    {
//		ellipse(mouseX,mouseY,100,100);



    }
}

