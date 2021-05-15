package org.example.console;

import org.example.level1.task1.ArrayUtil;
import org.example.level1.task2.ChessBoard;
import org.example.level1.task3.Triangle;
import org.example.level2.StringUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cli {
    ChessBoard chessBoard = new ChessBoard();
    ArrayUtil arrayUtil = new ArrayUtil();
    Triangle area = new Triangle();
    StringUtil stringUtil = new StringUtil();

    public void addLevelsMenu(String[] levelsName) throws IOException {
        for (int i = 0; i < levelsName.length; i++) {
            System.out.println((i + 1) + ": " + levelsName[i]);
        }
    }

    public int[] StringToIntegerArray(String string) {
        String[] str = string.split(",");
        int[] arr = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        return arr;
    }

    public void task1(BufferedReader reader, String input) throws IOException {
        System.out.println("get amount of unique values in array, \nenter numbers (example: 1,2,3,4)");
        input = reader.readLine();
        int result = arrayUtil.returnNumberOfUniqueVal(StringToIntegerArray(input));
        System.out.println(result);
        addTasksMenu(reader, input);
    }

    public void task2(BufferedReader reader, String input) throws IOException {
        System.out.println("Knights move \n1: show board, 2: move, 0: exit");
        input = reader.readLine();

        switch (input) {
            case "0": {
                addTasksMenu(reader, input);
                break;
            }
            case "1": {
                chessBoard.showBoard();
                task2(reader, input);
                break;
            }
            case "2": {
                System.out.println("enter figure position x,y,newX,newY (start knight position: x=1,y=0)");
                input = reader.readLine();

                int[] inputs = StringToIntegerArray(input);
                chessBoard.move(inputs[0], inputs[1], inputs[2], inputs[3]);
                task2(reader, input);
                break;
            }
        }
    }

    public void task3(BufferedReader reader, String input) throws IOException {
        System.out.println("Find area of triangle \n1: enter points, 0: exit");
        input = reader.readLine();
        switch (input) {
            case "0": {
                runMenu();
            }
            case "1": {
                input = reader.readLine();
                int[] inputs = StringToIntegerArray(input);
                String result = String.format("%.0f", area.getArea(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]));
                System.out.println(result);
                addTasksMenu(reader, input);
                break;
            }
        }
    }

    public void addTasksMenu(BufferedReader reader, String input) throws IOException {
        addLevelsMenu(new String[]{"task1", "task2", "task3"});
        input = reader.readLine();

        switch (input) {
            case "1": {
                task1(reader, input);
                break;
            }
            case "2": {
                task2(reader, input);
                break;
            }
            case "3": {
                task3(reader, input);
                break;
            }
        }
    }

    public void runMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        addLevelsMenu(new String[]{"level1", "level2"});

        while ((input = reader.readLine()) != null) {

            switch (input) {
                case "1": {
                    addTasksMenu(reader, input);
                }
                case "2": {
                    System.out.println("Check for Balanced Brackets \nenter string");
                    input = reader.readLine();
                    System.out.println(stringUtil.validateBrackets(input));
                    System.exit(0);
                }
            }
        }
    }
}
