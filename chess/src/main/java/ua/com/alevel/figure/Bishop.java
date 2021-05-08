package ua.com.alevel.figure;

import ua.com.alevel.chessBoard.Position;

public class Bishop extends Figure {

    @Override
    public boolean moveValidator(Position start, Position end) {
        if (Math.max(end.getRow(), start.getRow()) - Math.min(end.getRow(), start.getRow()) ==
                Math.max(end.getColumn(), start.getColumn()) - Math.min(end.getColumn(), start.getColumn())) {
            return true;
        } else {
            return false;
        }
    }
}
