package ua.com.alevel.figure;

import ua.com.alevel.chessBoard.Position;

public abstract class Figure {

    private boolean white = false;
    private Position position;

    public boolean isWhite() {
        return this.white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public abstract boolean moveValidator(Position start, Position end);

    public Position getPosition() {
        return position;}


    public void setPosition(Position position) {
        this.position = position;
    }
}