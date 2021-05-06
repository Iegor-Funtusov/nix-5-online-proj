package figure;

import java.util.Scanner;

public class Rook {

    public void rook (String area, String color) {
        Scanner scanner = new Scanner(System.in);
        String Wf = "R ";
        String Bf = "R*";
        ValidContr.upd_board_with_figure(area, color, Wf, Bf);
        char colorP = color.charAt(0);
        System.out.println("\n_____________________________________\n" +
                "Если хотите передвинуть фигуру, укажите поле\n" +
                "Например E5\n" +
                "Если вы хотите поменять фигуру укажите X");
        String step = scanner.nextLine();
        char exit = step.charAt(0);
        if (exit == 'X' || exit =='x')
            return;

        while (true) {
            if (exit == 'X' || exit =='x')
                return;
            while (!ValidContr.isAreaCorrect(step)) {
                System.out.println("Вы пытаетесь выйти за границы доски. Попробуйте еще раз.");
                step = scanner.nextLine();
                exit = step.charAt(0);
                if (exit == 'X' || exit =='x')
                    return;
            }
            while (!ValidContr.isRookStepCorr(area, step, colorP)) {
                if (exit == 'X' || exit == 'x')
                    return;
                while (!ValidContr.isAreaCorrect(step)) {
                    System.out.println("Вы пытаетесь выйти за границы доски. Попробуйте еще раз.");
                    step = scanner.nextLine();
                    exit = step.charAt(0);
                    if (exit == 'X' || exit == 'x')
                        return;
                }
                if(!ValidContr.isRookStepCorr(area, step, colorP)) {
                    System.out.println("Ладья так не ходит\n" +
                            "она ходит только по прямой. Попробуйте еще раз.");
                }
                step = scanner.nextLine();
                exit = step.charAt(0);
                if (exit == 'X' || exit == 'x')
                    return;
                while (!ValidContr.isAreaCorrect(step)) {
                    System.out.println("Вы пытаетесь выйти за границы доски. Попробуйте еще раз.");
                    step = scanner.nextLine();
                    exit = step.charAt(0);
                    if (exit == 'X' || exit == 'x')
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
