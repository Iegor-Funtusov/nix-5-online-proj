package ua.com.module1;

import java.util.Random;

public class LifeGame {
    private int w;
    private int h;
    private static boolean [][] board;

    public static void setBoard(boolean[][] board) {
        LifeGame.board = board;
    }

    public LifeGame(int w, int h) {
        this.w = w;
        this.h = h;
        Random random = new Random();
        board = new boolean[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                board[i][j] = random.nextBoolean();
            }
        }
    }

    public static void printBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++){
                if(board[i][j]){
                    System.out.print(" A ");
                }
                else {
                    System.out.print(" . ");
                }
            }
            System.out.println("");
        }
        System.out.println(" ");
        System.out.println(" ");
    }

    public static void nextGeneration(int w, int h){
        boolean[][] next = new boolean[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                next[i][j] = predict(i, j, board);
                }
            }
        setBoard(next);
        printBoard();
    }

        public static boolean predict(int a, int b, boolean [][] board){
            int lifeCell = 0;
                if (board[a][b]) {
                    lifeCell = -1;
                }
                    for (int i = -1; i <= 1; i++) {
                        if (a + i  < 0 || a + i >= board.length){
                            continue;}
                        for (int j = -1; j <= 1; j++) {
                            if (b + j < 0 || b + j >= board[0].length){
                                continue;}
                                    if (board[a + i][b + j]){
                                        lifeCell++;}
                }
            }
            return getResult(lifeCell);
        }

    public static boolean getResult(int a){
        boolean k;
        k = a == 2 || a == 3;
        return k;
    }

}


