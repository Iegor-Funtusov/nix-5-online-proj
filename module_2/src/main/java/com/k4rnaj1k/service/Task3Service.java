package com.k4rnaj1k.service;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task3Service {
    static String[][] matrix;
    static String[] output, cities;
    static Map<String, String> paths;

    public static void alg() {
        init();
        String res = "";
        for (String s :
                paths.keySet()) {
            int start = 0, end = 0;

            for (int i = 0; i < matrix[0].length; i++) {
                    if (cities[i].equals(paths.get(s))) {
                        end = i;
                    }
            }
            for (int i = 0; i < matrix[0].length; i++) {
                    if (cities[i].equals(s)) {
                        start = i;
                    }
            }
           res = res.concat(findPath(start,end)+"\n");
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("module_2\\output.txt", false))){
            writer.write(res);
        }catch (Exception e){
            System.out.println("Couldn't write to the file.");
        }
    }


    private static void init() {
        try (Scanner s = new Scanner(new FileInputStream("module_2\\input.txt"))) {
            int citiescount = Integer.parseInt(s.nextLine());
            matrix = new String[citiescount][citiescount];
            cities = new String[citiescount];
            for (int i = 0; i < citiescount; i++) {
                cities[i] = s.nextLine();
                int neighbours = Integer.parseInt(s.nextLine());
                for (int j = 0; j < neighbours; j++) {
                    matrix[i][s.nextInt() - 1] = String.valueOf(s.nextInt());
                    s.nextLine();
                }
            }
            paths = new HashMap<>();
            int pathscount = Integer.parseInt(s.nextLine());
            for (int i = 0; i < pathscount; i++) {
                String[] citiespath = s.nextLine().split(" ");
                paths.put(citiespath[0], citiespath[1]);
            }
            output = new String[pathscount];
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    static int min,minindex;
    static int[] d, v;

    private static int findPath(int start, int end) {
        int temp;
        v = new int[matrix[0].length];
        d = new int[matrix[0].length];
        Arrays.fill(v, 1);
        Arrays.fill(d, 10000);
        d[start] = 0;
        do {
            minindex = 30000;
            min = 10000;
            for (int i = 0; i < cities.length; i++) {
                if ((v[i] == 1) && (d[i] < min)) {
                    min = d[i];
                    minindex = i;
                }
            }
            if (minindex != 30000) {
                for (int i = 0; i < cities.length; i++) {
                    if (matrix[minindex][i] != null)
                        if (Integer.parseInt(matrix[minindex][i]) > 0) {
                            temp = min + Integer.parseInt(matrix[minindex][i]);
                            if (temp < d[i]) {
                                d[i] = temp;
                            }
                        }
                }
                v[minindex] = 0;
            }
        } while (minindex < 30000);
        return d[end];
    }

}