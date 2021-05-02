package com.nixsolutions.courses.figures;

import com.nixsolutions.courses.basics.Figure;
import com.nixsolutions.courses.basics.Square;

public class King extends Figure {

    public King() {
    }

    @Override
    public boolean validMove(Square from, Square to) {
        int x = Math.abs(from.getX() - to.getX());
        int y = Math.abs(from.getY() - to.getY());

        return x + y == 1;
    }
}
