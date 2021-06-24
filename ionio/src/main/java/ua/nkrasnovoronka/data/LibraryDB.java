package ua.nkrasnovoronka.data;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.List;

public interface LibraryDB {


    void createAuthor(Author author);

    void createBook(Book book);

    List<Author> getAllAuthors();

    List<Book> getAllBooks();

    Author getAuthorById(Long authorId);

    Book getBookById(Long bookId);
}
