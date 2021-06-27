package com.k4rnaj1k.Dao;

import com.k4rnaj1k.entities.Author;

import java.util.ArrayList;

public interface AuthorDao {

    enum CSVIndex{
        ID,
        NAME,
        SURNAME,
        BOOKS,
        VISIBLE
    }

    void create(Author author);

    ArrayList<Author> findAll();

    Author find(Author author);

    void update(Author current, Author updated);
}
