package com.nixsolutions.courses.controller;

import com.nixsolutions.courses.service.LibraryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleController {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    private final LibraryService libraryService = new LibraryService();
    private final AuthorController authorController = new AuthorController(reader, libraryService);
    private final BookController bookController = new BookController(reader, libraryService);

    private String readConsole() {
        System.out.println("Choose option:\n" +
                "0 - exit\n" +
                "1 - authors crud\n" +
                "2 - books crud");
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("Sorry, something went wrong with reading from console. Try again");
        }
        return input;
    }

    public void printMenu() {
        String input;
        while ((input = readConsole()) != null) {
            try {
                switch (input) {
                    case "0":
                        System.exit(0);
                        break;
                    case "1":
                        authorController.run();
                        break;
                    case "2":
                        bookController.run();
                        break;
                    default:
                        System.out.println("Wrong option. Try again");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
