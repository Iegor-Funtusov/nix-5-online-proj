package figure;

import java.util.Scanner;

import main.ValidContr;

public class Bishop {
    public void bishop(String place, String color) {
        Scanner sc = new Scanner(System.in);
        String Wf = "B ";
        String Bf = "B*";
        ValidContr.figure(place, color, Wf, Bf);
        char colorP = color.charAt(0);
        System.out.println("\n_____________________________________\n" +
                "Если хотите передвинуть фигуру, укажите поле\n" +
                "Например E5\n" +
                "Если вы хотите поменять фигуру укажите X");
        String move = sc.nextLine();
        char exit = move.charAt(0);
        if (exit == 'X' || exit == 'x')
            return;

        while (true) {
            if (exit == 'X' || exit == 'x')
                return;
            while (!ValidContr.correctPlace(move)) {
                System.out.println("Вы пытаетесь выйти за границы доски. Попробуйте еще раз.");
                move = sc.nextLine();
                exit = move.charAt(0);
                if (exit == 'X' || exit == 'x')
                    return;
            }
            while (!ValidContr.moveBishop(place, move, colorP)) {
                if (exit == 'X' || exit == 'x')
                    return;
                while (!ValidContr.correctPlace(move)) {
                    System.out.println("Вы пытаетесь выйти за границы доски. Попробуйте еще раз.");
                    move = sc.nextLine();
                    exit = move.charAt(0);
                    if (exit == 'X' || exit == 'x')
                        return;
                }
                if (!ValidContr.moveBishop(place, move, colorP)) {
                    System.out.println("Слон так не ходит, он ходит по диагонали. Попробуйте еще раз.");
                }
                move = sc.nextLine();
                exit = move.charAt(0);
                if (exit == 'X' || exit == 'x')
                    return;
                while (!ValidContr.correctPlace(move)) {
                    System.out.println("Вы пытаетесь выйти за границы доски. Попробуйте еще раз.");
                    move = sc.nextLine();
                    exit = move.charAt(0);
                    if (exit == 'X' || exit == 'x')
                        return;
                }
            }
            ValidContr.figure(move, color, Wf, Bf);
            place = move;
            System.out.println("___________________________________________\n" +
                    "Если хотите передвинуть фигуру, укажите поле\n" +
                    "Если вы хотите поменять фигуру укажите X");
            move = sc.nextLine();
        }
    }

}
