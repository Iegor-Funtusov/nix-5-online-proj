package ua.com.alevel.app.service;

import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.entity.Book;

import java.util.List;

public interface AuthorService {
    void create(Author author);
    void update(Author author);
    void delete(Author author);
    List<Author> read();
    List<Book> readList(Author author);
}
