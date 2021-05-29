package com.k4rnaj1k.Service;

import com.k4rnaj1k.DataClasses.Author;
import com.k4rnaj1k.Dao.AuthorDao;
import com.k4rnaj1k.Dao.BookDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookService {
    BookDao bookDao = new BookDao();

    private static Logger loggerInfo = LoggerFactory.getLogger("info");
    private static Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static Logger loggerError = LoggerFactory.getLogger("error");

    public void create(Author author, AuthorDao authorDao, Author.Book book){
        try{
        loggerWarn.warn("Trying to add a book with a name " + book.getName() + " to " + author.getName() + "'s list");
        bookDao.addBook(author, authorDao, book);
        loggerInfo.info("Added a book with a name " + book.getName() + " to " + author.getName() + "'s list");
    }catch (Exception ex){
            loggerError.error(ex.getMessage());
        }
    }

    public void delete(AuthorDao authorDao, Author.Book book){
        try{
            loggerWarn.warn("Trying to remove a book " + book.getName());
            bookDao.deleteBook(authorDao,book);
            loggerInfo.info("Removed a book with a name + " + book.getName());
    }catch (Exception ex){
            loggerError.error(ex.getMessage());
        }
    }

}
