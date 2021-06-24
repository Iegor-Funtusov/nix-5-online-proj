package ua.nkrasnovoronka.data;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.Collection;

public interface LibraryDB {
    Book getBookByName(String name);
    Collection<Book> readAllBooks();
    Collection<Book> getAllBooksByAuthorsName(String name);
    Author removeAuthor(String name);
    Book removeBook(String name);
    Collection<Author> readAllAuthors();
    Author getAuthorByName(String name);
}
