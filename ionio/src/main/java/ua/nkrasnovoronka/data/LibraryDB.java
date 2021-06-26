package ua.nkrasnovoronka.data;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.Collection;
import java.util.List;

public interface LibraryDB {


    void createAuthor(Author author);

    void createBook(Book book);

    List<Author> getAllAuthors();

    List<Book> getAllBooks();

    Author getAuthorById(Long authorId);

    Book getBookById(Long bookId);

    void deleteBookById(Long id);

    void deleteAuthorById(Long id);

    void updateAuthor(Author author);

    void updateBook(Book book);

    Collection<Book> getAllAuthorBooks(Long authorId);
}
