package com.nixcource;

import com.nixcource.util.ConsoleUtil;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ConsoleUtil.printTaskDescription();
        String userSelect;

        while(true) {
            userSelect = scanner.next();
            switch (userSelect) {
                case "1" -> ConsoleUtil.uniqueSymbols(ConsoleUtil.isRandomSelected());
                case "2" -> ConsoleUtil.horseMove();
                case "3" -> ConsoleUtil.triangleArea(ConsoleUtil.isRandomSelected());
                case "4" -> ConsoleUtil.stringParser(ConsoleUtil.isRandomSelected());
                case "5" -> ConsoleUtil.runGame();
            }
            if (userSelect.equals("q")) {
                break;
            }
            System.out.println("==========================");
            ConsoleUtil.printTaskDescription();
        }
    }
}
