package com.k4rnaj1k.heroes;

public class Knight extends Figure{
    public Knight(String text) {
        super(text);
    }

    @Override
    public String placeHero(int x, int y) {
        setX(x);
        setY(y);
        return this.figuresymbol;
    }

    @Override
    public boolean canBePLaced(int x, int y) {
        if(!placed)
        {
            this.placed = true;
            return true;
        }
        else return((Math.abs(this.x - x) == 2 && Math.abs(this.y - y) == 1) || (Math.abs(this.x-x) == 1 && Math.abs(this.y-y) == 2));
    }
}
