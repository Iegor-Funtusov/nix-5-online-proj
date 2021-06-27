package Dao;

import entities.Book;

import java.util.ArrayList;

public interface BookDao {
    ArrayList<Book> findAll();

    void update(Book current, Book updated);

    enum CSVIndex{
        ID,
        NAME,
        AUTHORS,
        VISIBLE;
    }

    void create(Book book);

    Book find(String bookName);
}
