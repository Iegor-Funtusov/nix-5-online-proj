package com.nixsolutions.courses.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class Level3Utils {

//    private static final int RANDOM_MIN = 0;
//    private static final int RANDOM_MAX = 1;

    public static int[][] createCells(BufferedReader reader, int n, int m) throws IOException {
        System.out.println("Do you want to input cells values?\n0 - no\n1 - yes");
        int[][] board = new int[n][m];
        if(reader.readLine().equals("0")){
            board = generateCells(n, m);
        } else {
             System.out.println("Enter number of alive cells:");
             int alive = Integer.parseInt(reader.readLine());
             int i,j;
             while(alive != 0) {
                 System.out.println("Enter x:");
                 i = Integer.parseInt(reader.readLine());
                 System.out.println("Enter y:");
                 j = Integer.parseInt(reader.readLine());
                 System.out.println("Next cell");
                 board[i][j] = 1;
                 alive--;
             }
        }
        return board;
    }

    public static int[][] generateCells(int n, int m) {
        Random random = new Random();
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = random.nextInt(2);
            }
        }
        return result;
    }

}
