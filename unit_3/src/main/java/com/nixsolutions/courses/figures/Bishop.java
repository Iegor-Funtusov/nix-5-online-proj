package com.nixsolutions.courses.figures;

import com.nixsolutions.courses.basics.Figure;
import com.nixsolutions.courses.basics.Square;

public class Bishop extends Figure {

    public Bishop() {
    }

    @Override
    public boolean validMove(Square from, Square to) {
        return (Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY())) && from.isWhite()==to.isWhite();
    }
}
