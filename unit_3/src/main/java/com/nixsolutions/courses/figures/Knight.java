package com.nixsolutions.courses.figures;

import com.nixsolutions.courses.basics.Figure;
import com.nixsolutions.courses.basics.Square;

public class Knight extends Figure {

    public Knight() {
    }

    @Override
    public boolean validMove(Square from, Square to) {
        int x = Math.abs(from.getX() - to.getX());
        int y = Math.abs(from.getY() - to.getY());

        return x * y == 2;
    }
}
