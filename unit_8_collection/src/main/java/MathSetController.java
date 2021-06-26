import mathset.MathSet;
import service.MathSetService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MathSetController {

    public void readConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите действие или нажмите '0' для выхода: ");
        System.out.println("1 - создание множества Set");
        System.out.println("2 - добавление чисел в множество Set");
        System.out.println("3 - объединение множеств");
        System.out.println("4 - сортировка множеств");
        System.out.println("5 - арифметические операции со множеством");
        System.out.println("6 - преобразование множества");
        System.out.println("7 - удаление элементов из множества");
        try {
            String input = reader.readLine();
            while (!"0".equals(input)) {
                switch (input) {
                    case "1":
                        createSet(reader);
                        break;
                    case "2":
                        addNumbers(reader);
                        break;
                    case "3":
                        joinSets(reader);
                        break;
                    case "4":
                        sortSets(reader);
                        break;
                    case "5":
                        arithmeticSets(reader);
                        break;
                    case "6":
                        convertSets(reader);
                        break;
                    case "7":
                        clearSet(reader);
                        break;
                    default:
                        System.out.println("Неизвестный выбор!");
                }
                System.out.println("Выберите действие или нажмите '0' для выхода: ");
                System.out.println("1 - создание множества Set");
                System.out.println("2 - добавление чисел в множество Set");
                System.out.println("3 - объединение множеств");
                System.out.println("4 - сортировка множеств");
                System.out.println("5 - арифметические операции со множеством");
                System.out.println("6 - преобразование множества");
                System.out.println("7 - удаление элементов из множества");
                input = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода!");
        }
    }

    private void createSet(BufferedReader reader) {
        System.out.println("Создать множество или '0' для выхода: ");
        System.out.println("1 - пустое (с параметрами по умолчанию)");
        System.out.println("2 - заданной ёмкости");
        System.out.println("3 - из заданного массива чисел");
        System.out.println("4 - из заданных массивов чисел");
        System.out.println("5 - из заданного множества");
        System.out.println("6 - из заданных множеств");
        try {
            String input = reader.readLine();
            while (!"0".equals(input)) {
                switch (input) {
                    case "1":
                        createEmptySet();
                        break;
                    case "2":
                        createCapacitySet(reader);
                        break;
                    case "3":
                        createArraySet(reader);
                        break;
                    case "4":
                        createArraysSet(reader);
                        break;
                    case "5":
                        createSetSet(reader);
                        break;
                    case "6":
                        createSetsSet(reader);
                        break;
                    default:
                        System.out.println("Неизвестный выбор!");
                }
                System.out.println("Создать множество или '0' для выхода: ");
                System.out.println("1 - пустое (с параметрами по умолчанию)");
                System.out.println("2 - заданной ёмкости");
                System.out.println("3 - из заданного массива чисел");
                System.out.println("4 - из заданных массивов чисел");
                System.out.println("5 - из заданного множества");
                System.out.println("6 - из заданных множеств");
                input = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода!");
        }
    }

    private void createEmptySet() {
        MathSet<Integer> set = new MathSet<>(Integer.class);
        System.out.println(Arrays.toString(set.toArray()));
        System.out.println();
    }

    private void createCapacitySet(BufferedReader reader) {
        System.out.println("Выберите тип данных:");
        System.out.println("1 - Integer");
        System.out.println("2 - Double");
        try {
            String input = reader.readLine();
            switch (input) {
                case "1": {
                    System.out.println("Введите ёмкость множества:");
                    int capacity = Integer.parseInt(reader.readLine());
                    MathSet<Integer> set = new MathSet<>(Integer.class, capacity);
                    for (int i = 0; i < capacity; i++) {
                        int sign = Math.random() < 0.5 ? -1 : 1;
                        set.add((int) (Math.random() * 100) * sign);
                    }
                    System.out.println("Полученное множество случайных чисел:");
                    System.out.println(Arrays.toString(set.toArray()));
                    System.out.println();
                    break;
                }
                case "2": {
                    System.out.println("Введите ёмкость множества:");
                    int capacity = Integer.parseInt(reader.readLine());
                    MathSet<Double> set = new MathSet<>(Double.class, capacity);
                    for (int i = 0; i < capacity; i++) {
                        int sign = Math.random() < 0.5 ? -1 : 1;
                        set.add((Math.random() * 100) * sign);
                    }
                    System.out.println("Полученное множество случайных чисел:");
                    System.out.println(Arrays.toString(set.toArray()));
                    System.out.println();
                    break;
                }
                default:
                    throw new IllegalStateException("Неизвестный выбор: " + input);
            }
        } catch (IllegalStateException e) {
            System.out.println(e/* + " Неизвестный выбор!"*/);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e + " Неверное число!");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void createArraySet(BufferedReader reader) {
        try {
            System.out.println("Введите числа массива через запятую:");
            Integer[] array = MathSetService.inputArray(reader);
            System.out.println("Данный массив:");
            System.out.println(Arrays.toString(array));
            System.out.println("Полученное множество:");
            MathSet<Integer> set = new MathSet<>(Integer.class, array);
            System.out.println(Arrays.toString(set.toArray()));
            System.out.println();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e + " Неверное число!");
        }
    }

    private void createArraysSet(BufferedReader reader) {
        try {
            System.out.println("Введите количество массивов:");
            int m = Integer.parseInt(reader.readLine());
            Integer[][] array = new Integer[m][];
            for (int i = 0; i < m; i++) {
                System.out.println("Введите числа " + (i + 1) + "-го массива через запятую:");
                array[i] = MathSetService.inputArray(reader);
                System.out.println("Данный массив:");
                System.out.println(Arrays.toString(array[i]));
            }
            //  System.out.println("Данные массивы:");
            //  System.out.println(Arrays.deepToString(array));
            MathSet<Integer> set = new MathSet<>(Integer.class, array);
            System.out.println("Полученное множество:");
            // System.out.println(Arrays.toString(set.toArray()));
            MathSetService.printSet(set);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e + " Неверное число!");
        } catch (NegativeArraySizeException e) {
            System.out.println(e + " : Количество массивов не может быть отрицательным!");
        }
    }

    private void createSetSet(BufferedReader reader) {
        try {
            System.out.println("Введите ёмкость множества:");
            MathSet<Integer> mathSet = MathSetService.createSetCapacity(reader);
            System.out.println("Исходное множество случайных чисел:");
            System.out.println(Arrays.toString(mathSet.toArray()));
            System.out.println("Множество из элементов исходного:");
            MathSet<Integer> set = new MathSet<>(Integer.class, mathSet);
            System.out.println(Arrays.toString(set.toArray()));
            System.out.println();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e + " Неверное число!");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void createSetsSet(BufferedReader reader) {
        try {
            System.out.println("Введите количество множеств:");
            int m = Integer.parseInt(reader.readLine());
            MathSet<Integer>[] mathSets = new MathSet[m];
            for (int i = 0; i < m; i++) {
                System.out.println("Введите ёмкость " + (i + 1) + "-го множества:");
                MathSet<Integer> mathSet = MathSetService.createSetCapacity(reader);
                mathSets[i] = mathSet;
                System.out.println((i + 1) + "-e множество случайных чисел:");
                System.out.println(Arrays.toString(mathSets[i].toArray()));
            }
            System.out.println("Множество из элементов исходных:");
            MathSet<Integer> set = new MathSet<>(Integer.class, mathSets);
            MathSetService.printSet(set);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e + " Неверное число!");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch (NegativeArraySizeException e) {
            System.out.println(e + " : Количество множеств не может быть отрицательным!");
        }
    }

    private void addNumbers(BufferedReader reader) {
        try {
            MathSet<Integer> set = MathSetService.createSet();
            System.out.println("Исходное множество случайных чисел:");
            System.out.println(Arrays.toString(set.toArray()));
            System.out.println("Введите число или несколько чисел через запятую:");
            Integer[] array = MathSetService.inputArray(reader);
            set.add(array);
            System.out.println("Полученное множество:");
            System.out.println(Arrays.toString(set.toArray()));
        } catch (IOException | NumberFormatException e) {
            System.out.println(e + " Неверное число!");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    private void joinSets(BufferedReader reader) {
        try {
            MathSet<Integer> set = MathSetService.createSet();
            System.out.println("Исходное множество случайных чисел:");
            System.out.println(Arrays.toString(set.toArray()));
            System.out.println("Введите количество множеств для объединения:");
            int m = Integer.parseInt(reader.readLine());
            MathSet<Integer>[] mathSets = new MathSet[m];
            for (int i = 0; i < m; i++) {
                System.out.println("Введите ёмкость " + (i + 1) + "-го множества:");
                MathSet<Integer> mathSet = MathSetService.createSetCapacity(reader);
                mathSets[i] = mathSet;
                //  set.join(mathSets[i]);
                System.out.println((i + 1) + "-e множество случайных чисел:");
                System.out.println(Arrays.toString(mathSets[i].toArray()));
            }
            set.join(mathSets);
            System.out.println("Множество после объединения:");
            MathSetService.printSet(set);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e + " Неверное число!");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch (NegativeArraySizeException e) {
            System.out.println(e + " : Количество множеств не может быть отрицательным!");
        }
    }

    private void sortSets(BufferedReader reader) {
        System.out.println("Выберите вид сортировки или 0 для выхода::");
        System.out.println("1 - по убыванию");
        System.out.println("2 - по убыванию в диапазоне заданных индексов");
        System.out.println("3 - по убыванию, начиная с заданного числа");
        System.out.println("4 - по возрастанию");
        System.out.println("5 - по возрастанию в диапазоне заданных индексов");
        System.out.println("6 - по возрастанию, начиная с заданного числа");
        try {
            String input = reader.readLine();
            while (!"0".equals(input)) {
                MathSet<Integer> set = MathSetService.createSet();
                System.out.println("Исходное множество случайных чисел:");
                System.out.println(Arrays.toString(set.toArray()));
                switch (input) {
                    case "1":
                        MathSetService.sortDesc(set);
                        break;
                    case "2":
                        System.out.println("Введите 1й индекс:");
                        int firstIndex = Integer.parseInt(reader.readLine());
                        System.out.println("Введите 2й индекс:");
                        int lastIndex = Integer.parseInt(reader.readLine());
                        MathSetService.sortDesc(set, firstIndex, lastIndex);
                        break;
                    case "3":
                        System.out.println("Введите число:");
                        Number value = Integer.parseInt(reader.readLine());
                        MathSetService.sortDesc(set, value);
                        break;
                    case "4":
                        MathSetService.sortAsc(set);
                        break;
                    case "5":
                        System.out.println("Введите 1й индекс:");
                        firstIndex = Integer.parseInt(reader.readLine());
                        System.out.println("Введите 2й индекс:");
                        lastIndex = Integer.parseInt(reader.readLine());
                        MathSetService.sortAsc(set, firstIndex, lastIndex);
                        break;
                    case "6":
                        System.out.println("Введите число:");
                        value = Integer.parseInt(reader.readLine());
                        MathSetService.sortAsc(set, value);
                        break;
                    default:
                        throw new IllegalStateException("Неизвестный выбор: " + input);
                }
                System.out.println("Множество после сортировки: ");
                System.out.println(Arrays.toString(set.toArray()));
                System.out.println();
                System.out.println("Выберите вид сортировки или 0 для выхода::");
                System.out.println("1 - по убыванию");
                System.out.println("2 - по убыванию в диапазоне заданных индексов");
                System.out.println("3 - по убыванию, начиная с заданного числа");
                System.out.println("4 - по возрастанию");
                System.out.println("5 - по возрастанию в диапазоне заданных индексов");
                System.out.println("6 - по возрастанию, начиная с заданного числа");
                input = reader.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e + " Неверное число!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    private void arithmeticSets(BufferedReader reader) {
        System.out.println("Выберите операцию или 0 для выхода:");
        System.out.println("1 - получить элемент по индексу");
        System.out.println("2 - максимальный элемент множества");
        System.out.println("3 - минимальный элемент множества");
        System.out.println("4 - среднее арифметическое множества");
        System.out.println("5 - медиана множества");
        try {
            String input = reader.readLine();
            while (!"0".equals(input)) {
                Number number = null;
                MathSet<Integer> set = MathSetService.createSet();
                System.out.println("Исходное множество случайных чисел:");
                System.out.println(Arrays.toString(set.toArray()));
                switch (input) {
                    case "1": {
                        System.out.println("Введите индекс элемента:");
                        int index = Integer.parseInt(reader.readLine());
                        number = MathSetService.getNumber(set, index);
                        System.out.println("Элемент по индексу " + index + ":");
                        break;
                    }
                    case "2": {
                        number = MathSetService.getMax(set);
                        System.out.println("Максимальный элемент множества:");
                        break;
                    }
                    case "3": {
                        number = MathSetService.getMin(set);
                        System.out.println("Минимальный элемент множества:");
                        break;
                    }
                    case "4": {
                        number = MathSetService.getAverage(set);
                        System.out.println("Среднее арифметическое множества:");
                        break;
                    }
                    case "5": {
                        number = MathSetService.getMedian(set);
                        System.out.println("Медиана множества:");
                        break;
                    }
                    default:
                        throw new IllegalStateException("Неизвестный выбор: " + input);
                }
                System.out.println(number);
                System.out.println();
                System.out.println("Выберите операцию или 0 для выхода:");
                System.out.println("1 - получить элемент по индексу");
                System.out.println("2 - максимальный элемент множества");
                System.out.println("3 - минимальный элемент множества");
                System.out.println("4 - среднее арифметическое множества");
                System.out.println("5 - медиана множества");
                input = reader.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e + " Неверное число!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    private void convertSets(BufferedReader reader) {
        System.out.println("Выберите операцию или '0' для выхода: ");
        System.out.println("1 - массив из всего множества");
        System.out.println("2 - массив в диапазоне индексов");
        System.out.println("3 - выделение множества из данного (squash)");
        try {
            String input = reader.readLine();
            while (!"0".equals(input)) {
                MathSet<Integer> set = MathSetService.createSet();
                System.out.println("Исходное множество случайных чисел:");
                System.out.println(Arrays.toString(set.toArray()));
                switch (input) {
                    case "1":
                        Number[] array = MathSetService.setToArray(set);
                        System.out.println("Полученный массив:");
                        System.out.println(Arrays.toString(array));
                        System.out.println();
                        break;
                    case "2":
                        System.out.println("Введите 1й индекс:");
                        int firstIndex = Integer.parseInt(reader.readLine());
                        System.out.println("Введите 2й индекс:");
                        int lastIndex = Integer.parseInt(reader.readLine());
                        array = MathSetService.setToArray(set, firstIndex, lastIndex);
                        System.out.println("Полученный массив:");
                        System.out.println(Arrays.toString(array));
                        System.out.println();
                        break;
                    case "3":
                        System.out.println("Введите 1й индекс:");
                        firstIndex = Integer.parseInt(reader.readLine());
                        System.out.println("Введите 2й индекс:");
                        lastIndex = Integer.parseInt(reader.readLine());
                        MathSet<?> mathSet = MathSetService.squash(Integer.class, set, firstIndex, lastIndex);
                        System.out.println("Полученное множество:");
                        System.out.println(Arrays.toString(mathSet.toArray()));
                        System.out.println();
                        break;
                    default:
                        throw new IllegalStateException("Неизвестный выбор: " + input);
                }
                System.out.println("Выберите операцию или '0' для выхода: ");
                System.out.println("1 - массив из всего множества");
                System.out.println("2 - массив в диапазоне индексов");
                System.out.println("3 - выделение множества из данного (squash)");
                input = reader.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e + " Неверное число!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    private void clearSet(BufferedReader reader) {
        System.out.println("Удаление элементов или '0' для выхода: ");
        System.out.println("1 - всех элементов множества");
        System.out.println("2 - в пределах заданного массива");
        try {
            String input = reader.readLine();
            while (!"0".equals(input)) {
                MathSet<Integer> set = MathSetService.createSet();
                System.out.println("Исходное множество случайных чисел:");
                System.out.println(Arrays.toString(set.toArray()));
                switch (input) {
                    case "1":
                        MathSetService.clear(set);
                        break;
                    case "2":
                        System.out.println("Введите массив чисел через запятую:");
                        Integer[] array = MathSetService.inputArray(reader);
                        System.out.println("Данный массив:");
                        System.out.println(Arrays.toString(array));
                        MathSetService.clear(set, array);
                        break;
                    default:
                        throw new IllegalStateException("Неизвестный выбор: " + input);
                }
                System.out.println("Полученное множество:");
                System.out.println(Arrays.toString(set.toArray()));
                System.out.println();
                System.out.println("Удаление элементов или '0' для выхода: ");
                System.out.println("1 - всех элементов множества");
                System.out.println("2 - в пределах заданного массива");
                input = reader.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e + " Неверное число!");
        } catch (IllegalStateException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }
}
