package Main;

import MathSet.MathSet;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class ConsolControl {
    public void control(){
        Scanner sc = new Scanner(System.in);
        MathSet<Integer> set = new MathSet<Integer>();
        while (true){
            System.out.println("Input action that you want to do with MathSet\n" +
                    "1 >> add\n" +
                    "2 >> join (random numbers to your MathSet)\n" +
                    "3 >> sort\n" +
                    "4 >> get (index, max, min, average, median)\n" +
                    "5 >> squash\n" +
                    "6 >> clear\n" +
                    "7 >> show all elements in MathSet\n" +
                    "8 >> end program");
            String choice = sc.next();
            switch (choice) {
                case "1": {
                    set = adding(set);
                }
                break;
                case "2": {
                    set = join(set);
                }
                break;
                case "3": {
                    set = sorting(set);
                }
                break;
                case "4": {
                    getting(set);
                }
                break;
                case "5": {
                    MathSet<Integer> newSet;
                    newSet = squash(set);
                    System.out.println("Your subset:");
                    readAll(newSet);
                }
                break;
                case "6": {
                    set = clear(set);
                }
                break;
                case "7":{
                    readAll(set);
                }break;
                case "8": {
                    System.exit(0);
                }
                break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private void readAll(MathSet set) {
        for (int i = 0; i < set.length(); i++) {
            System.out.print(set.get(i)+ " ");
        }
        System.out.println();
    }

    private MathSet join(MathSet set) {
        MathSet<Integer> mathSet = new MathSet<Integer>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            mathSet.add(random.nextInt(100));
        }
        set.join(mathSet);
        System.out.println("Random numbers was joined");
        return set;
    }

    private MathSet clear(MathSet set) {
        Scanner scanner = new Scanner(System.in);
        if(checkSet(set) == false){
            System.out.println("MathSet is empty");
            return set;
        }
        while (true) {
            System.out.println("Choose an action how to clear MathSet\n" +
                    "1 >> clear all MathSet\n" +
                    "2 >> clear via array of numbers");
            String choice = scanner.next();
            switch (choice) {
                case "1": {
                    set.clear();
                    return set;
                }
                case "2": {
                    System.out.println("Input integers that you want to clear in MathSet (separated by space)");
                    String numbers = checkInput();
                    String[] nums = numbers.split(" ");
                    Integer[] array = new Integer[nums.length];
                    for (int i = 0; i < nums.length; i++) {
                        array[i] = Integer.parseInt(nums[i]);
                    }
                    set.clear(array);
                    return set;
                }
                default:
                    System.out.println("Wrong Input");
            }
        }
    }

    private MathSet squash(MathSet set) {
        if(checkSet(set) == false){
            System.out.println("MathSet is empty");
            return set;
        }
        while (true) {
            System.out.println("Input indexes");
            int index1, index2;
            System.out.println("First index");
            index1 = checkIndex(set.length());
            System.out.println("Second index");
            index2 = checkIndex(set.length());
            if (index1 < index2) {
                return set.squash(index1, index2);
            } else {
                System.out.println("Wrong input. First index is more than second. Input again");
            }
        }
    }

    private void getting(MathSet set) {
        Scanner scanner = new Scanner(System.in);
        if(checkSet(set) == false){
            System.out.println("MathSet is empty");
            return;
        }
        while (true) {
            System.out.println("Choose what you want to get:\n" +
                    "1 >> get value by index\n" +
                    "2 >> get maximum\n" +
                    "3 >> get minimum\n" +
                    "4 >> get average\n" +
                    "5 >> get median");
            String choice = scanner.next();
            switch (choice) {
                case "1": {
                    System.out.println("Input index:");
                    int index = checkIndex(set.length());
                    System.out.println(set.get(index));
                    return;
                }
                case "2": {
                    System.out.println(set.getMax());
                    return;
                }
                case "3": {
                    System.out.println(set.getMin());
                    return;
                }
                case "4": {
                    System.out.println(set.getAverage());
                    return;
                }
                case "5": {
                    System.out.println(set.getMedian());
                    return;
                }
                default:
                {
                    System.out.println("Wrong input. Input again");
                }
            }
        }
    }

    private MathSet sorting(MathSet set) {
        Scanner scanner = new Scanner(System.in);
        if(checkSet(set) == false){
            System.out.println("MathSet is empty");
            return set;
        }
        boolean mainFlag = true;
        while (mainFlag) {
            System.out.println("Choose the way that you want to sort MathSet:\n" +
                    "1 >> sortAsc\n" +
                    "2 >> sortAsc (with indexes)\n" +
                    "3 >> sortAsc (with value)\n" +
                    "4 >> sortDesc\n" +
                    "5 >> sortDesc (with indexes)\n" +
                    "6 >> sortDesc (with value)\n");
            String choice = scanner.nextLine();
            boolean flag = true;
            switch (choice) {
                case "1": {
                    set.sortAsc();
                    mainFlag = false;
                }
                break;
                case "2": {
                    while (flag) {
                        System.out.println("Input indexes (by order)");
                        int first, last;
                        System.out.println("First index");
                        first = checkIndex(set.length());
                        System.out.println("Second index");
                        last = checkIndex(set.length());
                        if (first < last) {
                            set.sortAsc(first, last);
                            flag = false;
                            mainFlag = false;
                        }
                        else{
                            System.out.println("Wrong input. First index is more than second. Input again");
                        }
                    }
                }
                break;
                case "3": {
                    System.out.println("Input value from which you want to sort");
                    int num = checkNumber();
                    set.sortAsc(num);
                    mainFlag = false;
                }
                break;
                case "4": {
                    set.sortDesc();
                    mainFlag = false;
                }
                break;
                case "5": {
                    while (flag) {
                        System.out.println("Input indexes (by order)");
                        int first, last;
                        System.out.println("First index");
                        first = checkIndex(set.length());
                        System.out.println("Second index");
                        last = checkIndex(set.length());
                        if (first < last) {
                            set.sortDesc(first, last);
                            flag = false;
                            mainFlag = false;
                        }
                        else{
                            System.out.println("Wrong input. First index is more than second. Input again");
                        }
                    }
                }
                break;
                case "6": {
                    System.out.println("Input value from which you want to sort");
                    int num = checkNumber();
                    set.sortDesc(num);
                    mainFlag = false;
                }
                break;
                default:
                    System.out.println("Wrong input");
            }
        }
        return set;
    }

    private int checkIndex(int len) {
        Scanner scanner = new Scanner(System.in);
        int index;
        try{
            index = scanner.nextInt();
            if(index < 0 || index > len-1){
                System.out.println("Your index is beyond the bounds of MathSet. Input again");
                return checkIndex(len);
            }
            return index;
        }catch (InputMismatchException e){
            System.out.println("Input only one integer");
            return checkIndex(len);
        }
    }


    private boolean checkSet(MathSet set) {
        if(set.length() == 0)
            return false;
        return true;
    }

    private MathSet adding(MathSet set) {
        System.out.println("Input number or numbers (separate numbers by space) for adding it to MathSet");
        String numbers = checkInput();
        String[] nums = numbers.split(" ");
        for (int i = 0; i < nums.length; i++) {
            set.add(Integer.parseInt(nums[i]));
        }
        return set;
    }

    private String checkInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        for (int i = 0; i < input.length(); i++) {
            if(!(Character.isDigit(input.charAt(i))) && input.charAt(i) != ' '){
                System.out.println("Please, input numbers separated by space");
                return checkInput();
            }
        }
        return input;
    }
    private int checkNumber(){
        Scanner scanner = new Scanner(System.in);
        int num;
        try {
            num = scanner.nextInt();
            return num;
        } catch (InputMismatchException e){
            System.out.println("Input only one number. Repeat entering");
            return checkNumber();
        }
    }
}
