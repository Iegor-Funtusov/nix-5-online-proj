package ua.com.nkrasnovoronka.tests.level3.task1;

import ua.com.nkrasnovoronka.tasks.level3.task1.GameLife;
import ua.com.nkrasnovoronka.tests.Test;
import ua.com.nkrasnovoronka.util.UserInput;

import java.util.List;
import java.util.Random;

public class GameLifeTest implements Test {
    public void randomTest() {
        System.out.println("Starting random game test: Adding 10 iteration");
        Random random = new Random();
        int bound = random.nextInt(5) + 5;
        GameLife gameLife = new GameLife(bound, bound);
        for (int i = 0; i < bound * 2; i++) {
            gameLife.setAlive(random.nextInt(bound), random.nextInt(bound));
        }
        for (int i = 0; i < 10; i++) {
            gameLife.displayBoard();
            gameLife.move();
        }
    }

    public void userTest() {
        System.out.println("Starting user input game test");
        System.out.println("Enter width and height of board (2 numbers separate by space)");
        List<Integer> integers = UserInput.userInputNumbers();
        if (integers.size() != 2) {
            throw new IllegalArgumentException("You must enter 2 numbers only. Pleas restart program");
        }
        GameLife gameLife = new GameLife(integers.get(0), integers.get(1));
        System.out.println("enter number of alive pixels");
        int countAlivePixels = UserInput.userInputNumber();
        for (int i = 0; i < countAlivePixels; i++) {
            System.out.println("Enter " + (i + 1) + " pixel coordinates (x and y seperated by space");
            List<Integer> coordinates = UserInput.userInputNumbers();
            if (coordinates.size() != 2) {
                throw new IllegalArgumentException("You must enter 2 numbers only. Pleas restart program");
            }
            gameLife.setAlive(coordinates.get(0), coordinates.get(1));
        }
        for (int i = 0; i < 10; i++) {
            gameLife.displayBoard();
            gameLife.move();
        }
    }
}
