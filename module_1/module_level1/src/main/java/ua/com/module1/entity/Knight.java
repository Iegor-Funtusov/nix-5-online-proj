package ua.com.module1.entity;

public class Knight {
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

    public Knight(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Knight(){
        this.x = (int) Math.round(Math.random() * 15);
        this.y = (int) Math.round(Math.random() * 15);
    }

    public boolean run(int x, int y) {
        int dx = Math.abs(this.x - x);
        int dy = Math.abs(this.y - y);
        if (dx == 1 && dy == 2 || dx == 2 && dy == 1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Knight{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
