package ua.com.alevel.figure;

import ua.com.alevel.chessBoard.Position;

public class Rook extends Figure{

    @Override
    public boolean moveValidator(Position start, Position end) {
        return start.getRow() == end.getRow() || start.getColumn() == end.getColumn();
    }
}