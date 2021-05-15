package ua.com.nkrasnovoronka.tests.level1.task2;

import ua.com.nkrasnovoronka.tasks.level1.task2.HorseMove;
import ua.com.nkrasnovoronka.tasks.level1.task2.Square;
import ua.com.nkrasnovoronka.tests.Test;
import ua.com.nkrasnovoronka.util.UserInput;

import java.util.Random;

public class HorseMoveTest implements Test {
    public  void randomTest() {
        System.out.println("Starting random horse move test: Added 100 iteration");
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Square from = new Square(random.nextInt(8) + 'a', random.nextInt(8) + '0');
            Square to = new Square(random.nextInt(8) + 'a', random.nextInt(8) + '0');
            String ans = HorseMove.isHorseMoveValid(from, to) ? "Yes" : "No";
            if (ans.equals("Yes")) {
                System.out.println("From " + from.getChessPosition() + " to " + to.getChessPosition() + " is valid!");
            }
        }
    }

    public void userTest() {
        System.out.println("Starting user input horse move test");
        String[] move = UserInput.readMovePosition().split(" ");
        Square from = new Square(move[0]);
        Square to = new Square(move[1]);
        System.out.println("Is move from " + from.getChessPosition() + " to " + to.getChessPosition() + " valid ?");
        String ans = HorseMove.isHorseMoveValid(from, to) ? "Yes" : "No";
        System.out.println(ans);
    }

}
