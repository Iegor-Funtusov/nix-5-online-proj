package com.nixsolutions.courses;

import com.nixsolutions.courses.controllers.GroupController;

import java.io.IOException;

public class AppMain {

    public static void main(String[] args) {

        try {
            GroupController groupController = new GroupController();
            groupController.readConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
