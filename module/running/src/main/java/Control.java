import chess.Program;

import java.util.Scanner;

public class Control {
    public static void consoleControle(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Input what level do you want to check?\n" +
                    "\"level 1\"\n" +
                    "\"level 2\" (valid brackets)\n" +
                    "\"level 3\" (game of life)\n" +
                    "\"exit\" (exit from program)");
            String choice = scanner.nextLine();
            choice = choice.toLowerCase();
            switch (choice){
                case "level 1":{
                    chooseTask1();
                }break;
                case "level 2":{
                    Brackets.controle();
                }break;
                case "level 3":{
                    GameOfLife.controle();
                }break;
                case "exit":{
                    System.exit(0);
                }break;
                default:{
                    System.out.println("Incorrect input");
                }break;
            }
        }
    }

    private static void chooseTask1(){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("What task do you want to check?\n" +
                    "\"Task 1\" (unique numbers in array)\n" +
                    "\"Task 2\" (knight and infinite board)\n" +
                    "\"Task 3\" (area of the triangle)\n" +
                    "\"return\" (return to levels)");
            String choice = scanner.nextLine();
            choice = choice.toLowerCase();
            switch (choice){
                case "task 1":{
                    UniqueNum.control();
                }break;
                case "task 2":{
                    Program.controle();
                }break;
                case "task 3":{
                    Triangle.triangle();
                }break;
                case "return":{
                    return;
                }
                default:{
                    System.out.println("Incorrect input");
                }break;
            }
        }
    }
}
