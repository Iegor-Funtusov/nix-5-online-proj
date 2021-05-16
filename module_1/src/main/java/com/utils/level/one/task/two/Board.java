package com.utils.level.one.task.two;

public class Board {
    private final int x_size = 8;
    private final int y_size = 8;
    public Knight knight;
    public String[][] board = new String[8][8];

    public Board(int x, int y) {
        knight = new Knight(x, y);
        for (int i = 0; i < y_size; i++) {
            for (int j = 0; j < x_size; j++) {
                board[i][j] = "   ";
            }
        }
        board[y][x] = knight.symbol;
    }

    public void PlacePiece(int x, int y) {
        if (knight.isValidMove(x, y)) {
            System.out.println("The Knight can be placed there!\n");
            board[y][x] = " ✔ ";
        }
        else {
            System.out.println("The Knight can't be placed there!\n");
            board[y][x] = " X ";
        }
    }

    public void Print() {
        for (int i = 0; i < y_size; i++) {
            System.out.print((knight.getY() + i - y_size) + "|");
            for (int j = 0; j < x_size; j++) {
                System.out.print(board[i][j] + "|");
            }
        }
        for(int i = 0; i < x_size; i++) {
            System.out.print((knight.getX() + i - x_size) + "|");
        }
    }

}
