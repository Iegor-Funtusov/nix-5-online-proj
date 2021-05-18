package ua.com.threadedcode.figure;

import ua.com.threadedcode.controller.Player;

public abstract class Figure {
    private int x;
    private int y;
    private String type;
    private Player player;

    public Figure(int x, int y, String type, Player player) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.player = player;
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

    public Player getPlayer() {
        return player;
    }

    public abstract boolean isValidPath(int currentX, int currentY,int targetX, int targetY);

    @Override
    public String toString() {
        return  type;
    }
}
