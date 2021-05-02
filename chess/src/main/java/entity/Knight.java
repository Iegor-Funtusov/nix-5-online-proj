package entity;

public class Knight extends Figure {

    public char getName() {
        return 'N';
    }

    @Override
    public boolean run(int x, int y) {
        int dx = Math.abs(this.x - x);
        int dy = Math.abs(this.y - y);
        if (dx == 1 && dy == 2 || dx == 2 && dy == 1){
            return true;
    }
        else{
            System.out.println("Wrong way. Try again!");
            return false;
        }
    }
}