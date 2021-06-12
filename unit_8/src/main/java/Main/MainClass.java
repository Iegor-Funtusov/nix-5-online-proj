package Main;

import MathSet.MathSet;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("Main.MainClass.main");
        MathSet<Integer> set = new MathSet();
//        set.add(13.12);
        set.add(28);
//        set.add(2L);
//        set.add();
        set.add(28);
        set.add(5);
        set.add(29);
        set.add(11);
        set.add(4);
        set.add(200);
        System.out.println("Max = " + set.getMax());
        System.out.println("Min = " + set.getMin());
        System.out.println("Avg = " + set.getAverage());
        System.out.println("Med = " + set.getMedian());

        for (int i = 0; i < set.length(); i++) {
            System.out.println(set.get(i));
        }
//
        System.out.println("   ");
//        set.sortAsc();
        set.sortDesc();
//        set.sortDesc(0, 2);
//        set.sortAsc(0,4);
//        set.sortDesc(29);
//        set.sortAsc(29);
        for (int i = 0; i < set.length(); i++) {
            System.out.println(set.get(i));
        }

        MathSet<Integer> set1 = new MathSet();
        set1.add(300);
        set1.add(400);
        set1.add(500);

        MathSet<Integer> set2 = new MathSet();
        set2.add(600);
        set2.add(700);
        set2.add(800, 900, 1000);

//        Integer[] array;
//        array = set2.toArray();
//        System.out.println();
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }

//        set.join(set1, set2);
//        System.out.println();
//        for (int i = 0; i < set.length(); i++) {
//            System.out.println(set.get(i));
//        }

//        Integer[] array = set.toArray(0,5);
//        System.out.println();
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }

        MathSet mathSet = set.squash(0, 4);
        System.out.println();
        for (int i = 0; i < mathSet.length(); i++) {
            System.out.println(mathSet.get(i));
        }

        set1.clear();
        set1.add(1);
        System.out.println();
        for (int i = 0; i < set1.length(); i++) {
            System.out.println(set1.get(i));
        }

        Number[] numbers = {300, 400, 1, 600, 900};

        set2.clear(numbers);
        System.out.println();
        for (int i = 0; i < set2.length(); i++) {
            System.out.println(set2.get(i));
        }
    }
}
