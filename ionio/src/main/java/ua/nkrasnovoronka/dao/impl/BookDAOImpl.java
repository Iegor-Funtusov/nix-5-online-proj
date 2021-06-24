package ua.nkrasnovoronka.dao.impl;

import ua.nkrasnovoronka.dao.BookDAO;
import ua.nkrasnovoronka.data.CSVLibraryDB;
import ua.nkrasnovoronka.data.LibraryDB;
import ua.nkrasnovoronka.model.Book;

import java.util.List;

public class BookDAOImpl implements BookDAO {
    private final LibraryDB libraryDB = CSVLibraryDB.getInstance();

    @Override
    public void create(Book book) {
        libraryDB.createBook(book);
    }

    @Override
    public void update(Book book) {
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Book findByID(Long id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findByAuthorID(Long authorId) {
        return null;
    }
}
