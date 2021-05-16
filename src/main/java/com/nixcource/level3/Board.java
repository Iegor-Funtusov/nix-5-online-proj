package com.nixcource.level3;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int[][] grid;
    private int columns;
    private int rows;

    public Board(int columns, int rows) {
        Random random = new Random();
        this.columns = columns;
        this.rows = rows;
        this.grid = new int[columns][rows];
        for (int i = 0; i < columns; ++i) {
            for (int j = 0; j < rows; ++j) {
                this.grid[i][j] = random.nextInt(2);
            }
        }
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Board(int[][] grid) {
        this.grid = grid;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
}