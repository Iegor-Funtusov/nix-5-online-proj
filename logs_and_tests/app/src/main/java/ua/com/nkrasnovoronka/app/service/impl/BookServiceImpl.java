package ua.com.nkrasnovoronka.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.nkrasnovoronka.app.data.LibraryDB;
import ua.com.nkrasnovoronka.app.model.Book;
import ua.com.nkrasnovoronka.app.service.BookService;

import java.util.Collection;

public class BookServiceImpl implements BookService {
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    LibraryDB libraryDB = LibraryDB.getInstance();

    @Override
    public Book create(Book book) {
        if (book != null) {
            if (book.getName().isEmpty()) {
                loggerWarn.warn("book name is empty");
            }
            libraryDB.getBooks().create(book);
            loggerInfo.info("Creating book with id {}", book.getId());
            return book;
        }
        loggerError.error("Entity book is null");
        throw new NullPointerException("Entity book cannot be null");
    }

    @Override
    public Book removeBookByName(String name) {
        if (name != null) {
            Book book = libraryDB.removeBook(name);
            loggerInfo.info("Removing book with id {}", book.getId());
            return book;
        }
        loggerError.error("Book name is null");
        throw new NullPointerException("Book name cannot be null");
    }

    @Override
    public Collection<Book> getAllBooks() {
        loggerInfo.info("Getting all books");
        Collection<Book> books = libraryDB.getBooks().readAll();
        if (books != null) {
            return books;
        }
        loggerError.error("Cannot get all books");
        throw new NullPointerException("Cannot find books collection");
    }

    @Override
    public void updateBook(Book book) {
        if (book != null) {
            loggerInfo.info("Updating book with id {}", book.getId());
            libraryDB.getBooks().update(book);
        } else {
            loggerError.error("Book entity is null");
            throw new NullPointerException("Book entity cannot be null");
        }
    }

    @Override
    public Book getBookByName(String name) {
        if (name != null) {
            Book bookByName = libraryDB.getBookByName(name);
            loggerInfo.info("Get book by name id {}", bookByName.getId());
            return bookByName;
        }
        loggerError.error("Book name is null");
        throw new NullPointerException("Book name cannot be null");
    }
}
