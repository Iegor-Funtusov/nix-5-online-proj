import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Выберите уровень с заданиями или нажмите 'q' для выхода: ");
        System.out.println("1 - Уникальные значения в массиве, ход коня, площадь треугольника");
        System.out.println("2 - Допустимая строка");
        System.out.println("3 - Игра \"Жизнь\"");
        String choice = inputUser.next();
        while (!"q".equals(choice)) {
            switch (choice) {
                case "1":
                    level1();
                    break;
                case "2":
                    level2();
                    break;
                case "3":
                    level3();
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный выбор!");
            }
            System.out.println("Выберите уровень с заданиями или нажмите 'q' для выхода: ");
            System.out.println("1 - Уникальные значений в массиве, ход коня, площадь треугольника");
            System.out.println("2 - Допустимая строка");
            System.out.println("3 - Игра \"Жизнь\"");
            choice = inputUser.next();
        }
    }
    public static void level1() {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Выбор задания или 'q' для выхода: ");
        System.out.println("1 - Поиск уникальных значений в массиве");
        System.out.println("2 - Ход коня");
        System.out.println("3 - Площадь треугольника по трём точкам");
        String choice = inputUser.next();
        System.out.println();
        while (!"q".equals(choice)) {
            switch (choice) {
                case "1":
                    level1.Array.Main();
                    break;
                case "2":
                    level1.Knight.knightCheck();
                    break;
                case "3":
                    level1.TriangleSquare.Main();
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный выбор!");
            }
            System.out.println("Выбор задания или 'q' для выхода: ");
            System.out.println("1 - Поиск уникальных значений в массиве");
            System.out.println("2 - Ход коня");
            System.out.println("3 - Площадь треугольника по трём точкам");
            choice = inputUser.next();
            System.out.println();
        }
    }

    public static void level2() {
            level2.BracketSequences.Main();
    }

    public static void level3() {
            level3.Game.Main();
    }
}
