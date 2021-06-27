package ua.com.alevel.app.dao;

import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.entity.Book;

import java.util.List;

public interface LibraryDao {

    void createAuthor(Author author);

    List<Author> readAuthor();

    Author readAuthor(String id);

    void updateAuthor(Author author);

    void deleteAuthor(String id);

    List<Author> readByBookAuthor(String id);

    void createBook(Book book, List<String> authorsId);

    List<Book> readBook();

    Book readBook(String id);

    void updateBook(Book book);

    void deleteBook(String id);

    List<Book> readByAuthorBook(String id);


}
