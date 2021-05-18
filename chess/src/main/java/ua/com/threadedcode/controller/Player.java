package ua.com.threadedcode.controller;

public class Player {
    private Chess chess;
    private String figureColor;

    public String getFigureColor() {
        return figureColor;
    }

    public void setFigureColor(String figureColor) {
        this.figureColor = figureColor;
    }

    @Override
    public String toString() {
        return figureColor;
    }
}
