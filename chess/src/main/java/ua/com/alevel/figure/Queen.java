package ua.com.alevel.figure;

import ua.com.alevel.chessBoard.ChessBoard;
import ua.com.alevel.chessBoard.Position;

public class Queen extends Figure{

    @Override
    public boolean moveValidator(Position start, Position end) {
        return start.getRow() == end.getRow() || start.getColumn() == end.getColumn() ||
                (Math.abs(start.getRow() - end.getRow()) == Math.abs(start.getColumn() - end.getColumn()));
    }
}