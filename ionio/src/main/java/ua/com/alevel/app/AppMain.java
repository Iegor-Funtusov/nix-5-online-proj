package ua.com.alevel.app;

import ua.com.alevel.app.controller.Controller;

import java.io.IOException;

public class AppMain {

    public static void main(String[] args) {
        try {
            Controller.menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
