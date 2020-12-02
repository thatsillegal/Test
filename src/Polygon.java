import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Vector;

import static processing.core.PApplet.print;
import static processing.core.PApplet.println;

public class Polygon {
    ArrayList<Vec>vs;
    float ori_area=0;

    public Polygon(){
        vs= new ArrayList<Vec>();
    }

    public void rand_poly(int v_num,Vec center,float min,float max){
        float angle =(float) (Math.PI*2/v_num);
        for(int i=0;i<v_num;i++){
            float tmp_angle = i*angle;
            float r = (float) (Math.random()*(max-min)+min);
            float tx=(float)(r*Math.cos(tmp_angle));
            float ty=(float)(r*Math.sin(tmp_angle));
            Vec tv=new Vec(tx,ty);
            tv.add(center);
            vs.add(tv);
        }
        ori_area = get_area();
        min_angle=this.sum_opti_angle();
    }

    public int get_edge_num(){
        return this.vs.size();
    }

    public float get_area(){
        int len =vs.size();
        float area=0;
        for(int i=0;i<len;i++){
            int n=(i+1)%len;
            Vec cv=vs.get(i);
            Vec nv=vs.get(n);
            area+=(cv.y+nv.y)*(nv.x-cv.y);
        }
        return (float) Math.abs(area/2);
    }

    public float get_angle(int i){
        int p = (i-1+vs.size())%vs.size();
        int n=(i+1)%vs.size();


        Vec p_v=vs.get(p);
        Vec c_v=vs.get(i);
        Vec n_v=vs.get(n);

        Vec p_vec = p_v.sub(c_v);
        Vec n_vec= n_v.sub(c_v);
        return p_vec.angle(n_vec);
    }

    public void opti_shape(){
        int len=vs.size();
        for(int i=0;i<len;i++){
            Vec c_v = vs.get(i);
            float x =c_v.x;
            float y = c_v.y;
            c_v.set((float)(c_v.x+Math.random()*2-1),(float)(c_v.y+Math.random()*2-1));
            if(!opti_angle()||!opti_area(0.8f,1.2f)){//只要有一个不满足，就附原来的值
                c_v.set(x,y);
            }
        }
    }

    private float min_angle;
    private boolean opti_angle(){
        float tmp_angle = sum_opti_angle();
        if(tmp_angle < min_angle){
            min_angle = tmp_angle;
            return true;
        }else{
            return false;
        }
    }

    private boolean opti_area(float min_ratio,float max_ratio){
        float tmp_area=this.get_area();
        if(tmp_area>ori_area*min_ratio && tmp_area<ori_area*max_ratio){
            return true;
        }else{
            return false;
        }
    }

    private float sum_opti_angle(){//这个函数本质就是一个分段函数
        float sum_angle =0;
        for(int i=0;i<vs.size();i++){
            float angle=this.get_angle(i);
            if(angle<Math.PI/4){
                sum_angle +=-angle+1;
            }else{
                sum_angle+=Math.abs(Math.cos(angle));
            }
        }
        return sum_angle;
    }

    public void draw(PApplet app){
        app.pushStyle();
        app.stroke(0);
        app.noFill();
        app.beginShape();
        for(Vec v:vs){
            app.vertex(v.x,v.y);
        }
        app.endShape(app.CLOSE);
        app.popStyle();
//        app.println(app.width/2);
    }


}
