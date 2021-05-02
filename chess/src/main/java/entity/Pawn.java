package entity;

public class Pawn extends Figure {

    public char getName() {
        return 'P';
    }

    @Override
    public boolean run(int x, int y) {
        if(this.x + 1 == x && this.y == y && x!= 8){
            return true;
        }else {
            System.out.println("Wrong way. Try again!");
            return false;
        }
    }
}
