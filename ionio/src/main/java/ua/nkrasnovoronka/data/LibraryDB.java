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

    void updateAuthor(Long authorId, Author author);

    void updateBook(Long bookId, Book book);

    Collection<Book> getAllAuthorBooks(Long authorId);

    Collection<Author> getAllBooksAuthors(Long bookId);

    void addBookToAuthor(Long authorId, Long bookId);

    void removeBookFromAuthor(Long authorId, Long bookId);

    void addAuthorToBook(Long bookId, Long authorId);

    void removeAuthorFromBook(Long bookId, Long authorId);
}
