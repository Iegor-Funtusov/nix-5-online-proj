package ua.com.alevel.chessBoard;

import ua.com.alevel.figure.Figure;

public class Position {
    private Figure figure;
    private int row;
    private int column;

    public Position(int row, int column, Figure figure) {
        this.setFigure(figure);
        this.setRow(row);
        this.setColumn(column);
    }

    public Figure getFigure() {
        return this.figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
