package ua.com.Pieces;

public class Pawn extends Piece {

    public boolean firstStep;

    public Pawn(String s) {
        super(s);
        firstStep = true;
    }

    @Override
    public boolean availableToAlloc(int x, int y) {
        if (!isPresent) {
            this.isPresent = true;
            return true;
        } else {
            if (this.isWhite){
                if (this.y == 1 && firstStep){
                    this.firstStep = false;
                    return y - this.y <= 2 && y - this.y > 0 && this.x == x;
                }
                return y - this.y == 1 && this.x == x;
            } else {
                if (this.y == 6 && this.firstStep){
                    this.firstStep = false;
                    return this.y - y <= 2 && this.y - y > 0 && this.x == x;
                }
                return this.y - y == 1 && this.x == x;
            }
        }
    }
}
