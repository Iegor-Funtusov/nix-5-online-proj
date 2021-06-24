package ua.nkrasnovoronka.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nkrasnovoronka.dao.AuthorDAO;
import ua.nkrasnovoronka.dao.impl.AuthorDAOImpl;
import ua.nkrasnovoronka.data.CSVLibraryDB;
import ua.nkrasnovoronka.data.LibraryDB;
import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.service.AuthorService;

import java.util.Collection;

public class AuthorServiceImpl implements AuthorService {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");


    @Override
    public Author create(Author author) {
        return null;
    }

    @Override
    public Author removeAuthorByName(String name) {
        return null;
    }

    @Override
    public Collection<Author> getAllAuthors() {
        return null;
    }

    @Override
    public void updateAuthor(Author author) {

    }

    @Override
    public Author getAuthorByName(String name) {
        return null;
    }

    @Override
    public Collection<Book> getAllAuthorBooks(String authorName) {
        return null;
    }
}
