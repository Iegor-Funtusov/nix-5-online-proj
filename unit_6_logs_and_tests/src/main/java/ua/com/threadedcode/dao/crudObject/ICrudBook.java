package ua.com.threadedcode.dao.crudObject;

import ua.com.threadedcode.entity.Book;

import java.util.Collection;

public interface ICrudBook<E extends Book> {
    void createBook(Book book);

    void updateBook(Book book);

    void deleteBook(String title);

    Collection<Book> getAllBooks();

    Book findBookByTitle(String title);
    void deleteBookWithAuthor(String title, String firstname);
}
