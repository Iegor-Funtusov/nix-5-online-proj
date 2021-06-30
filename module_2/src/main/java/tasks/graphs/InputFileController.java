package tasks.graphs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputFileController {

    private final String IN = "input.txt";

    public InputFileController(){
        Path path = Paths.get("results");
        try {
            if (!path.toFile().exists()) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> read(){
        List<String> input = new ArrayList<>();
        List<String> Cities = new ArrayList<>();
        System.out.println("Input number of cities");
        String cities = number();
        while (Integer.parseInt(cities) > 10000 && Integer.parseInt((cities)) == 1){
            System.out.println("Number of city can't be more than 10000 and 1. Input again.");
            cities = number();
        }
        input.add(cities);
        for (int i = 0; i < Integer.parseInt(cities); i++) {
            System.out.println("Input name of city");
            String city = checkCity(Cities);
            Cities.add(city);
            input.add(city);
            String links;
            System.out.println("Input number of links between cities");
            links = number();
            while (Integer.parseInt(links) >= Integer.parseInt(cities)){
                System.out.println("number of links can be only less than number of cities" +
                        "("+Integer.parseInt(cities)+"). Input again");
                links = number();
            }
            input.add(links);
            System.out.println("Input index of city and distance to it separated by space\n" +
                    "(take into account that index of this city is " + (i+1) + ")");
            List<String> same = new ArrayList<>();
            for (int j = 0; j < Integer.parseInt(links); j++) {
                System.out.println("Input "+(j+1) + " link");
                String link = linksBetweenCities(i+1, Integer.parseInt(cities), same);
                same.add(link);
                input.add(link);
            }
        }
        System.out.println("Input number of links between cities where you would like to know distance between them");
        String links = number();
        while (Integer.parseInt(links) > 100){
            System.out.println("Links can't be more than 100. Please, input again");
            links = number();
        }
        input.add(links);
        System.out.println("Enter cities where you would like to know distance");
        for (int i = 0; i < Integer.parseInt(links); i++) {
            System.out.println((i+1) + " link");
            String link = findDistance(Cities);
            input.add(link);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(IN, true))) {
            for(String in : input) {
                writer.append(in+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    private String number(){
        Scanner scanner = new Scanner(System.in);
        int intNumber;
        String number;
        try {
            intNumber = scanner.nextInt();
            if(intNumber <= 0){
                System.out.println("Please, input positive number. Repeat attempt");
                return number();
            }
            number = String.valueOf(intNumber);
            return number;
        }
        catch (InputMismatchException e){
            System.out.println("Please, input only one number. Repeat attempt");
            return number();
        }
    }

    private String checkCity(List<String> cities){
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();
        for (int i = 0; i < cities.size(); i++) {
            if(city.equals(cities.get(i))){
                System.out.println("Such city already exists. Input another one");
                return checkCity(cities);
            }
        }
        return city;
    }

    private String linksBetweenCities(int index, int quantity, List<String> same){
        Scanner scanner = new Scanner(System.in);
        String link = scanner.nextLine();
        String[] parts = link.split(" ");
        if(parts.length != 2){
            System.out.println("Wrong input. Input should contain only 2 numbers separated by space (e.g. 1 3)");
            return linksBetweenCities(index, quantity, same);
        }
        for (int i = 0; i < 2; i++) {
            try {
                int n = Integer.parseInt(parts[i]);
            }
            catch (InputMismatchException | NumberFormatException e){
                System.out.println("Incorrect input. Input should contain only 2 numbers separated by space (e.g. 1 3)");
                return linksBetweenCities(index, quantity, same);
            }
        }

        if(index == Integer.parseInt(parts[0])){
            System.out.println("You can't assign distance to the same city (it's 0 by default)");
            return linksBetweenCities(index, quantity, same);
        }

        if(Integer.parseInt(parts[0]) > quantity || Integer.parseInt(parts[0]) <= 0){
            System.out.println("Index can't be less or equals 0 and can't be more than quantity of cities");
            return linksBetweenCities(index, quantity, same);
        }

        for (int i = 0; i < same.size(); i++) {
            String[] part = same.get(i).split(" ");
            if(part[0] == parts[0]){
                System.out.println("Such link already exists. Input new link");
                return linksBetweenCities(index, quantity, same);
            }
        }
        return link;
    }

    private String findDistance(List<String> Cities){
        Scanner scanner = new Scanner(System.in);
        String cities = scanner.nextLine();
        String[] parts = cities.split(" ");
        if(parts.length != 2){
            System.out.println("Input only 2 cities separated by space");
            return findDistance(Cities);
        }
        boolean flag = false;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < Cities.size(); j++) {
                if(parts[i].equals(Cities.get(j))){
                    flag = true;
                    break;
                }
            }
            if(flag == false){
                System.out.println("City " + parts[i] + " not found. Input again cities");
                return findDistance(Cities);
            }
            flag = false;
        }
        return cities;
    }
}
