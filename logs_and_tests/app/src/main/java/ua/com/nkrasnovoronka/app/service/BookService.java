package ua.com.nkrasnovoronka.app.service;

import ua.com.nkrasnovoronka.app.dao.Book;

import java.util.Collection;

public interface BookService {
    Book create(Book book);

    Book removeBookByName(String name);

    Collection<Book> getAllBooks();

    void updateBook(Book book);

    Book getBookByName(String name);
}
