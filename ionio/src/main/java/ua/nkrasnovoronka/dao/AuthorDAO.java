package ua.nkrasnovoronka.dao;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.Collection;
import java.util.List;

public interface AuthorDAO {
    void create(Author author);

    void update(Long authorId, Author author);

    void delete(Long id);

    Author findByID(Long id);

    List<Author> findAll();

    List<Author> findByBookID(Long bookId);

    Collection<Book> findAllAuthorsBooks(Long authorId);

    void addBookToAuthor(Long authorId, Long bookId);

    void removeBookFromAuthor(Long authorId, Long bookId);
}
