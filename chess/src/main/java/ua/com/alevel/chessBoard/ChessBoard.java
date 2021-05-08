package ua.com.alevel.chessBoard;

import ua.com.alevel.figure.*;

public class ChessBoard {

    Position [][] position = new Position[8][8];

    public ChessBoard(){
        ChessBoardBuilder chessBoardBuilder = new ChessBoardBuilder();
        this.newBoard();
    }

    public Position getPosition(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Not valid position, please select position under boundaries:");
            return null;
        }
        return position[x][y];
    }

    public void newBoard() {
        //  white figures
        position[0][0] = new Position(0, 0, new Rook());
        position[0][1] = new Position(0, 1, new Knight());
        position[0][2] = new Position(0, 2, new Bishop());
        position[0][3] = new Position(0, 3, new Queen());
        position[0][4] = new Position(0, 4, new King());
        position[0][5] = new Position(0, 5, new Bishop());
        position[0][6] = new Position(0, 6, new Knight());
        position[0][7] = new Position(0, 7, new Rook());

        //pawns
        for (int i = 1; i == 1; i++) {
            for (int j = 0; j < 8; j++) {
                position[i][j] = new Position(i, j, new Pawn());
            }
        }

        //  black figures
        position[7][0] = new Position(7, 0, new Rook());
        position[7][1] = new Position(7, 1, new Knight());
        position[7][2] = new Position(7, 2, new Bishop());
        position[7][3] = new Position(7, 3, new Queen());
        position[7][4] = new Position(7, 4, new King());
        position[7][5] = new Position(7, 5, new Bishop());
        position[7][6] = new Position(7, 6, new Knight());
        position[7][7] = new Position(7, 7, new Rook());

        //pawns
        for (int i = 6; i == 6; i++) {
            for (int j = 0; j < 8; j++) {
                position[i][j] = new Position(i, j, new Pawn());
            }
        }

        // position without figures
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                position[i][j] = new Position(i, j, null);
            }
        }
    }
}
