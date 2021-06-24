package ua.nkrasnovoronka.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nkrasnovoronka.data.CSVLibraryDB;
import ua.nkrasnovoronka.data.LibraryDB;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.service.BookService;

import java.util.Collection;

public class BookServiceImpl implements BookService {
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");


    @Override
    public Book create(Book book) {
        return null;
    }

    @Override
    public Book removeBookByName(String name) {
        return null;
    }

    @Override
    public Collection<Book> getAllBooks() {
        return null;
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public Book getBookByName(String name) {
        return null;
    }
}
