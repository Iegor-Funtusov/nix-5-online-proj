package ua.nkrasnovoronka.dao.impl;

import ua.nkrasnovoronka.dao.AuthorDAO;
import ua.nkrasnovoronka.data.CSVLibraryDB;
import ua.nkrasnovoronka.data.LibraryDB;
import ua.nkrasnovoronka.model.Author;

import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {
    private final LibraryDB libraryDB = CSVLibraryDB.getInstance();

    @Override
    public void create(Author author) {
        libraryDB.createAuthor(author);
    }

    @Override
    public void update(Author author) {
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Author findByID(Long id) {
        return null;
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public List<Author> findByBookID(Long bookId) {
        return null;
    }
}
