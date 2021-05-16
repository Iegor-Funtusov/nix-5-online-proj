package com.utils.level.one.task.two;

public class KnightMove {

    public static void Move(int posx, int posy, int movex, int movey) {
        Board b = new Board(posx, posy);
        b.PlacePiece(movex, movey);
        b.Print();
    }
}
