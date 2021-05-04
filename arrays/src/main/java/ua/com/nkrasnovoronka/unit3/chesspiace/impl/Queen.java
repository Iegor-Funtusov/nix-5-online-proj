package ua.com.nkrasnovoronka.unit3.chesspiace.impl;

import ua.com.nkrasnovoronka.unit3.chesspiace.AbstractFigure;
import ua.com.nkrasnovoronka.unit3.chesspiace.Square;

public class Queen extends AbstractFigure {

    @Override
    public boolean isMoveValid(Square start, Square end) {
        int x = start.getX();
        int y = start.getY();
        int x1 = end.getX();
        int y1 = end.getY();
        return x == x1 || y == y1 || (Math.abs(x - x1) == Math.abs(y - y1));
    }

    @Override
    public String toString() {
        return "Queen " + super.toString();
    }
}
