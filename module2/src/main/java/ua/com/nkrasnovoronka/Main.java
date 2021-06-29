package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.task1.DateFormatCheck;

public class Main {
    public static void main(String[] args) {
        DateFormatCheck dateFormatCheck = new DateFormatCheck();
        dateFormatCheck.writeValidDatesToFile("src/main/resources/task2/input.txt", "src/main/resources/task2/output.txt");
    }
}
