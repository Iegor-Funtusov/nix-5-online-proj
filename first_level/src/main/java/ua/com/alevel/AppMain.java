package ua.com.alevel;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class AppMain {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        callInterface();
        runFirstTask();
        runSecondTask();
    }

    private static void callInterface() {
        System.out.println("First level task!");
        while (true){
            printMenu();
            chooseTask();
        }
    }

    @SneakyThrows
    private static void chooseTask() {
        switch (reader.readLine()){
            case "1":{
                runFirstTask();
                break;
            }
            case "2":{
                runSecondTask();
                break;
            }
            case "3":{
                runThirdTask();
                break;
            }
            case "0":{
                break;
            }
            default:{
                System.out.println("Invalid choice! Try again.");
                break;
            }
        }

    }


    private static void printMenu() {
        System.out.println("Which task you want to test?" +
                "\n 1 - unique value" +
                "\n 2 - knight move" +
                "\n 3 - triangle area" +
                "\n 0 - exit");
    }

    private static void runSecondTask() {
    }

    private static void runThirdTask() {
    }

    private static void runFirstTask() {
        int[] array;
        while (true){
            printFirstTaskMenu();
            array = createArray();
            if(array == null)
                break;
            ArrayUtil.printArray(array);
            System.out.println("Number of unique elements: " +
                    ArrayUtil.countUniqueElements(array));
        }
    }

    private static void printFirstTaskMenu() {
        System.out.print("First task menu!" +
                "\nChoose type of array creating:" +
                "\n 1 - manual" +
                "\n 2 - random" +
                "\n 0 - exit menu" +
                "\n -->");
    }

    @SneakyThrows
    private static int[] createArray() {
        int[] array = null;
        switch (reader.readLine()){
            case "1":{
                array = getArray();
                break;
            }
            case "2":{
                array = getRandomArray();
                break;
            }
            case "0":{
                break;
            }
            default:{
                System.out.println("Invalid choice! Try again.");
                break;
            }
        }
        return array;
    }

    private static int[] getRandomArray() {
        Random random = new Random();
        int[] array = new int[getArraySize()];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10);
        }
        return array;
    }

    private static int getArraySize(){
        int size = 0;
        while (true) {
            try {
                System.out.print("Enter array size: ");
                size = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (size != 0)
                break;
        }
        return size;
    }

    private static int[] getArray() {
        int[] array = new int[getArraySize()];
        int counter = 0;
        while (true) {
            try {
                System.out.print("Enter integer elements separated by \"Enter\": ");
                for (int i = 0; i < array.length; i++) {
                    array[i] = Integer.parseInt(reader.readLine());
                    counter++;
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            if(counter == array.length)
                break;
        }
        return array;
    }

}
