package entity;

public class Queen extends Figure {
    public char getName() {
        return 'Q';
    }

    @Override
    public boolean run(int x, int y){
        if(Math.abs(this.x-x) == Math.abs(this.y-y) ||
                this.x == x || this.y == y) {
            return true;
        }
        else {
            System.out.println("Wrong way. Try again!");
            return false;
        }
    }

}
