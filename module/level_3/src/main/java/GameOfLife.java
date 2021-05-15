import java.util.InputMismatchException;
import java.util.Scanner;

public class GameOfLife {
    public static void controle(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Printing the board. Input the size.\nLength: ");
        int len, width;
        len = rightInput();
        System.out.print("Width: ");
        width = rightInput();
        int[][] arr = new int[width][len];
        System.out.println("Choose the way you want to input data (random, by hand)");
        String input = scanner.nextLine();
        input = input.toLowerCase();
        boolean flag = true;
        while(flag) {
            switch (input) {
                case "random": {
                    System.out.println("1-live, 0 - dead");
                    for (int i = 0; i < width; i++) {
                        for (int k = 0; k < len; k++) {
                            arr[i][k] = (int) Math.round((Math.random() * 1) % 1);
                            System.out.print(arr[i][k] + " ");
                        }
                        System.out.println();
                    }
                    flag = false;
                }break;
                case "by hand": {
                    System.out.println("Enter 0 or 1 (with space or enter button)");
                    System.out.println("1-live, 0 - dead");
                    for (int i = 0; i < width; i++) {
                        for (int k = 0; k < len; k++) {
                            arr[i][k] = scanner.nextInt();
                        }
                        flag = false;
                    }
                }break;
                default:{
                    System.out.println("Incorrect input. Input again.");
                    input = scanner.nextLine();
                }break;
            }
        }
        choice(arr, width, len);
    }

    private static void choice(int arr[][], int width, int len){
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
            System.out.println("Choose an action\n" +
                    "\"state\" (next state)\n" +
                    "\"exit\" (exit from program)");
            String choice = scanner.nextLine();
            choice = choice.toLowerCase();
            switch (choice) {
                case "state": {
                    nextState(arr, width, len);
                    return;
                }
                case "exit":{
                    return;
                }
                default:{
                    System.out.println("Incorrect input.");
                }break;
            }
        }
    }
    private static int rightInput(){
        Scanner scanner = new Scanner(System.in);
        try {
            int num = scanner.nextInt();
            if(num <= 0 || num > 100){
                System.out.println("Number can be from 1 to 100. Input again");
                return rightInput();
            }
            return num;
        }
        catch (InputMismatchException ex){
            System.out.println("It is not an integer. Input integer");
            return rightInput();
        }
    }

    public static void nextState(int arr[][], int width, int len){
        Scanner scanner = new Scanner(System.in);
        int[][] newArr = new int[width][len];
        for(int i = 0; i < arr.length; i++){
            int live;
            for (int k = 0; k < arr[i].length; k++){
                live = countCells(arr, i, k);
                if(arr[i][k] == 1){
                    if(live < 2 || live > 3){
                        newArr[i][k] = 0;
                    }
                    else {
                        newArr[i][k] = 1;
                    }
                }
                else {
                    if(live == 3){
                        newArr[i][k] = 1;
                    }
                }
            }
        }
        board(newArr);
        while(true) {
            System.out.println("Choose an action\n" +
                    "\"state\" (next state)\n" +
                    "\"exit\" (exit from program)");
            String choice = scanner.nextLine();
            choice = choice.toLowerCase();
            switch (choice) {
                case "state": {
                    nextState(newArr, width, len);
                }break;
                case "exit":{
                    return;
                }
                default:{
                    System.out.println("Incorrect input.");
                }break;
            }
        }
    }

    private static int countCells(int arr[][], int len, int width){
        int liveCells = 0;
        if(len-1 >= 0) {
            if (width-1 >= 0 && arr[len - 1][width - 1] == 1) {
                liveCells++;
            }
            if (arr[len - 1][width] == 1) {
                liveCells++;
            }
            if (width + 1 < arr[len].length && arr[len - 1][width + 1] == 1) {
                liveCells++;
            }
        }
        if(width-1 >= 0 && arr[len][width-1] == 1){
            liveCells++;
        }
        if(width + 1 < arr[len].length && arr[len][width+1] == 1){
            liveCells++;
        }
        if(len + 1 < arr.length) {
            if (width-1 >= 0 && arr[len + 1][width - 1] == 1) {
                liveCells++;
            }
            if (arr[len + 1][width] == 1) {
                liveCells++;
            }
            if (width + 1 < arr[len].length && arr[len + 1][width + 1] == 1) {
                liveCells++;
            }
        }
        return liveCells;
    }

    public static void board (int arr[][]){
        for(int i = 0; i < arr.length; i++){
            for(int k = 0; k < arr[i].length; k++){
                System.out.print(arr[i][k] + " ");
            }
            System.out.println();
        }
    }
}