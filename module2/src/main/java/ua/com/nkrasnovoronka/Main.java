package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.task1.DateFormatCheck;
import ua.com.nkrasnovoronka.task2.UniqueNameFinder;

public class Main {
    public static void main(String[] args) {
//        DateFormatCheck dateFormatCheck = new DateFormatCheck();
//        dateFormatCheck.writeValidDatesToFile("src/main/resources/task1/input.txt", "src/main/resources/task1/output.txt");
        UniqueNameFinder uniqueNameFinder = new UniqueNameFinder();
        uniqueNameFinder.getFirstUniqueName("src/main/resources/task2/input.txt", "src/main/resources/task2/output.txt");
    }
}
