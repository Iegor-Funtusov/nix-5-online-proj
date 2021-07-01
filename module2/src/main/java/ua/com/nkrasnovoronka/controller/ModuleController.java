package ua.com.nkrasnovoronka.controller;

import ua.com.nkrasnovoronka.task1.DateFormatCheck;
import ua.com.nkrasnovoronka.task2.UniqueNameFinder;
import ua.com.nkrasnovoronka.task3.ShortestPathFinder;
import ua.com.nkrasnovoronka.util.UserInput;

public class ModuleController {
    public static final String TASK1_INPUT = "src/main/resources/task1/input.txt";
    public static final String TASK1_OUTPUT = "src/main/resources/task1/output.txt";

    public static final String TASK2_INPUT = "src/main/resources/task2/input.txt";
    public static final String TASK2_OUTPUT = "src/main/resources/task2/output.txt";

    public static final String TASK3_INPUT = "src/main/resources/task3/input.txt";
    public static final String TASK3_OUTPUT = "src/main/resources/task3/output.txt";

    public static void run() {
        while (true) {
            System.out.println("Pleas enter task number to run");
            int userInput = UserInput.userInputNumber("1 - DateFormatCheck\n2 - UniqueNameFounder\n3 - ShortestPathFinder\n0 - Exit");
            switch (userInput) {
                case 1: {
                    DateFormatCheck dateFormatCheck = new DateFormatCheck();
                    dateFormatCheck.writeValidDatesToFile(TASK1_INPUT, TASK1_OUTPUT);
                    break;
                }
                case 2: {
                    UniqueNameFinder uniqueNameFinder = new UniqueNameFinder();
                    uniqueNameFinder.getFirstUniqueName(TASK2_INPUT, TASK2_OUTPUT);
                    break;
                }
                case 3: {
                    ShortestPathFinder shortestPathFinder = new ShortestPathFinder();
                    shortestPathFinder.findCheapestPath(TASK3_INPUT, TASK3_OUTPUT);
                    break;
                }
                case 0: {
                    System.out.println("Closing program...");
                    System.exit(0);
                    break;
                }
                default: {
                    System.err.println("Sorry wrong input. Please try again");
                    run();
                }
            }
        }
    }
}
