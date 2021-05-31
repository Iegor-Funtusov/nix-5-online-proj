package Controllers;

import Dates.Adding;
import Dates.Comparing;
import Dates.Difference;
import Dates.Subtracting;

import java.util.Scanner;

public class Controller {
    public static void ConsoleControl(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an action for working with dates:\n" +
                    "1 >> Difference between dates\n" +
                    "2 >> Add time to your date\n" +
                    "3 >> Subtract time from your date\n" +
                    "4 >> Compare dates\n" +
                    "5 >> exit from program");
            String input = scanner.nextLine();
            switch (input) {
                case "1": {
                    Difference.diff();
                }
                break;
                case "2": {
                    Adding.add();
                }
                break;
                case "3": {
                    Subtracting.subtract();
                }
                break;
                case "4": {
                    Comparing.compare();
                }break;
                case "5":{
                    System.exit(0);
                }
                default:
                    System.out.println("Wrong input. Input again");
            }
        }
    }
}
