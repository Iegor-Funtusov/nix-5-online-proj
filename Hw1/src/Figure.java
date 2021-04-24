public abstract class Figure{
    private int id;

    Figure(){
        this.id = (int)(Math.random() * 10000000);
    }

    public abstract  void printInfo();
    public abstract String calcPerimeter();
    public abstract String calcArea();

    public int getId(){
        return this.id;
    }
}