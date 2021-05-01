package com.nixsolutions.courses;

public class Square {

    private Figure figure;
    private final int x;
    private final int y;

    public Square(Figure figure, int x, int y) {
        this.figure = figure;
        this.x = x;
        this.y = y;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
