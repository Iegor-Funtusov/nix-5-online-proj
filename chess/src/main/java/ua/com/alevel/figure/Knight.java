package ua.com.alevel.figure;

import ua.com.alevel.chessBoard.ChessBoard;
import ua.com.alevel.chessBoard.Position;

public class Knight extends Figure {

    @Override
    public boolean moveValidator(Position start, Position end) {
        if (end.getFigure().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getRow() - end.getRow());
        int y = Math.abs(start.getColumn() - end.getColumn());
        return x * y == 2;
    }
}
