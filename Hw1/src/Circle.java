import java.lang.Math;
import java.lang.String;

public class Circle extends Figure{
    private int radius;

    @Override
    public double calcPerimeter(){
        return (2 * Math.PI * this.radius);
    }

    @Override
    public double calcArea(){
        return (Math.PI * this.radius * this.radius);
    }

    @Override
    public String toString(){
        return ("Circle, radius = " + this.radius + "   Perimeter = " + this.calcPerimeter() + "cm   Area = " + this.calcArea() + "cm^2");
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Circle c = (Circle) obj;
        if (this.radius != c.radius || this.getId() != c.getId())
            return false;
        return true;
    }

    public int getRadius(){  return this.radius; }

    public void setRadius(int value){ this.radius = value; }
}