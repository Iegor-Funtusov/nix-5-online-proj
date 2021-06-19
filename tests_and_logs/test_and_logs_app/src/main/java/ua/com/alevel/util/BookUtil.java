package ua.com.alevel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.Relation;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.RelationService;

import java.io.BufferedReader;
import java.io.IOException;

public class BookUtil {

    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public static Book createBook(BufferedReader reader)
            throws IOException, NumberFormatException {
        return getBook(reader);
    }

    private static Book getBook(BufferedReader reader)
            throws IOException, NumberFormatException {
        System.out.print("Please enter book title: ");
        String title = reader.readLine();//
        return new Book(title);
    }

    public static Book readBook(BookService bookService, BufferedReader reader)
            throws IOException, RuntimeException {
        System.out.println("Please enter book id:");
        System.out.print("--> ");
        return bookService.read(reader.readLine());
    }

    public static void updateBook(Book readBook, BookService bookService, BufferedReader reader)
            throws IOException, NumberFormatException{
        System.out.println("Enter new parameters for the book.");
        Book bookForUpdate = BookUtil.createBook(reader);
        bookForUpdate.setId(readBook.getId());
        bookService.update(bookForUpdate);
    }

    public static void printAllBooks(BookService bookService) {
        try{
            Object[] books = bookService.read();
            for (Object book : books) {
                System.out.println(book);
            }
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public static void deleteBook(Book book, BookService bookService, RelationService relationService)
            throws RuntimeException {
        Object[] relations = relationService.read();
        for (Object relation : relations) {
            if (((Relation) relation).getBook().getId().equals(book.getId())) {
                relationService.delete((Relation) relation);
            }
        }
        bookService.delete(book);
    }
}
