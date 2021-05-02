package entities;

import java.util.ArrayList;

public class Board {

    public static final String WHITE = "\u001B[30m";
    public static final String BLACK = "\u001B[0m";

    private final char [][] board;
    public static ArrayList<Piece> pieces = new ArrayList<>();

    public Board() {

        board = new char [8][8];
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                board[i][j] = '#';
            }
        }
    }

    public boolean fillBoard(int a, int b){

        return board[a][b] == '#';
    }

    public boolean ifEmpty(int x, int y){

        for (Piece piece : pieces){
            if(piece.getX() == x && piece.getY() == y){
                return false;
            }
        }
        return true;
    }

    public void fillEmptyCell(int x, int y){

        board[x][y] = '#';
    }

    public void setPiece(Piece piece){

        if(fillBoard(piece.getX(), piece.getY())){
            board[piece.getX()][piece.getY()] = piece.getType();
            pieces.add(piece);
        }
    }

    public void printBoard() {

        System.out.println("     A   B   C   D   E   F   G   H");

        for (int i = 0; i < 8; i++) {
            if(i == 0){
                System.out.println("   =================================");
            }
            else{
                System.out.println("   ---------------------------------");
            }

            System.out.print( i+1 + "  ");

            for (int j = 0; j < 8; j++) {
                if(!fillBoard(i, j)){
                    for(Piece piece : pieces) {
                        if(piece.getX() == i && piece.getY() == j){
                            System.out.print("| " + piece.getColour() + board[i][j] + " ");
                        }
                    }
                }
                else if ((i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0)){
                    System.out.print("| " + BLACK + board[i][j] + " " + WHITE);
                }
                else {
                    System.out.print("| " + board[i][j] + " " );
                }
            }
            System.out.print("|");
            System.out.println(" ");
        }
        System.out.println("   =================================");
    }
}