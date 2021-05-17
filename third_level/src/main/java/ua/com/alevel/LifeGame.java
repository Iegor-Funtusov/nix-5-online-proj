package ua.com.alevel;

import java.util.Arrays;

public class LifeGame {

    private int[][] gameBoard;

    public LifeGame() {
    }

    public LifeGame(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void printGameBoard() {
        System.out.println("Game board.");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print(Arrays.toString(gameBoard[i]));
            System.out.println();
        }
    }
}
