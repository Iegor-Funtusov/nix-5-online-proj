package com.k4rnaj1k.heroes;

public class Knight extends Figure{
    public Knight(String text) {
        super(text);
    }

    @Override
    public String placeHero(int x, int y) throws Exception {
        if(canBePLaced(x,y))
        return "♘";
        else
            throw new Exception("Can't place the chosen figure here.");
    }

    @Override
    public boolean canBePLaced(int x, int y) {
        if(!placed)
        {
            this.placed = true;
            setX(x);
            setY(y);
            return true;
        }
        else if((Math.abs(this.x - x) == 2 && Math.abs(this.y - y) == 1) || (Math.abs(this.x-x) == 1 && Math.abs(this.y-y) == 2)){
            setX(x);
            setY(y);
            return true;
        } return false;
    }
}
