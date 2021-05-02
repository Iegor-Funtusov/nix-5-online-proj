package entity;

import constants.Colour;

public class Pawn extends Figure {

    public char getName() {
        return 'P';
    }

    @Override
    public boolean run(int x, int y) {
        if(this.colour.equals(Colour.ANSI_WHITE) && this.x + 1 == x && this.y == y && x!= 8){
            return true;
        }
        if(this.colour.equals(Colour.ANSI_RED)&& this.x - 1 == x && this.y == y && x > 0){
          return true;
        }
        else {
            System.out.println("Wrong way. Try again!");
            return false;
        }
    }
}
