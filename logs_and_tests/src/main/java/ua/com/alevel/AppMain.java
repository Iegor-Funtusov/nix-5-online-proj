package ua.com.alevel;

import ua.com.alevel.app.UniversityUserInterface;

import java.io.IOException;

public class AppMain {
    public static void main(String[] args) throws IOException {
        UniversityUserInterface ui = new UniversityUserInterface();
        ui.chooseMethod();
    }
}