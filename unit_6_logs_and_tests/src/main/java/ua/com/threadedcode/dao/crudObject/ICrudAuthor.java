package ua.com.threadedcode.dao.crudObject;

import ua.com.threadedcode.entity.Author;

import java.util.Collection;

public interface ICrudAuthor<E extends Author> {
    void createAuthor(Author author);

    Collection findBooksByAuthorFistName(String firstname);

    void updateAuthor(Author author);

    void deleteAuthor(String firstName);

    Collection<Author> getAllAuthors();

    Author getAuthorByName(String firstName);
}
