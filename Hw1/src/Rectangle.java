import java.lang.Math;
import java.lang.String;

public class Rectangle extends Figure{
    private int a;
    private int b;

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
        return ("Rectangle, a = " + this.a + " b = " + this.b) + "   Perimeter = " + this.calcPerimeter() + "cm   Area = " + this.calcArea() + "cm^2";
    }


    public int getA(){  return this.a; }
    public int getB(){  return this.b; }

    public void setA(int value){ this.a = value; }
    public void setB(int value){ this.b = value; }
}