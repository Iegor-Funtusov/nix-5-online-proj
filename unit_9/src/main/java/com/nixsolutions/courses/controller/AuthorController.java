package com.nixsolutions.courses.controller;

import com.nixsolutions.courses.data.Author;
import com.nixsolutions.courses.service.LibraryService;
import com.nixsolutions.courses.util.CSVParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

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
        String[] fullName = reader.readLine().trim().split(" ");
        author.setName(fullName[0]);
        author.setSurname(fullName[1]);
        System.out.println("Enter book id (if several - comma-separated) or enter:");
        String books = reader.readLine().trim();
        if (!books.isEmpty()) author.setBooks((CSVParser.parseIdsList(books)));

        libraryService.createAuthor(author);
    }

    private void update() throws IOException {
        System.out.println("Enter author id:");
        String id = reader.readLine().trim();
        Author author = libraryService.getAuthorById(id);
        System.out.println("What to edit:\n1 - name\n2 - surname");
        switch (reader.readLine()) {
            case "1" :
                System.out.println("Enter new name:");
                author.setName(reader.readLine().trim());
                break;
            case "2":
                System.out.println("Enter new surname:");
                author.setSurname(reader.readLine().trim());
                break;
            default:
                System.out.println("wrong option");
        }
        libraryService.updateAuthor(author);
    }

    private void getById() throws IOException {
        System.out.println("Enter author id:");
        String id = reader.readLine().trim();
        Author author = libraryService.getAuthorById(id);
        System.out.println(author);
    }

    private void delete() throws IOException {
        System.out.println("Enter author id:");
        String id = reader.readLine().trim();
        libraryService.deleteAuthor(id);
    }

    private void readAll() {
        List<Author> list = libraryService.readAllAuthors();
        list.stream().skip(1).filter(Author::isVisible).forEach(System.out::println);
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
