package ua.com.nix.utils;

import ua.com.nix.pieces.*;

public class PieceUtil {

    public static void printPiece(AbstractPiece piece){
        if(piece.isWhite())
            System.out.print(" w");
        else
            System.out.print(" b");
        System.out.print(piece.getRepresentation());
        System.out.print("|");
    }

    public static AbstractPiece createPiece(int pieceType, boolean isWhite){
        AbstractPiece piece = null;
        switch (pieceType){
            case 1: {
                piece = new King(isWhite);
                break;
            }
            case 2: {
                piece = new Queen(isWhite);
                break;
            }
            case 3: {
                piece = new Bishop(isWhite);
                break;
            }
            case 4: {
                piece = new Knight(isWhite);
                break;
            }
            case 5: {
                piece = new Castle(isWhite);
                break;
            }
            case 6: {
                piece = new Pawn(isWhite);
                break;
            }
            default: {
                System.out.println("Wrong type, choose one type from list below!");
                break;
            }
        }
        return piece;
    }


}
