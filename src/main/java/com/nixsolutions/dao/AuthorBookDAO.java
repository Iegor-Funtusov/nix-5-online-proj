package com.nixsolutions.dao;

import com.nixsolutions.model.Author;
import com.nixsolutions.model.Book;

import java.util.List;
import java.util.UUID;

public interface AuthorBookDAO {

    void createAuthor(Author author);

    List<Author> readAllAuthors();

    Author findAuthorById(String id);

    void updateAuthor(Author author);

    void deleteAuthor(String id);

    List<Author> findAuthorByBook(String id);

    void createBook(Book book, List<String> authorsId);

    List<Book> reedAllBooks();

    Book findBookById(String id);

    void updateBook(Book book);

    void deleteBook(String id);

    List<Book> findBookByAuthor(String id);

    default String createBookId(String id) {
        if(findBookById(id).getId() == null) {
            return id;
        }
        else {
            return createBookId(UUID.randomUUID().toString());
        }
    }

    default String createAuthorId(String id) {
        if(findAuthorById(id).getId() == null){
            return id;
        }
        else {
            return createAuthorId(UUID.randomUUID().toString());
        }
    }
}
