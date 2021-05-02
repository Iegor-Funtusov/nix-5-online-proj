package entity;

public class Queen extends Figure {
    public char getName() {
        return 'Q';
    }

    @Override
    public boolean run(int x, int y){
        if((this.x == x && y!= 8)|| (this.x == x && y > 0) ||
        (this.y == y && x!=8) || (this.y == y && x > 0) ||
        (this.x+1 == x && this.y+1==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+1 == x && this.y-1==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+2 == x && this.y+2==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+2 == x && this.y-2==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+3 == x && this.y+3==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+3 == x && this.y-3==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+4 == x && this.y+4==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+4 == x && this.y-4==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+5 == x && this.y+5==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+5 == x && this.y-5==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+6 == x && this.y+6==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+6 == x && this.y-6==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+7 == x && this.y+7==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x+7 == x && this.y-7==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-1 == x && this.y+1==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-1 == x && this.y-1==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-2 == x && this.y+2==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-2 == x && this.y-2==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-3 == x && this.y+3==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-3 == x && this.y-3==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-4 == x && this.y+4==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-4 == x && this.y-4==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-5 == x && this.y+5==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-5 == x && this.y-5==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-6 == x && this.y+6==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-6 == x && this.y-6==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-7 == x && this.y+7==y && x>=0 && x<8 && y>=0 && y<8) ||
        (this.x-7 == x && this.y-7==y && x>=0 && x<8 && y>=0 && y<8)
        ){
            return true;
        }
        else {
            System.out.println("Wrong way. Try again!");
            return false;
        }
    }

}
