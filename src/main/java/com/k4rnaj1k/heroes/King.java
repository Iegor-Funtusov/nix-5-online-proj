package com.k4rnaj1k.heroes;

public class King extends Figure {
    public King(String text) {
        super(text);
    }

    @Override
    public String placeHero(int x, int y) throws Exception {
            if(canBePLaced(x,y))
                return figuresymbol;
            else
                throw new Exception("Can't place the chosen figure here.");
    }

    @Override
    public boolean canBePLaced(int x, int y) {
        if (!placed) {
            this.placed = true;
            setX(x);
            setY(y);
            return true;
        } else if (Math.abs(this.x - x) <= 1 && Math.abs(this.y - y) <= 1) {
            setX(x);
            setY(y);
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }
}
