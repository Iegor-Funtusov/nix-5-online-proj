package org.example.level1.task2;

public class Knight extends Figure {

    public Knight(String type) {
        super(type);
    }

    @Override
    public boolean isValidMove(int initX, int initY, int targetX, int targetY) {
        int x = Math.abs(targetX - initX);
        int y = Math.abs(targetY - initY);

        return (x == 1 && y == 2) || (x == 2 && y == 1);
    }
}
