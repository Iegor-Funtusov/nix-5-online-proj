package ua.com.alevel.app.controller;

import ua.com.alevel.app.service.DateFormatService;
import ua.com.alevel.app.service.SalesmanProblemService;
import ua.com.alevel.app.service.UniqueNameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static final String[] input_files;
    private static final String[] output_files;

    static {
        input_files = new String[]{"module_2/src/main/resources/task_1/input.txt"
                , "module_2/src/main/resources/task_2/input.txt"
                , "module_2/src/main/resources/task_3/input.txt"};
        output_files = new String[]{"module_2/src/main/resources/task_1/output.txt"
                , "module_2/src/main/resources/task_2/output.txt"
                , "module_2/src/main/resources/task_3/output.txt"};
    }

    public static void menu() throws IOException {
        while (true) {
            System.out.println("Enter the task number to perform\n" +
                    "1 -> Reformat list of dates\n" +
                    "2 -> Find first unique name\n" +
                    "3 -> Find the shortest path\n" +
                    "0 -> Exit");
            switch (reader.readLine()) {
                case "1": {
                    DateFormatService dateFormat = new DateFormatService();
                    dateFormat.formatFileDates(input_files[0], output_files[0]);
                    break;
                }
                case "2": {
                    UniqueNameService uniqueNameFinder = new UniqueNameService();
                    uniqueNameFinder.findFirstUnique(input_files[1], output_files[1]);
                    break;
                }
                case "3": {
                    SalesmanProblemService salesmanProblem = new SalesmanProblemService();
                    salesmanProblem.findCheapest(input_files[2], output_files[2]);
                    break;
                }
                case "0": {
                    System.exit(0);
                }
                default: {
                    System.err.println("Wrong input, try again");
                    menu();
                }
            }
        }
    }
}
