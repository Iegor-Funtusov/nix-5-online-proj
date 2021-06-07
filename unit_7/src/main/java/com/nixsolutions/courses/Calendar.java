package com.nixsolutions.courses;

import com.nixsolutions.courses.controller.ConsoleController;

import java.util.regex.Pattern;

public class Calendar {

    public static void main(String[] args) {
//
//        String format = "\\d{0,2}/\\d{1,2}/\\d{0,2}"; // dd/mm/yy
//        String date = "04/06/21";
//
////        Pattern.matches(input, "\\d{0,2}/\\d{1,2}/\\d{0,2}")
//        System.out.println(Pattern.matches(format,date));
        ConsoleController controller = new ConsoleController();
        controller.readConsole();
    }

}
