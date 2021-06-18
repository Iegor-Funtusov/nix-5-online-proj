import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UserControll {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void execute() throws IOException {
        printOptions();
        String input;
        MathSet<Number> mathSet = new MathSet<>();
        Number[] numbers;
        int[] bounds = new int[]{0, 0};
        int index;
        while ((input = bufferedReader.readLine()) != null) {
            try {
                switch (input) {
                    case "0":
                        return;
                    case "1":
                        mathSet = new MathSet<>();
                        break;
                    case "2":
                        System.out.println("Input capacity:");
                        mathSet = new MathSet<>(Integer.parseInt(bufferedReader.readLine()));
                        break;
                    case "3":
                        numbers = createNumberArray(bufferedReader);
                        mathSet = new MathSet<>(numbers);
                        break;
                    case "4":
                        mathSet = new MathSet<>(createMathSets(bufferedReader));
                        break;
                    case "5":
                        numbers = createNumberArray(bufferedReader);
                        mathSet.add(numbers);
                        break;
                    case "6":
                        mathSet.join(createMathSets(bufferedReader));
                        break;
                    case "7":
                        mathSet.sortDsc();
                        break;
                    case "8":
                        initBounds(bounds, bufferedReader);
                        mathSet.sortDsc(bounds[0], bounds[1]);
                        break;
                    case "9":
                        System.out.println("Input value");
                        mathSet.sortDsc(Long.parseLong(bufferedReader.readLine()));
                        break;
                    case "10":
                        mathSet.sortAsc();
                        break;
                    case "11":
                        initBounds(bounds, bufferedReader);
                        mathSet.sortAsc(bounds[0], bounds[1]);
                        break;
                    case "12":
                        System.out.println("Input value");
                        mathSet.sortAsc(Long.parseLong(bufferedReader.readLine()));
                        break;
                    case "13":
                        index = Integer.parseInt(bufferedReader.readLine());
                        System.out.println(mathSet.get(index));
                        break;
                    case "14":
                        System.out.println(mathSet.getMax());
                        break;
                    case "15":
                        System.out.println(mathSet.getMin());
                        break;
                    case "16":
                        System.out.println(mathSet.getAverage());
                        break;
                    case "17":
                        System.out.println(mathSet.getMedian());
                        break;
                    case "18":
                        System.out.println(Arrays.toString(mathSet.toArray()));
                        break;
                    case "19":
                        initBounds(bounds, bufferedReader);
                        System.out.println(Arrays.toString(mathSet.toArray(bounds[0], bounds[1])));
                        break;
                    case "20":
                        initBounds(bounds, bufferedReader);
                        System.out.println(Arrays.toString(mathSet.squash(bounds[0], bounds[1]).toArray()));
                        break;
                    case "21":
                        mathSet.clear();
                        break;
                    case "22":
                        numbers = createNumberArray(bufferedReader);
                        mathSet.clear(numbers);
                        break;
                    default:
                        System.out.println("Incorrect input. Try again.");
                }
            } catch (RuntimeException exception) {
                System.out.println("Error has occurred. Try again");
            }
            printOptions();
        }
    }

    private void initBounds(int[] bounds, BufferedReader bufferedReader) throws IOException {
        System.out.println("Input first index");
        bounds[0] = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Input second index");
        bounds[1] = Integer.parseInt(bufferedReader.readLine());
    }

    private Number[] createNumberArray(BufferedReader bufferedReader) throws IOException {
        System.out.println("Input count of elements");
        int count = Integer.parseInt(bufferedReader.readLine());
        Number[] numbers = new Number[count];
        System.out.println("Input elements:");
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Long.parseLong(bufferedReader.readLine());
        }
        return numbers;
    }

    private MathSet<Number>[] createMathSets(BufferedReader bufferedReader) throws IOException {
        System.out.println("Input count of math sets");
        int count = Integer.parseInt(bufferedReader.readLine());
        MathSet<Number>[] res = new MathSet[count];
        for (int i = 0; i < count; i++) {
            res[i] = new MathSet<>(createNumberArray(bufferedReader));
        }
        return res;

    }

    private void printOptions() {
        System.out.println("Choose an option: ");
        System.out.println("0 - stop");
        System.out.println("1 - create MathSet()");
        System.out.println("2 - create MathSet(capacity)");
        System.out.println("3 - create MathSet(Number[] ... numbers)");
        System.out.println("4 - create MathSet(MathSet ... numbers)");
        System.out.println("5 - add(Number ... n)");
        System.out.println("6 - join(MathSet ... ms)");
        System.out.println("7 - sortDesc()");
        System.out.println("8 - sortDesc(int firstIndex, int lastIndex)");
        System.out.println("9 - sortDesc(Num value)");
        System.out.println("10 - sortAsc()");
        System.out.println("11 - sortAsc(int firstIndex, int lastIndex)");
        System.out.println("12 - sortAsc(Num value)");
        System.out.println("13 - get(int index)");
        System.out.println("14 - getMax()");
        System.out.println("15 - getMin()");
        System.out.println("16 - getAverage()");
        System.out.println("17 - getMedian()");
        System.out.println("18 - toArray()");
        System.out.println("19 - toArray(int firstIndex, int lastIndex)");
        System.out.println("20 - squash(int firstIndex, int lastIndex)");
        System.out.println("21 - clear()");
        System.out.println("22 - clear(Number[] numbers)");
    }
}

