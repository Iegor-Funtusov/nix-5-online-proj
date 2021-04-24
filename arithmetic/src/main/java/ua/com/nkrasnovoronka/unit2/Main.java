package main.java.ua.com.nkrasnovoronka.unit2;

import main.java.ua.com.nkrasnovoronka.unit2.task1.Task1;
import main.java.ua.com.nkrasnovoronka.unit2.task2.Task2;
import main.java.ua.com.nkrasnovoronka.unit2.task3.Task3;
import main.java.ua.com.nkrasnovoronka.unit2.task4.Task4;
import main.java.ua.com.nkrasnovoronka.unit2.task5.Task5;
import main.java.ua.com.nkrasnovoronka.unit2.task6.Task6;
import main.java.ua.com.nkrasnovoronka.unit2.util.Util;

public class Main {
    public static void main(String[] args) {
        int[] arrayFromUserInput = Util.createArrayFromUserInput();
        Task1.arrayEvenNumbersPrinter(arrayFromUserInput);
//        Task2.calculateNumberOfPositiveNumber(arrayFromUserInput);
//        Task3.countNextNumberInArray(arrayFromUserInput);
//        Task4.countArrayNeighbors(arrayFromUserInput);
//        Task5.reverseArray(arrayFromUserInput);
//        Task6.swapNeighbors(arrayFromUserInput);
    }

}
