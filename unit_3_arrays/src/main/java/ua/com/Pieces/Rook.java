package ua.com.Pieces;

public class Rook extends Piece{

    public Rook(String s) {
        super(s);
    }

    @Override
    public boolean availableToAlloc(int x, int y) {
        if (!this.isPresent){
            this.isPresent = true;
            return true;
        } else {
            return Math.abs(this.x - x) > 0 && this.y == y || Math.abs(this.y - y) > 0 && this.x == x;
        }
    }
}
