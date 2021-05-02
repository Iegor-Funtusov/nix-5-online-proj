package entities;

public abstract class Piece {

    public static final String WHITE = "\u001B[30m";
    public static final String BLACK = "\u001B[0m";

    public char type;
    public int x;
    public int y;
    public String colour;

    public abstract boolean move(int x, int y);

    public char getType(){
        return type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(int colour) {

        if(colour == 1){
            this.colour = WHITE;
        }
        if(colour == 2) {
            this.colour = BLACK;
        }
    }
}