package level1;

import java.util.Scanner;

import static java.lang.Math.abs;

/**
 * Даны точки A,B,C на плоскости. Вычислить площадь треугольника ABC.
 */

public class TriangleSquare {
    public static void Main() {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Получение вершин треугольника или 'q' для выхода: ");
        System.out.println("M - Manual (ввод с клавиатуры)");
        System.out.println("R - Random (случайные числа)");
        String choice = inputUser.next();
        while (!"q".equals(choice)) {
            switch (choice) {
                case "M":
                    manualTriangle();
                    break;
                case "R":
                    randomSquare();
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный выбор!");
            }
            System.out.println("Получение вершин треугольника или 'q' для выхода: ");
            System.out.println("M - Manual (ввод с клавиатуры)");
            System.out.println("R - Random (случайные числа)");
            choice = inputUser.next();
            System.out.println();
        }
    }

    public static void manualTriangle() {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Введите координаты вершины A(x1, y1) треугольника: ");
        System.out.println("Введите x1: ");
        int x1 = inputUser.nextInt();
        System.out.println("Введите y1: ");
        int y1 = inputUser.nextInt();
        System.out.println("Введите координаты вершины B(x2, y2) треугольника: ");
        System.out.println("Введите x2: ");
        int x2 = inputUser.nextInt();
        System.out.println("Введите y2: ");
        int y2 = inputUser.nextInt();
        System.out.println("Введите координаты вершины C(x3, y3) треугольника: ");
        System.out.println("Введите x3: ");
        int x3 = inputUser.nextInt();
        System.out.println("Введите y3: ");
        int y3 = inputUser.nextInt();
        double square = triangleSquare(x1 ,y1, x2, y2, x3, y3);
        if (square == 0) System.out.println("Такого треугольника не существует!");
        System.out.println("Площадь треугольника: ");
        System.out.println(square);
        System.out.println();
    }

    public static void randomSquare() {
        int sign = Math.random() < 0.5 ? -1 : 1;
        int x1 = (int) (Math.random() * 10) * sign;
        System.out.println("x1 = " + x1);
        int y1 = (int) (Math.random() * 10) * sign;
        System.out.println("y1 = " + y1);
        int x2 = (int) (Math.random() * 10) * sign;
        System.out.println("x2 = " + x2);
        int y2 = (int) (Math.random() * 10) * sign;
        System.out.println("y2 = " + y2);
        int x3 = (int) (Math.random() * 10) * sign;
        System.out.println("x3 = " + x3);
        int y3 = (int) (Math.random() * 10) * sign;
        System.out.println("y3 = " + y3);
        double square = triangleSquare(x1 ,y1, x2, y2, x3, y3);
        if (square == 0) System.out.println("Такого треугольника не существует!");
        System.out.println("Площадь треугольника: ");
        System.out.println(square);
        System.out.println();
    }

    public static double triangleSquare(int x1, int y1, int x2, int y2, int x3, int y3) {
        return 0.5 * abs(((x2-x1)*(y3-y1))-((x3-x1)*(y2-y1)));
    }
}
