package MathSet;

public class MathSet<E extends Number & Comparable<E>> implements InterfaceMS<E>{
    private int counter = -1;
    private final int SIZE = 10;
    private Object[] set;
    public MathSet(){
        set = new Object[SIZE];
    }

    public MathSet(int capacity){
        if(capacity < 0){
            throw new NegativeArraySizeException("Capacity can't be negative: " + capacity);
        }
        set = new Object[capacity];
    }

    public MathSet(Number[] numbers){
        if(numbers == null || numbers.length == 0){
            throw new NullPointerException("Array can't be null and can't be empty");
        }
        set = new Object[SIZE];
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i] != null){
                add((E) numbers[i]);
            }
        }
    }

    public MathSet(E[] ... numbers){
        if(numbers == null){
            throw new NullPointerException("Array can't be null");
        }
        set = new Object[SIZE];
        for(int i = 0; i < numbers.length; i++){
            for (int j = 0; j < numbers[i].length; j++) {
                if(numbers[i][j] != null)
                    add(numbers[i][j]);
            }
        }
    }


    public MathSet(MathSet<E> numbers){
        this();
        join(numbers);
    }

    public MathSet(MathSet<E> ... numbers){
        this();
        for(MathSet mathSet:numbers){
            join(numbers);
        }
    }



    public void add(E n){
        checkNull(n);
        resize();
        for(int i = 0; i <= counter; i++){
            if(n == set[i]){
                return;
            }
        }
        counter++;
        set[counter] = n;
    }

    public void add(E ... n){
        boolean flag = false;
        for(E k: n){
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
        Object[] number = mathset.toArray();
        for (int i = 0; i < number.length; i++) {
            resize();
            add((E) number[i]);
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
                if(((E) set[i]).compareTo((E)set[j]) == -1){
                    Object swap = set[i];
                    set[i] = set[j];
                    set[j] = swap;
                }
            }
        }
    }

    public void sortDesc(int firstIndex, int lastIndex){
        exceptionsIndexes(firstIndex, lastIndex);

        for (int i = firstIndex; i <= lastIndex; i++) {
            for (int j = i+1; j <= lastIndex; j++) {
                if(((E) set[i]).compareTo((E)set[j]) == -1){
                    Object swap = set[i];
                    set[i] = set[j];
                    set[j] = swap;
                }
            }
        }
    }

    public void sortDesc(E value){
        checkNull(value);
        int index = checkNumber(value);
        if(index != -1) {
            for (int i = index; i <= counter; i++) {
                for (int j = i + 1; j <= counter; j++) {
                    if(((E) set[i]).compareTo((E)set[j]) == -1) {
                        Object swap = set[i];
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
                if(((E) set[i]).compareTo((E)set[j]) == 1){
                    Object swap = set[i];
                    set[i] = set[j];
                    set[j] = swap;
                }
            }
        }
    }

    public void sortAsc(int firstIndex, int lastIndex){
        exceptionsIndexes(firstIndex, lastIndex);

        for (int i = firstIndex; i <= lastIndex; i++) {
            for (int j = i+1; j <= lastIndex; j++) {
                if(((E) set[i]).compareTo((E)set[j]) == 1){
                    Object swap = set[i];
                    set[i] = set[j];
                    set[j] = swap;
                }
            }
        }
    }

    public void sortAsc(E value){
        checkNull(value);
        int index = checkNumber(value);
        if(index != -1) {
            for (int i = index; i <= counter; i++) {
                for (int j = i + 1; j <= counter; j++) {
                    if(((E) set[i]).compareTo((E)set[j]) == 1) {
                        Object swap = set[i];
                        set[i] = set[j];
                        set[j] = swap;
                    }
                }
            }
        }
    }

    public E get(int index){
        if(index < 0 || index > counter)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        return (E) set[index];
    }

    public E getMax(){
        if(counter+1 == 0){
            throw new RuntimeException("Maximum number doesn't exist in empty set");
        }
        int max = 0;
        for(int i = 0; i <= counter; i++){
            if(((E) set[max]).compareTo((E)set[i])  == -1){
                max = i;
            }
        }
        return (E) set[max];
    }

    public E getMin(){
        if(counter+1 == 0){
            throw new RuntimeException("Minimum number doesn't exist in empty set");
        }
        int min = 0;
        for(int i = 1; i <= counter; i++){
            if(((E) set[min]).compareTo((E)set[i]) == 1){
                min = i;
            }
        }
        return (E) set[min];
    }

    public double getAverage(){
        if(counter+1 == 0){
            throw new RuntimeException("Minimum number doesn't exist in empty set");
        }
        Number[] numbers = new Number[set.length];
        System.arraycopy(set, 0, numbers, 0, set.length);
        double sum = 0, average;
        for (int i = 0; i <= counter; i++) {
            sum += numbers[i].doubleValue();
        }
        average = sum/(counter+1);
        return average;
    }

    public double getMedian(){
        if(counter+1 == 0){
            throw new RuntimeException("Minimum number doesn't exist in empty set");
        }
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

    public Object[] toArray(){
        Object[] arr = new Object[counter+1];
        System.arraycopy(set,0, arr, 0, counter+1);
        return arr;
    }

    public Object[] toArray(int firstIndex, int lastIndex){
        exceptionsIndexes(firstIndex, lastIndex);

        Object[] numbers = new Object[lastIndex-firstIndex];
        int k = 0;
        for (int i = firstIndex; i < lastIndex; i++) {
            numbers[k] = set[i];
            k++;
        }
        return numbers;
    }

    public MathSet<E> squash(int firstIndex, int lastIndex){
        exceptionsIndexes(firstIndex, lastIndex);
        MathSet<E> mathSet = new MathSet();
        for (int i = firstIndex; i <= lastIndex; i++) {
            mathSet.add((E)set[i]);
        }
        return mathSet;
    }

    public void clear(){
        set = new Object[SIZE];
        counter = -1;
    }

    public void clear(Number[] numbers){
        for (int i = 0; i < numbers.length; i++) {
            checkNull((E) numbers[i]);
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
            Object[] arr = new Object[set.length*2];
            System.arraycopy(set,0, arr, 0, counter+1);
            set = arr;
        }
    }

    private int checkNumber(E value){
        int index = -1;
        for (int i = 0; i <= counter; i++) {
            if(value.equals(set[i]))
            {
                index = i;
                return index;
            }
        }
        return index;
    }

    private void checkNull(E value){
        if(value == null){
            throw new NullPointerException("MathSet contains null");
        }
    }
}
