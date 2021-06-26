package ua.com.alevel.app.controller;

import ua.com.alevel.app.service.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MathSetController {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void menu() throws IOException {
        String input;
        MathSet<Number> mathSet = new MathSet<>();
        MathSet<Number> additionalMathSet;
        Number[] numbers;
        Number[][] arrOfNums;
        int[] bounds = new int[]{0, 0};
        int index;

        printOptions();
        while ((input = bufferedReader.readLine()) != null) {
            try {
                switch (input) {
                    case "0":
                        return;
                    case "1":
                        mathSet = new MathSet<>();
                        break;
                    case "2":
                        System.out.println("Please, input capacity:");
                        mathSet = new MathSet<>(Integer.parseInt(bufferedReader.readLine()));
                        break;
                    case "3":
                        numbers = inputElements(bufferedReader);
                        mathSet = new MathSet<>(numbers);
                        break;
                    case "4":
                        mathSet = new MathSet<>(createNumberArrays(bufferedReader));
                        break;
                    case "5":
                        numbers = inputElements(bufferedReader);
                        mathSet = new MathSet<>(new MathSet<>(numbers));
                        break;
                    case "6":
                        mathSet = new MathSet<>(createMathSets(bufferedReader));
                        break;
                    case "7":
                        System.out.println("Please, enter the number:");
                        mathSet.add(Double.parseDouble(bufferedReader.readLine()));
                        break;
                    case "8":
                        numbers = inputElements(bufferedReader);
                        mathSet.add(numbers);
                        break;
                    case "9":
                        numbers = inputElements(bufferedReader);
                        additionalMathSet = new MathSet<>(numbers);
                        mathSet.join(additionalMathSet);
                        break;
                    case "10":
                        mathSet.join(createMathSets(bufferedReader));
                        break;
                    case "11":
                        mathSet.sortDesc();
                        break;
                    case "12":
                        inputBounds(bounds, bufferedReader);
                        mathSet.sortDesc(bounds[0], bounds[1]);
                        break;
                    case "13":
                        System.out.println("Please, input value");
                        mathSet.sortDesc(Integer.parseInt(bufferedReader.readLine()));
                        break;
                    case "14":
                        mathSet.sortAsc();
                        break;
                    case "15":
                        inputBounds(bounds, bufferedReader);
                        mathSet.sortAsc(bounds[0], bounds[1]);
                        break;
                    case "16":
                        System.out.println("Please, input value");
                        mathSet.sortAsc(Integer.parseInt(bufferedReader.readLine()));
                        break;
                    case "17":
                        System.out.println("Please, enter the index:");
                        index = Integer.parseInt(bufferedReader.readLine());
                        System.out.println(mathSet.get(index));
                        break;
                    case "18":
                        System.out.println(mathSet.getMax());
                        break;
                    case "19":
                        System.out.println(mathSet.getMin());
                        break;
                    case "20":
                        System.out.println(mathSet.getAverage());
                        break;
                    case "21":
                        System.out.println(mathSet.getMedian());
                        break;
                    case "22":
                        System.out.println(Arrays.toString(mathSet.toArray()));
                        break;
                    case "23":
                        inputBounds(bounds, bufferedReader);
                        System.out.println(Arrays.toString(mathSet.toArray(bounds[0], bounds[1])));
                        break;
                    case "24":
                        inputBounds(bounds, bufferedReader);
                        System.out.println(Arrays.toString(mathSet.squash(bounds[0], bounds[1]).toArray()));
                        break;
                    case "25":
                        mathSet.clear();
                        break;
                    case "26":
                        numbers = inputElements(bufferedReader);
                        mathSet.clear(numbers);
                        break;
                    default:
                        System.out.println("Your input is wrong. Please, try again.");
                }
            } catch (RuntimeException exception) {
                System.out.println("Something went wrong. Please, try again");
            }
            printOptions();
        }
    }

    private Number[] inputElements(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, input number of elements:");
        int count = Integer.parseInt(bufferedReader.readLine());
        Number[] numbers = new Number[count];
        System.out.println("Please, input elements(Separate with ENTER):");
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(bufferedReader.readLine());
        }
        return numbers;
    }

    private void inputBounds(int[] bounds, BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, input first bound index");
        bounds[0] = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Please, input second bound index");
        bounds[1] = Integer.parseInt(bufferedReader.readLine());
    }

    private MathSet<Number>[] createMathSets(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, input amount of math sets");
        int count = Integer.parseInt(bufferedReader.readLine());
        MathSet<Number>[] res = new MathSet[count];
        for (int i = 0; i < count; i++) {
            res[i] = new MathSet<>(inputElements(bufferedReader));
        }
        return res;
    }

    private Number[][] createNumberArrays(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, input amount of number arrays");
        int count = Integer.parseInt(bufferedReader.readLine());
        Number[][] res = new Number[count-1][];
        for (int i = 0; i < count; i++) {
            Number[] tmp = inputElements(bufferedReader);
            System.arraycopy(tmp, 0, res[i], 0, tmp.length);
        }
        return res;
    }

    private void printOptions() {
        System.out.println("1 -> create MathSet()\n" +
                "2 -> create MathSet(int capacity)\n" +
                "3 -> create MathSet(Number[] numbers)\n" +
                "4 -> create MathSet(Number[] ... numbers)\n" +
                "5 -> create MathSet(MathSet numbers)\n" +
                "6 -> create MathSet(MathSet ... numbers)\n" +
                "7 -> add(Number n)\n" +
                "8 -> add(Number ... n)\n" +
                "9 -> join(MathSet ms)\n" +
                "10 -> join(MathSet ... ms)\n" +
                "11 -> sortDesc()\n" +
                "12 -> sortDesc(int firstIndex, int lastIndex)\n" +
                "13 -> sortDesc(Number value)\n" +
                "14 -> sortAsc()\n" +
                "15 -> sortAsc(int firstIndex, int lastIndex)\n" +
                "16 -> sortAsc(Number value)\n" +
                "17 -> get(int index)\n" +
                "18 -> getMax()\n" +
                "19 -> getMin()\n" +
                "20 -> getAverage()\n" +
                "21 -> getMedian()\n" +
                "22 -> toArray()\n" +
                "23 -> toArray(int firstIndex, int lastIndex)\n" +
                "24 -> squash(int firstIndex, int lastIndex)\n" +
                "25 -> clear()\n" +
                "26 -> clear(Number[] numbers)\n" +
                "0 -> back to menu");
    }
}
