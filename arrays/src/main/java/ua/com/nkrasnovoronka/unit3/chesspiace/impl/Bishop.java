package ua.com.nkrasnovoronka.unit3.chesspiace.impl;

import ua.com.nkrasnovoronka.unit3.chesspiace.ChessPiece;
import ua.com.nkrasnovoronka.unit3.chesspiace.Color;

public class Bishop implements ChessPiece {
    private Color color;
    @Override
    public void move(int position) {


    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bishop{" +
                "color=" + color +
                '}';
    }
}
