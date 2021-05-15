package level1;

import java.util.Scanner;

/** Ход коня по бесконечной шахматной доске.
 * Вводится текущее положение коня и клетка в которую пробуют его передвинуть за 1 ход.
 * Программа должна проверить, возможно ли это сделать.
 */

public class Knight {
    public boolean isRightPositionOnBoard(int letter, int num) {
        return ((letter >= 'a' && letter <= 'h') && (num >= 1 && num <= 8));
    }

    public boolean isRightMove(int letCurrPos, int numCurrPos, int letNewPos, int numNewPos) {
        return (isRightPositionOnBoard(letCurrPos, numCurrPos) &&
            isRightPositionOnBoard(letNewPos, numNewPos) &&
         ((Math.abs(letCurrPos - letNewPos) == 2 && Math.abs(numCurrPos - numNewPos) == 1) ||
            (Math.abs(letCurrPos - letNewPos) == 1 && Math.abs(numCurrPos - numNewPos) == 2)));
    }

    public static void knightCheck() {
        Knight knight = new Knight();
        Scanner inputUser = new Scanner(System.in);

        System.out.println("Текущее положение коня в формате \'b1\' или \'q\' для выхода: ");
        char currPosX;
        char currPosY;
        char nextPosX;
        char nextPosY;

        String move = inputUser.next();
        while (!"q".equals(move)) {
            currPosX = move.charAt(0);
            currPosY = move.charAt(1);
            if (knight.isRightPositionOnBoard(currPosX, Character.digit(currPosY, 10))) {
                System.out.println("Введите ход в формате \'c3\' или \'q\' для выхода: ");
                move = inputUser.next();
                while (!"q".equals(move)) {
                    nextPosX = move.charAt(0);
                    nextPosY = move.charAt(1);
                    if (knight.isRightMove(currPosX, Character.digit(currPosY, 10),
                        nextPosX, Character.digit(nextPosY, 10))) {
                        System.out.println("Ход верный");
                        currPosX = nextPosX;
                        currPosY = nextPosY;
                        //  System.out.println("Введите новый ход: ");
                        // move = inputUser.next();
                    } else {
                        System.out.println("Так конь не ходит!");
                        System.out.println();
                        // break;
                    }
                    // currPosX = nextPosX;
                    // currPosY = nextPosY;
                    System.out.println("Введите новый ход или q для выхода: ");
                    move = inputUser.next();
                }
            } else {
                System.out.println("Конь за пределами поля!");
                System.out.println();
            }
            System.out.println("Текущее положение коня в формате \'b1\' или \'q\' для выхода: ");
            move = inputUser.next();
        }
    }
}
