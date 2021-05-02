package com.nixsolutions.courses.figures;

import com.nixsolutions.courses.basics.Figure;
import com.nixsolutions.courses.basics.Square;

public class Rook extends Figure {

    public Rook() {
    }

    @Override
    public boolean validMove(Square from, Square to) {
        return from.getX() == to.getX() || from.getY() == to.getY();
    }
}
