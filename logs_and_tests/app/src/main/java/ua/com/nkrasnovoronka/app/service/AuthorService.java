package ua.com.nkrasnovoronka.app.service;

import ua.com.nkrasnovoronka.app.model.Author;
import ua.com.nkrasnovoronka.app.model.Book;

import java.util.Collection;

public interface AuthorService {
    Author create(Author author);

    Author removeAuthorByName(String name);

    Collection<Author> getAllAuthors();

    void updateAuthor(Author author);

    Author getAuthorByName(String name);

    Collection<Book> getAllAuthorBooks(String authorName);
}
