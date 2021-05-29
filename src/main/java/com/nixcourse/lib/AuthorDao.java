package com.nixcourse.lib;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class AuthorDao {

    private Author[] authors = new Author[1];
    private int authorsCounter = 0;

    public void create(String name, String surname) {
        if (authorsCounter >= authors.length) {
            authors = Arrays.copyOf(authors, authors.length * 2);
        }
        authors[authorsCounter] = new Author(name, surname);
        ++authorsCounter;
    }

    public Author[] getAllAuthors() {
        return authors;
    }

    public Author read(String id) {
        Author result = null;
        for (Author author : authors) {
            if (author != null) {
                if (author.getId().equals(id)) {
                    result = author;
                }
            }
        }
        return result;
    }

    public Author findAuthorByBook(String bookId) {
        for (Author author : authors) {
            if (author != null) {
                for (Book book : author.getBooks()) {
                    if (book != null) {
                        if (book.getId().equals(bookId)) {
                            return author;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void updateBookTitle(String bookId, String title) {
        Author author = findAuthorByBook(bookId);
        if (author != null) {
            Book book = author.getBook(bookId);
            if (book != null) {
                book.setTitle(title);
            }
        }
    }

    public void updateBookText(String bookId, String text) {
        Author author = findAuthorByBook(bookId);
        if (author != null) {
            Book book = author.getBook(bookId);
            if (book != null) {
                book.setText(text);
            }
        }
    }

    public void deleteAuthor(String authorId) {
        for (int i = 0; i < authors.length; ++i) {
            if (authors[i] != null) {
                if (authors[i].getId().equals(authorId)) {
                    this.authors = ArrayUtils.remove(authors, i);
                }
            }
        }
    }

    public void deleteBook(String bookId) {
        Author author = findAuthorByBook(bookId);
        if (author != null) {
            author.deleteBook(bookId);
        }
    }
}
