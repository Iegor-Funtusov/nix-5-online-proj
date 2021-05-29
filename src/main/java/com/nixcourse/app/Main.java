package com.nixcourse.app;

import com.nixcourse.lib.Author;
import com.nixcourse.lib.AuthorDao;
import com.nixcourse.lib.Book;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AuthorDao authorService = new AuthorDao();

        authorService.create("George", "Orwell");
        Author author = authorService.getAllAuthors()[0];

        author.writeBook("First book", "desc1");
        author.writeBook("Second book", "desc2");

        Book book = authorService.read(author.getId()).getBooks()[0];

        System.out.println(Arrays.toString(authorService.getAllAuthors()));
        System.out.println(Arrays.toString(authorService.read(author.getId()).getBooks()));

        authorService.updateBookTitle(book.getId(), "1984");
        System.out.println(Arrays.toString(authorService.read(author.getId()).getBooks()));

        authorService.deleteBook(book.getId());
        System.out.println(Arrays.toString(authorService.read(author.getId()).getBooks()));
    }
}
