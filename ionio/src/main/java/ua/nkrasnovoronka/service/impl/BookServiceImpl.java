package ua.nkrasnovoronka.service.impl;

import ua.nkrasnovoronka.dao.BookDAO;
import ua.nkrasnovoronka.dao.impl.BookDAOImpl;
import ua.nkrasnovoronka.model.Author;
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
    public void updateBook(Long bookId, Book book) {
        bookDAO.update(bookId, book);
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookDAO.findByID(bookId);
    }

    @Override
    public Collection<Author> getAllBookAuthors(Long bookId) {
        return bookDAO.findAllBooksAuthors(bookId);
    }

    @Override
    public void addAuthorToBook(Long bookId, Long authorId) {
        bookDAO.addAuthorToBook(bookId, authorId);
    }

    @Override
    public void removeAuthorFromBook(Long bookId, Long authorId) {
        bookDAO.removeAuthorFromBook(bookId, authorId);
    }

}
