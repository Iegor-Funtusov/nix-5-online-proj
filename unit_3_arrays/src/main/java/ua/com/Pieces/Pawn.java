package ua.com.Pieces;

public class Pawn extends Piece {

    public boolean firstStep;

    public Pawn(String s, boolean color) {
        super(s, color);
        firstStep = true;
    }

    @Override
    public boolean availableToAlloc(int x, int y) {
        if (!isPresent) {
            this.isPresent = true;
            return true;
        } else {
            if (this.isWhite){
                if (this.x == 1 && firstStep){
                    this.firstStep = false;
                    return x - this.x <= 2 && x - this.x > 0 && this.y == y;
                }
                return x - this.x == 1 && this.y == y;
            } else {
                if (this.x == 6 && this.firstStep){
                    this.firstStep = false;
                    return this.x - x <= 2 && this.x - x > 0 && this.y == y;
                }
                return this.x - x == 1 && this.y == y;
            }
        }
    }
}
