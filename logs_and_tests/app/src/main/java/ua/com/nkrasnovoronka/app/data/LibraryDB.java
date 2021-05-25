package ua.com.nkrasnovoronka.app.data;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.nkrasnovoronka.app.model.Author;
import ua.com.nkrasnovoronka.app.model.Book;
import ua.com.nkrasnovoronka.lib.Crud;
import ua.com.nkrasnovoronka.lib.CrudProcessorFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public class LibraryDB {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    Crud<Book> books = CrudProcessorFactory.getInstance().getCrudProcessorImpl();
    Crud<Author> authors = CrudProcessorFactory.getInstance().getCrudProcessorImpl();


    private static LibraryDB libraryDB = new LibraryDB();

    private LibraryDB() {
    }

    public static LibraryDB getInstance() {

        if (libraryDB == null) {
            loggerInfo.info("Creating LibraryDB instance");
            return new LibraryDB();
        }
        loggerInfo.info("Getting LibraryDb instance");
        return libraryDB;
    }

    public Collection<Book> readAllBooks() {
        loggerInfo.info("Reading all books");
        return books.readAll();
    }

    public Collection<Author> readAllAuthors() {
        loggerInfo.info("Reading all authors");
        return authors.readAll();
    }


    public Collection<Book> getAllBooksByAuthorsName(String name) {
        if (name != null) {
            loggerInfo.info("Getting all books by author name {}", name);
            Set<Book> collect = new HashSet<>();
            for (Book book : readAllBooks()) {
                if (book.getAuthorId() != null && book.getAuthorId().equals(getAuthorByName(name).getId())) {
                    collect.add(book);
                }
            }
            return collect;
        }
        loggerError.error("Author name is null");
        throw new NullPointerException("Author name is null");
    }


    public Author removeAuthor(String name) {
        if (name != null) {
            Collection<Book> allBooksByAuthorsName = getAllBooksByAuthorsName(name);
            loggerInfo.info("Getting all books by author name {}", name);
            Author author = getAuthorByName(name);
            authors.delete(author.getId());
            loggerInfo.info("Deleting author with id {}", author.getId());
            loggerWarn.warn("All author books will be deleted");
            for (Book b : allBooksByAuthorsName) {
                books.delete(b.getId());
                loggerInfo.info("Book with id {} deleted", b.getId());
            }
            return author;
        }
        loggerError.error("Author name is null");
        throw new NullPointerException("Author name cannot be null");
    }


    public Author getAuthorByName(String authorName) {
        if (authorName != null) {
            Author author = readAllAuthors().stream()
                    .filter(author1 -> author1.getName().equals(authorName))
                    .findFirst()
                    .orElseThrow(() -> {
                        loggerError.error("Cannot find author with name {}", authorName);
                        return new RuntimeException(String.format("Author with name: %s doesn`t exists", authorName));
                    });
            loggerInfo.info("Get author id {} by name {}", author.getId(), authorName);
            return author;

        }
        loggerError.error("Author name is null");
        throw new NullPointerException("Author name cannot be null");
    }

    public Book getBookByName(String bookName) {
        if (bookName != null) {
            Book book = readAllBooks().stream()
                    .filter(book1 -> book1.getName().equals(bookName))
                    .findFirst()
                    .orElseThrow(() -> {
                        loggerError.error("Cannot find book with name {}", bookName);
                        return new RuntimeException(String.format("Book with name: %s doesn`t exists", bookName));
                    });

            return book;
        }
        loggerError.error("Book name is null");
        throw new NullPointerException("Book name cannot be null");
    }

    public Book removeBook(String name) {
        if (name != null) {
            Book book = getBookByName(name);
            books.delete(book.getId());
            return book;
        }
        loggerError.error("Book name is null");
        throw new NullPointerException("Book name cannot be null");
    }
}
