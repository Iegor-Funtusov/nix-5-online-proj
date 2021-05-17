package ua.com.alevel;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class AppMain {

    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {
        while (true)
            callInterface();
    }

    private static void callInterface() {
        LifeGame game = null;
        while (true) {
            printLifeGameMenu();
            game = createGame();
            if (game == null) {
                System.out.println("Invalid entering!");
                continue;
            }
            game.printGameBoard();
            LifeGameUtils.calculateNextLifeGameState(game);
            System.out.println("Next generation:");
            game.printGameBoard();
        }
    }

    @SneakyThrows
    private static LifeGame createGame() {
        LifeGame game = null;
        int[][] board;
        switch (reader.readLine()){
            case "1":{
                board = getManualGameBoard();
                if (board != null) {
                    game = new LifeGame(board);
                }
                break;
            }
            case "2":{
                board = getRandomGameBoard();
                if (board != null) {
                    game = new LifeGame(board);
                }
                break;
            }
            case "0":{
                break;
            }
            default:{
                System.out.println("Invalid choice! Try again!");
            }
        }
        return game;
    }

    private static int[][] getRandomGameBoard() {
        Random random = new Random();
        int[][] board = null;
        int rows = 0;
        int columns = 0;
        try {
            System.out.print("Enter number of rows: ");
            rows = Integer.parseInt(reader.readLine());
            System.out.print("Enter number of columns: ");
            columns = Integer.parseInt(reader.readLine());
            board = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    board[i][j] = random.nextInt(2);
                }
            }
        }catch (Exception e){
            System.out.println();
        }
        return board;
    }

    private static int[][] getManualGameBoard() {
        int[][] board = null;
        int rows = 0;
        int columns = 0;
        try {
            System.out.print("Enter number of rows: ");
            rows = Integer.parseInt(reader.readLine());
            System.out.print("Enter number of columns: ");
            columns = Integer.parseInt(reader.readLine());
            board = new int[rows][columns];
            System.out.println("Enter your board: ");
            for (int i = 0; i < rows; i++) {
                System.out.print("Enter " + (i + 1) + " row elements separated by Enter (1 - live, 0 - dead) :");
                for (int j = 0; j < columns; j++) {
                    board[i][j] = Integer.parseInt(reader.readLine());
                }
            }
        }catch (Exception e){
            System.out.println();
        }
        return board;
    }

    private static void printLifeGameMenu() {
        System.out.print("Life game!" +
                "\nFirst task menu!" +
                "\nChoose type of game board creating :" +
                "\n 1 - manual" +
                "\n 2 - random" +
                "\n 0 - exit menu" +
                "\n -->");
    }
}
