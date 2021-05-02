package entity;

public class King extends Figure {

    public char getName() {
        return 'K';
    }

    @Override
    public boolean run(int x, int y) {
        if ((this.x-x==1 || this.x-x==-1 || this.x-x==0) &&
                (this.y-y==1 || this.y-y==-1 || this.y-y==0)) {
            return true;
        } else {
            System.out.println("Wrong way. Try again!");
            return false;
        }
    }
}
