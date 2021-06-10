package MathSet;

import java.util.Iterator;

public class MathSet implements InterfaceMS {
    private int counter = -1;
    private final int SIZE = 10;
    private Number[] set;
    public MathSet(){
        set = new Number[SIZE];
    }

    public MathSet(int capacity){
        if(capacity < 0){
            throw new NegativeArraySizeException("Capacity can't be negative: " + capacity);
        }
        set = new Number[capacity];
    }

    public MathSet(Number[] numbers){
        if(numbers == null){
            throw new NullPointerException("Array can't be null");
        }
        set = numbers;
    }

    public MathSet(Number[] ... numbers){
        if(numbers == null){
            throw new NullPointerException("Array can't be null");
        }
        set = new Number[numbers.length];
        for(Number[] i : numbers){
            add(i);
        }
    }

    public MathSet(MathSet numbers){
        set = numbers.toArray();
    }

    public MathSet(MathSet ... numbers){
        for(MathSet mathSet:numbers){
            add(mathSet.toArray());
        }
    }



    public void add(Number n){
        resize();
        for(int i = 0; i <= counter; i++){
            if(n == set[i]){
                return;
            }
        }
        counter++;
        set[counter] = n;
    }

    public void add(Number ... n){
        boolean flag = false;
        for(Number k: n){
            for(int i = 0; i <= counter; i++){
                if(set[i] == k)
                {
                    flag = true;
                    break;
                }
            }
            if(flag == false)
                add(k);
            flag = false;
        }
    }

    public void join(MathSet mathset){
        Number[] number = mathset.toArray();
        System.out.println(number.length);
        for (int i = 0; i < number.length; i++) {
            resize();
            add(number[i]);
        }

    }

    public void join(MathSet ... mathset){
        for(MathSet mathSet: mathset){
            join(mathSet);
        }
    }

    public void sortDesc(){
        for (int i = 0; i <= counter; i++) {
            for (int j = i+1; j <= counter; j++) {
                if(set[i].doubleValue() < set[j].doubleValue()){
                    Number swap = set[i];
                    set[i] = set[j];
                    set[j] = swap;
                }
            }
        }
    }

    public void sortDesc(int firstIndex, int lastIndex){
        exceptionsIndexes(firstIndex, lastIndex);

        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = i+1; j < lastIndex; j++) {
                if(set[i].doubleValue() < set[j].doubleValue()){
                    Number swap = set[i];
                    set[i] = set[j];
                    set[j] = swap;
                }
            }
        }
    }

    public void sortDesc(Number value){
        int index = checkNumber(value);
        if(index != -1) {
            for (int i = index; i <= counter; i++) {
                for (int j = i + 1; j <= counter; j++) {
                    if (set[i].doubleValue() < set[j].doubleValue()) {
                        Number swap = set[i];
                        set[i] = set[j];
                        set[j] = swap;
                    }
                }
            }
        }
    }

    public void sortAsc(){
        for (int i = 0; i <= counter; i++) {
            for (int j = i+1; j <= counter; j++) {
                if(set[i].doubleValue() > set[j].doubleValue()){
                    Number swap = set[i];
                    set[i] = set[j];
                    set[j] = swap;
                }
            }
        }
    }

    public void sortAsc(int firstIndex, int lastIndex){
        exceptionsIndexes(firstIndex, lastIndex);

        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = i+1; j < lastIndex; j++) {
                if(set[i].doubleValue() > set[j].doubleValue()){
                    Number swap = set[i];
                    set[i] = set[j];
                    set[j] = swap;
                }
            }
        }
    }

    public void sortAsc(Number value){
        int index = checkNumber(value);
        if(index != -1) {
            for (int i = index; i <= counter; i++) {
                for (int j = i + 1; j <= counter; j++) {
                    if (set[i].doubleValue() > set[j].doubleValue()) {
                        Number swap = set[i];
                        set[i] = set[j];
                        set[j] = swap;
                    }
                }
            }
        }
    }

    public Number get(int index){
        if(index < 0 || index > counter)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        return set[index];
    }

    public Number getMax(){
        if(set.length == 0){
            throw new RuntimeException("Maximum number doesn't exist in empty set");
        }
        int max = 0;
        for(int i = 0; i <= counter; i++){
            if(set[max].doubleValue() < set[i].doubleValue()){
                max = i;
            }
        }
        return set[max];
    }

    public Number getMin(){
        if(set.length == 0){
            throw new RuntimeException("Maximum number doesn't exist in empty set");
        }
        int min = 0;
        for(int i = 1; i <= counter; i++){
            if(set[min].doubleValue() > set[i].doubleValue()){
                min = i;
            }
        }
        return set[min];
    }

    public Number getAverage(){
        double sum = 0, average;
        for (int i = 0; i <= counter; i++) {
            sum += set[i].doubleValue();
        }
        average = sum/(counter+1);
        return average;
    }

    public Number getMedian(){
        Number[] numbers = new Number[set.length];
        System.arraycopy(set, 0, numbers, 0, set.length);
        for (int i = 0; i <= counter; i++) {
            for (int j = i+1; j <= counter; j++) {
                if(numbers[i].doubleValue() > numbers[j].doubleValue()){
                    Number swap = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = swap;
                }
            }
        }
        double median;
        if((counter+1) % 2 == 0){
            median = (numbers[(counter+1)/2].doubleValue() + numbers[(counter+1)/2 - 1].doubleValue())/2;
        }
        else {
            median = numbers[(counter+1)/2].doubleValue();
        }
        return median;
    }

    public Number[] toArray(){
        Number[] arr = new Number[counter+1];
        System.arraycopy(set,0, arr, 0, counter+1);
        return arr;
    }

    public Number[] toArray(int firstIndex, int lastIndex){
        exceptionsIndexes(firstIndex, lastIndex);

        Number[] numbers = new Number[lastIndex-firstIndex];
        int k = 0;
        for (int i = firstIndex; i < lastIndex; i++) {
            numbers[k] = set[i];
            k++;
        }
        return numbers;
    }

    public MathSet squash(int firstIndex, int lastIndex){
        exceptionsIndexes(firstIndex, lastIndex);

        MathSet mathSet = new MathSet();
        for (int i = firstIndex; i < lastIndex; i++) {
            mathSet.add(set[i]);
        }
        return mathSet;
    }

    public void clear(){
        set = new Number[SIZE];
        counter = -1;
    }

    public void clear(Number[] numbers){
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < set.length; j++) {
                if(numbers[i].equals(set[j])){
                    for (int k = j+1; k <= counter; k++) {
                        set[j] = set[k];
                        j++;
                    }
                    set[counter] = null;
                    counter--;
                    break;
                }
            }
        }
    }

    public int length(){
        return counter+1;
    }

    private void exceptionsIndexes(int firstIndex, int lastIndex){
        if(firstIndex > lastIndex)
            throw new IllegalArgumentException("First index can't be more than last index");
        if(firstIndex < 0 || firstIndex > counter)
            throw new ArrayIndexOutOfBoundsException("First index out of bounds");
        if(lastIndex > counter)
            throw new ArrayIndexOutOfBoundsException("Last index out of bounds");
    }

    private void resize(){
        if(set[set.length-1] != null){
            Number[] arr = new Number[set.length*2];
            System.arraycopy(set,0, arr, 0, counter+1);
            set = arr;
        }
    }

    private int checkNumber(Number value){
        int index = -1;
        for (int i = 0; i <= counter; i++) {
            if(value.doubleValue() == set[i].doubleValue())
            {
                index = i;
                return index;
            }
        }
        return index;
    }

    @Override
    public Iterator<Number> iterator() {
        return null;
    }
}
