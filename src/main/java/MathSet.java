
    public class MathSet<Num extends Number> {
        private int capacity = 16;
        private Num[] number;
        private int lastIndex;

        public MathSet() {
            number = (Num[]) new Number[capacity];
        }

        public MathSet(int capacity) {
            this.capacity = capacity;
            number = (Num[]) new Number[capacity];
        }

        public MathSet(Num[] number) {
            this();
            add(number);
        }

        public MathSet(Num[]... number) {
            this();
            for (Num[] numbers : number) {
                add(numbers);
            }
        }

        public MathSet(MathSet<Num> mathSet) {
            this();
            join(mathSet);
        }

        public MathSet(MathSet<Num>... mathSet) {
            this();
            join(mathSet);
        }

        public void add(Num n) {
            if (isNumRepeat(n))
                throw new IllegalArgumentException("argument is already present");
            number[lastIndex++] = n;
            if (lastIndex == capacity)
                increaseCapacity();
        }

        private boolean isNumRepeat(Num num) {
            if (lastIndex > 1) {
                for (int i = 0; i < lastIndex; i++) {
                    Num value = number[i];
                    if (value.equals(num))
                        return true;
                }
            }
            return false;
        }

        @SafeVarargs
        public final void add(Num... n) {
            for (Num number1 : n) {
                add(number1);
            }
        }

        public void join(MathSet<Num> ms) {
            Num[] msArray = ms.toArray();
            add(msArray);
        }

        @SafeVarargs
        public final void join(MathSet<Num>... ms) {
            for (MathSet<Num> m : ms) {
                join(m);
            }
        }

        public Num[] toArray() {
            return toArray(0, lastIndex);
        }

        public Num[] toArray(int firstIndex, int lastIndex) {
            Num[] resArray = (Num[]) new Number[lastIndex - firstIndex];
            System.arraycopy(number, firstIndex, resArray, 0, lastIndex - firstIndex);
            return resArray;
        }

        public MathSet<Num> squash(int firstIndex, int lastIndex) {
            Num[] tempArray = (Num[]) new Number[lastIndex - firstIndex];
            System.arraycopy(number, firstIndex, tempArray, 0, lastIndex - firstIndex);
            clear(tempArray);
            return new MathSet<>(tempArray);
        }

        private void increaseCapacity() {
            capacity = (capacity * 3) / 2 + 1;
            Num[] newArray = (Num[]) new Number[capacity];
            System.arraycopy(number, 0, newArray, 0, lastIndex);
            number = newArray;
        }

        public void sortAsc() {
            sortAsc(0, lastIndex);
        }

        public void sortAsc(Num value)
        {
            sortAsc(0, findIndex(value));
        }

        private int findIndex(Num value)
        {
            for (int i = 0; i < lastIndex; i++) {
                if(value.equals(number[i]))
                    return i;
            }
            throw new IllegalArgumentException("Incorrect value");
        }

        public void sortAsc(int firstIndex, int lastIndex) {
            Num[] tempArray = makeSortAsc(firstIndex, lastIndex);
            System.arraycopy(tempArray, 0, number, firstIndex, lastIndex - firstIndex);
        }

        private Num[] makeSortAsc(int firstIndex, int lastIndex) {
            Num[] tempArray = (Num[]) new Number[lastIndex - firstIndex];
            System.arraycopy(number, firstIndex, tempArray, 0, lastIndex - firstIndex);
            Num temp;
            for (int i = 0; i <= lastIndex - firstIndex; i++) {
                for (int j = 1; j < (lastIndex - firstIndex - i); j++) {
                    if (tempArray[j - 1].longValue() > tempArray[j].longValue()) {
                        temp = tempArray[j - 1];
                        tempArray[j - 1] = tempArray[j];
                        tempArray[j] = temp;
                    }
                }
            }
            return tempArray;
        }

        public void sortDsc() {
            sortDsc(0, lastIndex);
        }

        public void sortDsc(Num value){
            sortDsc(0, findIndex(value));
        }

        public void sortDsc(int firstIndex, int lastIndex) {
            Num[] tempArray = makeSortDsc(firstIndex, lastIndex);
            System.arraycopy(tempArray, 0, number, firstIndex, lastIndex - firstIndex);
        }

        private Num[] makeSortDsc(int firstIndex, int lastIndex) {
            Num[] tempArray = (Num[]) new Number[lastIndex - firstIndex];
            System.arraycopy(number, firstIndex, tempArray, 0, lastIndex - firstIndex);
            Num temp;
            for (int i = 0; i <= lastIndex - firstIndex; i++) {
                for (int j = 1; j < (lastIndex - firstIndex - i); j++) {
                    if (tempArray[j - 1].longValue() < tempArray[j].longValue()) {
                        temp = tempArray[j - 1];
                        tempArray[j - 1] = tempArray[j];
                        tempArray[j] = temp;
                    }
                }
            }
            return tempArray;
        }

        public Num get(int index) {
            if (number[index] == null)
                throw new IllegalArgumentException("Incorrect input");
            return number[index];
        }

        public Num getMax() {
            Num[] tempArray = makeSortAsc(0, lastIndex);
            return tempArray[tempArray.length - 1];
        }

        public Num getMin() {
            Num[] tempArray = makeSortAsc(0, lastIndex);
            return tempArray[0];
        }

        public Num getAverage() {
            double res = 0;
            for (int i = 0; i < lastIndex; i++) {
                res += number[i].doubleValue();
            }
            res /= lastIndex;
            return (Num) Double.valueOf(res);
        }

        public Num getMedian() {
            Num[] tempArray = makeSortAsc(0, lastIndex);
            int middleIndex = tempArray.length / 2;
            if (tempArray.length % 2 == 0) {
                double sumValue = tempArray[middleIndex].doubleValue() + tempArray[middleIndex - 1].doubleValue();
                return (Num) Double.valueOf(sumValue / 2d);
            }
            return tempArray[middleIndex];
        }

        public void clear() {
            capacity = 16;
            number = (Num[]) new Number[capacity];
            lastIndex = 0;
        }

        public void clear(Num[] nums) {
            for (int i = 0; i < lastIndex; i++) {
                for (Num num : nums) {
                    if (number[i].equals(num)) {
                        number[i] = null;
                        break;
                    }
                }
            }
            recreateArray();
        }

        private void recreateArray() {
            Num[] resArray = (Num[]) new Number[capacity];
            int index = 0;
            for (int i = 0; i < lastIndex; i++) {
                if (number[i] != null) {
                    resArray[index] = number[i];
                    index++;
                }
            }
            lastIndex = index;
            number = resArray;
        }

        public int getCapacity() {
            return capacity;
        }
        public int getSize() {
            return lastIndex;
        }

    }


