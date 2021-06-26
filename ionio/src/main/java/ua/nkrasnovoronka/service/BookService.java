package ua.nkrasnovoronka.service;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.Collection;

public interface BookService {
    void create(Book book);

    void removeBookById(Long id);

    Collection<Book> getAllBooks();

    void updateBook(Long bookId, Book book);

    Book getBookById(Long bookId);

    Collection<Author> getAllBookAuthors(Long bookId);

    void addAuthorToBook(Long bookId, Long authorId);

    void removeAuthorFromBook(Long bookId, Long authorId);
}
