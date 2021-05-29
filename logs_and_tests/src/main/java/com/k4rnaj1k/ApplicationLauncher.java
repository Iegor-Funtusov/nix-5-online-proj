package com.k4rnaj1k;

import com.k4rnaj1k.DataClasses.Author;
import com.k4rnaj1k.Service.AuthorService;
import com.k4rnaj1k.Service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ApplicationLauncher {
    static Scanner s = new Scanner(System.in);
    static AuthorService authorService = new AuthorService();
    static BookService bookService = new BookService();

    private static Logger loggerInfo = LoggerFactory.getLogger("info");
    private static Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static Logger loggerError = LoggerFactory.getLogger("error");

    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            System.out.println("""
                    
                    Please choose what you'd like to do:
                    1 - Add an author;
                    2 - Add a book to the author
                    3 - Get a complete list of authors and books
                    4 - Delete an author
                    5 - Delete a book
                    Any other number to stop the app's execution.""");
            int chosen = s.nextInt();
            s.nextLine();
            switch (chosen) {
                case 1:
                    addAuthor();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    getAll();
                    break;
                case 4:
                    deleteAuthor();
                    break;
                case 5:
                    deleteBook();
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }

    public static void deleteBook() {
        try{
            loggerWarn.warn("Starting book removal");
            System.out.println("Please input the book's name that u'd like to remove.");
            Author.Book book = new Author.Book(s.nextLine());
            bookService.delete(authorService.getDao(), book);
        }catch (Exception ex){
            System.out.println("Error, couldn't find the book with this name.");
            loggerError.error(ex.getMessage());
        }
    }

    public static void getAll() {
        if(authorService.findAll().length>0){
        for (Author author :
                authorService.findAll()) {
            System.out.println(author.getName());
            for (Author.Book book :
                    author.getBooks()) {
                System.out.println("\t" + book.getName());
            }
        }
        }else {
            System.out.println("The authors list is empty.");
            loggerInfo.info("The authors list is empty.");
        }
    }

    public static void deleteAuthor() {
        try {
            loggerInfo.info("Starting the proccess of author removal.");
            System.out.println("Please input author's name.");
            Author author = new Author(s.nextLine());
            authorService.delete(author);
            System.out.println("Success, an author with this name was removed.");
            loggerInfo.info("Success, " + author.getName() + " was removed.");
        } catch (Exception ex) {
            loggerError.error(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    public static void addAuthor() {
        try {
            loggerInfo.info("Starting the proccess of author addition.");
            System.out.println("Please enter author's name.");
            Author author = new Author(s.nextLine());
            authorService.create(author);
            System.out.println("Success, an author with this name was added.");
            loggerInfo.info("Success, " + author.getName() + " was added.");
        } catch (Exception ex) {
            loggerError.error(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    public static void addBook() {
        try {
            loggerInfo.info("Starting the proccess of book addition.");
            System.out.println("Please enter the author's name you'd like to add the book to.");
            Author author = new Author(s.nextLine());
            System.out.println("Please enter the book's name.");
            Author.Book book = new Author.Book(s.nextLine());
            bookService.create(author, authorService.getDao(), book);
            System.out.println("Success, the book was added to the author's array.");
            loggerInfo.info("Success, " + book.getName() + " was added to " + author.getName()+ "'s books.");
        } catch (Exception ex) {
            loggerError.error(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

}
