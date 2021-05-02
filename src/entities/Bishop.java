package entities;

public class Bishop extends Piece {

    public char getType() {
        return 'B';
    }

    @Override
    public boolean move(int x, int y) {

        for(int i =0; i<8; i++){
            if((this.x-i == x && this.y+i==y && x>=0 && x<8 && y>=0 && y<8)||(this.x+i == x && this.y-i==y && x>=0 && x<8 && y>=0 && y<8)||(this.x+i == x && this.y+i==y && x>=0 && x<8 && y>=0 && y<8)||(this.x-i == x && this.y-i==y && x>=0 && x<8 && y>=0 && y<8)){
                return true;
            };
        }

        System.out.println("You can't move there.");
        return false;
    }
}