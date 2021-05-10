package ua.com;


import ua.com.Board.Board;
import ua.com.Pieces.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Service {
    public static Board board = new Board();
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static boolean chooseColor() throws IOException {
        System.out.println("'0' - white\n '1' - black\n");
        String choice = reader.readLine();
        switch (choice){
            case "0":
                return true;
            case "1":
                return false;
            default:
                System.out.println("The invalid data range!\n");
                return chooseColor();
        }
    }

    private static Piece createNew() throws IOException {
        System.out.println("Choose a piece by choosing a number: \n" +
                "'0' - Pawn\n" +
                "'1' - Bishop\n" +
                "'2' - Knight\n" +
                "'3' - Rook\n" +
                "'4' - King\n" +
                "'5' - Queen\n" +
                "Your variant: ");
        String choice = reader.readLine();
        switch (choice){
            case "0":
                return new Pawn("♙", chooseColor());
            case "1":
                return new Bishop("♗", chooseColor());
            case "2":
                return new Knight("♘", chooseColor());
            case "3":
                return new Rook("♖", chooseColor());
            case "4":
                return new King("♔",chooseColor());
            case "5":
                return new Queen("♕", chooseColor());
            default:
                System.out.println("The invalid data range!\n");
                return createNew();
        }
    }

    public static Piece choosePiece() throws IOException {
        System.out.println("\nPress '0' if you'd like to create a new one piece" +
                                 " '1' if you'd like to choose an existed one \n");
        int choice = Integer.parseInt(reader.readLine());
        switch (choice) {
            case 0:
                return createNew();
            case 1:
                System.out.println("Enter the x and y of desired piece: ");
                int[] coords = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
                if (!board.inBounds(coords[0], coords[1])) {
                    System.out.println("The invalid data range!\n");
                    return choosePiece();
                }
                if (board.isFieldEmpty(coords[0], coords[1])) {
                    System.out.println("The field is empty. Try another!");
                    return choosePiece();
                }
                return board.getPiece(coords[0], coords[1]);
            default:
                System.out.println("The invalid data range!\n");
                return choosePiece();
        }
    }

    public static void Game() throws Exception {
        while(true) {
            System.out.println("Enter '0' to start/continue game and something else to end");
            if (!reader.readLine().equals("0")) {
                System.exit(0);
            }
            board.print();
            Piece piece = choosePiece();
            System.out.println("Enter the destination x and y: ");
            int[] coords = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            while (!board.inBounds(coords[0], coords[1])) {
                System.out.println("Out of boundaries values. Try another!\n");
                coords = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            }
            while (!board.isFieldEmpty(coords[0], coords[1])) {
                System.out.println("The field is busy. Try another!\n");
                coords = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            }
            board.board[piece.getX()][piece.getY()] = null;
            piece.move(coords[0], coords[1]);
            board.board[coords[0]][coords[1]] = piece;
        }

    }

}
