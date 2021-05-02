package entities;

public class Queen extends Piece {

    public char getType() {
        return 'Q';
    }

    @Override
    public boolean move(int x, int y){

        if(this.x == x && y != 8 || this.x == x || this.y == y && x != 8 || this.y == y){
            return true;
        }

        for(int i =0; i<8; i++){
            if((this.x-i == x && this.y+i==y && x>=0 && x<8 && y>=0 && y<8)||
                    (this.x+i == x && this.y-i==y && x>=0 && x<8 && y>=0 && y<8)||
                    (this.x+i == x && this.y+i==y && x>=0 && x<8 && y>=0 && y<8)||
                    (this.x-i == x && this.y-i==y && x>=0 && x<8 && y>=0 && y<8)){
                return true;
            }
        }

        System.out.println("You can't move there.");
        return false;
    }

}