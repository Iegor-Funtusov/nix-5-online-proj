package com.nixsolutions.courses;

import com.nixsolutions.courses.controllers.GroupController;

public class AppMain {

    public static void main(String[] args) {

            GroupController groupController = new GroupController();
            groupController.readConsole();
    }

}
