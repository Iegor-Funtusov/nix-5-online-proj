package ua.com.nkrasnovoronka.level3.task1;

public class GameLife {
    private int width;
    private int height;
    private int[][] board;

    public GameLife(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }


    public void displayBoard() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board[x][y] == 0) {
                    System.out.print('.');
                } else {
                    System.out.print('*');
                }
            }
            System.out.println();
        }
    }

    public int countNeighbors(int x, int y) {
        int res = 0;

        //left
        res += checkIndex(x - 1, y);
        //right
        res += checkIndex(x + 1, y);
        //up
        res += checkIndex(x, y + 1);
        //down
        res += checkIndex(x, y - 1);

        //down-left
        res += checkIndex(x - 1, y - 1);
        //down-right
        res += checkIndex(x + 1, y - 1);

        //up-left
        res += checkIndex(x - 1, y + 1);

        //up-right
        res += checkIndex(x + 1, y + 1);


        return res;
    }

    public int checkIndex(int x, int y) {
        if (x < 0 || x > width - 1) {
            return 0;
        }
        if (y < 0 || y > height - 1) {
            return 0;
        }
        return board[x][y];
    }

    public void move() {
        int[][] newBoard = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int aliveNeighbors = countNeighbors(x, y);

                if (board[x][y] == 1) {
                    if (aliveNeighbors < 2) {
                        newBoard[x][y] = 0;
                    } else if (aliveNeighbors == 2 || aliveNeighbors == 3) {
                        newBoard[x][y] = 1;
                    } else {
                        newBoard[x][y] = 0;
                    }
                } else {
                    if (aliveNeighbors == 3) {
                        newBoard[x][y] = 1;
                    }
                }
            }

        }
        board = newBoard;
    }

    public void setAlive(int x, int y) {
        this.board[x][y] = 1;
    }

    public void setDead(int x, int y) {
        this.board[x][y] = 0;
    }
}
