package entities;

public class Pawn extends Piece {

    public char getType() {
        return 'P';
    }

    @Override
    public boolean move(int x, int y) {

        if(this.x++ == x && this.y == y && x!= 8){
            return true;
        }
        else {
            System.out.println("You can't move there.");
            return false;
        }
    }
}