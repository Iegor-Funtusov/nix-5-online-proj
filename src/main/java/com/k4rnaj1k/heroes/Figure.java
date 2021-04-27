package com.k4rnaj1k.heroes;

abstract public class Figure {
    int x;
    int y;
    public String figuresymbol;

    public Figure(String figuresymbol){
        setFiguresymbol(figuresymbol);
    }


    void setFiguresymbol(String s){
        this.figuresymbol = s;
    }

    boolean placed = false;
    void setX(int x){
        this.x = x;
    }

    void setY(int y){
        this.y = y;
    }
    public abstract String placeHero(int x, int y) throws Exception;
    public abstract boolean canBePLaced(int x, int y);

    @Override
    public String toString() {
        return figuresymbol;
    }
}
