package ua.com.nkrasnovoronka.unit3.chesspiace.impl;

import ua.com.nkrasnovoronka.unit3.chesspiace.AbstractFigure;
import ua.com.nkrasnovoronka.unit3.chesspiace.Square;

public class Bishop extends AbstractFigure {

    @Override
    public boolean isMoveValid(Square start, Square end) {
        return (Math.abs(start.getX() - start.getY()) == Math.abs(end.getX() - end.getY())
                || start.getX() + end.getY() == start.getX() + start.getY());
    }

    @Override
    public String toString() {
        return "Bishop " + super.toString();
    }
}
