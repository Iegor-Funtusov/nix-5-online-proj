package com.k4rnaj1k.heroes;

public class Queen extends Figure{
    public Queen(String text) {
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
        if(!this.placed)
        {
            this.placed = true;
            return true;
        }
        else return ((Math.abs(this.x - x) <= 1 && Math.abs(this.y - y) <= 1) || (Math.abs(this.x - x) == 0 && Math.abs(this.y - y) != 0) || (Math.abs(this.x-x) != 0 && Math.abs(this.y-y) ==0)
        || (this.y - y > 0 && this.y-y == Math.abs(this.x-x)) || (this.y-y < 0 && y-this.y == Math.abs(this.x-x)));
    }
}
