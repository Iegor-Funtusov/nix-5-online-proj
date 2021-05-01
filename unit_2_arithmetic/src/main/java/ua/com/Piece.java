package ua.com;

public abstract class Piece {

    int x;
    String y;
    String symbol;
    boolean isOnBoard = false;

    public Piece(String sym){
        this.symbol = sym;
    }

    @Override
    public String toString(){
        return this.symbol;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }
    public abstract String move(int x, String y);
    public abstract boolean possibleAllocation(int x, String y);
}
