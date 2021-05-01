package Figures;

import mainClasses.Control;

import java.util.Scanner;

public class King {
    public void king (String place, String color){
        Scanner sc = new Scanner(System.in);
        String Wfigure = "Wk";
        String Bfigure = "Bk";
        Control.figure(place, color, Wfigure, Bfigure);
        char colorP = color.charAt(0);
        System.out.println("\nInput place where you want to move (e.g. e5 or c3)\n " +
                "If you want to change piece, write \"p\"");
        String move = sc.nextLine();
        char exit = move.charAt(0);
        if (exit == 'P' || exit =='p')
            return;

        while (true) {
            if (exit == 'P' || exit =='p')
                return;
            while (!Control.correctPlace(move)) {
                System.out.println("Input is not correct. Repeat attempt");
                move = sc.nextLine();
                exit = move.charAt(0);
                if (exit == 'P' || exit =='p')
                    return;
            }
            while (!Control.moveKing(place, move, colorP)) {
                if (exit == 'P' || exit == 'p')
                    return;
                while (!Control.correctPlace(move)) {
                    System.out.println("Input is not correct. Repeat attempt");
                    move = sc.nextLine();
                    exit = move.charAt(0);
                    if (exit == 'P' || exit == 'p')
                        return;
                }
                if(!Control.moveKing(place, move, colorP)) {
                    System.out.println("King can't move there. Input new place.");
                }
                move = sc.nextLine();
                exit = move.charAt(0);
                if (exit == 'P' || exit == 'p')
                    return;
                while (!Control.correctPlace(move)) {
                    System.out.println("Input is not correct. Repeat attempt");
                    move = sc.nextLine();
                    exit = move.charAt(0);
                    if (exit == 'P' || exit == 'p')
                        return;
                }
            }
            Control.figure(move, color, Wfigure, Bfigure);
            place = move;
            System.out.println("Next step or changing the piece \"p\"");
            move = sc.nextLine();
        }
    }
}
