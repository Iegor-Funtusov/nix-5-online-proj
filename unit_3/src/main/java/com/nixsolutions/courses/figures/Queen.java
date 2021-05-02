package com.nixsolutions.courses.figures;

import com.nixsolutions.courses.basics.Figure;
import com.nixsolutions.courses.basics.Square;

public class Queen extends Figure {

    public Queen() {
    }

    @Override
    public boolean validMove(Square from, Square to) {
        return from.getX() == to.getX() || from.getY() == to.getY() || (Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY()));
    }
}
