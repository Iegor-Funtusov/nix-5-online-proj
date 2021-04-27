package com.k4rnaj1k.heroes;

public class Pawn extends Figure {
    public Pawn(String text) {
        super(text);
    }

    @Override
    public String placeHero(int x, int y) throws Exception {
        if (canBePLaced(x, y))
            return figuresymbol;
        else
            throw new Exception("Can't place the chosen figure here.");
    }

    boolean startingmove = true;

    @Override
    public boolean canBePLaced(int x, int y) {
        if (!placed) {
            setX(x);
            setY(y);
            this.placed = true;
            return true;
        } else if (startingmove && (figuresymbol.equals("♟") ? (y - this.y <= 2) : (this.y - y <= 2)) && this.x - x == 0) {
            System.out.println("starting move");
            setX(x);
            setY(y);
            this.startingmove = false;
            return true;
        } else if ((figuresymbol.equals("♟") ? (y - this.y == 1) : (this.y - y == 1)) && this.x - x == 0) {
            setX(x);
            setY(y);
            return true;
        } else {
            return false;
        }

    }

}
