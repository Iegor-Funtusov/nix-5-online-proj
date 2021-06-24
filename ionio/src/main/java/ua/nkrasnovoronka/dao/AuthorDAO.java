package ua.nkrasnovoronka.dao;

import ua.nkrasnovoronka.model.Author;

import java.util.List;

public interface AuthorDAO {
    Author create (Author author);
    Author update(Author author);
    void delete(Long id);
    Author findByID(Long id);
    List<Author> findAll();
    List<Author> findByBookID(Long bookId);
}
