package ua.nkrasnovoronka.service;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.Collection;

public interface AuthorService {
    void create(Author author);

    Author removeAuthorById(Long id);

    Collection<Author> getAllAuthors();

    void updateAuthor(Author author);

    Author getAuthorById(Long id);

    Collection<Book> getAllAuthorBooks(Long authorId);
}
