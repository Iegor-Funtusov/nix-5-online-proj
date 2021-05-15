package org.example.level1.task2;

import java.util.Arrays;

public class ChessBoard {
    private Figure[][] boardArea = new Figure[8][8];

    public ChessBoard() {
        this.boardArea[1][0] = new Knight("knight");
    }

    public void move(int x, int y, int newX, int newY) {
        if (boardArea[x][y].isValidMove(x, y, newX, newY)) {
            boardArea[newX][newY] = boardArea[x][y];
            boardArea[x][y] = null;
            System.out.println("your position is: " + newX + "," + newY);
        } else {
            try {
                throw new Exception("Illegal move");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("your position is: " + x + "," + y);
            }
        }
    }

    public void showBoard() {
        Arrays.stream(boardArea).map(a -> Arrays.toString(a)).
                forEach(x -> System.out.println(x.toString()));
    }
}
