package ua.com.alevel.app;

import ua.com.alevel.app.controller.MathSetController;

import java.io.IOException;

public class AppMain {

    public static void main(String[] args) {
        MathSetController mathSetController = new MathSetController();
        try {
            mathSetController.menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
