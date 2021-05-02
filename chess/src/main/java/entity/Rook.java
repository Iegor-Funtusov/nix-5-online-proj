package entity;

public class Rook extends Figure {
    public char getName() {
        return 'R';
    }

    @Override
    public boolean run(int x, int y) {
        if((this.x == x)||(this.y==y)){
            return true;
        }
        else {
            System.out.println("Wrong way. Try again!");
            return false;
        }
    }
}
