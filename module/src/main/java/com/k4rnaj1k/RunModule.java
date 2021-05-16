package com.k4rnaj1k;

import com.k4rnaj1k.level1.Task1;
import com.k4rnaj1k.level1.Task3;
import com.k4rnaj1k.level1.task2.Task2;

import java.util.Scanner;

public class RunModule {
    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("Please choose task level(1-3)");
            int tasklevel = s.nextInt();
            switch (tasklevel) {
                case 1: {
                    System.out.println("Please select task(1-3)");
                    int tasknum = s.nextInt();
                    level1select(tasknum);
                }
                break;
                case 2: {
                    // com.k4rnaj1k.level1.
                }
            }
            System.out.println("Repeat?(y/n)");
        } while (s.next().toLowerCase().startsWith("y"));
    }

    public static void level1select(int tasknum) {
        System.out.println("So you have chosen ");
        switch (tasknum) {
            case 1: {
                System.out.println(Task1.name());
                Task1.solve();
            }
            break;
            case 2: {
                System.out.println(Task2.name());
                Task2.run();
            }
            break;
            case 3: {
                System.out.println(Task3.name);
                Task3.run();
            }
            break;
        }
    }

}

