package figure;
import main.ValidContr;

import java.util.Scanner;

public class Pawn {
    public void pawn (String place, String color) {
        Scanner sc = new Scanner(System.in);
        char colorP = color.charAt(0);
        String Wf = "P ";
        String Bf = "P*";
        ValidContr.figure(place, color, Wf, Bf);

        System.out.println("\n_____________________________________\n" +
                "Если хотите передвинуть фигуру, укажите поле\n" +
                "Например E5\n" +
                "Если вы хотите поменять фигуру укажите X");
        String move = sc.nextLine();
        char exit = move.charAt(0);
        if (exit == 'X' || exit =='x')
            return;
        while (true) {
            while (!ValidContr.movePawn(place, move, colorP)) {
                System.out.println("Пешка так не ходит, она ходит на одну клетку вперед.");
                move = sc.nextLine();
                exit = move.charAt(0);
                if (exit == 'X' || exit =='x')
                    return;
                while (!ValidContr.correctPlace(move)) {
                    System.out.println("Вы пытаетесь выйти за границы доски");
                    move = sc.nextLine();
                    exit = move.charAt(0);
                    if (exit == 'X' || exit =='x')
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
