package ua.com.Pieces;

public class King extends Piece {

    public King(String s) {
        super(s);
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
