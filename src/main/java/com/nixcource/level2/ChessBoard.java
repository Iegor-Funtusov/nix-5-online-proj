package com.nixcource.level2;

import java.util.Random;

public class ChessBoard {
    Random random;
    private final int columns;
    private final int rows;
    private final Horse horse;

    public ChessBoard(int columns, int rows) {
        random = new Random();
        int randomColumn = random.nextInt(columns);
        int randomRow = random.nextInt(rows);

        this.horse = new Horse(randomColumn, randomRow);
        this.columns = columns;
        this.rows = rows;
    }

    public void displayBoard() {
        System.out.print("  ");
        for (int i = 0; i < rows; ++i) {
            System.out.printf(" %s ", i);
        }
        System.out.println();
        for (int column = 0; column < columns; ++column) {
            System.out.printf("%s ", column);
            for (int row = 0; row < rows; ++row) {
                if (column == horse.getX() && row == horse.getY()) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int x, int y) {
        return (Math.abs(horse.getX() - x) == 2 && Math.abs(horse.getY() - y) == 1
                || (Math.abs(horse.getY() - y) == 2 && Math.abs(horse.getX() - x) == 1));
    }

    public void move(int x, int y) {
        if (isValidMove(x, y)) {
            horse.setCoordinates(x, y);
        } else {
            System.out.println("INVALID MOVE! TRY AGAIN...");
        }
    }

    private static class Horse {
        private int x;
        private int y;

        Horse(int x, int y) {
            setCoordinates(x, y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setCoordinates(int x, int y) {
            System.out.printf("NEW COORDINATES: %sx%s\n", x, y);
            this.x = x;
            this.y = y;
        }
    }
}