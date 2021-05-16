package app;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main.main");
        StringController controller = new StringController();
        try {
            controller.userInterface();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}