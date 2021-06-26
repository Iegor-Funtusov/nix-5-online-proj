package com.nixsolutions.courses.controller;

import com.nixsolutions.courses.MathSet;
import com.nixsolutions.courses.service.MathSetService;

import javax.management.InstanceAlreadyExistsException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final MathSetService<Integer> mathSetService = new MathSetService<>();

    private void create() throws Exception {
        if (mathSetService.isNull()) {
//            System.out.println("You can enter capacity, space-separated numbers, comma separated group of numbers or nothing");
            if (mathSetService.create()) {
                System.out.println("MathSet created");
            } else {
                throw new Exception("MathSet was not created");
            }
        } else {
            throw new InstanceAlreadyExistsException("MathSet instance already exists");
        }
    }

    private void add() throws IOException {
        if (!mathSetService.isNull()) {
            System.out.println("Enter one or several space-separated numbers:");
            Integer[] numbers = formatInput(reader.readLine());
            if (mathSetService.add(numbers)) {
                System.out.println("");
                System.out.println("Result mathset: " + printSet(mathSetService.getMathSet()));
            }
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private void join() throws IOException {
        if (!mathSetService.isNull()) {
            System.out.println("Enter space-separated numbers for mathset to join existing:");
            Integer[] numbers = formatInput(reader.readLine());
            MathSet<Integer> set = mathSetService.createNewSet(numbers);
            if (mathSetService.join(set)) {
                System.out.println("New mathset joined");
                System.out.println("Result mathset: " + printSet(mathSetService.getMathSet()));
            }
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private void sortDesc() {
        if (!mathSetService.isNull()) {
            if (mathSetService.sortDesc()) {
                System.out.println("Mathset sorted in descending order");
                System.out.println("Result mathset: " + printSet(mathSetService.getMathSet()));
            }
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private void sortAsc() {
        if (!mathSetService.isNull()) {
            if (mathSetService.sortAsc()) {
                System.out.println("Mathset sorted in ascending order");
                System.out.println("Result mathset: " + printSet(mathSetService.getMathSet()));
            }
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private void getByIndex() throws IOException {
        if (!mathSetService.isNull()) {
            System.out.println("Enter index:");
            int index = Integer.parseInt(reader.readLine());
            Integer element = mathSetService.get(index);
            System.out.println("Element by index " + index + ": " + element);
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private void getMin() {
        if (!mathSetService.isNull()) {
            Integer min = (Integer) mathSetService.getMin();
            System.out.println("Mathset minimum: " + min);
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private void getMax() {
        if (!mathSetService.isNull()) {
            Integer max = (Integer) mathSetService.getMax();
            System.out.println("Mathset maximum: " + max);
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private void getAverage() {
        if (!mathSetService.isNull()) {
            Integer average = (Integer) mathSetService.getAverage();
            System.out.println("Mathset average: " + average);
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private void getMedian() {
        if (!mathSetService.isNull()) {
            Number median = mathSetService.getMedian();
            System.out.println("Mathset median: " + median);
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private void toArray() {
        if (!mathSetService.isNull()) {
            System.out.println(printSet(mathSetService.getMathSet()));
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private void squash() throws IOException {
        if (!mathSetService.isNull()) {
            System.out.println("Enter space-separated indexes:");
            Number[] indexes = formatInput(reader.readLine());
            MathSet set = mathSetService.squash((int) indexes[0], (int) indexes[1]);
            System.out.println(printSet(set));
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private void clear() {
        if (!mathSetService.isNull()) {
            mathSetService.clear();
            System.out.println("Mathset cleared");
            System.out.println("size: " + mathSetService.getSize());
        } else {
            throw new NullPointerException("MathSet is null");
        }
    }

    private Integer[] formatInput(String input) {
        String[] data = input.split(" ");
        Integer[] array = new Integer[data.length];
        for (int i = 0; i < data.length; i++) {
            array[i] = Integer.parseInt(data[i]);
        }
        return array;
    }

    private String printSet(MathSet mathSet) {
        StringBuilder out = new StringBuilder();
        for (Number number : mathSetService.toArray(mathSet)) {
            out.append(number).append(" ");
        }
        return String.valueOf(out);
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
