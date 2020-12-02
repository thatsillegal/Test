import processing.core.PApplet;
import processing.core.PVector;

public class vector extends PApplet{
    public static void main(String[] args){
        PApplet.main("vector");
    }

    public void settings(){
        size(1000,1000);
    }

    PVector v1,v2;

    public void setup(){
        noLoop();
        //vector.dot. 可以用一个一维数组表示，比如float[] p = new float[]{10,0};
        //然后自己设定一些计算方法，不一定用processing提供的
        //然后polygon就是不同点的按顺序的集合，所以就是二维数组
        //比如：
        float[]p0=new float[]{100,0};
        float[]p1=new float[]{100,200};
        float[]p2=new float[]{0,200};
        float[]p3=new float[]{0,0};
        float[][]polygon = new float[][]{p0,p1,p2,p3};//二位数组还能这样装载一维数组，没想到啊
        beginShape();
        for(int i=0;i<polygon.length;i++){
            vertex(polygon[i][0],polygon[i][1]);
        }
        endShape(CLOSE);

        //求多边形面积：按顺序，用梯形面积相加即可：


        //processing自带的PVector对象
        v1= new PVector(40,20);
        v2= new PVector(20,-40);
    }

    public void draw(){
        float cosin = v1.dot(v2)/v1.mag()*v2.mag();
        float angle = acos(cosin);
        print("radians="+angle);
        print("degrees="+degrees(angle));


    }

}
