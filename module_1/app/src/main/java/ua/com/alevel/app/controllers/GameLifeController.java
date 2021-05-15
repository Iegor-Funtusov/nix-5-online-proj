package ua.com.alevel.app.controllers;

import ua.com.alevel.lib.LifeGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GameLifeController {

    public static void gameLife() {

        Scanner s = new Scanner(System.in);
        System.out.println("You need to input a board, choose the way of input:\n" +
                "1 -> Do it by yourself\n" +
                "2 -> Random input");
        int choice = s.nextInt();

        if (choice == 1) {
            System.out.print("Input board length: ");
            int length = s.nextInt();
            System.out.print("Input board height: ");
            int height = s.nextInt();
            int[][] board = new int[length][height];
            System.out.println("Input elements (0-dead or 1-alive): ");
            for (int i = 0; i < length; i++) {
                for (int j =0; j < height; j++) {
                    board[i][j] = s.nextInt();
                }
            }
            LifeGame.printBoard(board.length, board[0].length, board);
            changeState(board);
        }
        else if (choice == 2) {
            System.out.print("Input board length: ");
            int length = s.nextInt();
            System.out.print("Input board height: ");
            int height = s.nextInt();
            int[][] board = new int[length][height];
            for (int i = 0; i < length; i++) {
                for (int j =0; j < height; j++) {
                    board[i][j] = (int) Math.round(Math.random());
                }
            }
            LifeGame.printBoard(board.length, board[0].length, board);
            changeState(board);
        }
    }

    public static void changeState(int[][] board) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        try {
            System.out.println("Choose the action:\n" +
                    "1 -> Next state\n" +
                    "2 -> Print board\n" +
                    "0 -> End the game");
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "1" : {
                        LifeGame.gameOfLife(board);
                    } break;
                    case "2" : {
                        LifeGame.printBoard(board.length, board[0].length, board);
                    } break;
                    case "0": {
                        return;
                    }
                    default: {
                        System.out.println("Wrong input");
                    } break;
                }
                System.out.println("Choose the action:\n" +
                        "1 -> Next state\n" +
                        "2 -> Print board\n" +
                        "0 -> End the game");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
