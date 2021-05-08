package ua.com.alevel.util;

import ua.com.alevel.chessBoard.ChessBoard;
import ua.com.alevel.chessBoard.ChessBoardBuilder;
import ua.com.alevel.chessBoard.Position;
import ua.com.alevel.figure.*;
import java.io.IOException;
import java.util.Scanner;

public class Util {

    public static ChessBoard chessBoard = new ChessBoard();
    public static Figure figure;

    public static void start() throws IOException {
        System.out.println("Hello chess player :)");
        System.out.println("This is your chess board.");
        ChessBoardBuilder.printBoard();
        startApplication();
    }

    public static Position choosePosition() throws IOException{
        int row = 0, column = 0;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (true) {
            switch (input.charAt(0)) {
                case 'A':
                    column = 0;
                    break;
                case 'B':
                    column = 1;
                    break;
                case 'C':
                    column = 2;
                    break;
                case 'D':
                    column = 3;
                    break;
                case 'E':
                    column = 4;
                    break;
                case 'F':
                    column = 5;
                    break;
                case 'G':
                    column = 6;
                    break;
                case 'H':
                    column = 7;
                    break;
                default:
                    System.out.println("Please select right column from chessboard.");
                    break;
            }
            switch (input.charAt(1)) {
                case '1':
                    row = 7;
                    break;
                case '2':
                    row = 6;
                    break;
                case '3':
                    row = 5;
                    break;
                case '4':
                    row = 4;
                    break;
                case '5':
                    row = 3;
                    break;
                case '6':
                    row = 2;
                    break;
                case '7':
                    row = 1;
                    break;
                case '8':
                    row = 0;
                    break;
                default:
                    System.out.println("Please select right row from chessboard.");
            }
            if (chessBoard.getPosition(row, column) != null) {
                return chessBoard.getPosition(row, column);
            }
        }
    }

    public static boolean selectColor() throws IOException {
        System.out.println("Please select piece's color.\nEnter 1- for white; 2 - for black color; 0 - for exit from application. ");
        Scanner scanner = new Scanner(System.in);
        int color;
        while (true) {
            color = scanner.nextInt();
            switch (color) {
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                case 1:
                    System.out.println("White color selected.");
                    return false;
                case 2:
                    System.out.println("Black color selected.");
                    return true;
                default:
                    System.out.println("Wrong number. Please select 1- for white; 2 - for black color, 0 - for exit from application.");
            }
        }
    }

    public static Figure selectFigure() throws IOException {
        System.out.println("Please select chess figure.\nEnter 1- for King; 2 - for Queen; " +
                "3- for Pawn; 4 - for Bishop; 5 - for Knight; 6 - for Rook; " +
                "0 - for exit from application. ");
        Scanner scanner = new Scanner(System.in);
        int piece = scanner.nextInt();
        while (true) {
            switch (piece) {
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                case 1:
                    System.out.println("King selected.");
                    return new King();
                case 2:
                    System.out.println("Queen selected.");
                    return new Queen();
                case 3:
                    System.out.println("Pawn selected.");
                    return new Pawn();
                case 4:
                    System.out.println("Bishop selected.");
                    return new Bishop();
                case 5:
                    System.out.println("Knight selected.");
                    return new Knight();
                case 6:
                    System.out.println("Rook selected.");
                    return new Rook();
                default:
                    System.out.println("Please use number from 0 to 6 for selecting any options.");
            }
        }
    }

    public static void chooseMove(Figure figure) throws IOException {
        while (true) {
            Position newPosition = choosePosition();
            try {
                if (figure.moveValidator(figure.getPosition(), newPosition)) {
                    System.out.println("Figure moved");
                    figure.setPosition(newPosition);
                    if(askNextAction()){
                        chooseMove(figure);
                    }
                    return;
                }
            } catch (IOException e) {
                System.out.println("");
                e.printStackTrace();
            }
        }
    }


    public static boolean askNextAction() throws IOException {
        Scanner askNextAction = new Scanner(System.in);
        System.out.println("Do you want to choose another position or start game again?\n 1 - continue moving\n 0 - start again");
        switch (askNextAction.nextInt()) {
            case 1:
                System.out.println("Please choose next position: (e.g. A3)");
                return true;
            case 0:
                return false;
        }
        return false;
    }

    public static void startApplication() throws IOException {
        while (true) {
            figure = selectFigure();
            figure.setWhite(selectColor());
            System.out.println("Choose initial position (e.g. A3):");
            figure.setPosition(choosePosition());
            System.out.println("Select expected position (e.g. B3):");
            chooseMove(figure);
        }
    }
}
