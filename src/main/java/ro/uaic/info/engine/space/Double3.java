package ro.uaic.info.engine.space;

public class Double3 {
    private double x;
    private double y;
    private double z;

    public Double3(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Double3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Double3 setY(double y) {
        this.y = y;
        return this;
    }

    public Double3 setX(double x) {
        this.x = x;
        return this;
    }

    public Double3 setZ(double z) {
        this.z = z;
        return this;
    }

    @Override
    public String toString() {
        return "Double3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
