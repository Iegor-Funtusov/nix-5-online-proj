package ua.nkrasnovoronka.service;

import ua.nkrasnovoronka.model.Book;

import java.util.Collection;

public interface BookService {
    void create(Book book);

    void removeBookById(Long id);

    Collection<Book> getAllBooks();

    void updateBook(Book book);

    Book getBookById(Long bookId);
}
