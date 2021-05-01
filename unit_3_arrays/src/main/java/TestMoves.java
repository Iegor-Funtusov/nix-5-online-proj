import java.util.Scanner;

public class TestMoves {

    public static void main(String[] args) {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Выберите фигуру или нажмите 'q' для выхода: ");
        System.out.println("K - King (король)");
        System.out.println("Q - Queen (ферзь)");
        System.out.println("N - Knight (конь)");
        System.out.println("R - Rook (ладья)");
        System.out.println("B - Bishop (слон)");
        System.out.println("P - Pawn (пешка белая)");
        System.out.println("Pb - Pawn (пешка чёрная)");
        String figure = inputUser.next();
        while (!"q".equals(figure)) {
            switch (figure) {
                case "K":
                    kingCheck();
                    break;
                case "Q":
                    queenCheck();
                    break;
                case "N":
                    knightCheck();
                    break;
                case "R":
                    rookCheck();
                    break;
                case "B":
                    bishopCheck();
                    break;
                case "P":
                    pawnCheck();
                    break;
                case "Pb":
                    pawnBlackCheck();
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестная фигура!");
            }
            System.out.println("Выберите фигуру или нажмите 'q' для выхода: ");
            System.out.println("K - King (король)");
            System.out.println("Q - Queen (ферзь)");
            System.out.println("N - Knight (конь)");
            System.out.println("R - Rook (ладья)");
            System.out.println("B - Bishop (слон)");
            System.out.println("P - Pawn (пешка белая)");
            System.out.println("Pb - Pawn (пешка чёрная)");
            figure = inputUser.next();
        }
    }

    public static void kingCheck() {
        King king = new King();
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Введите ход в формате \'b1-b2\' или \'q\' для выхода: ");
        String currPos;
        String nextPos;

        String move = inputUser.next();
        while (!"q".equals(move)) {
            currPos = move.split("-")[0].toLowerCase();
            nextPos = move.split("-")[1].toLowerCase();
            if (king.isRightMove(currPos.charAt(0), Character.digit(currPos.charAt(1), 10),
                nextPos.charAt(0), Character.digit(nextPos.charAt(1), 10)))
                System.out.println("Ход верный");
            else System.out.println("Так король не ходит!");
            System.out.println("Введите новый ход: ");
            move = inputUser.next();
        }
    }

    public static void queenCheck() {
        Queen queen = new Queen();
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Введите ход в формате \'b1-b5\' или \'q\' для выхода: ");
        String currPos;
        String nextPos;

        String move = inputUser.next();
        while (!"q".equals(move)) {
            currPos = move.split("-")[0].toLowerCase();
            nextPos = move.split("-")[1].toLowerCase();
            if (queen.isRightMove(currPos.charAt(0), Character.digit(currPos.charAt(1), 10),
                nextPos.charAt(0), Character.digit(nextPos.charAt(1), 10)))
                System.out.println("Ход верный");
            else System.out.println("Так ферзь не ходит!");
            System.out.println("Введите новый ход: ");
            move = inputUser.next();

        }
    }

        public static void knightCheck() {
        Knight knight = new Knight();
            Scanner inputUser = new Scanner(System.in);
            System.out.println("Введите ход в формате \'b1-c3\' или \'q\' для выхода: ");
            String currPos;
            String nextPos;

            String move = inputUser.next();
            while (!"q".equals(move)) {
                currPos = move.split("-")[0].toLowerCase();
                nextPos = move.split("-")[1].toLowerCase();
                if (knight.isRightMove(currPos.charAt(0), Character.digit(currPos.charAt(1), 10),
                    nextPos.charAt(0), Character.digit(nextPos.charAt(1), 10)))
                    System.out.println("Ход верный");
                else System.out.println("Так конь не ходит!");
                System.out.println("Введите новый ход: ");
                move = inputUser.next();
            }
    }

    public static void rookCheck() {

        Rook rook = new Rook();
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Введите ход в формате \'b1-b2\' или \'q\' для выхода: ");
        String currPos;
        String nextPos;

        String move = inputUser.next();
        while (!"q".equals(move)) {
            currPos = move.split("-")[0].toLowerCase();
            nextPos = move.split("-")[1].toLowerCase();
            if (rook.isRightMove(currPos.charAt(0), Character.digit(currPos.charAt(1), 10),
                nextPos.charAt(0), Character.digit(nextPos.charAt(1), 10)))
                System.out.println("Ход верный");
            else System.out.println("Так ладья не ходит!");
            System.out.println("Введите новый ход: ");
            move = inputUser.next();
        }
    }

    public static void bishopCheck() {

        Bishop bishop = new Bishop();
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Введите ход в формате \'b1-c2\' или \'q\' для выхода: ");
        String currPos;
        String nextPos;
        String move = inputUser.next();
        while (!"q".equals(move)) {
            currPos = move.split("-")[0].toLowerCase();
            nextPos = move.split("-")[1].toLowerCase();

        if (bishop.isRightMove(currPos.charAt(0), Character.digit(currPos.charAt(1),10),
            nextPos.charAt(0), Character.digit(nextPos.charAt(1),10)))
            System.out.println("Ход верный");
        else System.out.println("Так слон не ходит!");
        System.out.println("Введите новый ход: ");
        move = inputUser.next();;
        }
    }

    public static void pawnCheck() {
        Pawn pawn = new Pawn();
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Введите ход в формате \'b2-b3\' или \'q\' для выхода: ");
        String currPos;
        String nextPos;

        String move = inputUser.next();
        while (!"q".equals(move)) {
            currPos = move.split("-")[0].toLowerCase();
            nextPos = move.split("-")[1].toLowerCase();
            if (pawn.isRightMove(currPos.charAt(0), Character.digit(currPos.charAt(1), 10),
                nextPos.charAt(0), Character.digit(nextPos.charAt(1), 10)))
                System.out.println("Ход верный");
            else System.out.println("Так белая пешка не ходит!");
            System.out.println("Введите новый ход: ");
            move = inputUser.next();
        }
    }

    public static void pawnBlackCheck() {
        PawnB pawn = new PawnB();
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Введите ход в формате \'h7-h6\' или \'q\' для выхода: ");
        String currPos;
        String nextPos;

        String move = inputUser.next();
        while (!"q".equals(move)) {
            currPos = move.split("-")[0].toLowerCase();
            nextPos = move.split("-")[1].toLowerCase();
            if (pawn.isRightMove(currPos.charAt(0), Character.digit(currPos.charAt(1), 10),
                nextPos.charAt(0), Character.digit(nextPos.charAt(1), 10)))
                System.out.println("Ход верный");
            else System.out.println("Так чёрная пешка не ходит!");
            System.out.println("Введите новый ход: ");
            move = inputUser.next();
        }
    }
}
