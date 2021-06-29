package com.nixsolutions.courses;

import com.nixsolutions.courses.data.Author;
import com.nixsolutions.courses.data.Book;
import com.nixsolutions.courses.service.impl.AuthorService;
import com.nixsolutions.courses.service.impl.BookService;
import com.nixsolutions.courses.util.CSVParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookCrudTest {

    private static final BookService bookService = new BookService();
    private static final String TEST_STRING = "test";
    private static String AUTHOR_ID;

    @BeforeAll
    static void init() {
        bookService.init();
        AuthorService authorService = new AuthorService();
        authorService.init();
        Author author = new Author();
        author.setName(TEST_STRING);
        author.setSurname(TEST_STRING);
        authorService.create(author);
        AUTHOR_ID = author.getId();
    }

    @Test
    void create() {
        Book book = new Book();
        book.setTitle(TEST_STRING);
        book.setAuthors(CSVParser.parseIdsList(AUTHOR_ID));
        bookService.create(book);
        Book createdBook = bookService.findById(book.getId());

        Assertions.assertEquals(book, createdBook);
    }
}
