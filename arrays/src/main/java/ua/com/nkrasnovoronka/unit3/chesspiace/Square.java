package ua.com.nkrasnovoronka.unit3.chesspiace;

import java.util.Locale;

public class Square {
    private AbstractFigure figure;
    private String chessPosition;
    private int x;
    private int y;

    public Square(String chessPosition) {
        if(chessPosition.length() != 2){
            throw new IllegalArgumentException("Invalid chess position");
        }
        this.chessPosition = chessPosition;
        //convert chess coordinates to x y
        this.x = chessPosition.toLowerCase(Locale.ROOT).charAt(0) - 'a';
        this.y = chessPosition.toLowerCase(Locale.ROOT).charAt(1) - '1';
    }

    public Square(String chessPosition, AbstractFigure figure) {
        this(chessPosition);
        this.figure = figure;
    }

    public String getChessPosition() {
        return chessPosition;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public AbstractFigure getFigure() {
        return figure;
    }

    public void setFigure(AbstractFigure figure) {
        this.figure = figure;
    }

    @Override
    public String toString() {
        return figure + "";
    }
}
