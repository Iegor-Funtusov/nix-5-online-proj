package ua.com.alevel.controller;

import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.RelationService;
import ua.com.alevel.util.ConsoleControllerUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleController {

    private static final BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
    private static final BookService bookService = new BookService();
    private static final AuthorService authorService = new AuthorService();
    private static final RelationService relationService = new RelationService();

    public void run() {
        System.out.println("Hello, this is CRUD-application " +
                "for operations with Books & Authors.");
        while (true) {
            printMenu();
            String operation;
            try {
                operation = reader.readLine();
                chooseOperation(operation);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("try again");
            }
        }
    }

    private static void printMenu() {
        System.out.print("\n\t\t\tMain menu\nPlease, choose what would you like to do:" +
                "\n 1 - create the book " +
                "\n 2 - create the author " +
                "\n 3 - create the relation book-author" +
                "\n 4 - update the book" +
                "\n 5 - update the author" +
                "\n 6 - print all books" +
                "\n 7 - print all authors" +
                "\n 8 - print all relations" +
                "\n 9 - print all books with authors" +
                "\n 10 - print all authors with books" +
                "\n 11 - delete the book from the database" +
                "\n 12 - delete the author from the database" +
                "\n 13 - delete the relation from the database" +
                "\n 14 - remove the book from selected author's books" +
                "\n 15 - remove the author from authors of the selected book" +
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
                ConsoleControllerUtil.createRelation(bookService, authorService, relationService, reader);
                break;
            }
            case "4": {
                ConsoleControllerUtil.updateBook(bookService, reader);
                break;
            }
            case "5": {
                ConsoleControllerUtil.updateAuthor(authorService, reader);
                break;
            }
            case "6": {
                ConsoleControllerUtil.printAllBooks(bookService);
                break;
            }
            case "7": {
                ConsoleControllerUtil.printAllAuthors(authorService);
                break;
            }
            case "8": {
                ConsoleControllerUtil.printAllRelations(relationService);
                break;
            }
            case "9": {
                ConsoleControllerUtil.printAllBooksWithAuthors(bookService, authorService, relationService);
                break;
            }
            case "10": {
                ConsoleControllerUtil.printAllAuthorsWithBooks(bookService, authorService, relationService);
                break;
            }
            case "11": {
                ConsoleControllerUtil.deleteBook(bookService, relationService, reader);
                break;
            }
            case "12": {
                ConsoleControllerUtil.deleteAuthor(authorService, relationService, reader);
                break;
            }
            case "13": {
                ConsoleControllerUtil.deleteRelation(relationService, reader);
                break;
            }
            case "14": {
                ConsoleControllerUtil.removeBook(authorService,bookService, relationService, reader);
                break;
            }
            case "15": {
                ConsoleControllerUtil.removeAuthor(authorService, bookService, relationService, reader);
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
