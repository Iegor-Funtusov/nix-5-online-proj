package com.nixsolutions.service;

import com.nixsolutions.model.Author;
import com.nixsolutions.model.Book;
import java.util.List;

public interface AuthorBookService {

    void createAuthor(Author author);

    List<Author> readAllAuthors();

    Author findAuthorById(String id);

    void updateAuthor(Author author);

    void deleteAuthor(String id);

    List<Author> findAuthorByBook(String id);

    void createBook(Book book, List<String> authors);

    List<Book> readAllBooks();

    Book findBookById(String id);

    void updateBook(Book book);

    void deleteBook(String id);

    List<Book> findBookByAuthor(String id);
}
