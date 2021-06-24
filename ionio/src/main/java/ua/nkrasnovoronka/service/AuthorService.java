package ua.nkrasnovoronka.service;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.Collection;

public interface AuthorService {
    Author create(Author author);

    Author removeAuthorByName(String name);

    Collection<Author> getAllAuthors();

    void updateAuthor(Author author);

    Author getAuthorByName(String name);

    Collection<Book> getAllAuthorBooks(String authorName);
}
