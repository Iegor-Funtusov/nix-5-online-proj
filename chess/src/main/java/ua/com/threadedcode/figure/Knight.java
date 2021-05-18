package ua.com.threadedcode.figure;

import ua.com.threadedcode.controller.Player;

public class Knight extends Figure {


    public Knight(int x, int y, String type, Player player) {
        super(x, y, type, player);
    }

    @Override
    public boolean isValidPath(int currentX, int currentY,int targetX, int targetY) {
        int x = Math.abs(targetX - currentX);
        int y = Math.abs(targetY - currentY);

        return (x == 1 && y == 2) || (x == 2 && y == 1);
    }
}
