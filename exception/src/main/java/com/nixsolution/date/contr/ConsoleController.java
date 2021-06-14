package com.nixsolution.date.contr;

import com.nixsolution.date.util.UserInput;

public class ConsoleController {
    private final DateController dateController = new DateController();

    public void printMainMenu() {

        System.out.println("Please, choose the operation and input 0-4 to " +
                "select and then press Enter.\n" +
                "1. Difference between two dates.\n" +
                "2. Plus to time or date.\n" +
                "3. Subtract from time or date.\n" +
                "4. Create some dates and sort them.\n" +
                "0. Exit.\n");

        switch (UserInput.SelectionInput()) {
            case "1": {
                dateController.printDatesDifference();
                break;
            }
            case "2": {
                dateController.calculateDate(1);
                break;
            }
            case "3": {
                dateController.calculateDate(2);
                break;
            }
            case "4": {
                dateController.sortDate();
                break;
            }
            case "0": {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Wrong input, try again!");
                break;
            }
        }
        printMainMenu();
    }
}
