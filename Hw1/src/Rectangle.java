import java.lang.Math;
import java.lang.String;
import java.text.DecimalFormat;

public class Rectangle extends Figure{
    private int a;
    private int b;

    @Override
    public String calcPerimeter() {
        return new DecimalFormat("#.##").format(2 * (this.a + this.b));
    }

    @Override
    public String calcArea() {
        return new DecimalFormat("#.##").format(a * b);
    }

    @Override
    public String toString(){
        return ("Rectangle, a = " + this.a + " b = " + this.b) + "   Perimeter = " + this.calcPerimeter() + "cm   Area = " + this.calcArea() + "cm^2";
    }

    @Override
    public void printInfo(){
        System.out.println("Rectangle, id: " + this.getId() + " a = " + this.a + "cm b = " + this.b + "cm");
    }


    public int getA(){  return this.a; }
    public int getB(){  return this.b; }

    public void setA(int value){ this.a = value; }
    public void setB(int value){ this.b = value; }
}