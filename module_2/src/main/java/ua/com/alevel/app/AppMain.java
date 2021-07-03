package ua.com.alevel.app;

import ua.com.alevel.app.controller.MainController;

import java.io.IOException;

public class AppMain {

    public static void main(String[] args) {
        try {
            MainController.menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
