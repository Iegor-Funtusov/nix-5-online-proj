package app;

import java.io.IOException;

public class ContactTest {
    public static void main(String[] args) throws IOException {
     //   System.out.println("ContactTest.main");
        ContactController contactController = new ContactController();
        contactController.readConsole();
    }
}
