package com.k4rnaj1k.level3;

import static com.k4rnaj1k.RunModule.s;

public class GameOfLife {
    private static boolean[][] field;

    public static String name = "Game of Life";

    public static void run() {
        System.out.println("Please input size of the field(m, n)");
        int m, n;
        m = s.nextInt();
        n = s.nextInt();
        field = new boolean[m][n];
        System.out.println("please input the coordinates of the alive cells");
        do {
            int x = s.nextInt();
            int y = s.nextInt();
            makeAlive(x, y, field);
            System.out.println("Continue input?(y/n)");
        } while (s.next().toLowerCase().startsWith("y"));
        System.out.println("Previous step");
        print_matrix();
        nextStep();
        System.out.println("Next step");
        print_matrix();
    }

    public static void print_matrix() {
        for (boolean[] n :
                field) {
            for (boolean c :
                    n) {
                if (c)
                    System.out.print(".");
                else
                    System.out.print("o");
            }
            System.out.println();
        }
    }

    public static void nextStep() {
        boolean[][] temp = new boolean[field.length][field[0].length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                int alivenear = countAliveNearby(i, j);
                if(field[i][j]) {
                    if (alivenear < 2)
                        makeDead(i, j, temp);
                    else if (alivenear == 2 || alivenear == 3)
                        makeAlive(i, j, temp);
                    else if (alivenear > 3)
                        makeDead(i, j, temp);
                }else{
                    if(alivenear == 3)
                        makeAlive(i,j,temp);
                }
            }
        }
        field = temp;
    }

    public static int countAliveNearby(int x, int y) {
        int aliveNear = 0;
        aliveNear += isAlive(x - 1, y - 1);
        aliveNear += isAlive(x, y - 1);
        aliveNear += isAlive(x + 1, y - 1);

        aliveNear += isAlive(x - 1, y);
        aliveNear += isAlive(x + 1, y);

        aliveNear += isAlive(x - 1, y + 1);
        aliveNear += isAlive(x, y + 1);
        aliveNear += isAlive(x + 1, y + 1);
        return aliveNear;
    }

    public static int isAlive(int x, int y) {
        if (x < 0 || y < 0)
            return 0;
        if (x >= field.length || y >= field[0].length)
            return 0;
        else if(field[x][y])
            return 1;
        else
            return 0;
    }

    public static void makeDead(int x, int y, boolean[][] field) {
        field[x][y] = false;
    }

    public static void makeAlive(int x, int y, boolean[][]field) {
        try {
            if (x > field.length || y > field[0].length) {
                throw new Exception("Out of bounds.");
            }
            field[x][y] = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
