package ua.com.nkrasnovoronka.controller;

import ua.com.nkrasnovoronka.mathset.MathSet;
import ua.com.nkrasnovoronka.mathset.impl.MathSetImpl;
import ua.com.nkrasnovoronka.util.UserInput;

import java.util.List;

public class MathSetController {
    private static MathSet mathSet = new MathSetImpl();

    public void run(){
        while (true){
            int i = 0;

            try {
                i = UserInput.userInputNumber("Pleas choose action\n1 - add\n2 - sort\n3 - getMax\n4 - getMin" +
                        "\n5 - getAverage\n6 - getMedian\n7 - print\n0 - exit");
            }catch (NumberFormatException e){
                System.err.println("Invalid input pleas try again");
                run();
            }
            switch (i){
                case 1: addElement(); break;
                case 2: sortMathSet(); break;
                case 3: getMax(); break;
                case 4: getMin(); break;
                case 5: getAverage(); break;
                case 6: getMedian(); break;
                case 7:showMathSetElements(); break;
                case 0: System.exit(0);
            }

        }
    }

    private void getMin() {
        Number min = mathSet.getMin();
        System.out.println("min = " + min);
    }

    public void addElement(){
        List<Integer> integers = UserInput.userInputNumbers("Pleas enter numbers that will be added to mathSet");
        for (int n:integers) {
            mathSet.add(n);
        }
    }

    public void showMathSetElements(){
        System.out.println(mathSet);
    }

    public void getMax(){
        Number max = mathSet.getMax();
        System.out.println("max = " + max);
    }

    public void getAverage(){
        Number average = mathSet.getAverage();
        System.out.println("average = " + average);
    }

    public void getMedian(){
        Number median = mathSet.getMedian();
        System.out.println("median = " + median);
    }

    public void sortMathSet(){
        int i = UserInput.userInputNumber("1 - ASC\n2 - DESC");
        switch (i){
            case 1:{
                ascSorts();
                break;
            }
            case 2:{
                descSort();
                break;
            }
            default:{
                System.out.println("Wrong action pleas try again");
                sortMathSet();
            }
        }


    }

    private void descSort() {
        int i = UserInput.userInputNumber("1 - Without index\n2 - with index");
        switch (i){
            case 1:{
                mathSet.sortDesc();
                break;
            }
            case 2:{
                indexSort(true);
                break;
            }
            default:{
                System.out.println("Wrong action pleas try again");
                descSort();
            }
        }


    }

    private void ascSorts() {
        int i = UserInput.userInputNumber("1 - Without index\n2 - with index");
        switch (i){
            case 1:{
                mathSet.sortAsc();
                break;
            }
            case 2:{
                indexSort(false);
                break;
            }
            default:{
                System.out.println("Wrong action pleas try again");
                ascSorts();
            }
        }

    }

    private void indexSort(boolean desc) {
        List<Integer> integers = UserInput.userInputNumbers("Enter first and last index seperated by space");
        if(integers.size() != 2){
            System.out.println("Only two index allowed pleas try again");
            indexSort(false);
        }
        if(desc){
            mathSet.sortDesc(integers.get(0), integers.get(1));
        }else {
            mathSet.sortAsc(integers.get(0), integers.get(1));
        }
    }


}
