import processing.core.PApplet;

public class youhua extends PApplet {

    Polygon poly;

    public static void main(String[] args) {
        PApplet.main("youhua");
    }

    public void settings() {
        size(800,800);
    }


    public void setup() {
//        noLoop();
        poly = new Polygon();
        poly.rand_poly(10,new Vec(width/2,height/2),200,400);
    }


    public void draw() {
        background(255);
        poly.opti_shape();
        poly.draw(this);//this 指的就是PApplet
    }


}
