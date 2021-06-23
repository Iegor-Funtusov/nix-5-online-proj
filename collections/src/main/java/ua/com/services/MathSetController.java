package ua.com.services;

import ua.com.collections.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class MathSetController {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        boolean check = true;
        System.out.println("System have default realisation: ");
        defaultrealistion();
        MathSet<Integer> math = new MathSet<Integer>(generate(10));
        System.out.println("\n\nGenerated Mathset<Integer> with 10 elements");
        math.showMathset();
        while (check) {
            System.out.println("\nChoose operation \n1 -> add number\n2 -> add numbers \n" +
                    "3 -> get min \n4 -> get max \n5 -> get average \n6 -> get median \n" +
                    "7 -> sort ASC \n8 -> sort DESC \n9 -> squash \n10 -> clear " +
                    "\n11 -> exit \nEnter your choise!!! ");
            int res = 0;
            try {
                String choise = reader.readLine();
                res = Integer.parseInt(choise);
            } catch (IOException | IllegalArgumentException exception) {
                System.out.println("Incorrect input. Try once more");
                continue;
            }
                switch(res) {
                    case 1: {
                        enterAddNumber(math);
                        break;
                    }
                    case 2: {
                        enterAddNumbers(math);
                        break;
                    }
                    case 3: {
                        System.out.println("Min value: " + math.getMin());
                        break;
                    }
                    case 4: {
                        System.out.println("Max value: " + math.getMax());
                        break;
                    }
                    case 5: {
                        System.out.println("Average value: " + math.getAverage());
                        break;
                    }
                    case 6: {
                        System.out.println("Median value: " + math.getMedian());
                        break;
                    }
                    case 7: {
                        math.sortAsc();
                        System.out.println("Sorted Mathset: (ASC) ");
                        math.showMathset();
                        break;
                    }
                    case 8: {
                        math.sortDesc();
                        System.out.println("Sorted Mathset: (DESC) ");
                        math.showMathset();
                        break;
                    }
                    case 9: {
                        enterIndexesToSquash(math);
                        break;
                    }
                    case 10: {
                        enterIndexesToClear(math);
                        break;
                    }
                    case 11: {
                        check = false;
                        break;
                    }
                    default: {
                        System.out.println("Make your choise again!");
                    }
                }
            }
        }

    public void enterIndexesToSquash(MathSet math){
        System.out.println("Please enter indexes: \nFrom");
        try {
            Integer from, to = null;
            String choise = reader.readLine();
            System.out.println("To");
            String choise2 = reader.readLine();
            from = Integer.parseInt(choise);
            to = Integer.parseInt(choise2);
            if(from!=null && to!=null){
                MathSet<Integer> newMath = math.squash(from, to);
                newMath.showMathset();
            }
            else{
                System.out.println("Error in entered parameters! try once more");
            }
        } catch (IOException  | IllegalArgumentException e) {
            System.out.println("Incorrect input. Try once more");
        }
    }

    public void enterIndexesToClear(MathSet math){
        System.out.println("Please enter numbers to clear: \n");
        try {
            String numbersArray = reader.readLine();
            String[] strs = numbersArray.trim().split("\\s+");
            Integer[] numbers = new Integer[strs.length];
            for (int i = 0; i < strs.length; i++){
                numbers[i] = Integer.parseInt(strs[i]);
            }
            math.clear(numbers);
            math.showMathset();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Incorrect input. Try once more");
        }
    }

    public void enterAddNumber(MathSet math){
        System.out.println("Enter number: ");
        Integer addnumber = null;
        try {
            String choise = reader.readLine();
            addnumber = Integer.parseInt(choise);
                math.add(addnumber);
                math.showMathset();
        } catch (IOException | IllegalArgumentException ex) {
            System.out.println("Incorrect input. Try once more");
        }
    }

    public void enterAddNumbers(MathSet math){
        System.out.println("Enter numbers: make a space between numbers");
        try {
            String numbersArray = reader.readLine();
            String[] strs = numbersArray.trim().split("\\s+");
            Integer[] numbers = new Integer[strs.length];
            for (int i = 0; i < strs.length; i++){
                numbers[i] = Integer.parseInt(strs[i]);
            }
                math.add(numbers);
                math.showMathset();
        } catch (IOException | IllegalArgumentException ex) {
            System.out.println("Incorrect input. Try once more");
        }
    }


    public Integer[] generate(int count){
        if(count > 0){
        Random random = new Random();
        Integer[] array = new Integer[count];
        for (int i = 0; i < count; i++) {
            array[i] = random.nextInt(100);
            }
        return array;
        }
        else{
            throw new IllegalArgumentException("Count of elements should be > 0");
        }
    }

    public void defaultrealistion() {
        Integer[] numbers = generate(6);
        System.out.print("Array of Integers:\n");
        for (Integer i : numbers) {
            System.out.print(i + " ");
        }
        System.out.println("\nPut into into constructor and get: ");
        MathSet<Integer> mathSet = new MathSet<Integer>(numbers);
        mathSet.showMathset();
        System.out.println("\nAdd several elements: ");
        Integer[] number2 = generate(3);
        System.out.print("Array of Integers:\n");
        for (Integer a : number2) {
            System.out.print(a + " ");
        }
        System.out.println("\nResult:");
        mathSet.add(number2);
        mathSet.showMathset();
        mathSet.sortAsc();
        System.out.println("\nSorted Mathset: (ASC) ");
        mathSet.showMathset();
        mathSet.sortDesc();
        System.out.println("\nSorted Mathset: (DESC) ");
        mathSet.showMathset();
        mathSet.sortAsc(1, 3);
        System.out.println("\nSorted Mathset: (ASC, from 1 to 3 values) ");
        mathSet.showMathset();
        mathSet.sortDesc(1, 3);
        System.out.println("\nSorted Mathset: (DESC, from 1 to 3 values) ");
        mathSet.showMathset();
        int value = mathSet.get(4);
        mathSet.sortAsc(value);
        System.out.println("\nSorted Mathset: (ASC, from 4 value) ");
        mathSet.showMathset();
        int value2 = mathSet.get(2);
        mathSet.sortDesc(value2);
        System.out.println("\nSorted Mathset: (DESC, from 2 value) ");
        mathSet.showMathset();
        System.out.println("\nMax value: " + mathSet.getMax());
        System.out.println("Min value: " + mathSet.getMin());
        System.out.println("Average value: " + mathSet.getAverage());
        System.out.println("Median value: " + mathSet.getMedian());
        System.out.println("Clear 1, 2, 5 elements ");
        mathSet.showMathset();
        Integer[] mas = new Integer[] {mathSet.get(1), mathSet.get(2), mathSet.get(5)};
        System.out.println("\nResult: ");
        mathSet.clear(mas);
        mathSet.showMathset();
        System.out.println("\nSquash 1-3 elements");
        MathSet<Integer> mathSet1 = mathSet.squash(1, 3);
        mathSet1.showMathset();
    }

}
