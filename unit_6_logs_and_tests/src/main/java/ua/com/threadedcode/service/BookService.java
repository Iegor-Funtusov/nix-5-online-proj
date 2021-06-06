package ua.com.threadedcode.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.threadedcode.dao.CrudFactory;
import ua.com.threadedcode.dao.ICrudProcess;
import ua.com.threadedcode.dao.crudObject.CrudObject;
import ua.com.threadedcode.entity.Book;

import java.util.Collection;

public class BookService {
    //    private ICrudProcess<Book> bookCrud = CrudFactory.getInstance().getCrudProcess();
    private ICrudProcess<Book> bookCrud = CrudObject.getInstance();
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public void createBook(Book book) {
        loggerInfo.info("Start create book " + book.getTitle());
        bookCrud.createBook(book);

    }

    public void updateBook(Book book) {
        loggerInfo.info("Start update book" + book.getTitle());
        bookCrud.updateBook(book);
    }

    public void deleteBook(String title) {
        loggerWarn.warn("Start delete book " + title);
        try {
            bookCrud.deleteBook(title);
        }catch (NullPointerException e){
            loggerError.error("can't delete book " + title);
            throw new RuntimeException("can't delete book " + title);
        }
    }

    public Collection getAllBooks() {
        return bookCrud.getAllBooks();
    }

    public Book findBookByTitle(String title) {
        try {
            loggerInfo.info("Try to find books by title " + title);
            return bookCrud.findBookByTitle(title);
        } catch (NullPointerException e) {
            loggerError.error("can't find any books by title " + title);
            throw new RuntimeException("there are no book with this title");
        }
    }

    public void deleteBookWithAuthor(String title, String firstname) {
        loggerWarn.warn("Start delete book " + title + ", author firstname " + firstname );
        try {
            bookCrud.deleteBookWithAuthor(title, firstname);
        }catch (NullPointerException e){
            loggerError.error("can't find any books by title " + title);
            throw new RuntimeException("there are no book with this title");
        }
    }
}
