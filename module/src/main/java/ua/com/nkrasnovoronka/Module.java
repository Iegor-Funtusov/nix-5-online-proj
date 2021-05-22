package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.tests.level1.Level1Test;
import ua.com.nkrasnovoronka.tests.level2.Level2Test;
import ua.com.nkrasnovoronka.tests.level3.Level3Test;
import ua.com.nkrasnovoronka.util.UserInput;

public class Module {
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\nPleas choose level 1, 2, 3: 0 to exit");
            int level = UserInput.userInputNumber();
            switch (level) {
                case 1 -> Level1Test.start();
                case 2 -> Level2Test.start();
                case 3 -> Level3Test.start();
                case 0 -> running = false;
                default -> {
                    System.err.println("Wrong action. Program will be closed");
                    running = false;
                }
            }
        }
    }
}
