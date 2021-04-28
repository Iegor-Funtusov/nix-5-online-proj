package com.k4rnaj1k.heroes;

public class Bishop extends Figure{
    public Bishop(String figuresymbol) {
        super(figuresymbol);
    }

    @Override
    public String placeHero(int x, int y) {
        setX(x);
        setY(y);
        return this.figuresymbol;
    }

    @Override
    public boolean canBePLaced(int x, int y) {
        if(!this.placed){
            this.placed = true;
            return true;
        }else return (this.y - y > 0 && this.y - y == Math.abs(this.x - x)) || (this.y - y < 0 && y - this.y == Math.abs(this.x - x));
    }
}
