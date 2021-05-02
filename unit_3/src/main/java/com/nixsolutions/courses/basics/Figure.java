package com.nixsolutions.courses.basics;

public abstract class Figure {

    private boolean isWhite;
    private Square position;

    public Figure() {
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setIsWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public Square getPosition() {
        return position;
    }

    public void setPosition(Square position) {
        this.position = position;
    }

    public abstract boolean validMove(Square from, Square to);
}
