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
            System.out.println("""
                To continue playing Game of Life press Enter...
                To exit from game write q and press Enter...""");
        } while(!scanner.nextLine().equals("q"));
    }
}
