package ua.com.Board;

import ua.com.Pieces.Piece;

public class Board {
    private final int x = 8;
    private final int y = 8;
    private final Piece[][] board;

    public Board(){
        board = new Piece[x][y];
    }

    public boolean inBounds(int x, int y) {
        return (x < this.x && x >= 0) && (y < this.y && y >= 0);
    }
}
