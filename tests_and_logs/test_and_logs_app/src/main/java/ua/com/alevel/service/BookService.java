package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.entity.Book;
import ua.com.alevel.lib.CrudService;
import ua.com.alevel.lib.CrudServiceFactory;

public class BookService {

    private final CrudService<Book> bookCrudService = CrudServiceFactory.getInstance().getCrudService();
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public void create(Book book){
        loggerInfo.info("Start creating the book: " + book.getTitle());
        bookCrudService.create(book);
        loggerInfo.info("The book was created");
    }

    public void update(Book book){
        loggerInfo.info("Start updating the book with id: " + book.getId());
        try{
            bookCrudService.update(book);
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }
        loggerInfo.info("The book was updated.");
    }

    public void delete(Book book){
        loggerWarn.warn("Start deleting book by id: " + book.getId());
        try{
            bookCrudService.delete(book.getId());
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }
        loggerWarn.warn("The book was deleted.");
    }

    public Book read(String id){
        loggerInfo.info("Start reading the book by id: " + id);
        try{
            Book book = bookCrudService.read(id);
            loggerInfo.info("The book was read.");
            return book;
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }
    }

    public Object[] read(){
        try{
            loggerInfo.info("Start reading all books");
            Object[] books = bookCrudService.read();
            loggerInfo.info("Books were read");
            return books;
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }
    }
}
