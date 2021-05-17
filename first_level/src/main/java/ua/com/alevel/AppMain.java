package ua.com.alevel;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class AppMain {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        callInterface();
    }

    private static void callInterface() {
        System.out.println("First level task!");
        while (true){
            printMainMenu();
            chooseTask();
        }
    }

    @SneakyThrows
    private static void chooseTask() {
        switch (reader.readLine()){
            case "1":{
                runFirstTask();
                break;
            }
            case "2":{
                runSecondTask();
                break;
            }
            case "3":{
                runThirdTask();
                break;
            }
            case "0":{
                System.exit(0);
                break;
            }
            default:{
                System.out.println("Invalid choice! Try again.");
                break;
            }
        }

    }


    private static void printMainMenu() {
        System.out.print("Which task you want to test?" +
                "\n 1 - unique value" +
                "\n 2 - knight move" +
                "\n 3 - triangle area" +
                "\n 0 - exit" +
                "\n --> ");
    }

    private static void runSecondTask() {
        Knight knight;
        int x, y;
        while (true){
            printSecondTaskMenu();
            knight = createKnight();
            if (knight == null)
                break;
            System.out.println(knight);
            printMovingMenu();
            moveKnight(knight);
            System.out.println(knight);
        }
    }

    @SneakyThrows
    private static void moveKnight(Knight knight) {
        switch (reader.readLine()){
                case "1":{
                    manualMove(knight);
                    break;
                }
                case "2":{
                    randomMove(knight);
                    break;
                }
                case "0":{
                    break;
                }
                default:{
                    System.out.println("Invalid choice! Try again.");
                    break;
                }
        }
    }

    private static void randomMove(Knight knight) {
        int r = 2;
        int ax = knight.getX() - r;
        int ay = knight.getY() - r;
        int bx = knight.getX() + r;
        int by = knight.getY() + r;
        int x = (int) (Math.random() * (bx - ax)) + ax;
        int y = (int) (Math.random() * (by - ay)) + ay;
        System.out.println("Generated coordinates: x= " + x + " y= " + y);
        if(knight.isMoveValid(x,y)) {
            knight.move(x, y);
            System.out.println("Moved: " + knight);
        }
        else {
            System.out.println("Invalid coordinates!");
        }
    }

    private static void manualMove(Knight knight) {
        int x, y;
        System.out.println("Enter coordinates for moving:");
        x = getX();
        y = getY();
        if(knight.isMoveValid(x,y)) {
            knight.move(x, y);
            System.out.println("Moved: " + knight);
        }
        else {
            System.out.println("Invalid coordinates!");
        }
    }

    private static void printMovingMenu() {
        System.out.print("Choose type of knight moving:" +
                "\n 1 - manual" +
                "\n 2 - random" +
                "\n 0 - exit menu" +
                "\n -->");
    }



    @SneakyThrows
    private static Knight createKnight() {
        Knight knight = null;
        switch (reader.readLine()){
            case "1":{
                knight = getManualKnight();
                break;
            }
            case "2":{
                knight = getRandomKnight();
                break;
            }
            case "0":{
                break;
            }
            default:{
                System.out.println("Invalid choice! Try again.");
                break;
            }
        }
        return knight;
    }

    private static Knight getRandomKnight() {
        int min = 0;
        int max = 100;
        int x = (int) (Math.random() * (max - min)) + min;
        int y = (int) (Math.random() * (max - min)) + min;
        return new Knight(x,y);
    }

    private static Knight getManualKnight(){
        Knight knight = new Knight(getX(), getY());
        return knight;
    }

    private static int getY() {
        int y = 0;
        while (true) {
            System.out.print("Enter y: ");
            try {
                y = Integer.parseInt(reader.readLine());
                return y;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Invalid y coordinate! Try again.");
            }
        }
    }

    private static int getX() {
        int x = 0;
        while (true) {
            System.out.print("Enter x: ");
            try {
                x = Integer.parseInt(reader.readLine());
                return x;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Invalid x coordinate! Try again.");
            }
        }
    }

    private static void printSecondTaskMenu() {
        System.out.print("Second task menu!" +
                "\nChoose type of knight creating :" +
                "\n 1 - manual" +
                "\n 2 - random" +
                "\n 0 - exit menu" +
                "\n -->");
    }

    private static void runThirdTask() {
        Triangle triangle;
        while (true){
            printThirdTaskMenu();
            triangle = createTriangle();
            if (triangle == null)
                break;
            System.out.println(triangle);
            System.out.println("Triangle area: " + TriangleUtils.calculateArea(triangle));
        }
    }

    private static void printThirdTaskMenu() {
        System.out.print("Third task menu!" +
                "\nChoose type of triangle creating :" +
                "\n 1 - manual" +
                "\n 2 - random" +
                "\n 0 - exit menu" +
                "\n -->");
    }

    @SneakyThrows
    private static Triangle createTriangle() {
        Triangle triangle = null;
        switch (reader.readLine()){
            case "1":{
                triangle = getManualTriangle();
                break;
            }
            case "2":{
                triangle = getRandomTriangle();
                break;
            }
            case "0":{
                break;
            }
            default:{
                System.out.println("Invalid choice! Try again.");
                break;
            }
        }
        return triangle;
    }

    private static Triangle getRandomTriangle() {
        Point a = getRandomPoint();
        Point b = getRandomPoint();
        Point c = getRandomPoint();
        return new Triangle(a, b, c);
    }

    private static Point getRandomPoint(){
        int min = 0;
        int max = 100;
        int x = (int) (Math.random() * (max - min)) + min;
        int y = (int) (Math.random() * (max - min)) + min;
        return new Point(x, y);
    }

    private static Triangle getManualTriangle() {
        System.out.println("Enter coordinates for point A.");
        Point a = new Point(getX(), getY());
        System.out.println("Enter coordinates for point B.");
        Point b = new Point(getX(), getY());
        System.out.println("Enter coordinates for point C.");
        Point c = new Point(getX(), getY());
        return new Triangle(a, b, c);
    }

    private static void runFirstTask() {
        int[] array;
        while (true){
            printFirstTaskMenu();
            array = createArray();
            if(array == null)
                break;
            ArrayUtil.printArray(array);
            System.out.println("Number of unique elements: " +
                    ArrayUtil.countUniqueElements(array));
        }
    }

    private static void printFirstTaskMenu() {
        System.out.print("First task menu!" +
                "\nChoose type of array creating:" +
                "\n 1 - manual" +
                "\n 2 - random" +
                "\n 0 - exit menu" +
                "\n -->");
    }

    @SneakyThrows
    private static int[] createArray() {
        int[] array = null;
        switch (reader.readLine()){
            case "1":{
                array = ArrayUtil.getArray(reader);
                break;
            }
            case "2":{
                array = ArrayUtil.getRandomArray(reader);
                break;
            }
            case "0":{
                break;
            }
            default:{
                System.out.println("Invalid choice! Try again.");
                break;
            }
        }
        return array;
    }
}
