package main.java.ua.com.nkrasnovoronka.unit2;

import main.java.ua.com.nkrasnovoronka.unit2.task1.Task1;
import main.java.ua.com.nkrasnovoronka.unit2.task2.Task2;
import main.java.ua.com.nkrasnovoronka.unit2.util.Util;

public class Main {
    public static void main(String[] args) {
        int[] arrayFromUserInput = Util.createArrayFromUserInput();
        Task1.arrayEvenNumbersPrinter(arrayFromUserInput);
        Task2.calculateNumberOfPositiveNumber(arrayFromUserInput);
    }
}
