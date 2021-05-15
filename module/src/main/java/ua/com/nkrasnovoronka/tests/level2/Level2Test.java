package ua.com.nkrasnovoronka.tests.level2;

import ua.com.nkrasnovoronka.tests.level2.task1.BracketParserTest;
import ua.com.nkrasnovoronka.tests.level2.task2.NodeSizeTest;
import ua.com.nkrasnovoronka.util.UserInput;

import static ua.com.nkrasnovoronka.util.UserInput.taskAction;

public class Level2Test {
    public static void start() {
        System.out.println("Choose task:\n1 BracketParser\n2 NodeSize");
        int task = UserInput.userInputNumber();
        System.out.println("Use random ? 1 - Yes 2 - No");
        boolean random = UserInput.userInputNumber() == 1;
        switch (task) {
            case 1 -> taskAction(new BracketParserTest(), random);
            case 2 -> taskAction(new NodeSizeTest(), random);
            default -> System.err.println("Wrong action closing program");
        }
    }
}
