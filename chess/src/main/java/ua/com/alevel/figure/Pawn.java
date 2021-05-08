package ua.com.alevel.figure;

import ua.com.alevel.chessBoard.Position;

public class Pawn extends Figure {

    @Override
    public boolean moveValidator(Position start, Position end) {

        if (end.getRow() == start.getRow() & (end.getColumn() > start.getColumn() & end.getColumn() - start.getColumn() < 3)) {
            return true;
        } else {
            return false;
        }
    }
}

