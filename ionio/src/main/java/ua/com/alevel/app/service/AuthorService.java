package ua.com.alevel.app.service;

import ua.com.alevel.app.entity.Author;

import java.util.List;

public interface AuthorService {

    void create(Author author);

    List<Author> read();

    Author read(String id);

    void update(Author author);

    void delete(String id);

    List<Author> readByBook(String id);
}
