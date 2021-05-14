package ua.com.nix.pieces;

import ua.com.nix.board.ChessBoard;
import ua.com.nix.board.Position;

public class Pawn extends AbstractPiece{

    private static final char CONSOLE_REPRESENTATION = 'P';
    private int moveCount = 0;

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    public boolean isMoveValid(Position newPosition, ChessBoard board){
        Position currentPos = this.getPosition();
        if(!board.isSpaceEmpty(newPosition.getRow(), newPosition.getColumn()))
            return false;
        if(currentPos.getColumn() != newPosition.getColumn())
            return false;
        if(this.isWhite()) {
            if (newPosition.getRow() > currentPos.getRow())
                return false;
        }else if(newPosition.getRow() < currentPos.getRow())
            return false;
        int rowDifference = Math.abs(currentPos.getRow() - newPosition.getRow());
        if(rowDifference > 2)
            return false;
        else if(rowDifference == 2 && moveCount > 1)
            return false;
        this.moveCount++;
        return true;
    }

    public boolean isPositionValid(ChessBoard board, Position  position){
        if(board.isSpaceEmpty(position.getRow(), position.getColumn()))
            return true;
        return false;
    }

    public char getRepresentation(){
        return CONSOLE_REPRESENTATION;
    }
}
