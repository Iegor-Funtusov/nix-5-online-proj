package com.nixsolutions.courses.level3;

import com.nixsolutions.courses.utils.Level3Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Level3 {

    static int[][] board;
    static int n;
    static int m;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void allDead() throws IOException {
        System.out.println("All cells are dead. Create new alive cells?\n0 - no\n1 - yes");
        if(reader.readLine().equals("1")) {
            board = Level3Utils.createCells(reader, n, m);
            printBoard();
        }
    }

    public static int countAliveNeighbors(int x, int y) {
        int count = 0;
        if(y < m-1 && board[x][y+1] == 1) count++;
        if(y > 0 && board[x][y-1] == 1) count++;
        if(x < n-1 && board[x+1][y] == 1) count++;
        if(x > 0 && board[x-1][y] == 1) count++;
        if(x < n-1 && y < m-1 && board[x+1][y+1] == 1) count++;
        if(x > 0 && y >0 && board[x-1][y-1] == 1) count++;
        return count;
    }

    public static void nextState() throws IOException {
        int totalAlive = 0;
        int alive;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                alive = countAliveNeighbors(i,j);
                if(board[i][j] == 1) {
                    if (alive < 2 || alive > 3) {
                        board[i][j] = 0;
                    } else {
                        totalAlive++;
                    }
                } else if (alive == 3){
                    board[i][j] = 1;
                    totalAlive++;
                }
            }
        }
        System.out.println(":::::::::Next state:::::::::");
        printBoard();
        if(totalAlive == 0) allDead();
    }

    public static void printBoard() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void run() throws IOException {
        System.out.println("-----Game of life-----");
        System.out.println("Enter width of board (n):");
        n = Integer.parseInt(reader.readLine());
        System.out.println("Enter length of board (m):");
        m = Integer.parseInt(reader.readLine());
        board = Level3Utils.createCells(reader, n, m);
        printBoard();
        while(true) {
            nextState();
            System.out.println("Continue?\n0 - no\n1 - yes");
            if(reader.readLine().equals("0")) break;
        }
    }

}
