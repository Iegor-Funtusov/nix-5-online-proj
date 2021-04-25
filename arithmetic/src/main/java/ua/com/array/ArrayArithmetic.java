package ua.com.array;

import java.util.Arrays;

public class ArrayArithmetic {

    private int[] array;

    public ArrayArithmetic(int[] array) {
        this.array = array;
    }

    public void printEvenNumbers(){
        for (int i : this.array) {
            if(i % 2 == 0 && i != 0)
                System.out.print(" " + i);
        }
    }

    public int numberOfPositiveElements(){
        int counter = 0;
        for (int i : this.array) {
            if(i > 0)
                counter++;
        }
        return counter;
    }

    public int numberOfBiggerThanPrevious(){
        int counter = 0;
        for (int i = 1; i < this.array.length; i++) {
            if(this.array[i] > this.array[i-1])
                counter++;
        }
        return counter;
    }

    public int numberOfBiggerThanNeighbors(){
        int counter = 0;
        for (int i = 1; i < this.array.length - 1; i++) {
            if(this.array[i] > this.array[i-1] && this.array[i] > this.array[i+1])
                counter++;
        }
        return counter;
    }

    public void reverseArray(){
        int temp;
        for (int i = 0; i < this.array.length/2; i++) {
            temp = this.array[i];
            this.array[i] = this.array[array.length-i-1];
            this.array[array.length-i-1] = temp;
        }
    }

    public void reshufflePair(){
        int temp;
        for (int i = 1; i < this.array.length; i+=2) {
            temp = this.array[i];
            this.array[i] = this.array[i-1];
            this.array[i-1] = temp;
        }
    }


    @Override
    public String toString(){
        return Arrays.toString(this.array);
    }

}
