package ua.com.Pieces;

public class Knight extends Piece{

    public Knight(String s) {
        super(s);
    }

    @Override
    public boolean availableToAlloc(int x, int y) {
        if (!this.isPresent){
            this.isPresent = true;
            return true;
        } else {
            return (Math.abs(this.x - x) == 2 && Math.abs(this.y - y) == 1) ||
                    (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 2);
        }
    }
}
