package entity;

import constants.Colour;

public abstract class Figure {
    public char name;
    public int x;
    public int y;
    public String colour;

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "name=" + this.getName() +
                ", x=" + x +
                ", y=" + y +
                ", colour='" + colour + '\'' +
                '}';
    }

    public char getName(){
        return name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColour(int colour) {
        if(colour == 1){
            this.colour = Colour.ANSI_RED;
        }
        else {
            this.colour = Colour.ANSI_WHITE;
        }
    }

    public abstract boolean run(int x, int y);
}
