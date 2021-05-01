package ua.com.Pieces;

public class Bishop extends Piece{

    public Bishop(String s) {
        super(s);
    }

    @Override
    public boolean availableToAlloc(int x, int y) throws Exception {
        if (!this.isPresent){
            this.isPresent = true;
            return true;
        } else {
            return Math.abs(this.x - x ) == Math.abs(this.y - y);
        }
    }

    @Override
    public void move(int x, int y) throws Exception {
        if (availableToAlloc(x, y)) {
            this.setX(x);
            this.setY(y);
        } else {
            throw new Exception("The piece can't be placed right here");
        }
    }
}
