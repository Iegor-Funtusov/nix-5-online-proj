package ua.nkrasnovoronka.service.impl;

import ua.nkrasnovoronka.dao.BookDAO;
import ua.nkrasnovoronka.dao.impl.BookDAOImpl;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.service.BookService;

import java.util.Collection;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO = new BookDAOImpl();


    @Override
    public void create(Book book) {
        bookDAO.create(book);
    }

    @Override
    public void removeBookById(Long id) {
        bookDAO.delete(id);
    }

    @Override
    public Collection<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.update(book);
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookDAO.findByID(bookId);
    }

}
