package ua.com.Pieces;

public class Bishop extends Piece{

    public Bishop(String s, boolean color) {
        super(s, color);
    }

    @Override
    public boolean availableToAlloc(int x, int y) {
        if (!this.isPresent){
            this.isPresent = true;
            return true;
        } else {
            return Math.abs(this.x - x) == Math.abs(this.y - y);
        }
    }
}
