package ua.com.alevel.app.service;

import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.entity.Book;

import java.util.List;

public interface BookService {
    void create(Book book);
    void update(String id);
    void delete(Book book);
    List<Book> read();
    List<Author> readList(Book book);
}
