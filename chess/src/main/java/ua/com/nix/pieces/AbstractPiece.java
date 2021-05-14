package ua.com.nix.pieces;

import lombok.Getter;
import lombok.Setter;
import ua.com.nix.board.ChessBoard;
import ua.com.nix.board.Position;

@Getter
@Setter
public abstract class AbstractPiece {

    private Position position;
    private boolean isWhite;

    public AbstractPiece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public abstract boolean isMoveValid(Position newPosition, ChessBoard board);

    public abstract char getRepresentation();

    public abstract boolean isPositionValid(ChessBoard board, Position  position);
}
