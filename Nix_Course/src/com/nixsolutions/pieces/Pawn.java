package com.nixsolutions.pieces;

import com.nixsolutions.pieces.AbstractPiece;

public class Pawn extends AbstractPiece {
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void draw() {
        if (this.isWhite) {
            System.out.print("\u2659");
        }
        if (!(this.isWhite)) {
            System.out.print("\u265F");
        }
    }

    @Override
    public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
        //if pawn moves forward one
        //or moves forward two from starting position
        //or takes a piece of black colour
        //break, else return false (not valid move)
        if (this.isWhite) {
            return (((srcCol == destCol) && srcRow == (destRow + 1))
                    || ((srcRow == 6) && (srcCol == destCol) && (srcRow == (destRow + 2)))
                    || ((srcRow == (destRow + 1))
                            && (Math.abs(srcCol - destCol) == 1)));
        }
        else {
            return (((srcCol == destCol) && srcRow == (destRow - 1))
                    || ((srcRow == 1) && (srcCol == destCol) && (srcRow == (destRow - 2)))
                    || ((srcRow == (destRow - 1))
                            && (Math.abs(srcCol - destCol) == 1)));
        }



    }

    @Override
    public int relativeValue() {
        return 1;
    }

}
