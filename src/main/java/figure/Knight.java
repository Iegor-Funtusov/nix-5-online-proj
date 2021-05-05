package figure;
import main.ValidContr;

import java.util.Scanner;

public class Knight  {
    public void knight (String place, String color) {
        Scanner sc = new Scanner(System.in);
        String Wf = "N ";
        String Bf = "N*";
        ValidContr.figure(place, color, Wf, Bf);
        char colorP = color.charAt(0);
        System.out.println("\n_____________________________________\n" +
                "Если хотите передвинуть фигуру, укажите поле\n" +
                "Например E5\n" +
                "Если вы хотите поменять фигуру укажите X");
        String move = sc.nextLine();
        char exit = move.charAt(0);
        if (exit == 'X' || exit =='x')
            return;

        while (true) {
            if (exit == 'X' || exit =='x')
                return;
            while (!ValidContr.correctPlace(move)) {
                System.out.println("Вы пытаетесь выйти за границы доски. Попробуйте еще раз.");
                move = sc.nextLine();
                exit = move.charAt(0);
                if (exit == 'X' || exit =='x')
                    return;
            }
            while (!ValidContr.moveKnight(place, move, colorP)) {
                if (exit == 'X' || exit == 'x')
                    return;
                while (!ValidContr.correctPlace(move)) {
                    System.out.println("Вы пытаетесь выйти за границы доски. Попробуйте еще раз.");
                    move = sc.nextLine();
                    exit = move.charAt(0);
                    if (exit == 'X' || exit == 'x')
                        return;
                }
                if(!ValidContr.moveKnight(place, move, colorP)) {
                    System.out.println("Конь так не ходит, он ходит буквой Г. Попробуйте еще раз.");
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
