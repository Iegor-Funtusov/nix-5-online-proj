package ua.nkrasnovoronka.service;

import ua.nkrasnovoronka.model.Book;

import java.util.Collection;

public interface BookService {
    Book create(Book book);

    Book removeBookByName(String name);

    Collection<Book> getAllBooks();

    void updateBook(Book book);

    Book getBookByName(String name);
}
