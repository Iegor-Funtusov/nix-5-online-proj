package com.k4rnaj1k.controller;

import com.k4rnaj1k.service.DateService;
import com.k4rnaj1k.service.Task3Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ModuleController {
    Scanner s = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("""
                    Which task would u like to test?
                    1 - dates;
                    2 - finding of the first unique name;
                    3 - find the shortest path between cities;""");
            switch (s.nextLine()) {
                case "1":
                    task1();
                    break;
                case "2":
                    task2();
                    break;
                case "3":
                    task3();
                    break;
            }
        }
    }

    private void task1() {
        System.out.println("Input your dates with spaces in between them.");
        List<Date> dates = new ArrayList<>();
        for (String date :
                s.nextLine().split(" ")) {
            try {
                dates.add(DateService.parse(date));
            } catch (ParseException ignored) {
            }
        }
        System.out.println("Dates formatted");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        for (Date date :
                dates) {
            System.out.println(sdf.format(date));
        }
    }

    private void task2() {
        System.out.println("Please input names with spaces between them.");
        String input = s.nextLine();
        String[] names = input.split(" ");
        for (String name :
                names) {
            int prev = input.length();
            input = input.replaceAll(name, "");
            if (prev - name.length() == input.length()) {
                System.out.println("First unique name = " + name);
                return;
            }
        }
        System.out.println("No unique names.");
    }

    private void task3() {
        Task3Service.alg();
    }
}
