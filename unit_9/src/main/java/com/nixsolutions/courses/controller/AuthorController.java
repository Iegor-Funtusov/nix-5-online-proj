package com.nixsolutions.courses.controller;

import com.nixsolutions.courses.data.Author;
import com.nixsolutions.courses.data.Book;
import com.nixsolutions.courses.service.LibraryService;
import com.nixsolutions.courses.util.CSVParser;

import java.io.BufferedReader;
import java.io.IOException;

public class AuthorController {

    private final BufferedReader reader;
    private final LibraryService libraryService;

    public AuthorController(BufferedReader reader, LibraryService libraryService) {
        this.reader = reader;
        this.libraryService = libraryService;
    }

    private void create() throws IOException {
        Author author = new Author();
        System.out.println("Enter author name and surname:");
        String[] fullName = reader.readLine().split(" ");
        author.setName(fullName[0]);
        author.setSurname(fullName[1]);
        System.out.println("Enter book id (if several - comma-separated) or enter:");
        String books = reader.readLine();
        if (!books.isEmpty()) author.setBooks((CSVParser.parseIds(books)));

        libraryService.createAuthor(author);
    }

    private void update() {

    }

    private void getById() {

    }

    private void delete() {

    }

    private void readAll() {

    }

    private void readAllBooks() {

    }

    private String authorsOptions() {
        System.out.println("Choose option:\n" +
                "0 - exit\n" +
                "1 - create author\n" +
                "2 - update author\n" +
                "3 - get by id\n" +
                "4 - delete\n" +
                "5 - read all\n" +
                "6 - read all books");
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("Sorry, something went wrong with reading from console. Try again");
        }
        return input;
    }

    public void run() {
        String input;
        while (!(input = authorsOptions()).equals("0")) {
            try {
                switch (input) {
                    case "1":
                        create();
                        break;
                    case "2":
                        update();
                        break;
                    case "3":
                        getById();
                        break;
                    case "4":
                        delete();
                        break;
                    case "5":
                        readAll();
                        break;
                    case "6":
                        readAllBooks();
                        break;
                    default:
                        System.out.println("wrong option. Try again");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
