import processing.core.PApplet;

public class Infection extends PApplet {

    float distance_infection=20;
    int population = 4000;
    int time4Infected=1000;
    float activity= 1;
    float [][]persons;

    public static void main(String[] args){
        PApplet.main("Infection");
    }

    public void settings(){
        size(1000,1000);
    }

    public void setup(){
        initial();
    }

    public void draw(){
        background(0);
        move();
        detect_infected();
        draw_agent();
    }

    void detect_infected(){
        for(int i=0;i<population;i++){
            if(persons[i][5]==0){//如果这个人没有被感染
                boolean anybodyInfected = false;
                for(int j =0 ; j<population;j++){
                    if(within_infection(persons[i],persons[j])){
                        if (persons[j][5]!=1){
                            anybodyInfected=true;
                        }
                    }
                }
                if(!anybodyInfected){//未感染，且周围也没有感染者
                    persons[i][6]=-1;
                }
            }else{//如果这个人被感染了
                for(int j=0;j<population;j++){
                    if (within_infection(persons[i],persons[j])){
                        if (persons[j][5]==0){
                            if(persons[j][6]==-1){
                                persons[j][6]=millis();
                            }else{
                                if(millis()-persons[j][6]>time4Infected){
                                    persons[j][5]=1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    void initial(){//用一个一维数组来表示一个人的状况
        persons = new float[population][7];
        for(int i=0;i<population;i++){
            persons[i][0]=i; //第几个人
            persons[i][1]=random(width);//X位置
            persons[i][2]=random(height);//Y位置
//            persons[i][3]=0;
//            persons[i][4]=0;
            persons[i][5]=random(1)>0.001?0:1; //是否被感染,产生0~1的随机数，如果大于0.001，就取0，否则取1；大部人人不会被感染
            persons[i][6]=persons[i][5]==0?-1:millis();//从未接触过感染者，那就是-1.若该家伙已经被感染了，那就把当前的时刻赋值给它
        }
    }

    void move(){
        for(int i=0;i<population;i++){
            float tempX = persons[i][1]+random(-activity,activity);
            float tempY = persons[i][2]+random(-activity,activity);
            //不能移动出屏幕
            if(tempX>0&&tempX<width){
                persons[i][1]=tempX;
            }
            if(tempY>0&&tempY<height){
                persons[i][2]=tempY;
            }
        }
    }

    boolean within_infection(float[]p1,float[]p2){
        float deltaX=p1[1]-p2[1];
        float deltaY=p1[2]-p2[2];
        float dist=sqrt(deltaX*deltaX+deltaY*deltaY);
        return dist<=distance_infection;
    }

    void draw_agent(){
        noStroke();
        for(int i=0;i<population;i++){
            if(persons[i][5]==1){
                fill(255,0,0);
            }else{
                fill(0,255,0);
            }
        ellipse(persons[i][1],persons[i][2],8,8);
        }
    }


}



