package com.nixsolutions.courses.controller;

import com.nixsolutions.courses.data.Book;
import com.nixsolutions.courses.service.LibraryService;
import com.nixsolutions.courses.util.CSVParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class BookController {

    private final BufferedReader reader;
    private final LibraryService libraryService;

    public BookController(BufferedReader reader, LibraryService libraryService) {
        this.reader = reader;
        this.libraryService = libraryService;
    }

    private void create() throws IOException {
        Book book = new Book();
        System.out.println("Enter book title:");
        book.setTitle(reader.readLine().trim());
        System.out.println("Enter at least one author id (if several - comma-separated):");
        book.setAuthors(CSVParser.parseIdsList(reader.readLine().trim()));
        libraryService.createBook(book);
    }

    private void update() throws IOException {
        System.out.println("Enter book id:");
        String id = reader.readLine().trim();
        Book book = libraryService.getBookById(id);
        System.out.println("Enter new title for book:");
        book.setTitle(reader.readLine().trim());
        libraryService.updateBook(book);
        System.out.println("Book updated:");
        System.out.println(libraryService.getBookById(id));
    }

    private void getById() throws IOException {
        System.out.println("Enter book id:");
        String id = reader.readLine().trim();
        Book book = libraryService.getBookById(id);
        System.out.println(book);
    }

    private void delete() throws IOException {
        System.out.println("Enter book id:");
        String id = reader.readLine().trim();
        libraryService.deleteBook(id);
        System.out.println("Book deleted");
    }

    private void readAll() {
        List<Book> list = libraryService.readAllBooks();
        list.stream().filter(i -> list.indexOf(i) != 0).forEach(System.out::println);
    }

    private void readAllAuthors() throws IOException {
//        System.out.println("Enter book id:");
//        String id = reader.readLine().trim();
//        Book book = libraryService.getBookById(id);
//        book.getAuthors()
    }

    private String booksOptions() {
        System.out.println("Choose option:\n" +
                "0 - exit\n" +
                "1 - create book\n" +
                "2 - update book\n" +
                "3 - get by id\n" +
                "4 - delete\n" +
                "5 - read all\n" +
                "6 - read all authors");
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
        while (!(input = booksOptions()).equals("0")) {
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
                        readAllAuthors();
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
