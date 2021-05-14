package ua.com.nix.pieces;

import ua.com.nix.board.ChessBoard;
import ua.com.nix.board.Position;

public class Queen extends AbstractPiece{

    public static final char CONSOLE_REPRESENTATION = 'Q';

    public Queen(boolean isWhite) {
        super(isWhite);
    }

    public boolean isMoveValid(Position newPosition, ChessBoard board){
        Position currentPos = this.getPosition();
        if (!board.isSpaceEmpty(newPosition.getRow(), newPosition.getColumn()))
            return false;
        if (Math.abs(currentPos.getRow() - newPosition.getRow())
                == Math.abs(currentPos.getColumn() - newPosition.getColumn()))
            return true;
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
