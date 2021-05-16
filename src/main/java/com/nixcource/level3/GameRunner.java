package com.nixcource.level3;

import java.util.Scanner;

public class GameRunner {
    GameOfLife game;
    Scanner scanner;

    public GameRunner(int columns, int rows) {
        game = new GameOfLife(columns, rows);
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
