package ua.com.nkrasnovoronka.tests.level3;

import ua.com.nkrasnovoronka.tests.level3.task1.GameLifeTest;
import ua.com.nkrasnovoronka.util.UserInput;

import static ua.com.nkrasnovoronka.util.UserInput.taskAction;

public class Level3Test {
    public static void start() {
        System.out.println("Testing level3 task");
        System.out.println("Use random ? 1 - Yes 2 - No");
        boolean random = UserInput.userInputNumber() == 1;
        taskAction(new GameLifeTest(), random);
    }
}
