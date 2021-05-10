package ua.com.Board;

import ua.com.Pieces.Piece;

public class Board {
    private final int x = 8;
    private final int y = 8;
    public final Piece[][] board;

    public Board() {
        board = new Piece[x][y];
    }

    public boolean inBounds(int x, int y) {
        return (x < this.x && x >= 0) && (y < this.y && y >= 0);
    }

    public Piece getPiece(int x, int y){
        return board[x][y];
    }

    public boolean isFieldEmpty(int x, int y) {
        return getPiece(x, y) == null;
    }

    public void print() {
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null){
                    System.out.print("   |");
                    continue;
                }
                System.out.print(" "+board[i][j].symbol + " |");
            }
            System.out.print("\n");
        }
        System.out.print("   |");
        for (int i = 0; i < 8; i++) {
            System.out.print(" " + i + " |");
        }
        System.out.print("   |");
    }

}
