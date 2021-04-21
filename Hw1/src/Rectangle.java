import java.lang.Math;
import java.lang.String;

public class Rectangle extends Figure{
    private int a;
    private int b;

    public Rectangle(){
        this.a = 0;
        this.b = 0;
    }

    public Rectangle(int side1, int side2){
        this.a = side1;
        this.b = side2;
    }

    @Override
    public double calcPerimeter() {
        return 2 * (this.a + this.b);
    }

    @Override
    public double calcArea() {
        return a * b;
    }

    public double calcDiagonal(){
        return Math.sqrt(this.a * this.a + this.b * this.b);
    }

    @Override
    public String toString(){
        return ("Rectangle, a = " + this.a + " b = " + this.b);
    }


    public int getA(){  return this.a; }
    public int getB(){  return this.b; }

    public void setA(int value){ this.a = value; }
    public void setB(int value){ this.b = value; }
}