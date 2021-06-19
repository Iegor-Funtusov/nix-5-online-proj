import book.Book;
import book.BookService;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {

    final static private String NAME = "Ready player one";
    private static final BookService bookService = new BookService();

    @Test
    @Order(1)
    public void create() {
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle(NAME + i);
            book.setNumPg(i);
            bookService.create(book);
        }
        Book[] books = bookService.readAll();
        Assertions.assertTrue(books != null && books.length == 10);
    }

    @Test
    @Order(2)
    public void update() {
        Book[] books = bookService.readAll();
        Book newBook;
        for (int i = 0; i < books.length; i++) {
            newBook = books[i];
            newBook.setTitle(NAME + i + 10);
            bookService.update(newBook);
        }
        Assertions.assertTrue(books != null && books.length == 10 && books[5].getTitle().equals("Ready player one510"));
    }

    @Test
    @Order(3)
    public void deleteAll() {
        Book[] books = bookService.readAll();
        for (int i = 0; i < books.length; i++) {
            bookService.delete(books[i].getId());
        }
        books = bookService.readAll();
        for (int i = 0; i < books.length; i++) {
            Assertions.assertNull(books[i]);
        }
    }

    @Test
    @Order(4)
    public void find() {
        Book book = new Book();
        book.setTitle("Hello");
        book.setNumPg(12);
        bookService.create(book);

        Assertions.assertSame(book, bookService.findById(book.getId()));
    }
}