package utils;

import entities.*;

public class Utils {

    public static Piece switchPiece(int piece, int colour, int x, int y) {

        switch (piece) {
            case 1 -> {
                King king = new King();
                setParam(king, colour, x, y);
                return king;
            }
            case 2 -> {
                Queen queen = new Queen();
                setParam(queen, colour, x, y);
                return queen;
            }
            case 3 -> {
                Horse horse = new Horse();
                setParam(horse, colour, x, y);
                return horse;
            }
            case 4 -> {
                Bishop bishop = new Bishop();
                setParam(bishop, colour, x, y);
                return bishop;
            }
            case 5 -> {
                Rook rook = new Rook();
                setParam(rook, colour, x, y);
                return rook;
            }
            case 6 -> {
                Pawn pawn = new Pawn();
                setParam(pawn, colour, x, y);
                return pawn;
            }
            default -> System.out.println("Wrong choice. Restart the program.");
        }
        return null;
    }

    public static int defineY(char Y){

        switch (Y) {
            case 'A' -> {
                return 0;
            }
            case 'B' -> {
                return 1;
            }
            case 'C' -> {
                return 2;
            }
            case 'D' -> {
                return 3;
            }
            case 'E' -> {
                return 4;
            }
            case 'F' -> {
                return 5;
            }
            case 'G' -> {
                return 6;
            }
            case 'H' -> {
                return 7;
            }
            default -> System.out.println("Wrong choice. Restart the program.");
        }
        return -1;
    }

    public static void setParam(Piece piece, int colour, int a, int b){

        piece.setColour(colour);
        piece.setX(a);
        piece.setY(b);
    }
}