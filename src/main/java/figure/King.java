package figure;

import java.util.Scanner;

public class King{
    public void king (String area, String color){
        Scanner scanner = new Scanner(System.in);
        String Wf = "K ";
        String Bf = "K*";
        ValidContr.upd_board_with_figure(area, color, Wf, Bf);
        char colorP = color.charAt(0);
        System.out.println("\n_____________________________________\n" +
                "Если хотите передвинуть фигуру, укажите поле\n" +
                "Например e5\n" +
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
            while (!ValidContr.isKingStepCorr(area, step, colorP)) {
                if (exit == 'X' || exit == 'x')
                    return;
                while (!ValidContr.isAreaCorrect(step)) {
                    System.out.println("Вы пытаетесь выйти за границы доски. Попробуйте еще раз.");
                    step = scanner.nextLine();
                    exit = step.charAt(0);
                    if (exit == 'X' || exit == 'x')
                        return;
                }
                if(!ValidContr.isKingStepCorr(area, step, colorP)) {
                    System.out.println("Король так не ходит");
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
