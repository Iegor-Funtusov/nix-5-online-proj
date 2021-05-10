package ua.com.Pieces;

public class King extends Piece {

    public King(String s, boolean color) {
        super(s,color);
    }

    @Override
    public boolean availableToAlloc(int x, int y) {
        if (!this.isPresent){
            this.isPresent = true;
            return true;
        } else {
            return Math.abs(this.x - x ) == 1 || Math.abs(this.y - y) == 1;
        }
    }
}
