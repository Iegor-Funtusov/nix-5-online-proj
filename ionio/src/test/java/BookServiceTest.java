import org.junit.jupiter.api.*;
import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.service.AuthorService;
import ua.nkrasnovoronka.service.BookService;
import ua.nkrasnovoronka.service.impl.AuthorServiceImpl;
import ua.nkrasnovoronka.service.impl.BookServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookServiceTest {
    private static BookService bookService = new BookServiceImpl();
    private static AuthorService authorService = new AuthorServiceImpl();

    @BeforeAll
    public static void setUp() {
        Author author = new Author();
        author.setFirstName("Test");
        authorService.create(author);
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setBookTitle(String.valueOf(i));
            book.addAuthorToBook(1L);
            bookService.create(book);
        }
    }

    @Test
    @Order(1)
    void createBook() {
        Book book = new Book();
        book.setId(11L);
        book.setBookTitle("book");
        bookService.create(book);
        Book result = bookService.getBookById(11L);
        assertEquals(book, result);
    }

    @Test
    @Order(2)
    void shouldThrowAnExceptionIfBookIsNull() {
        assertThrows(NullPointerException.class, () -> bookService.create(null));
    }

    @Test
    @Order(3)
     void removeBookById() {
        bookService.removeBookById(1L);
        assertFalse(bookService.getAllBooks().stream().anyMatch(book -> book.getId().equals(1L)));
    }

    @Test
    @Order(4)
     void shouldThrowExceptionIfRemovedBookIsNull() {
        assertThrows(NullPointerException.class, () -> bookService.removeBookById(null));
    }

    @Test
    @Order(5)
    void getAllBooks() {
        assertFalse(bookService.getAllBooks().isEmpty());
    }

    @Test
    @Order(6)
    void updateBookTest() {
        Book bookByName = bookService.getBookById(2L);
        bookByName.setBookTitle("updated");
        bookService.updateBook(2L, bookByName);
        assertEquals("updated", bookService.getBookById(2L).getBookTitle());
    }

    @Test
    @Order(7)
    void getBookById() {
        Book book = new Book();
        book.setId(12L);
        book.setBookTitle("Get");
        bookService.create(book);
        assertEquals(book, bookService.getBookById(12L));
    }

    @Test
    @Order(8)
    public void shouldThrowExceptionIfGetBookIsNull() {
        assertThrows(NullPointerException.class, () -> bookService.getBookById(null));
    }
}
