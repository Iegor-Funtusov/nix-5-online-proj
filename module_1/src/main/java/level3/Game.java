package level3;

import java.util.Scanner;

/**
 * Реализация игры "Жизнь"
 */

public class Game {
    private final int m;
    private final int n;
    private int generation;
    public static boolean[][] boardC;

    public Game(int m, int n) {
        this.m = m;
        this.n = n;
        this.generation = 0;
    }

    public static void Main() {
        Scanner inputUser = new Scanner(System.in);
        System.out.print("Введите высоту доски m: ");
        int m = inputUser.nextInt();
        System.out.print("Введите ширину доски n: ");
        int n = inputUser.nextInt();
        Game game = new Game(m, n);
        game.createBoard();
        game.printBoard();
        inputUser = new Scanner(System.in);
        System.out.print("Введите количество поколений: ");
        int generations = inputUser.nextInt();
        int i = 0;
        while (i < generations) {
            game.generateNewBoard();
            game.printBoard();
            i++;
        }
    }

    // создание экземпляра доски
    public void createBoard() {
        boolean temp[][] = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                temp[i][j] = (Math.random() < 0.5);
        }
        boardC = temp;
    }

    //печать доски
    public void printBoard() {
        //System.out.println();
        System.out.println("Поколение : " + generation);
        //System.out.println();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (boardC[i][j])
                    System.out.print("#");
                else
                    System.out.print(".");
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        //	System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~");
    }

    // создание новой доски
    public void generateNewBoard() {
        boolean temp[][] = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                temp[i][j] = check(i, j);
        }
        boardC = temp;
        generation++;
    }

    //проверяет, будет жить или умрёт ячейка в следующем поколении
    public boolean check(int row, int col) {
        boolean curr = boardC[row][col];
        int liveNeighbours = 0;
        for (int i = (-1); i <= 1; i++) {
            int r = row + i;
            if (r < 0 || r >= m)
                continue;
            for (int j = (-1); j <= 1; j++) {
                int c = col + j;
                if (c < 0 || c >= n)
                    continue;
                if (boardC[r][c])
                    liveNeighbours++;
            }
        }
        if (curr) liveNeighbours--;
        if (liveNeighbours == 2 && curr)
            return true;
        else return liveNeighbours == 3;
    }
}
