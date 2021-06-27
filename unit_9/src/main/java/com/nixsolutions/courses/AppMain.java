package com.nixsolutions.courses;

import com.nixsolutions.courses.controller.ConsoleController;

public class AppMain {

    public static void main(String[] args) {
        ConsoleController consolecontroller = new ConsoleController();
        consolecontroller.printMenu();
    }
}
