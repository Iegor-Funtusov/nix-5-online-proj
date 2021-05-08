package ua.com.alevel.figure;

import ua.com.alevel.chessBoard.Position;

public class King extends Figure {

    @Override
    public boolean moveValidator(Position start, Position end) {

        int x = Math.abs(start.getRow() - end.getRow());
        int y = Math.abs(start.getColumn() - end.getColumn());
        if (x + y == 1) {
            return true;
        }

        return false;
    }
}
