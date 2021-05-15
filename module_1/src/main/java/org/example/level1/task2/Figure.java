package org.example.level1.task2;

public abstract class Figure {
    private String type;

    public Figure(String type) {
        this.type = type;
    }

    public abstract boolean isValidMove(int initX, int initY, int targetX, int targetY);

    @Override
    public String toString() {
        return type;
    }
}