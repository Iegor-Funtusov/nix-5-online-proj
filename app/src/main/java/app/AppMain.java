package app;

import java.io.IOException;

public class AppMain {
    public static void main(String[] args) {
        PointController pointController = new PointController();

        try {
            pointController.userInterface();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
