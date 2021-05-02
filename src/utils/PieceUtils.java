package utils;

import entities.*;

public class PieceUtils {

    public static Piece switchPiece(int piece, int colour, int a, int b) {

        switch (piece) {
            case 1 -> {
                King king = new King();
                setParam(king, colour, a, b);
                return king;
            }
            case 2 -> {
                Queen queen = new Queen();
                setParam(queen, colour, a, b);
                return queen;
            }
            case 3 -> {
                Horse horse = new Horse();
                setParam(horse, colour, a, b);
                return horse;
            }
            case 4 -> {
                Bishop bishop = new Bishop();
                setParam(bishop, colour, a, b);
                return bishop;
            }
            case 5 -> {
                Rook rook = new Rook();
                setParam(rook, colour, a, b);
                return rook;
            }
            case 6 -> {
                Pawn pawn = new Pawn();
                setParam(pawn, colour, a, b);
                return pawn;
            }
            default -> System.out.println("Wrong choice. Restart the program.");
        }
        return null;
    }

    public static void setParam(Piece piece, int colour, int a, int b){

        piece.setColour(colour);
        piece.setX(a);
        piece.setY(b);
    }
}