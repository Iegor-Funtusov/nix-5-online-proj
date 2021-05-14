package com.nixsolutions.firsttask;

import java.util.Scanner;

public class ChessRunner {

    //task 1.2
    public static void runChess() {

        Scanner scanner = new Scanner(System.in);

        int initial_x = 0;
        int initial_y = 0;
        int step_x = 0;
        int step_y = 0;

        System.out.println("Please, input Initial position (Example. x-y): ");

        String a = scanner.next();

        String[] charArray = a.split("-");
        initial_x = Integer.parseInt(charArray[0]);
        initial_y = Integer.parseInt(charArray[1]);

        boolean cycle = true;
        while (cycle) {

            System.out.println("Please, input new position: ");
            a = scanner.next();

            charArray = a.split("-");
            step_x = Integer.parseInt(charArray[0]);
            step_y = Integer.parseInt(charArray[1]);

            if (StepValidator.isStepValid(initial_x, initial_y, step_x, step_y)
                    && StepValidator.isStepInBoard(step_x, step_y)) {
                initial_x = step_x;
                initial_y = step_y;
                System.out.println("Step is valid, now knight at:  " + initial_x + "-" + initial_y);
            } else {
                System.out.println("Invalid input. Please try again");
            }
        }
    }
}
