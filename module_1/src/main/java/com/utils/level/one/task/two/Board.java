package com.utils.level.one.task.two;

public class Board {
    private final int x_size = 8;
    private final int y_size = 8;
    public Knight knight;
    public String[][] board = new String[8][8];

    public Board(int x, int y) {
        knight = new Knight(x , y);
        for (int i = 0; i < y_size; i++) {
            for (int j = 0; j < x_size; j++) {
                board[i][j] = "   ";
            }
        }
        board[y % 8][x % 8] = knight.symbol;
    }

    public void PlacePiece(int x, int y) {
        if (knight.isValidMove(x % 8, y % 8)) {
            System.out.println("The Knight can be placed there!");
            board[y % 8][x % 8] = " ✔ ";
        }
        else {
            System.out.println("The Knight can't be placed there!");
            board[y % 8][x % 8] = " X ";
        }
    }
}
