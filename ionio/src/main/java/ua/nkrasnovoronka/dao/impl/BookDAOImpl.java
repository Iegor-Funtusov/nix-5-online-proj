package ua.nkrasnovoronka.dao.impl;

import ua.nkrasnovoronka.dao.BookDAO;
import ua.nkrasnovoronka.data.LibraryDB;
import ua.nkrasnovoronka.data.impl.CSVLibraryDB;
import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.Collection;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private final LibraryDB libraryDB = CSVLibraryDB.getInstance();

    @Override
    public void create(Book book) {
        libraryDB.createBook(book);
    }

    @Override
    public void update(Long bookId, Book book) {
        libraryDB.updateBook(bookId, book);
    }

    @Override
    public void delete(Long id) {
        libraryDB.deleteBookById(id);
    }

    @Override
    public Book findByID(Long id) {
        return libraryDB.getBookById(id);
    }

    @Override
    public List<Book> findAll() {
        return libraryDB.getAllBooks();
    }

    @Override
    public List<Book> findByBookID(Long bookId) {
        return (List<Book>) libraryDB.getAllAuthorBooks(bookId);
    }

    @Override
    public Collection<Author> findAllBooksAuthors(Long bookId) {
        return libraryDB.getAllBooksAuthors(bookId);
    }

    @Override
    public void addAuthorToBook(Long bookId, Long authorId) {
        libraryDB.addAuthorToBook(bookId, authorId);
    }

    @Override
    public void removeAuthorFromBook(Long bookId, Long authorId) {
        libraryDB.removeAuthorFromBook(bookId, authorId);
    }

}
