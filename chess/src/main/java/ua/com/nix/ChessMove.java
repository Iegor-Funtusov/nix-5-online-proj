package ua.com.nix;

import lombok.SneakyThrows;
import ua.com.nix.board.ChessBoard;
import ua.com.nix.board.Position;
import ua.com.nix.pieces.*;
import ua.com.nix.utils.PieceUtil;
import ua.com.nix.utils.PositionUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChessMove {

    private final BufferedReader reader;
    private final ChessBoard board;

    public ChessMove() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.board = new ChessBoard();
    }

    public static void main(String[] args) {
        System.out.println("Hello, Grandmaster!");
        ChessMove chessMove = new ChessMove();
        chessMove.callInterface();
    }

    @SneakyThrows
    private void callInterface() {
        AbstractPiece piece;
        boolean movingFlag;
        boolean appFlag = true;
        while (appFlag){
            piece = getPiece();
            placePiece(piece);
            movingFlag = true;
            while (movingFlag){
                movePiece(piece);
                System.out.println("Piece is moved.");
                printMenu();
                switch(reader.readLine()){
                    case "1": {
                        movingFlag = true;
                        break;
                    }
                    case "2": {
                        movingFlag = false;
                        break;
                    }
                    case "0": {
                        movingFlag = false;
                        appFlag = false;
                        break;
                    }
                }
            }
        }
    }

    private void placePiece(AbstractPiece piece){
        Position position;
        while (true) {
            position = choosePositionToPlace();
            if (!piece.isPositionValid(board, position))
                System.out.println("Invalid position! Please try again.");
            else {
                piece.setPosition(position);
                board.addPiece(piece);
                board.printBoard();
                break;
            }
        }
    }

    @SneakyThrows
    private void movePiece(AbstractPiece piece){
        Position position;
        while (true) {
            position = choosePositionToMove();
            if (piece.isMoveValid(position, board)) {
                board.move(piece, position);
                board.printBoard();
                break;
            }
            else
                System.out.println("Invalid position to move!");
        }
    }

    private void printMenu(){
        System.out.print("What do you want to do? " +
                "\n 1 - continue moving this piece " +
                "\n 2 - create new piece " +
                "\n 0 - exit" +
                "\n --> ");
    }

    private Position choosePositionToMove(){
        Position position;
        do {
            System.out.print("Please, choose position on the board where you want to " +
                    "move your piece(e.g. a4): ");
        } while ((position = PositionUtil.createPosition(reader)) == null);
        return position;
    }

    private Position choosePositionToPlace(){
        Position position;
        do {
            System.out.print("Please, choose position on the board to place the piece(e.g. a2): ");
        } while ((position = PositionUtil.createPosition(reader)) == null);
        return position;
    }

    @SneakyThrows
    private AbstractPiece getPiece() {
        int pieceType = choosePieceType();
        boolean isWhite = choosePieceColor();
        return PieceUtil.createPiece(pieceType, isWhite);
    }

    @SneakyThrows
    private int choosePieceType(){
        int pieceType;
        while (true) {
            System.out.print("Please, choose piece that you want to place on the board:" +
                    "\n 1 - King " +
                    "\n 2 - Queen " +
                    "\n 3 - Bishop " +
                    "\n 4 - Knight " +
                    "\n 5 - Castle(Rook)" +
                    "\n 6 - Pawn " +
                    "\n --> ");
            pieceType = Integer.parseInt(reader.readLine());
            if (pieceType >= 0 && pieceType <= 6)
                break;
            else
                System.out.println("Invalid type. Try again.");
        }
        return pieceType;
    }

    @SneakyThrows
    private boolean choosePieceColor(){
        String colorType;
        boolean isWhite;
        while (true) {
            System.out.print("Please choose piece color: " +
                    "\n 1 - White " +
                    "\n 2 - Black " +
                    "\n -->  ");
            colorType = reader.readLine();
            if (colorType.equals("1")) {
                isWhite = true;
                break;
            }else if (colorType.equals("2")) {
                isWhite = false;
                break;
            }
            System.out.println("Invalid color. Try again.");
        }
        return isWhite;
    }
}
