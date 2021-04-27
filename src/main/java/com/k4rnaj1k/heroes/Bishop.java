package com.k4rnaj1k.heroes;

public class Bishop extends Figure{
    public Bishop(String figuresymbol) {
        super(figuresymbol);
    }

    @Override
    public String placeHero(int x, int y) throws Exception {
        if(canBePLaced(x,y))
        return figuresymbol;
        else{
            throw new Exception("Can't place the chosen figure here.");
        }
    }

    @Override
    public boolean canBePLaced(int x, int y) {
        if(!placed){
            this.placed = true;
            setX(x);
            setY(y);
            return true;
        }else if((this.y - y > 0 && this.y-y == Math.abs(this.x-x)) || (this.y-y < 0 && y-this.y == Math.abs(this.x-x))){
            setX(x);
            setY(y);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return figuresymbol;
    }
}
