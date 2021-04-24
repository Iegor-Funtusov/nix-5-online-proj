import java.util.Scanner;

class firstDZ {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите количество элементтов: ");
        int size = input.nextInt();
        int array[] = new int[size];
        System.out.println("Вводите числа:");
        for (int i = 0; i < size; i++) {
            array[i] = input.nextInt();
        }
        first(array);
        second(array);
        third(array);
        fourth(array);
        fifth(array);
        sixth(array);

    }

    public static void first(int[] input) {
        System.out.println("Первое задание:");
        for (int i : input) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }

        }
        System.out.println("");
    }


    public static void second(int[] input) {
        System.out.println("Второе задание:");
        int a = 0;
        for (int i : input) {
            if (i>0) {
             ++a;
            }
        }
        System.out.println(a);
    }


    public static void third(int[] input) {
        System.out.println("Третье задание:");
        int a = 0;
        for (int i = 1; i<input.length;i++) {
            if (input[i]>input[i-1]) {
                a++;
            }

        }
        System.out.println(a);

    }
    public static void fourth(int[] input) {
        System.out.println("Четвертое задание:");
        int a = 0;
        for (int i = 1; i<input.length-1;i++) {
            if (input[i]>input[i-1] && input[i]>input[i+1]) {
                a++;
            }

        }
        System.out.println(a);

    }

    public static void fifth(int[] input) {
        System.out.println("Пятое задание:");
        int a = 0;
        int n = input.length;
        for (int i = 0; i < n / 2; i++) {
            a = input[n - i - 1];
            input[n - i - 1] = input[i];
            input[i] = a;
        }
        for (int i : input) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
    public static void sixth(int[] input) {
        System.out.println("Шестое задание:");
        int b = 0;
        int n = input.length;
        for (int i = 0; i < n / 2; i+=2) {
            b = input[i];  // b = 1
            input[i]=input[i+1];// 1=2
            input[i+1]=b;// 2 = b(1)
        }
        for (int i : input) {
            System.out.print(i + " ");
        }
        System.out.println();

    }


}

