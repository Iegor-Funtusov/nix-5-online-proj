package ChessConsole;

import java.util.Scanner;

public class Pawn {
    public void pawn (String area, String color) {
        Scanner scanner = new Scanner(System.in);
        char colorP = color.charAt(0);
        String Wf = "P ";
        String Bf = "P*";
        ValidContr.upd_board_with_figure(area, color, Wf, Bf);

        System.out.println("\n_____________________________________\n" +
                "Если хотите передвинуть фигуру, укажите поле\n" +
                "Например e5\n" +
                "Если вы хотите поменять фигуру укажите X");
        String step = scanner.nextLine();
        char exit = step.charAt(0);
        if (exit == 'X' || exit =='x')
            return;
        while (true) {
            while (!ValidContr.isPawnStepCorr(area, step, colorP)) {
                System.out.println("Пешка так не ходит, она ходит на одну клетку вперед.");
                step = scanner.nextLine();
                exit = step.charAt(0);
                if (exit == 'X' || exit =='x')
                    return;
                while (!ValidContr.isAreaCorrect(step)) {
                    System.out.println("Вы пытаетесь выйти за границы доски");
                    step = scanner.nextLine();
                    exit = step.charAt(0);
                    if (exit == 'X' || exit =='x')
                        return;
                }
            }
            ValidContr.upd_board_with_figure(step, color, Wf, Bf);
            area = step;
            System.out.println("___________________________________________\n" +
                    "Если хотите передвинуть фигуру, укажите поле\n" +
                    "Если вы хотите поменять фигуру укажите X");
            step = scanner.nextLine();
        }

    }
}
