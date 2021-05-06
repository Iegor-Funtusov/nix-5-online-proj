package ChessConsole;

import java.util.Scanner;

public class Queen {
    public void queen (String area, String color){
        Scanner scanner = new Scanner(System.in);
        String Wf = "Q ";
        String Bf = "Q*";
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
            while (!ValidContr.isQueenStepCorr(area, step, colorP)) {
                if (exit == 'X' || exit == 'x')
                    return;
                while (!ValidContr.isAreaCorrect(step)) {
                    System.out.println("Вы пытаетесь выйти за границы доски. Попробуйте еще раз.");
                    step = scanner.nextLine();
                    exit = step.charAt(0);
                    if (exit == 'X' || exit == 'x')
                        return;
                }
                if(!ValidContr.isQueenStepCorr(area, step, colorP)) {
                    System.out.println("Ферзь так не ходит, \n" +
                            "он ходит в любом направлении по \n" +
                            "прямой линии или диагонали. Попробуйте еще раз.");
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