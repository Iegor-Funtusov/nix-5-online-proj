package ua.com.nkrasnovoronka.tasks.level1.task2;

public class HorseMove {
    public static boolean isHorseMoveValid(Square from, Square to) {
        int x = from.getX();
        int y = from.getY();
        int x1 = to.getX();
        int y1 = to.getY();
        return (Math.abs(x - x1) == 2 && Math.abs(y - y1) == 1) || (Math.abs(y - y1) == 2 && Math.abs(x - x1) == 1);
    }
}
