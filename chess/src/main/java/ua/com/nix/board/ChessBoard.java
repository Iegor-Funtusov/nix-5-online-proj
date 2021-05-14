package ua.com.nix.board;

import ua.com.nix.pieces.AbstractPiece;
import ua.com.nix.utils.BoardUtil;
import ua.com.nix.utils.PieceUtil;

public class ChessBoard {

    private AbstractPiece[][] board;

    public ChessBoard() {
        this.board = new AbstractPiece[8][8];
    }


    public void move(AbstractPiece piece, Position newPosition){
        board[piece.getPosition().getRow()][piece.getPosition().getColumn()] = null;
        piece.setPosition(newPosition);
        this.addPiece(piece);
    }

    public void addPiece(AbstractPiece piece){
        board[piece.getPosition().getRow()][piece.getPosition().getColumn()] = piece;
    }

    public void printBoard(){
        System.out.println("\t\t\tChess Board");
        this.printColumnsHeader();
        this.printLine();
        for (int i = 0; i < 8; i++) {
            System.out.print( 8 - i + " |");
            for (int j = 0; j < 8; j++) {
                    if(isSpaceEmpty(i, j))
                        if(BoardUtil.isSpaceWhite(i, j)){
                            System.out.print("%%%|");
                        }else
                            System.out.print("   |");
                    else {
                        PieceUtil.printPiece(board[i][j]);
                    }
                }
            System.out.print( " " + (8 - i));
            this.printLine();
        }
        this.printColumnsHeader();
        System.out.println();
    }

    public boolean isSpaceEmpty(int i, int j){
        if(board[i][j] == null)
            return true;
        return false;
    }

    private void printLine(){
        System.out.print("\n  ");
        for (int i = 0; i < 33; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void printColumnsHeader(){
        System.out.print("    ");
        for (int i = 0; i < 8; i++) {
            System.out.print((char)('a' + i) + "   ");
        }
    }


}
