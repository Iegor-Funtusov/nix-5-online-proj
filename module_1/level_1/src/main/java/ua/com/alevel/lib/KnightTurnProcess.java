package ua.com.alevel.lib;

public class KnightTurnProcess {

    public static boolean move(int x, int y, int xDest, int yDest) {
        if ((x + 2 == xDest && xDest > 0 && y + 1 == yDest) ||
                (x + 2 == xDest && y - 1 == yDest && xDest > 0 && yDest > 0) ||
                (x - 2 == xDest && y + 1 == yDest && xDest > 0 && yDest > 0) ||
                (x - 2 == xDest && y - 1 == yDest && xDest > 0 && yDest > 0) ||
                (x + 1 == xDest && y + 2 == yDest && yDest > 0 && xDest > 0) ||
                (x - 1 == xDest && y + 2 == yDest && yDest > 0 && xDest > 0) ||
                (x + 1 == xDest && y - 2 == yDest && yDest > 0 && xDest > 0) ||
                (x - 1 == xDest && y - 2 == yDest && yDest > 0 && xDest > 0)){
            return true;
        }
        else{
            System.out.println("You can't move there.");
            return false;
        }
    }
}
