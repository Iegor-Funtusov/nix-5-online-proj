package ua.com.alevel.app.dao;

import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.entity.Book;

import java.util.List;

public interface DaoAuthor {
    void create(Author author);
    void update(Author author);
    List<Author> read();
    List<Book> readList(Author author);
    void delete(Author author);
}
