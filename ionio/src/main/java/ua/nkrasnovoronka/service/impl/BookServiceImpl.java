package ua.nkrasnovoronka.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nkrasnovoronka.dao.BookDAO;
import ua.nkrasnovoronka.dao.impl.BookDAOImpl;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.service.BookService;

import java.util.Collection;

public class BookServiceImpl implements BookService {
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    private BookDAO bookDAO = new BookDAOImpl();


    @Override
    public void create(Book book) {
        bookDAO.create(book);
    }

    @Override
    public Book removeBookByName(String name) {
        return null;
    }

    @Override
    public Collection<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public Book getBookByName(String name) {
//        return bookDAO.findAll().stream().filter(book -> book.equals());
        return null;
    }
}
