package Level1;

public class KnightsMove {
    private int x;
    private int y;

    //Реализация аналогична как в моём дз №3
    public boolean checkPossibilityOfMove(int xCoord, int yCoord) {
        //Конь ходит буквой Г
        int x = Math.abs(xCoord - this.x);
        int y = Math.abs(yCoord - this.y);
        if ((x == 1 && y == 2) || (x == 2 && y == 1)){
            move(xCoord, yCoord);
            return true;
        }
        return false;
    }

    private void move(int xCoord, int yCoord){
        this.x = xCoord;
        this.y = yCoord;
    }

    @Override
    public String toString() {
        return "Current position: " + "X: " + x + "; Y: " + y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
