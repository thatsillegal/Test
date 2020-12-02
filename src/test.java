import processing.core.PApplet;

public class test extends PApplet {
    Vec fixedPoint;
    Vec movingPoint;

    public static void main(String[] args) {
        PApplet.main("test");
    }

    public void settings() {
        size(1000, 1000);
    }

    public void setup() {
//        background(255);
        fixedPoint= new Vec(700,700);
        movingPoint= new Vec(100,100);
    }

    public void draw(){
//        noLoop();
        background(255);
        update();
        ellipse(fixedPoint.x,fixedPoint.y,8,8);
        ellipse(movingPoint.x,movingPoint.y,4,4);

    }

    public void update(){
        float randx=random(-2,2);
        float randy=random(-2,2);
        float tempx=movingPoint.x;
        float tempy=movingPoint.y;
        float dist1=fixedPoint.distance(movingPoint);
        movingPoint.add(randx,randy);
        float dist2=fixedPoint.distance(movingPoint);
        if(dist1<dist2){
            movingPoint.set(tempx,tempy);
        }
//        println(min(dist2,dist1));
    }

}
