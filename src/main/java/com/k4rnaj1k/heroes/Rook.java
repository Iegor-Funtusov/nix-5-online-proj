package com.k4rnaj1k.heroes;

public class Rook extends Figure{
    public Rook(String text) {
        super(text);
    }

    @Override
    public String placeHero(int x, int y) throws Exception {
        if(canBePLaced(x,y))
        return "♖";
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
        else if((this.x-x == 0 && this.y-y !=0) || (this.y-y == 0 && this.x-x != 0)){
            setX(x);
            setY(y);
            return true;}
        return false;
    }
}
