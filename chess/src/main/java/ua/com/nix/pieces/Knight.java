package ua.com.nix.pieces;

import ua.com.nix.board.ChessBoard;
import ua.com.nix.board.Position;

public class Knight extends AbstractPiece {

    private static final char CONSOLE_REPRESENTATION = 'N';

    public Knight(boolean isWhite) {
        super(isWhite);
    }

    public boolean isMoveValid(Position newPosition, ChessBoard board){
        Position currentPos = this.getPosition();
        if(!board.isSpaceEmpty(newPosition.getRow(), newPosition.getColumn()))
            return false;
        if ( Math.abs(currentPos.getRow() - newPosition.getRow()) == 2 &&
                Math.abs(currentPos.getColumn() - newPosition.getColumn()) == 1)
            return true;
        if ( Math.abs(currentPos.getRow() - newPosition.getRow()) == 1 &&
                Math.abs(currentPos.getColumn() - newPosition.getColumn()) == 2)
            return true;
        return false;
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
