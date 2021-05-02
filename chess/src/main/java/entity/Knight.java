package entity;

public class Knight extends Figure {

    public char getName() {
        return 'N';
    }

    @Override
    public boolean run(int x, int y) {
        if ((this.x + 2 == x && x < 8 && x > 0 && this.y + 1 == y) ||
                (this.x + 2 == x && x < 8 && x > 0 && this.y - 1 == y) ||
                (this.x - 2 == x && x < 8 && x > 0 && this.y + 1 == y) ||
                (this.x - 2 == x && x < 8 && x > 0 && this.y - 1 == y) ||
                (this.y + 2 == y && y < 8 && y > 0 && this.x + 1 == x) ||
                (this.y + 2 == y && y < 8 && y > 0 && this.x - 1 == x) ||
                (this.y - 2 == y && y < 8 && y > 0 && this.x + 1 == x) ||
                (this.y - 2 == y && y < 8 && y > 0 && this.x - 1 == x)){
            return true;
    }
        else{
            System.out.println(this.x + 2 + "  ; " + x);
            System.out.println("Wrong way. Try again!");
            return false;
        }
    }
}