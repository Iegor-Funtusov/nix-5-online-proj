package Dao;

import entities.Author;

import java.util.ArrayList;

public interface AuthorDao {

    enum CSVIndex{
        ID,
        NAME,
        SURNAME,
        BOOKS,
        VISIBLУ
    }

    void create(Author author);

    ArrayList<Author> findAll();

    Author find(Author author);

    void update(Author current, Author updated);
}
