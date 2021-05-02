package com.nixsolutions.courses.basics;

public class Square {

    private final int x;
    private final int y;
    private boolean isWhite;

    public Square(int x, int y, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }
}
