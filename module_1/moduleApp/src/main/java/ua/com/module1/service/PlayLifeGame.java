package ua.com.module1.service;

import ua.com.module1.LifeGame;

import java.io.IOException;

public class PlayLifeGame {
    public static void getBoard() throws IOException {
        LifeGame lifeGame = new LifeGame(7, 8);
        LifeGame.printBoard();
        LifeGame.nextGeneration(7, 8);
    }
}
