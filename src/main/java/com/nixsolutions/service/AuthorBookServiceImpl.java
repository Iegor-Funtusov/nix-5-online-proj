package com.nixsolutions.service;

import com.nixsolutions.dao.AuthorBookDAOImpl;
import com.nixsolutions.model.Book;
import com.nixsolutions.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class AuthorBookServiceImpl implements AuthorBookService {

    AuthorBookDAOImpl authorBookDAO = new AuthorBookDAOImpl();

    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");

    public void createFiles() {
        loggerInfo.info("Creating of txt file!");
        authorBookDAO.createAuthorBookCsv();
        loggerInfo.info("txt file created!");
    }

    @Override
    public void createAuthor(Author author) {
        loggerInfo.info("Creating Author with First Name - " + author.getFirstName()
                + "; Last Name - " + author.getLastName());
        authorBookDAO.createAuthor(author);
        loggerInfo.info("Author Created!");
    }

    @Override
    public List<Author> readAllAuthors() {
        loggerInfo.info("Reading all Authors");
        return authorBookDAO.readAllAuthors();
    }

    @Override
    public Author findAuthorById(String id) {
        loggerInfo.info("Reading Author with id - " + id);
        return authorBookDAO.findAuthorById(id);
    }

    @Override
    public void updateAuthor(Author author) {
        loggerWarn.info("Updating Author - " + author.getFirstName()
                + " " + author.getLastName());
        authorBookDAO.updateAuthor(author);
        loggerWarn.info("Author updated!");
    }

    @Override
    public void deleteAuthor(String id) {
        loggerWarn.info("Deleting author with id - " + id);
        authorBookDAO.deleteAuthor(id);
        loggerWarn.info("Author Deleted!");
    }

    public List<Author> findAuthorByBook(String id) {
        loggerInfo.info("Searching for Author with book's id - " + id);
        return authorBookDAO.findAuthorByBook(id);
    }

    @Override
    public void createBook(Book book, List<String> authors) {
        loggerInfo.info("Creating book  - " + book.getTitle() );
        authorBookDAO.createBook(book,authors);
        loggerInfo.info("Book created!");
    }

    @Override
    public List<Book> readAllBooks() {
        loggerInfo.info("Reading all books");
        return authorBookDAO.reedAllBooks();
    }

    @Override
    public Book findBookById(String id) {
        loggerInfo.info("Reading book with id - " + id);
        return authorBookDAO.findBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        loggerWarn.info("Updating book - " + book.getTitle());
        authorBookDAO.updateBook(book);
        loggerWarn.info("Book updated!");
    }

    @Override
    public void deleteBook(String id) {
        loggerWarn.info("Deleting book with id - " + id);
        authorBookDAO.deleteBook(id);
        loggerWarn.info("Book deleted!");
    }

    @Override
    public List<Book> findBookByAuthor(String id){
        loggerInfo.info("Searching for books with author's id - " + id);
        return authorBookDAO.findBookByAuthor(id);
    }
}