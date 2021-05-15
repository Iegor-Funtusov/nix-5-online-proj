package ua.com.nkrasnovoronka.tasks.level1.task2;

import java.util.Locale;

public class Square {
    private String chessPosition;
    private int x;
    private int y;

    public Square(String chessPosition) {
        if(chessPosition.length() != 2){
            throw new IllegalArgumentException("Invalid chess position");
        }
        this.chessPosition = chessPosition;
        //convert chess coordinates to x y
        this.x = chessPosition.toLowerCase(Locale.ROOT).charAt(0) - 'a';
        this.y = chessPosition.toLowerCase(Locale.ROOT).charAt(1) - '1';
    }

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        chessPosition = new String(new char[]{(char) x, (char) y});

    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getChessPosition() {
        return chessPosition;
    }

}
