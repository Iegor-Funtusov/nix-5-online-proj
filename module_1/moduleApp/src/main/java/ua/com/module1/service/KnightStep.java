package ua.com.module1.service;

import ua.com.module1.entity.Knight;

import java.util.Scanner;

public class KnightStep {
    static int counter = 0;

    public static void getKnightStep() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter x: ");
    int x = scanner.nextInt();
    System.out.println("Enter y: ");
    int y = scanner.nextInt();
    Knight knight = new Knight(x, y);
    System.out.println("Enter next x: ");
    int x_next = scanner.nextInt();
    System.out.println("Enter next y: ");
    int y_next = scanner.nextInt();
    if(knight.run(x_next, y_next)){
        System.out.println("You can make this step!");
    }
    else {
        System.out.println("Unfortunately, knight can not make this step");
    }
}

    public static void getKnightStepAuto() {
        Knight knight = new Knight();
        System.out.println(knight);
        int counter = 0;
        while(counter <=20) {
            System.out.println(" ");
            System.out.println("Random step ¹ " + counter);
            generateNext(knight);
            counter++;
        }
    }

    public static int generateXCoordinate(Knight knight){
     int a = (int) Math.round(Math.random() * 4) - 2  + knight.getX();
     if(Math.abs(a - knight.getX()) == 1 || Math.abs(a - knight.getX()) == 0){
         generateXCoordinate(knight);
        }
        return a;
    }

    public static int generateYCoordinate(Knight knight){
        int b = (int) Math.round(Math.random() * 4) - 2  + knight.getY();
        if(Math.abs(b - knight.getY()) == 1 || Math.abs(b - knight.getY()) == 0){
            generateXCoordinate(knight);
        }
        return b;
    }

    public static void generateNext(Knight knight){
        int x_next = generateXCoordinate(knight);
        int y_next = generateYCoordinate(knight);
        System.out.println("Next coordinate: x=" + x_next + ", y=" + y_next);
        if(knight.run(x_next, y_next)){
            System.out.println("You can make this step!");
        }
        else {
            System.out.println("Unfortunately, knight can not make this step");
        }
    }


}