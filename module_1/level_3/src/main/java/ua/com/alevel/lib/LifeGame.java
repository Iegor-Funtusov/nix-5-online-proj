package ua.com.alevel.lib;

public class LifeGame {

    public static void gameOfLife(int[][] board) {
        int x = board.length;
        int y = board[0].length;
        int willLive = 2;
        int willDie = -1;

        for (int i = 0; i< x; i++){
            for (int j = 0; j< y; j++){
                int alive = countAlive(i,j,board);
                if (board[i][j] == 0 && alive == 3)
                    board[i][j] = willLive;
                else if (board[i][j] == 1){
                    if (alive == 2 || alive == 3)
                        continue;
                    board[i][j] = willDie;
                }
            }
        }
        for (int i = 0; i< x; i++){
            for (int j = 0; j< y; j++){
                if (board[i][j] == willDie)
                    board[i][j] = 0;
                if (board[i][j] == willLive)
                    board[i][j] = 1;
            }
        }
        printBoard(x, y, board);
    }

    public static void printBoard(int x, int y, int[][] board){
        for(int i = 0 ;i < x; i++){
            for(int j = 0; j < y; j++){
                System.out.print(board[i][j] + "   ");
            }
            System.out.println("\n");
        }
    }

    private static int countAlive(int i, int j, int[][] board){
        int count = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};

        for (int[] dir:dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length){
                if (board[x][y] == 1) count ++;
            }
        }
        return count;
    }
}
