package ua.com.nkrasnovoronka.tests.level1;

import ua.com.nkrasnovoronka.tests.level1.task1.UniqueArrayElementTest;
import ua.com.nkrasnovoronka.tests.level1.task2.HorseMoveTest;
import ua.com.nkrasnovoronka.tests.level1.task3.TriangleAreaTest;
import ua.com.nkrasnovoronka.util.UserInput;

import static ua.com.nkrasnovoronka.util.UserInput.taskAction;

public class Level1Test {
    public static void start() {
        System.out.println("Choose task:\n1 UniqueArrayElement\n2 HorseMove\n3 TriangleArea");
        int task = UserInput.userInputNumber();
        System.out.println("Use random ? 1 - Yes 2 - No");
        boolean random = UserInput.userInputNumber() == 1;
        switch (task) {
            case 1 -> taskAction(new UniqueArrayElementTest(), random);
            case 2 -> taskAction(new HorseMoveTest(), random);
            case 3 -> taskAction(new TriangleAreaTest(), random);
            default -> System.err.println("Wrong action closing program");
        }
    }
}
