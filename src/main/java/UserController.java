import java.io.IOException;
import java.util.Scanner;
import LevelControlles.Lev1Controller;
import LevelControlles.Lev2Controller;
import LevelControlles.Lev3Controller;

public class UserController {
    private Scanner scanner = new Scanner(System.in);


    public void UserInterface() throws IOException {
        do {
            System.out.println("Choose the level: 1 or 2 or 3");

            switch (scanner.nextInt()) {
                case 1 -> {
                    System.out.println("You chose level 1:");
                    Lev1Controller lev1Controller = new Lev1Controller();
                    lev1Controller.level1Interface();
                }
                case 2 -> {
                    System.out.println("You chose level 2:");
                    Lev2Controller lev2Controller = new Lev2Controller();
                    lev2Controller.level2Interface();
                }
                case 3 -> {
                    System.out.println("You chose level 3:");
                    Lev3Controller lev3Controller = new Lev3Controller();
                    lev3Controller.level3Interface();
                }
                default -> System.out.println("Incorrect value entered");
            }

            System.out.println("Do you want to continue use the program at all? 1-yes, else-no");

        } while (scanner.nextInt() == 1);

    }
}