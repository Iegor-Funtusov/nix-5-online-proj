package com.k4rnaj1k.heroes;

public class Rook extends Figure {
    public Rook(String text) {
        super(text);
    }

    @Override
    public String placeHero(int x, int y) {
        return this.figuresymbol;
    }

    @Override
    public boolean canBePLaced(int x, int y) {
        if (!placed) {
            this.placed = true;
            return true;
        } else return ((this.x - x == 0 && this.y - y != 0) || (this.y - y == 0 && this.x - x != 0));
    }
}
