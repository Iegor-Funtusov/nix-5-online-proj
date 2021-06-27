package ua.nkrasnovoronka;

import ua.UICommonTest;
import ua.nkrasnovoronka.util.UserInput;

public class Main {

    public static void main(String[] args) {
        int userInp = UserInput.userInputNumber("Chose action\n1 - user interface\n2 - simulation");
        switch (userInp) {
            case 1: {
                UICommonTest.runUI();
                break;
            }
            case 2: {
                CommonTests.runSimulation();
                break;
            }
            default: {
                System.err.println("Sorry wrong input. Pleas try again");
            }
        }
    }
}
