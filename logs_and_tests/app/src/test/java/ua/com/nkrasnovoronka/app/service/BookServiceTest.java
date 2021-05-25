package ua.com.nkrasnovoronka.app.service;

import org.junit.jupiter.api.*;
import ua.com.nkrasnovoronka.app.model.Author;
import ua.com.nkrasnovoronka.app.model.Book;
import ua.com.nkrasnovoronka.app.service.impl.AuthorServiceImpl;
import ua.com.nkrasnovoronka.app.service.impl.BookServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {
    private static BookService bookService = new BookServiceImpl();
    private static AuthorService authorService = new AuthorServiceImpl();

    @BeforeAll
    public static void setUp() {
        Author author = new Author("test author");
        authorService.create(author);
        for (int i = 0; i < 10; i++) {
            Book book = new Book(String.valueOf(i));
            book.setAuthorId(author.getId());
            bookService.create(book);
        }
    }

    @Test
    @Order(1)
    public void createBook() {
        Book book = new Book("created");
        bookService.create(book);
        Book result = bookService.getBookByName("created");
        assertEquals(book, result);
    }

    @Test
    @Order(2)
    public void shouldThrowAnExceptionIfBookIsNull() {
        assertThrows(NullPointerException.class, () -> bookService.create(null));
    }

    @Test
    @Order(3)
    public void removeBookByName() {
        Book removed = bookService.removeBookByName("9");
        assertFalse(bookService.getAllBooks().contains(removed));
    }

    @Test
    @Order(4)
    public void shouldThrowExceptionIfRemovedBookIsNull() {
        assertThrows(NullPointerException.class, () -> bookService.removeBookByName(null));
    }

    @Test
    @Order(5)
    public void getAllBooks() {
        assertFalse(bookService.getAllBooks().isEmpty());
    }

    @Test
    @Order(6)
    public void updateBookTest() {
        Book bookByName = bookService.getBookByName("2");
        bookByName.setName("updated");
        bookService.updateBook(bookByName);
        assertSame(bookByName, bookService.getBookByName("updated"));
    }

    @Test
    @Order(7)
    public void shouldThrowExceptionIfUpdatedBookIsNull() {
        assertThrows(NullPointerException.class, () -> bookService.updateBook(null));
    }

    @Test
    @Order(8)
    public void getBookByName() {
        Book book = new Book("get");
        bookService.create(book);
        assertSame(book, bookService.getBookByName("get"));
    }

    @Test
    @Order(9)
    public void shouldThrowExceptionIfGetBookIsNull() {
        assertThrows(NullPointerException.class, () -> bookService.getBookByName(null));
    }
}
