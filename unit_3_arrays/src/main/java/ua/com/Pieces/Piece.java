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

    public Piece(String s, boolean color) {
        this.isWhite = color;
        this.symbol = s;
    }

    @Override
    public String toString() {
        return this.symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getSymbol() {
        return symbol;
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

    public abstract boolean availableToAlloc(int x, int y);

    public void move(int x, int y) throws Exception {
        if (availableToAlloc(x, y)) {
            this.setX(x);
            this.setY(y);
        } else {
            throw new Exception("The piece can't be placed right here");
        }
    }
}
