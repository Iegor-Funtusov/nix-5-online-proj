package ua.com.alevel.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.app.dao.LibraryDao;
import ua.com.alevel.app.dao.impl.LibraryDaoImpl;
import ua.com.alevel.app.entity.Book;
import ua.com.alevel.app.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    LibraryDao libraryDao = new LibraryDaoImpl();

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");

    public void create(Book book, List<String> authors){
        loggerInfo.info("Start create book  " + book.getTitle() );
        libraryDao.createBook(book,authors);
        loggerInfo.info("End create book  " );
    }

    public List<Book> read(){
        loggerInfo.info("Read all book");
        return libraryDao.readBook();
    }

    public Book read(String id){
        loggerInfo.info("Read book by id : " + id);
        return libraryDao.readBook(id);
    }

    public void update(Book book){
        loggerWarn.info("Start updating book : "+ book.getTitle());
        libraryDao.updateBook(book);
        loggerWarn.info("End updating book ");
    }

    public void delete(String id){
        loggerWarn.info("Start deleting book by id : " + id);
        libraryDao.deleteBook(id);
        loggerWarn.info("End deleting book by id ");
    }

    public List<Book> readByAuthor(String id){
        loggerInfo.info("Find books by author with id : " + id);
        return libraryDao.readByAuthorBook(id);
    }
}
