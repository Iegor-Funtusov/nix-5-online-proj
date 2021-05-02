package entity;

public class Bishop extends Figure {
    public char getName() {
        return 'B';
    }

    @Override
    public boolean run(int x, int y) {
        if(Math.abs(this.x-x) == Math.abs(this.y-y)){
            return true;
        }
        System.out.println("Wrong way. Try again!");
        return false;
    }
}
