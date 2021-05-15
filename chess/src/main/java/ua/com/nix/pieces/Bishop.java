package ua.com.nix.pieces;

import ua.com.nix.board.ChessBoard;
import ua.com.nix.board.Position;
import ua.com.nix.utils.BoardUtil;

public class Bishop extends AbstractPiece {

    private static final char CONSOLE_REPRESENTATION = 'B';

    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    public boolean isMoveValid(Position newPosition, ChessBoard board){
        Position currentPos = this.getPosition();
        if(!board.isSpaceEmpty(newPosition.getRow(), newPosition.getColumn()))
            return false;
        return (Math.abs(currentPos.getRow() - newPosition.getRow())
                == Math.abs(currentPos.getColumn() - newPosition.getColumn()));
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
