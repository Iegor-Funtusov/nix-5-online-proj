package com.nixsolutions.courses.figures;

import com.nixsolutions.courses.basics.Figure;
import com.nixsolutions.courses.basics.Square;

public class Pawn extends Figure {

    public Pawn() {
    }

    //valid move was simplified to 1 square forward
    @Override
    public boolean validMove(Square from, Square to) {
        if (from.getX()==to.getX()) {
            if(this.isWhite()) {
                return to.getY() - from.getY() == 1;
            } else return from.getY() - to.getY() == 1;
        }
        return false;
    }
}
