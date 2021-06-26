package ua.nkrasnovoronka.dao.impl;

import ua.nkrasnovoronka.dao.AuthorDAO;
import ua.nkrasnovoronka.data.LibraryDB;
import ua.nkrasnovoronka.data.impl.CSVLibraryDB;
import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.Collection;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {
    private final LibraryDB libraryDB = CSVLibraryDB.getInstance();

    @Override
    public void create(Author author) {
        libraryDB.createAuthor(author);
    }

    @Override
    public void update(Long authorId, Author author) {
        libraryDB.updateAuthor(authorId, author);
    }

    @Override
    public void delete(Long id) {
        libraryDB.deleteAuthorById(id);
    }

    @Override
    public Author findByID(Long id) {
        return libraryDB.getAuthorById(id);
    }

    @Override
    public List<Author> findAll() {
        return libraryDB.getAllAuthors();
    }

    @Override
    public List<Author> findByBookID(Long bookId) {
        return (List<Author>) libraryDB.getAllBooksAuthors(bookId);
    }

    @Override
    public Collection<Book> findAllAuthorsBooks(Long authorId) {
        return libraryDB.getAllAuthorBooks(authorId);
    }

    @Override
    public void addBookToAuthor(Long authorId, Long bookId) {
        libraryDB.addBookToAuthor(authorId, bookId);
    }

    @Override
    public void removeBookFromAuthor(Long authorId, Long bookId) {
        libraryDB.removeBookFromAuthor(authorId, bookId);
    }
}
