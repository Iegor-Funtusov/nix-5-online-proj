import java.lang.Math;
import java.lang.String;

public class Circle extends Figure{
    private int r;

    public Circle(){
        this.r = 0;
    }

    public Circle(int radius) {
        this.r = radius;
    }

    @Override
    public double calcPerimeter(){
        return (2 * Math.PI * this.r);
    }

    @Override
    public double calcArea(){
        return (Math.PI * this.r * this.r);
    }

    @Override
    public String toString(){
        return ("Circle, radius = " + this.r);
    }

    public int getR(){  return this.r; }

    public void setR(int value){ this.r = value; }
}