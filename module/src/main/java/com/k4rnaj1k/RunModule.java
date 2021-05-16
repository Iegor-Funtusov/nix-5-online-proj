package com.k4rnaj1k;

import com.k4rnaj1k.level1.Task1;
import com.k4rnaj1k.level1.Task3;
import com.k4rnaj1k.level1.task2.Task2;
import com.k4rnaj1k.level2.StringCheck;
import com.k4rnaj1k.level3.GameOfLife;

import java.util.Scanner;

public class RunModule {
    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("Please choose task level(1-3)");
            System.out.println("""
                    1.1)Unique nums
                      2)Endless chessboard
                      3)Triangle area
                    2.String validation
                    3.Game of Life
                    """);
            int tasklevel = s.nextInt();
            int tasknum;
            switch (tasklevel) {
                case 1: {
                    System.out.println("Please select task(1-3)");
                    tasknum = s.nextInt();
                }
                break;
                case 2: {
                    tasknum = 4;
                }
                break;
                case 3: {
                    tasknum = 5;
                }
                break;
                default:
                    tasknum = 5;
            }
            levelselect(tasknum);
            System.out.println("Repeat?(y/n)");
        } while (s.next().toLowerCase().startsWith("y"));
    }

    public static void levelselect(int tasknum) {
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
            case 4: {
                System.out.println(StringCheck.name);
                StringCheck.run();
            }
            case 5: {
                System.out.println(GameOfLife.name);
                GameOfLife.run();
            }
            break;
        }
    }

}

