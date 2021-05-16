package com.nixcource.level3;

public class GameOfLife {
    private final Board board;

    public GameOfLife(int columns, int rows) {
        this.board = new Board(columns, rows);
    }

    public void displayBoard() {
        for (var i : board.getGrid()) {
            for (var j : i) {
                if (j == 0) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
    }

    public void clock() {
        int[][] nextGrid = new int[board.getColumns()][board.getRows()];

        for (int column = 1; column < this.board.getColumns() - 1; column++) {
            for (int row = 1; row < board.getRows() - 1; row++) {
                int liveNeighbour = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        liveNeighbour += board.getGrid()[column + i][row + j];
                    }
                }
                liveNeighbour -= board.getGrid()[column][row];

                if ((board.getGrid()[column][row] == 0) && (liveNeighbour == 3)) {
                    nextGrid[column][row] = 1;
                } else if ((board.getGrid()[column][row] == 1) && (liveNeighbour > 3)) {
                    nextGrid[column][row] = 0;
                } else if ((board.getGrid()[column][row] == 1) && (liveNeighbour < 2)) {
                    nextGrid[column][row] = 0;
                } else {
                    nextGrid[column][row] = board.getGrid()[column][row];
                }
            }
        }
        board.setGrid(nextGrid);
    }
}
