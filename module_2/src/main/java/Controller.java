import task1.ChangeDateFormat;
import task2.FindUniqueName;
import task3.InfoFromToFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    BufferedReader reader = reader = new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        boolean check = true;
        while (check) {
            System.out.println("\n1 -> task 1 - Convert list of dates " +
                    "\n2 -> task 2 - Find first unique name " +
                    "\n3 -> task 3 - Find the shortest path between two cities" +
                    "\n4 -> exit \nEnter your choise!!! ");
            int res = 0;
            try {
                String choise = reader.readLine();
                res = Integer.parseInt(choise);
            } catch (IOException | IllegalArgumentException exception) {
                System.out.println("Incorrect input. Try once more");
                continue;
            }
            switch (res) {
                case 1: {
                    firstTask();
                    break;
                }
                case 2: {
                    SecondTask();
                    break;
                }
                case 3: {
                    try{
                    InfoFromToFile infoFromToFile = new InfoFromToFile();
                    infoFromToFile.getInfoFromFile("src/main/resources/input.txt");
                    infoFromToFile.setInfoToFile("src/main/resources/output.txt");
                    System.out.println("Please, open output.txt");}
                    catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    check = false;
                    break;
                }
                default:{
                    System.out.println("Make your choise again!");
                }
            }
        }
    }

    public void firstTask(){
        System.out.println("\n1 -> Enter dates " +
                "\n2 -> Demonstrate example \nEnter your choise!!! ");
        int res = 0;
        try {
            String choise = reader.readLine();
            res = Integer.parseInt(choise);
        } catch (IOException | IllegalArgumentException exception) {
            System.out.println("Incorrect input. Try once more");
        }
        switch (res) {
            case 1: {
                System.out.println("Enter dates: make a space between them");
                try {
                    String datesArray = reader.readLine();
                    if (datesArray.length() != 0) {
                        System.out.println(datesArray);
                        ChangeDateFormat changeDateFormat = new ChangeDateFormat();
                        List<String> formated = changeDateFormat.defineFormat(datesArray);
                        for (String outputLine: formated) {
                            System.out.print(outputLine + " ");
                        }
                    }
                }
                catch (IOException e){
                    System.out.println("Incorrect input. Try once more");
                }
                break;
            }
            case 2: {
                String st = "2020/04/05 05/04/2020 04-05-2020 56-45-3425 23:42:23 2020/06/30 " +
                        "17-23-2342 02-12-2034";
                System.out.println(st);
                ChangeDateFormat changeDateFormat = new ChangeDateFormat();
                List<String> formated = changeDateFormat.defineFormat(st);
                for (String outputLine: formated) {
                    System.out.print(outputLine + " ");
                }
                break;
            }
            default: {
                System.out.println("Make your choise again!");
            }
        }
    }

    public void SecondTask(){
        System.out.println("\n1 -> Enter names " +
                "\n2 -> demonstrate example \nEnter your choise!!! ");
        int res = 0;
        try {
            String choise = reader.readLine();
            res = Integer.parseInt(choise);
        } catch (IOException | IllegalArgumentException exception) {
            System.out.println("Incorrect input. Try once more");
        }
        switch (res) {
            case 1: {
                try {
                    String namesArray = reader.readLine();
                    if (namesArray.length() != 0) {
                        String[] strings = namesArray.trim().split("\\s+");
                        for (String s: strings) {
                            System.out.print(s + " ");
                        }
                        List<String> names = new ArrayList<String>();
                        names = Arrays.asList(strings);
                        FindUniqueName findUniqueName = new FindUniqueName();
                        String unique = findUniqueName.find(names);
                        if(unique!=null){
                            System.out.println("\nFirst unique name is " + unique);
                        }
                    }
                }
                catch (IOException e){
                    System.out.println("Incorrect input. Try once more");
                }
                break;
            }
            case 2: {
                List<String> names = new ArrayList<String>();
                String[] strings = new String[] {"Jane", "Ivan", "Mark", "Ann", "Jane", "Ann", "Ivan"};
                System.out.println("List of names: ");
                for (String s: strings) {
                    System.out.print(s + " ");
                }
                names = Arrays.asList(strings);
                FindUniqueName findUniqueName = new FindUniqueName();
                String unique = findUniqueName.find(names);
                if(unique!=null){
                    System.out.println("\nFirst unique name is " + unique);
                }
                break;
            }
            default:{
                System.out.println("Make your choise again!");
            }
        }
    }
}