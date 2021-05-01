package ua.com.Pieces;

public abstract class Piece {

    int x;
    int y;
    public String symbol;
    public boolean isWhite;
    public boolean isPresent = false;

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public Piece(String s) {
        this.symbol = s;
    }

    @Override
    public String toString() {
        return this.symbol;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public abstract boolean availableToAlloc(int x, int y) throws Exception;
    public abstract void move(int x, int y) throws Exception;
}
