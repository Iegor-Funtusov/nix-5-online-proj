package main;

import FileType.fileType;
import tasks.DateConverter;
import tasks.UniqueName;
import tasks.graphs.CountDistance;
import tasks.graphs.InputFileController;
import tasks.graphs.OutputFileController;

import java.io.IOException;
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
        Scanner scanner = new Scanner(System.in);
        List<String> dates = new ArrayList<>();
        DateConverter converter = new DateConverter();
        System.out.println("Would you like to input dates yourself or see example?\n" +
                "1 >> yourself\n" +
                "2 >> example");
        String choice = scanner.nextLine();
        boolean correct = true;
        while (correct) {
            switch (choice) {
                case "1": {
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
                    if (dates.size() == 0)
                        System.out.println("All input was wrong format or at all wasn't dates");
                    correct = false;
                }
                break;
                case "2": {
                    datesEx();
                    correct = false;
                }
                break;
                default:
                    System.out.println("Wrong input. Try again");
            }
        }
    }

    private void datesEx(){
        Path path = Paths.get(fileType.DATES.getPath());
        DateConverter converter = new DateConverter();
        try {
            List<String> dates = Files.readAllLines(path);
            System.out.println("All dates:");
            for(String date : dates){
                System.out.println(date);
            }
            System.out.println("\nConverted correct dates:");
            dates = converter.converter(dates);
            for (int i = 0; i < dates.size(); i++) {
                System.out.println(dates.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findUniqueName() {
        Scanner sc = new Scanner(System.in);
        UniqueName name = new UniqueName();
        List<String> names = new ArrayList<>();
        System.out.println("Would you like to input names yourself or see example?\n" +
                "1 >> yourself\n" +
                "2 >> example");
        String choice = sc.nextLine();
        boolean correct = true;
        while (correct) {
            switch (choice) {
                case "1": {
                    String flag = "";
                    while (!flag.equals("1")) {
                        System.out.println("Input name (input \"1\" for end of inputting):");
                        flag = sc.nextLine();
                        names.add(flag);
                    }
                    String uniqueName = name.uniqueName(names);
                    System.out.println("First unique name: " + uniqueName);
                    correct = false;
                }
                break;
                case "2": {
                    namesEx();
                    correct = false;
                }
                break;
                default:
                    System.out.println("Wrong input. Try again");
            }
        }
    }

    private void namesEx(){
        Path path = Paths.get(fileType.NAMES.getPath());
        UniqueName Name = new UniqueName();
        try {
            List<String> names = Files.readAllLines(path);
            System.out.println("All names:");
            for(String name : names){
                System.out.println(name);
            }
            String uniqueName = Name.uniqueName(names);
            System.out.println("\nFirst unique name: " + uniqueName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cities() {
        Scanner scanner = new Scanner(System.in);
        InputFileController controller = new InputFileController();
        OutputFileController output = new OutputFileController();
        CountDistance distance = new CountDistance();
        System.out.println("Would you like to input via console or work with files (you input data in file)?\n" +
                "1 >> yourself\n" +
                "2 >> work with files");
        String choice = scanner.nextLine();
        boolean correct = true;
        while (correct) {
            switch (choice) {
                case "1": {
                    controller.read();
                    try {
                        Path path = Paths.get("input.txt");
                        List<String> input = Files.readAllLines(path);
                        int[] results = distance.distance(input);
                        output.output(input, results, "output.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    correct = false;
                }
                break;
                case "2": {
                    graphsFiles();
                    correct = false;
                }
                break;
                default:
                    System.out.println("Wrong input. Try again");
            }
        }
    }

    private void graphsFiles(){
        Scanner scanner = new Scanner(System.in);
        OutputFileController output = new OutputFileController();
        CountDistance distance = new CountDistance();
        try {
            Path path = Paths.get(fileType.GRAPHS_INPUT.getPath());
            List<String> input = Files.readAllLines(path);
            int[] results = distance.distance(input);
            output.output(input, results, fileType.GRAPHS_OUTPUT.getPath());

            Path path1 = Paths.get(fileType.GRAPHS_EX2_INPUT.getPath());
            List<String> input1 = Files.readAllLines(path1);
            results = distance.distance(input1);
            output.output(input1, results, fileType.GRAPHS_EX2_OUTPUT.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
