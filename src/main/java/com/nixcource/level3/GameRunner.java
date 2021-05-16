package com.nixcource.level3;

import java.util.Scanner;

public class GameRunner {
    Board board;
    GameOfLife game;
    Scanner scanner;

    public GameRunner(int columns, int rows) {
        board = new Board(columns, rows);
        game = new GameOfLife(board);
        scanner = new Scanner(System.in);
    }

    public void play() {
        do {
            game.clock();
            game.displayBoard();
            System.out.println("To exit type q and press Enter...\nTo continue press Enter...");
            System.out.println("=============================");
        } while(!scanner.nextLine().equals("q"));
    }
}
