package ua.com.alevel;

import ua.com.alevel.app.controller.UniversityController;

import java.io.IOException;

public class AppMain {

    public static void main(String[] args) throws IOException {
        UniversityController ui = new UniversityController();
        ui.chooseMethod();
    }
}