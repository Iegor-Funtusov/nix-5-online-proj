package ua.com.alevel.app.service;

import ua.com.alevel.app.entity.Book;

import java.util.List;

public interface BookService {

    void create(Book book, List<String> authors);

    List<Book> read();

    Book read(String id);

    void update(Book book);

    void delete(String id);

    List<Book> readByAuthor(String id);
}
