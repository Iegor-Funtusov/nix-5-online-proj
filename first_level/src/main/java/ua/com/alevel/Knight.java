package ua.com.alevel;

public class Knight {

    private int x;
    private int y;

    public Knight(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private boolean isMoveValid(int x, int y){
        if( (Math.abs(this.x - x) == 2) && (Math.abs(this.y - y) == 1))
            return true;
        if( (Math.abs(this.y - y) == 2) && (Math.abs(this.x - x) == 1))
            return true;
        return false;
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

    @Override
    public String toString() {
        return "Knight x= " + x +
                ", y=" + y;
    }
}
