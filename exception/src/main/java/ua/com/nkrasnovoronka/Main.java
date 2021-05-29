package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.formatter.DateFormatter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        DateFormatter dateFormatter = new DateFormatter();
        dateFormatter.formatStringToDate("dd/mm/yy", s);
    }
}
