package ua.com.alevel.util;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;

import java.io.BufferedReader;

public class ConsoleControllerUtil {


    public static void createBook(BookService bookService, BufferedReader reader) {
        bookService.create(BookUtils.createBook(reader));
    }

    public static void createAuthor(AuthorService authorService, BufferedReader reader) {
        authorService.create(AuthorUtil.createAuthor(reader));
    }

    public static void addAuthors(BookService bookService, AuthorService authorService, BufferedReader reader) {
        printAllBooks(bookService);
        printAllAuthors(authorService);
        Book readBook = BookUtils.readBook(bookService, reader);
        BookUtils.addAuthors(readBook, bookService, authorService, reader);
    }

    public static void addBooks(BookService bookService, AuthorService authorService, BufferedReader reader) {
        printAllBooks(bookService);
        printAllAuthors(authorService);
        Author readAuthor = AuthorUtil.readAuthor(authorService, reader);
        AuthorUtil.addBooks(readAuthor, bookService, authorService, reader);
    }

    public static Book readBook(BookService bookService, BufferedReader reader) {
        printAllBooks(bookService);
        return BookUtils.readBook(bookService, reader);
    }

    public static Author readAuthor(AuthorService authorService, BufferedReader reader) {
        printAllAuthors(authorService);
        return AuthorUtil.readAuthor(authorService, reader);
    }

    public static void updateBook(BookService bookService, BufferedReader reader) {
        printAllBooks(bookService);
        Book readBook = BookUtils.readBook(bookService, reader);
        BookUtils.updateBook(readBook, bookService, reader);
        System.out.println("Book updated!");
    }

    public static void updateAuthor(AuthorService authorService, BufferedReader reader) {
        printAllAuthors(authorService);
        Author readAuthor = AuthorUtil.readAuthor(authorService, reader);
        AuthorUtil.updateAuthor(readAuthor, authorService, reader);
        System.out.println("Author updated!");
    }

    public static void printAllBooks(BookService bookService) {
        System.out.println("\t\tBooks");
        BookUtils.printAllBooks(bookService);
    }

    public static void printAllAuthors(AuthorService authorService) {
        System.out.println("\t\tAuthors");
        AuthorUtil.printAllAuthors(authorService);
    }

    public static void deleteBook(AuthorService authorService, BookService bookService, BufferedReader reader) {
        printAllBooks(bookService);
        BookUtils.printAllBooks(bookService);
        Book book = BookUtils.readBook(bookService, reader);
        BookUtils.deleteBook(book, bookService, authorService);
        System.out.println("Book deleted!");
        BookUtils.printAllBooks(bookService);
    }

    public static void deleteAuthor(AuthorService authorService, BookService bookService, BufferedReader reader) {
        printAllAuthors(authorService);
        AuthorUtil.printAllAuthors(authorService);
        Author author = AuthorUtil.readAuthor(authorService, reader);
        AuthorUtil.deleteAuthor(author, bookService, authorService);
        System.out.println("Author deleted!");
        AuthorUtil.printAllAuthors(authorService);
    }

    public static void removeBook(AuthorService authorService, BookService bookService, BufferedReader reader) {
        printAllAuthors(authorService);
        System.out.println("Choose the author and his book that you want to remove:");
        Author author = AuthorUtil.readAuthor(authorService, reader);
        System.out.println("author = " + author);
        System.out.println("Which book?");
        Book book = BookUtils.readBook(bookService, reader);
        AuthorUtil.remove(author, book);
        System.out.println("author = " + author);
        bookService.update(book);
        authorService.update(author);
    }

    public static void removeAuthor(AuthorService authorService, BookService bookService, BufferedReader reader) {
        printAllBooks(bookService);
        System.out.println("Choose the book and its author you want to remove:");
        Book book = BookUtils.readBook(bookService, reader);
        System.out.println("book = " + book);
        System.out.println("Which author?");
        Author author = AuthorUtil.readAuthor(authorService, reader);
        BookUtils.remove(book, author);
        System.out.println("book = " + book);
        bookService.update(book);
        authorService.update(author);
    }
}
