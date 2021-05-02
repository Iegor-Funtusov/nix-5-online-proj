package entities;

public class King extends Piece {

    public char getType() {
        return 'K';
    }

    @Override
    public boolean move(int x, int y) {

        if ((this.x + 1 == x && this.y + 1 == y && x >= 0 && x < 8 && y >= 0 && y < 8) ||
                (this.x + 1 == x && this.y == y && x >= 0 && x < 8 && y >= 0 && y < 8) ||
                (this.x + 1 == x && this.y - 1 == y && x >= 0 && x < 8 && y >= 0 && y < 8) ||
                (this.x == x && this.y - 1 == y && x >= 0 && x < 8 && y >= 0 && y < 8) ||
                (this.x == x && this.y + 1 == y && x >= 0 && x < 8 && y >= 0 && y < 8) ||
                (this.x - 1 == x && this.y + 1 == y && x >= 0 && x < 8 && y >= 0 && y < 8) ||
                (this.x - 1 == x && this.y == y && x >= 0 && x < 8 && y >= 0 && y < 8) ||
                (this.x - 1 == x && this.y - 1 == y && x >= 0 && x < 8 && y >= 0 && y < 8)) {
            return true;
        }
        else {
            System.out.println("You can't move there.");
            return false;
        }
    }
}