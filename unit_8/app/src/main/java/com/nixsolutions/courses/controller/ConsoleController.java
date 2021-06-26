package com.nixsolutions.courses.controller;

import com.nixsolutions.courses.MathSet;
import com.nixsolutions.courses.service.MathSetService;

import javax.management.InstanceAlreadyExistsException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleController {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final MathSetService<Integer> mathSetService;

    public ConsoleController() {
        mathSetService = new MathSetService<>();
    }

    private void create() throws InstanceAlreadyExistsException {
        if (mathSetService.isNull()) {
            mathSetService.create();
            System.out.println("Empty mathSet created");
        } else {
            throw new InstanceAlreadyExistsException("MathSet instance already exists");
        }
    }

    private void createRandom() {
        mathSetService.createRandom();
        System.out.println("Instance creates");
    }

    private void add() throws IOException {
        System.out.println("Enter one or several space-separated numbers:");
        Integer[] numbers = formatInput(reader.readLine());
        mathSetService.add(numbers);
        System.out.println("Result mathset: " + mathSetService.toString());
    }

    private void join() throws IOException {
        System.out.println("Enter space-separated numbers for mathset to join existing:");
        Integer[] numbers = formatInput(reader.readLine());
        MathSet<Integer> set = mathSetService.createNewSet(numbers);
        mathSetService.join(set);
        System.out.println("New mathset joined");
        System.out.println("Result mathset: ");
        toArray();
    }

    private void sortDesc() {
        mathSetService.sortDesc();
        System.out.println("Mathset sorted in descending order");
        System.out.println("Result mathset: ");
        toArray();
    }

    private void sortAsc() {
        mathSetService.sortAsc();
        System.out.println("Mathset sorted in ascending order");
        System.out.println("Result mathset: ");
        toArray();
    }

    private void getByIndex() throws IOException {
        System.out.println("Enter index:");
        int index = Integer.parseInt(reader.readLine());
        Integer element = mathSetService.get(index);
        System.out.println("Element by index " + index + ": " + element);
    }

    private void getMin() {
        Integer min = mathSetService.getMin();
        System.out.println("Mathset minimum: " + min);
    }

    private void getMax() {
        Integer max = mathSetService.getMax();
        System.out.println("Mathset maximum: " + max);
    }

    private void getAverage() {
        Number average = mathSetService.getAverage();
        System.out.println("Mathset average: " + average);
    }

    private void getMedian() {
        Number median = mathSetService.getMedian();
        System.out.println("Mathset median: " + median);
    }

    private void toArray() {
        Number[] array = mathSetService.toArray();
        for (Number number : array) {
            System.out.print(number + " ");
        }
        System.out.println("");
    }

    private void squash() throws IOException {
        System.out.println("Enter space-separated indexes:");
        Number[] indexes = formatInput(reader.readLine());
        MathSet<Integer> set = mathSetService.squash((int) indexes[0], (int) indexes[1]);
        System.out.println(set.toString());
    }

    private void clear() {
        mathSetService.clear();
        System.out.println("Mathset cleared");
        System.out.println("size: " + mathSetService.getSize());
    }

    private Integer[] formatInput(String input) {
        String[] data = input.split(" ");
        Integer[] array = new Integer[data.length];
        for (int i = 0; i < data.length; i++) {
            array[i] = Integer.parseInt(data[i]);
        }
        return array;
    }

    private String readConsole() {
        System.out.println("Choose option:\n" +
                "0 - exit\n" +
                "1 - create\n" +
                "2 - add\n" +
                "3 - join\n" +
                "4 - descending sort\n" +
                "5 - ascending sort\n" +
                "6 - get element by index\n" +
                "7 - get minimum\n" +
                "8 - get maximum\n" +
                "9 - get average\n" +
                "10 - get median\n" +
                "11 - to array\n" +
                "12 - squash\n" +
                "13 - clear");
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("Sorry, something went wrong with reading from console. Try again");
        }
        return input;
    }

    public void printMenu() {
        String input;
        while ((input = readConsole()) != null) {
            try {
                if (!input.equals("0") && !input.equals("1") && mathSetService.isNull()) {
                    System.out.println("No mathSet instance");
                    createRandom();
                }
                switch (input) {
                    case "0":
                        System.exit(0);
                        break;
                    case "1":
                        create();
                        break;
                    case "2":
                        add();
                        break;
                    case "3":
                        join();
                        break;
                    case "4":
                        sortDesc();
                        break;
                    case "5":
                        sortAsc();
                        break;
                    case "6":
                        getByIndex();
                        break;
                    case "7":
                        getMin();
                        break;
                    case "8":
                        getMax();
                        break;
                    case "9":
                        getAverage();
                        break;
                    case "10":
                        getMedian();
                        break;
                    case "11":
                        toArray();
                        break;
                    case "12":
                        squash();
                        break;
                    case "13":
                        clear();
                        break;
                    default:
                        System.out.println("Wrong option. Try again");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
