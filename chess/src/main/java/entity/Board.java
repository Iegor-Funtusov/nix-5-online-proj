package entity;

public class Board {
    private int [][] board;

    public boolean setBoard(Figure figure, int a, int b){
        if(board[a][b]!=1){
            board[a][b]=1;
            return true;
        }
        return false;
    }

    public Board() {
        board = new int [8][8];
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                board[i][j] = 0;
            }
        }
    }

    public void showBoard() {
        System.out.println("      A     B     C     D     E     F     G     H   ");
        for (int i = 0; i < 8; i++) {
            System.out.println("   #################################################");
            System.out.println("   #     #     #     #     #     #     #     #     #");
            System.out.print( 8-i + "  ");
            for (int j = 0; j < 8; j++) {
                System.out.print("#  " + board[i][j] + "  ");
            }
            System.out.print("#");
            System.out.println(" ");
        }
        System.out.println("   #################################################");
    }

}
