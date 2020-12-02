import static processing.core.PApplet.println;

public class Vec {
    //properties
    float x, y, z;

    //construction：创造出来的就是实例（或对象）
    public Vec(float xx, float yy, float zz) {
        this.x = xx;//this：谁调用，this就是谁
        this.y = yy;
        this.z = zz;
    }

    public Vec(float xx, float yy) {
        x = xx;
        y = yy;
    }

    public void  add(Vec q) {

        this.x+=q.x;
        this.y+=q.y;
    }
//下面这个方法就是没有改变movingpoint的值！！！
//    Vec add(float xx,float yy) {
//        return new Vec(this.x + xx, this.y + yy);
//    }

    //    这是对的！！！
    void add(float x, float y) {
        this.x+=x;
        this.y+=y;
    }

    Vec sub(Vec q) {
        return new Vec(x - q.x, y - q.y);
    }

    Vec mean(Vec q) {
        return new Vec((x + q.x) / 2, (y + q.y) / 2);
    }

    Vec scale(float s) {
        return new Vec(x * s, y * s);
    }

    Vec between(Vec p, Vec q, float s) {
        return new Vec(p.x + (q.x - p.x) * s, p.y + (q.y - p.y) * s);
    }

    float distance(Vec q) {
        float dx=this.x-q.x;
        float dy=this.y-q.y;
//        return (float) Math.sqrt(dx*dx+dy*dy);
        return (float) Math.sqrt(Math.pow(q.x - x, 2) + Math.pow(q.y - y, 2));
    }

    float mag() {
        return (float) Math.sqrt(x * x + y * y);
    }

    float dot(Vec q) {
        return x * q.x + y * q.y;
    }

    float angle(Vec q) {
        float dot_=this.dot(q);
        return (float) Math.acos(dot_/ (this.mag() * q.mag()));//计算顺序很重要！！！/和*是平级的
    }

    float _area(float[][] p) {//只针对平面图形
        float area = 0;
        int num = p.length;
        for (int i = 0; i < num; i++) {
            area += (p[i][1] + p[(i + 1) % num][1]) * (p[(i + 1) % num][0] - p[i][0]) * 0.5;
        }
        if (area < 0) area *= -1;
        return area;
    }


    public String info() {
        return "[" + x + "," + y + "," + z + "]";
    }

    public void set(float tempx, float tempy) {
        this.x = tempx;
        this.y = tempy;
    }
}
