package ua.nkrasnovoronka.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    LibraryDB libraryDB = CSVLibraryDB.getInstance();


    @Override
    public Author create(Author author) {
        if (author != null) {
//            if (author.getName().isEmpty()) {
//                loggerWarn.warn("Author name is empty!!");
//            }
//            libraryDB.getAuthors().create(author);
//            loggerInfo.info("Created new Author with name {} and id {}", author.getName(), author.getId());
            return author;
        }
        loggerError.error("Author cannot be null!!");
        throw new NullPointerException("Entity author is null");
    }

    @Override
    public Author removeAuthorByName(String name) {
        if (name == null) {
            loggerError.error("Author name is null");
            throw new NullPointerException("Author name cannot be null");
        }
        Author author = libraryDB.removeAuthor(name);
//        loggerInfo.info("Removed Author with name {} and id {}", author.getName(), author.getId());
        return author;
    }

    @Override
    public Collection<Author> getAllAuthors() {
        Collection<Author> authors = libraryDB.readAllAuthors();
        loggerInfo.info("Getting list of all authors");
        return authors;
    }

    @Override
    public void updateAuthor(Author author) {
        if (author != null) {
//            libraryDB.getAuthors().update(author);
            loggerInfo.info("Author with id {} updated", author.getId());
        } else {
            loggerError.error("Author is null!!!");
            throw new NullPointerException("Entity author cannot be null");
        }


    }

    @Override
    public Author getAuthorByName(String name) {
        if (name != null) {
            if (name.isEmpty()) {
                loggerWarn.warn("Name field is empty");
            }
            Author authorByName = libraryDB.getAuthorByName(name);
            loggerInfo.info("Get author with id {}", authorByName.getId());
            return authorByName;
        }
        loggerError.error("Name cannot be null");
        throw new NullPointerException("Name cannot be null");
    }

    @Override
    public Collection<Book> getAllAuthorBooks(String authorName) {
        if (authorName != null) {
            if (authorName.isEmpty()) {
                loggerWarn.warn("Author name is empty");
            }
            loggerInfo.info("Getting all books with author name {}", authorName);
            return libraryDB.getAllBooksByAuthorsName(authorName);

        } else {
            loggerError.error("Author name is null");
            throw new NullPointerException("Author name cannot be null");
        }
    }

}
