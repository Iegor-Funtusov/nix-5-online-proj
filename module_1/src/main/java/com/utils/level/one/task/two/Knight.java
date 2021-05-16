package com.utils.level.one.task.two;

public class Knight {

    private int x;
    private int y;
    public final String symbol = " ♘ ";

    public Knight(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public boolean isValidMove(int x, int y) {
        return Math.abs(this.x - x) == 2 && Math.abs(this.y - y) == 1 ||
                Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 2;

    }

}
