package ua.nkrasnovoronka.service;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.Collection;

public interface AuthorService {
    void create(Author author);

    Collection<Author> getAllAuthors();

    void updateAuthor(Long authorId, Author author);

    Author getAuthorById(Long id);

    Collection<Book> getAllAuthorBooks(Long authorId);

    void addBookToAuthor(Long authorId, Long bookId);

    void removeBookFromAuthor(Long authorId, Long bookId);
}
