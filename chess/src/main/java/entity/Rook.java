package entity;

public class Rook extends Figure {
    public char getName() {
        return 'R';
    }

    @Override
    public boolean run(int x, int y) {
        if((this.x == x && y!= 8)|| (this.x == x && y > 0)
        ||(this.y == y && x!=8) || (this.y == y && x > 0)){
            return true;
        }
        else {
            System.out.println("Wrong way. Try again!");
            return false;
        }
    }
}
