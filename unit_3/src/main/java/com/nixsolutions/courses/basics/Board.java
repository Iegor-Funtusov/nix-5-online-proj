package com.nixsolutions.courses.basics;

import com.nixsolutions.courses.basics.Square;

public class Board {
    Square[][] squares;

    public Board() {
        this.init();
    }

    public Square getSquare(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Not valid position, out of boundaries, choose again");
            return null;
        }
        return squares[x][y];
    }

    public void init() {
        squares = new Square[8][8];
        boolean tmp = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(i, j, tmp);
                tmp = !tmp;
            }
            tmp = !tmp;
        }
    }
}
