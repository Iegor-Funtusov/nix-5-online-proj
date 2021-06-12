package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.util.ConsoleControllerUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleController {

    private static final BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
    private static BookService bookService = new BookService();
    private static AuthorService authorService = new AuthorService();

    public void run() {
        System.out.println("Hello, this is CRUD-application " +
                "for operations with Books & Authors.");
        while (true){
            printMenu();
            String operation = null;
            try {
                operation = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("try again");
                continue;
            }
            chooseOperation(operation);
        }
    }

    private static void printMenu() {
        System.out.print("\n\t\t\tMain menu\nPlease, choose what would you like to do:" +
                "\n 1 - create a book and add it to the database" +
                "\n 2 - create an author and add it to the database" +
                "\n 3 - add authors to the book" +
                "\n 4 - add books to the author" +
                "\n 5 - read the book" +
                "\n 6 - read the author" +
                "\n 7 - update the book" +
                "\n 8 - update the author" +
                "\n 9 - print all books" +
                "\n 10 - print all authors" +
                "\n 11 - delete the book from the database" +
                "\n 12 - delete the author from the database" +
                "\n 13 - remove the book from the books of selected author" +
                "\n 14 - remove the author from the authors of selected book" +
                "\n 0 - exit" +
                "\n --> ");
    }

    private static void chooseOperation(String operation) {
        switch (operation){
            case "1": {
                ConsoleControllerUtil.createBook(bookService, reader);
                break;
            }
            case "2": {
                ConsoleControllerUtil.createAuthor(authorService, reader);
                break;
            }
            case "3": {
                ConsoleControllerUtil.addAuthors(bookService, authorService, reader);
                break;
            }
            case "4": {
                ConsoleControllerUtil.addBooks(bookService, authorService, reader);
                break;
            }
            case "5": {
                Book book = ConsoleControllerUtil.readBook(bookService, reader);
                System.out.println("readBook = " + book);
                break;
            }
            case "6": {
                Author readAuthor = ConsoleControllerUtil.readAuthor(authorService, reader);
                System.out.println("readAuthor = " + readAuthor);
                break;
            }
            case "7": {
                ConsoleControllerUtil.updateBook(bookService, reader);
                break;
            }
            case "8": {
                ConsoleControllerUtil.updateAuthor(authorService, reader);
                break;
            }
            case "9": {
                ConsoleControllerUtil.printAllBooks(bookService);
                break;
            }
            case "10": {
                ConsoleControllerUtil.printAllAuthors(authorService);
                break;
            }
            case "11": {
                ConsoleControllerUtil.deleteBook(authorService, bookService, reader);
                break;
            }
            case "12": {
                ConsoleControllerUtil.deleteAuthor(authorService, bookService, reader);
                break;
            }
            case "13": {
                ConsoleControllerUtil.removeBook(authorService,bookService, reader);
                break;
            }
            case "14": {
                ConsoleControllerUtil.removeAuthor(authorService,bookService, reader);
                break;
            }
            case "0": {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Invalid choice. Try again.");
                break;
            }
        }
    }
}
