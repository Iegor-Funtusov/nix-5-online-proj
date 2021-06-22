package ua.com.collections;

import java.util.Arrays;

public class MathSet<N extends Number & Comparable<N>> {

    static final int DEFAULT_CAPACITY = 16;
    private int counter;
    private Object [] array;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    public MathSet(){
        array = new Object[DEFAULT_CAPACITY];
    }

    public MathSet(int capacity){
        if(capacity!=0){
            array = new Object[capacity];
        }
        else {
            throw new IllegalArgumentException("Capacity should be more than 0");
        }
    }

    public MathSet(N[] numbers){
        if(numbers.length!= 0){
            array = new Object[numbers.length];
            add(numbers);
        }
        else{
            throw new IllegalArgumentException("Capacity should be more than 0");
        }
    }

    MathSet(N[] ... numbers){
        if(numbers.length!=0){
            array = new Object[DEFAULT_CAPACITY];
            for (N[] elements : numbers) {
                add(elements);
            }
        }
        else{
            throw new IllegalArgumentException("Capacity should be more than 0");
        }
    }

    MathSet(MathSet<N> numbers){
        if(numbers.array.length!=0){
            array = new Object[DEFAULT_CAPACITY];
            for (Object element: numbers.toArray()) {
                add((N) element);
            }
        }
        else{
            throw new IllegalArgumentException("MathSet is empty");
        }
    }

    public MathSet(MathSet<N>... numbers){
        if(numbers.length!=0){
            array = new Object[DEFAULT_CAPACITY];
            for (MathSet<N> mathSet: numbers) {
                for (Object element: mathSet.toArray()) {
                    add((N)element);
                }
            }
        }
        else{
            throw new IllegalArgumentException("MathSet Set is empty");
        }
    }

    public void add(N n) {
        if(isUnique(n)){
           if(counter!=array.length){
               array[counter++] = n;
           }
           else {
               resize(counter);
               array[counter++] = n;
           }
        }
    }

    public void add(N... n) {
        for (N element : n) {
            add(element);
        }
    }

    public void join(MathSet ms) {
            for (Object element: ms.toArray()) {
                add((N)element);
        }
    }

    public void join(MathSet<N>... ms) {
        for (MathSet<N> mathSet: ms) {
            for (Object element: mathSet.toArray()) {
                add((N)element);
            }
        }
    }

    public void sortDesc() {
        generalSort(0,counter-1, false);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if(firstIndex>=1 && lastIndex<=counter && lastIndex > firstIndex){
            generalSort(firstIndex-1, lastIndex-1, false);
        }
        else{
            throw new IllegalArgumentException("Invalid parameters");
        }
    }

    public void sortDesc(N value) {
        int v = getIndexIfExist(value);
        if(v!=-1){
            generalSort(v, counter-1, false);
        }
    }

    public void sortAsc() {
        generalSort(0,counter-1, true);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if(firstIndex>=1 && lastIndex<=counter && lastIndex > firstIndex){
            generalSort(firstIndex-1, lastIndex-1, true);
        }
        else{
            throw new IllegalArgumentException("Invalid parameters");
        }
    }

    public void sortAsc(N value) {
        if(getIndexIfExist(value) != -1){
            generalSort(getIndexIfExist(value), counter-1, true);
        }
    }

    public void generalSort(int from, int to, boolean order){
        for (int i = from; i <= to; i++) {
            for (int j = i+1; j <= to; j++) {
                if(order){
                    if(((N) array[i]).compareTo((N)array[j]) == 1){
                        Object x = array[i];
                        array[i] = array[j];
                        array[j] = x;
                    }
                }
                else{
                    if(((N) array[i]).compareTo((N)array[j]) == -1){
                        Object x = array[i];
                        array[i] = array[j];
                        array[j] = x;
                    }
                }
            }
        }
//        for (Object o: array) {
//            System.out.println(o);
//        }
    }

