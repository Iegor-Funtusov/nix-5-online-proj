package ua.nkrasnovoronka.dao;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.Collection;
import java.util.List;

public interface BookDAO {
    void create (Book book);
    void update(Long bookID, Book book);
    void delete(Long id);
    Book findByID(Long id);
    List<Book> findAll();
    List<Book> findByBookID(Long bookId);
    Collection<Author> findAllBooksAuthors(Long bookId);
    void addAuthorToBook(Long bookId, Long authorId);
    void removeAuthorFromBook(Long bookId, Long authorId);
}
