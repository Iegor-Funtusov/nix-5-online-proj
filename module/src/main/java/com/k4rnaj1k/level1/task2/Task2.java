package com.k4rnaj1k.level1.task2;

import com.k4rnaj1k.RunModule;

import java.util.Random;
import java.util.Scanner;

public class Task2 {
    public static void run() {
        Knight knight = new Knight();
        Scanner s = RunModule.s;
        int x;
        int y;
        Random r = new Random();
        do {
            System.out.println("Manual input?(y/n)");
            if (s.next().toLowerCase().startsWith("y")) {
                x = s.nextInt();
                y = s.nextInt();
            } else {
                x = r.nextInt(knight.getY()+3);
                y = r.nextInt(knight.getX()+3);
            }if(knight.placeKnight(x, y))
                System.out.println("Knight placed on coordinates ");
            else{
                System.out.println("Knight couldn't be placed at ");
            }
            System.out.printf("(%d, %d)%n", x, y);
            System.out.println("Continue?(y/n)");
        }while(s.next().startsWith("y"));
    }

    public static String name(){
        return "Infinite board";
    }

}
