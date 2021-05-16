package com.k4rnaj1k.level1.task2;

public class Knight{
    private int x;
    private int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    boolean placed = false;

    public boolean placeKnight(int x, int y){
        if(!placed){
            setX(x);
            setY(y);
            return placed = true;
        }else if((Math.abs(this.x - x) == 2 && Math.abs(this.y - y) == 1) || (Math.abs(this.x-x) == 1 && Math.abs(this.y-y) == 2)) {
            setY(y);
            setX(x);
            return true;
        }
        else return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
