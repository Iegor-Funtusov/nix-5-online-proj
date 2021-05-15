package chess;

import java.util.Scanner;

public class Board {
    public void boardPlacing (){
        String [][] Board = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1)
                    Board[i][j] = "%%";
                else
                    Board[i][j] = "  ";
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(Board[i][j]);
                if(j == 7)
                    System.out.println("  " + (i+1));
            }
        }
        for(int i = 0, diff = i + 8; i < diff; i++)
        {
            System.out.print((i+1) + " ");
        }
        System.out.println();
    }

    public void updatedBoard (int horizontal, int vertical) {
        Scanner sc = new Scanner(System.in);

        String figure = "Kn";


        String[][] Board = new String[8][8];
        for (int i = vertical - 4, verDiff = i + 8, h = 0; i < verDiff; i++, h++) {
            for (int j = horizontal - 4, horDiff = j + 8, v = 0; j < horDiff; j++, v++) {
                if (horizontal == j && vertical == i) {
                    Board[h][v] = figure;
                    continue;
                }
                if (Math.abs(i % 2) == 0 && Math.abs(j % 2) == 0 || Math.abs(i % 2) == 1 && Math.abs(j % 2) == 1)
                    Board[h][v] = "%%";
                else
                    Board[h][v] = "  ";
            }
        }

        for (int i = 0, hor = horizontal - 4; i < 8; i++, hor++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(Board[i][j]);
                if (j == 7)
                    System.out.println("  " + (hor));
            }
        }

        int i;
        i = vertical - 4;
        int min, max, diff;
        String strMin = String.valueOf(i);
        String strMax = String.valueOf(i+8);
        min = strMin.length();
        max = strMax.length();

        if(min > max)
            diff = min;
        else
            diff = max;

        for(int m = 0; m < diff; m++) {
            for (int k = 0; k < 8; k++) {
                i = vertical - 4 + k;

                strMin = String.valueOf(i);
                if(strMin.length() + 1 < m)
                    continue;
                try {
                    char symb = strMin.charAt(m);
                    if(symb == '-')
                        System.out.print("|" + " ");
                    else
                        System.out.print(symb + " ");
                }
                catch (StringIndexOutOfBoundsException ex)
                {
                    System.out.print("  ");
                    continue;
                }
            }
            System.out.println();
        }
    }
}
