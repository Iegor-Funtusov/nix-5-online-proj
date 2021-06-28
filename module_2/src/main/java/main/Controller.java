package main;

import tasks.DateConverter;
import tasks.UniqueName;
import tasks.graphs.CountDistance;
import tasks.graphs.InputFileController;
import tasks.graphs.OutputFileController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    public void start(){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Input action:\n" +
                    "1 >> convert dates\n" +
                    "2 >> find first unique name\n" +
                    "3 >> find minimum distances between cities\n" +
                    "4 >> exit from program");
            String choice = sc.nextLine();
            switch (choice) {
                case "1": {
                    converterOfDates();
                }
                break;
                case "2": {
                    findUniqueName();
                }
                break;
                case "3": {
                    cities();
                }
                break;
                case "4": {
                    System.exit(0);
                }
                break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private void converterOfDates() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        List<String> dates = new ArrayList<>();
        DateConverter converter = new DateConverter();
        String flag = "";
        while (!flag.equals("1")) {
            System.out.println("Input date (input \"1\" for end of inputting):");
            flag = scanner.nextLine();
            dates.add(flag);
        }
        dates = converter.converter(dates);
        System.out.println("Right dates in new format:");
        for (int i = 0; i < dates.size(); i++) {
            System.out.println(dates.get(i));
        }
        if(dates.size() == 0)
            System.out.println("All input was wrong format or at all wasn't dates");
    }

    private void findUniqueName() {
        Scanner sc = new Scanner(System.in);
        UniqueName name = new UniqueName();
        List<String> names = new ArrayList<>();
        String flag = "";
        while (!flag.equals("1")) {
            System.out.println("Input name (input \"1\" for end of inputting):");
            flag = sc.nextLine();
            names.add(flag);
        }
        String uniqueName = name.uniqueName(names);
        System.out.println("First unique name: " + uniqueName);
    }

    private void cities() {
        InputFileController controller = new InputFileController();
        OutputFileController output = new OutputFileController();
        CountDistance distance = new CountDistance();
        controller.read();
        try {
            Path path = Paths.get("input.txt");
            List<String> input = Files.readAllLines(path);
            int[] results = distance.distance(input);
            output.output(input, results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