    public N get(int index) {
        if(index-1 < counter){
        return (N) array[index-1];}
        else {
            throw new IndexOutOfBoundsException("Enter index up to "+ counter);
        }
    }

    public N getMax() {
        if(counter!=0) {
            N max = (N) this.get(1);
            for (int i = 1; i <= counter; i++) {
                if(max.compareTo(this.get(i)) == -1){
                    max = this.get(i);
                }
            }
            return max;
        }
        else{
            throw new IllegalArgumentException("MathSet is empty");
        }
    }

    public N getMin() {
        if(counter!=0) {
            N min = (N) this.get(1);
            for (int i = 1; i <= counter; i++) {
                if(min.compareTo(this.get(i)) == 1){
                    min = this.get(i);
                }
            }
            return min;
        }
        else{
            throw new IllegalArgumentException("MathSet is empty");
        }
    }

    public Double getAverage() {
        if(counter!=0) {
            Double average = 0.0;
            for (int i = 1; i < counter; i++) {
                    average += this.get(i).doubleValue();
                }
            return average/counter;
            }
        else {
            throw new IllegalArgumentException("MathSet is empty");
        }
    }

    public N getMedian() {
        MathSet<N> new_ = new MathSet<N>();
        new_ = this;
        new_.sortAsc();
        double result = 0.0;
        if(counter%2 == 0){
            return new_.get(counter/2 + 1);
        }
        return new_.get(counter/2 + 1);
    }

    public Object[] toArray() {
        Object [] arrayFromMathset = new Object[counter];
        for (int i = 0; i < counter; i++){
            arrayFromMathset[i] =  array[i];
        }
        return arrayFromMathset;
    }

    public Object[] toArray(int firstIndex, int lastIndex) {
        if(firstIndex>=1 && lastIndex<=counter && lastIndex > firstIndex){
            Object [] arrayFromMathset = new Object[lastIndex-firstIndex+1];
            for (int i = firstIndex-1; i < lastIndex; i++){
                arrayFromMathset[i] =  array[i];
            }
            return arrayFromMathset;
        }
        else{
            throw new IllegalArgumentException("Invalid parameters");
        }
    }

    public MathSet<N> squash(int firstIndex, int lastIndex) {
        MathSet<N> mathSetNew = new MathSet();
        if(firstIndex>=1 && lastIndex<=counter && lastIndex > firstIndex){
            for (int i = firstIndex-1; i < lastIndex; i++) {
                mathSetNew.add((N) array[i]);
            }
            return mathSetNew;
        }
        else{
            throw new IllegalArgumentException("Invalid parameters");
        }
    }

    public void clear() {
        counter = 0;
        array = new Object[DEFAULT_CAPACITY];
    }

    public void clear(N[] numbers) {
       if(numbers.length!=0) {
           for (int i = 0; i < counter; i++) {
               for (int j = 0; j < numbers.length; j++) {
                   if (array[i].equals(numbers[j])) {
                       System.arraycopy(array, i+1, array, i, counter-1-i);
                       array[counter-1] = null;
                       counter--;
//                       for (Object g: array) {
//                           System.out.println(g);
//                       }
                   }
               }
           }
       }
       else{
           throw new IllegalArgumentException("Entered empty Number array");
       }
    }

    public boolean isUnique(N n){
        for (int i = 0; i < counter; i++){
            if(n == array[i]){
                return false;
            }
        }
        return true;
    }

    public void resize(int counter){
        Object [] extendedArray = new Object[(counter*3)/2+1];
        System.arraycopy(array, 0, extendedArray, 0, counter);
        array = extendedArray;
    }

    public int getIndexIfExist(N number){
        for (int i = 0; i < counter; i++){
            if(number == array[i]){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "MathSet{" +
                "counter=" + counter +
                ", array=" + Arrays.toString(array) +
                '}';
    }

    public void showMathset() {
        StringBuilder s = null;
        for (Object a : array) {
            if (a != null) {
                System.out.print(a + " ");
            }
        }
    }
}

