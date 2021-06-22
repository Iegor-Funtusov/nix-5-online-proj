package ua.com.services;

import ua.com.collections.MathSet;

import java.util.Random;

public class MathSetController {

    public void run(){
        boolean check = false;
        System.out.println("System have default realisation: ");
        defaultrealistion();
//        while(check){
//
//        }
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
