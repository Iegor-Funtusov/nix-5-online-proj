package entities;

public class Horse extends Piece {

    public char getType() {
        return 'H';
    }

    @Override
    public boolean move(int x, int y) {

        if ((this.x + 2 == x && x < 8 && x > 0 && this.y + 1 == y) ||
                (this.x + 2 == x && this.y - 1 == y && x < 8 && x > 0 && y < 8 && y > 0) ||
                (this.x - 2 == x && this.y + 1 == y && x < 8 && x > 0 && y < 8 && y > 0) ||
                (this.x - 2 == x && this.y - 1 == y && x < 8 && x > 0 && y < 8 && y > 0) ||
                (this.x + 1 == x && this.y + 2 == y && y < 8 && y > 0 && x < 8 && x > 0) ||
                (this.x - 1 == x && this.y + 2 == y && y < 8 && y > 0 && x < 8 && x > 0) ||
                (this.x + 1 == x && this.y - 2 == y && y < 8 && y > 0 && x < 8 && x > 0) ||
                (this.x - 1 == x && this.y - 2 == y && y < 8 && y > 0 && x < 8 && x > 0)){
            return true;
        }
        else{
            System.out.println("You can't move there.");
            return false;
        }
    }
}