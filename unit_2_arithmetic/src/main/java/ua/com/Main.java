package ua.com;

import java.util.Scanner;

public class Main {
    public static int[] Init(int size){
        Scanner sc = new Scanner(System.in);
        if (size > 100 || size < 1){
            System.exit(-1);
        }
        int[] arr = new int[size];
        for (int i = 0; i < size; i++){
            arr[i] = sc.nextInt();
        }
        return arr;
    }

    public static void task1() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = Init(size);
        for (int i = 0; i < size; i++){
            if (arr[i] % 2 == 0){
                System.out.print(arr[i] + " ");
            }
        }
    }

    public static void task2() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = Init(size);
        int res = 0;
        for (int i : arr){
            if (i > 0){
                res++;
            }
        }
        System.out.print(res);
    }

    public static void task3() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = Init(size);
        int res = 0;
        for (int i = 1; i < size; i++){
            if (arr[i] > arr[i - 1]){
                res++;
            }
        }
        System.out.print(res);
    }

    public static void task4() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = Init(size);
        int res = 0;
        for (int i = 1; i < size - 1; i++){
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]){
                res++;
            }
        }
        System.out.print(res);
    }

    public static void task5(){
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = Init(size);
        int res = 0, tmp = 0;
        for (int i = 0; i < size/2; i++){
            tmp = arr[i];
            arr[i] = arr[size - (i + 1)];
            arr[size - (i + 1)] = tmp;
        }
        for (int i = 0; i < size; i++){
            System.out.print(arr[i] + " ");
        }
    }

    public static int[] Rewrite(int[] arr, int size){
        for (int i = 0; i < size; i += 2) {
            int tmp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = tmp;
        }
        return arr;
    }

    public static void task6(){
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = Init(size);
        if (size % 2 == 0) {
            arr = Rewrite(arr, size);
        }
        if (size % 2 != 0) {
            arr = Rewrite(arr, size - 1);
        }
        for (int i = 0; i < size; i++){
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
    }

}
