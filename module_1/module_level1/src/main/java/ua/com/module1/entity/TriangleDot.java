package ua.com.module1.entity;

public class TriangleDot {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TriangleDot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TriangleDot() {
        this.x = (int) Math.round(Math.random() * 15);
        this.y = (int) Math.round(Math.random() * 15);
    }
}
