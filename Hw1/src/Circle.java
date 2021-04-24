import java.lang.Math;
import java.lang.String;
import java.text.DecimalFormat;

public class Circle extends Figure{
    private int radius;

    @Override
    public String calcPerimeter(){
        return new DecimalFormat("#.##").format(2 * Math.PI * this.radius);
    }

    @Override
    public String calcArea(){
        return new DecimalFormat("#.##").format(Math.PI * this.radius * this.radius);
    }

    @Override
    public String toString(){
        return ("Circle, radius = " + this.radius + "cm   Perimeter = " + this.calcPerimeter() + "cm   Area = " + this.calcArea() + "cm^2");
    }

    @Override
    public void printInfo(){
        System.out.println("Circle, id: " + this.getId() + " radius = " + this.radius + "cm");
    }

    public int getRadius(){  return this.radius; }

    public void setRadius(int value){ this.radius = value; }
}