package ua.com.alevel.app.dao;

import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.entity.Book;

import java.util.List;

public interface DaoBook {
    void create(Book book);
    void update(String id);
    List<Book> read();
    List<Author> readList(Book book);
    void delete(Book book);
}
