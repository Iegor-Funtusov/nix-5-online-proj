package entities;

public class Rook extends Piece {

    public char getType() {
        return 'R';
    }

    @Override
    public boolean move(int x, int y) {

        if(this.x == x && y != 8 || this.x == x || this.y == y && x != 8 || this.y == y){
            return true;
        }
        else {
            System.out.println("You can't move there.");
            return false;
        }
    }
}