package ua.com.alevel.controllers;

import lombok.SneakyThrows;
import ua.com.alevel.data_classes.Author;
import ua.com.alevel.data_classes.Book;
import ua.com.alevel.services.AuthorService;
import ua.com.alevel.services.BookService;
import ua.com.alevel.utils.AuthorUtils;
import ua.com.alevel.utils.BookUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleController {

    private static final BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
    private static BookService bookService = new BookService();
    private static AuthorService authorService = new AuthorService();

    public void printInterface() {
        printWelcomeMassage();
        while (true){
            printMenu();
            chooseOperation();
        }
    }

    private static void printMenu() {
        System.out.print("\n\t\t\tMain menu\nPlease, choose what would you like to do:" +
                "\n 1 - create a book and add it to the database" +
                "\n 2 - create an author and add it to the database" +
                "\n 3 - add authors to the book" +
                "\n 4 - add books to the author" +
                "\n 5 - read a book" +
                "\n 6 - read an author" +
                "\n 7 - update a book" +
                "\n 8 - update an author" +
                "\n 9 - print all books" +
                "\n 10 - print all authors" +
                "\n 11 - delete the book from the database" +
                "\n 12 - delete the author from the database" +
                "\n 13 - remove the book from the books of selected author" +
                "\n 14 - remove the author from the authors of selected book" +
                "\n 0 - exit" +
                "\n --> ");
    }

    @SneakyThrows
    private static void chooseOperation() {
        switch (reader.readLine()){
            case "1": {
                bookService.create(BookUtils.createBook(reader));
                break;
            }
            case "2": {
                authorService.create(AuthorUtils.createAuthor(reader));
                break;
            }
            case "3": {
                Book readBook = BookUtils.readBook(bookService, reader);
                BookUtils.addAuthors(readBook, bookService, authorService, reader);
                break;
            }
            case "4": {
                Author readAuthor = AuthorUtils.readAuthor(authorService, reader);
                AuthorUtils.addBooks(readAuthor, bookService, authorService, reader);
                break;
            }
            case "5": {
                Book readBook = BookUtils.readBook(bookService, reader);
                System.out.println("readBook = " + readBook);
                break;
            }
            case "6": {
                Author readAuthor = AuthorUtils.readAuthor(authorService, reader);
                System.out.println("readAuthor = " + readAuthor);
                break;
            }
            case "7": {
                Book readBook = BookUtils.readBook(bookService, reader);
                BookUtils.updateBook(readBook, bookService, reader);
                System.out.println("Book updated!");
                break;
            }
            case "8": {
                Author readAuthor = AuthorUtils.readAuthor(authorService, reader);
                AuthorUtils.updateAuthor(readAuthor, authorService, reader);
                System.out.println("Author updated!");
                break;
            }
            case "9": {
                System.out.println("\t\tBooks");
                BookUtils.printAllBooks(bookService);
                break;
            }
            case "10": {
                System.out.println("\t\tAuthors");
                AuthorUtils.printAllAuthors(authorService);
                break;
            }
            case "11": {
                BookUtils.printAllBooks(bookService);
                Book book = BookUtils.readBook(bookService, reader);
                BookUtils.deleteBook(book, bookService, authorService);
                System.out.println("Book deleted!");
                BookUtils.printAllBooks(bookService);
                break;
            }
            case "12": {
                AuthorUtils.printAllAuthors(authorService);
                Author author = AuthorUtils.readAuthor(authorService, reader);
                AuthorUtils.deleteAuthor(author, bookService, authorService);
                System.out.println("Author deleted!");
                AuthorUtils.printAllAuthors(authorService);
                break;
            }
            case "13": {
                System.out.println("Choose the author and his book that you want to remove:");
                Author author = AuthorUtils.readAuthor(authorService, reader);
                System.out.println("author = " + author);
                System.out.println("Which book?");
                Book book = BookUtils.readBook(bookService, reader);
                AuthorUtils.remove(author, book);
                System.out.println("author = " + author);
                bookService.update(book);
                authorService.update(author);
                break;
            }
            case "14": {
                System.out.println("Choose the book and its author you want to remove:");
                Book book = BookUtils.readBook(bookService, reader);
                System.out.println("book = " + book);
                System.out.println("Which author?");
                Author author = AuthorUtils.readAuthor(authorService, reader);
                BookUtils.remove(book, author);
                System.out.println("book = " + book);
                bookService.update(book);
                authorService.update(author);
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

//    @SneakyThrows
//    private static void chooseReadBookOperation(Book readBook) {
//        boolean flag = true;
//        while (flag) {
//            System.out.print("What do you want to do with read user?" +
//                    "\n 1 - update" +
//                    "\n 2 - delete" +
//                    "\n 3 - nothing" +
//                    "\n --> ");
//            switch (reader.readLine()) {
//                case "1": {
//                    System.out.println("Enter new parameters for user.");
//                    Book userForUpdate = BookUtils.createBook(reader);
//                    userForUpdate.setId(readBook.getId());
//                    bookService.update(userForUpdate);
//                    System.out.println("User updated!");
//                    flag = false;
//                    break;
//                }
//                case "2": {
//                    bookService.delete(readBook);
//                    System.out.println("User deleted!");
//                    flag = false;
//                    break;
//                }
//                case "3": {
//                    flag = false;
//                    break;
//                }
//                default:
//                    System.out.println("Invalid choice!");
//                    break;
//            }
//        }
//    }

    private static void printWelcomeMassage() {
        System.out.println("Hello, this is CRUD-application for operations with Books & Authors.");
    }

}
