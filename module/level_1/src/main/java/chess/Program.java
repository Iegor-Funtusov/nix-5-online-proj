package chess;

import java.util.Scanner;

public class Program {
    public static void controle(){
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        board.boardPlacing();
        System.out.println("Choose the place(input 2 numbers)");
        System.out.print("Horizontal number: ");
        int horNumber = Control.correctNum();
        System.out.print("Vertical number: ");
        int verNumber = Control.correctNum();
        Knight knight = new Knight();
        knight.knight(horNumber, verNumber);
    }
}
