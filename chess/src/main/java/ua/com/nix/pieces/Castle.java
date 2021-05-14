package ua.com.nix.pieces;

import ua.com.nix.board.ChessBoard;
import ua.com.nix.board.Position;

public class Castle extends AbstractPiece {

    private static final char CONSOLE_REPRESENTATION = 'C';

    public Castle(boolean isWhite) {
        super(isWhite);
    }

    public boolean isMoveValid(Position newPosition, ChessBoard board){
        Position currentPos = this.getPosition();
        if(!board.isSpaceEmpty(newPosition.getRow(), newPosition.getColumn()))
            return false;
        if(currentPos.getColumn() == newPosition.getColumn())
            return true;
        if(currentPos.getRow() == newPosition.getRow())
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
