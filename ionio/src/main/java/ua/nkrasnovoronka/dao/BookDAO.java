package ua.nkrasnovoronka.dao;

import ua.nkrasnovoronka.model.Book;

import java.util.List;

public interface BookDAO {
    Book create (Book book);
    Book update(Book book);
    void delete(Long id);
    Book findByID(Long id);
    List<Book> findAll();
    List<Book> findByAuthorID(Long authorId);
}
