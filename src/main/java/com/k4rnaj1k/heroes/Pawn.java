package com.k4rnaj1k.heroes;

public class Pawn extends Figure {
    public Pawn(String text) {
        super(text);
    }

    @Override
    public String placeHero(int x, int y) {
        setX(x);
        setY(y);
        return this.figuresymbol;
    }

    boolean startingmove = true;

    @Override
    public boolean canBePLaced(int x, int y) {
        if (!placed) {
            this.placed = true;
            return true;
        } else if (this.startingmove && (this.figuresymbol.equals("♟") ? (y - this.y <= 2) : (this.y - y <= 2)) && this.x - x == 0) {
            this.startingmove = false;
            return true;
        } else return (this.figuresymbol.equals("♟") ? (y - this.y == 1) : (this.y - y == 1)) && this.x - x == 0;

    }

}
