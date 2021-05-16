package Level1;

public class TriangleArea {
    private Point a;
    private Point b;
    private Point c;

    public TriangleArea(){
        this.a = new Point();
        this.b = new Point();
        this.c = new Point();
    }

    public TriangleArea(int xA, int yA, int xB, int yB, int xC, int yC){
        this.a = new Point(xA, yA);
        this.b = new Point(xB, yB);
        this.c = new Point(xC, yC);
    }


    public double calculateArea(){
        double line1 = calculateSide(a,b);
        double line2 = calculateSide(b,c);
        double line3 = calculateSide(a,c);
        double semiPerimeter = (line1 + line2 + line3) / 2;

        return Math.sqrt(semiPerimeter * (semiPerimeter - line1) * (semiPerimeter - line2) * (semiPerimeter - line3));
    }


    private double calculateSide(Point p1, Point p2){
        double x = (p2.getX() - p1.getX());
        double y = (p2.getY() - p1.getY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public String toString() {
        return  "A (" + a.x + ";" + a.y + ") " +
                "B (" + b.x + ";" + b.y + ") " +
                "C (" + c.x + ";" + c.y + ") ";
    }



    private class Point{
        private int x;
        private int y;

        Point(){
            this.x = (int)(Math.random() * 100);
            this.y = (int)(Math.random() * 100);
        }

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
