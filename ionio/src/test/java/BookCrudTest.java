import org.junit.jupiter.api.*;
import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.entity.Book;
import ua.com.alevel.app.service.AuthorService;
import ua.com.alevel.app.service.BookService;
import ua.com.alevel.app.service.impl.AuthorServiceImpl;
import ua.com.alevel.app.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookCrudTest {

    final static private String TITLE = "title_of_book";
    final static private String FIRST_NAME = "first_name";
    final static private String LAST_NAME = "second_name";

    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final BookService bookService = new BookServiceImpl();

    @Test
    @BeforeAll
    static void CommonTest() {
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle(TITLE + i);
            bookService.create(book);
        }
        List<Book> books = bookService.read();

        Book newBook;
        newBook = books.get(0);
        newBook.setTitle(FIRST_NAME + "UPDATED" + 0);
        bookService.update(books.get(0).getId());

        books = bookService.read();

        bookService.delete(books.get(0));
        bookService.delete(books.get(1));

        books = bookService.read();

        Assertions.assertEquals(books.size(), 8);
    }

    @Test
    @Order(1)
    public void create() {
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle(TITLE + i);
            bookService.create(book);
        }
        List<Book> books = bookService.read();
        Assertions.assertTrue(books != null && books.size() == 18);
    }

    @Test
    @Order(2)
    public void update() {
        List<Book> books = bookService.read();
        Book newAuthor;
        for (int i = 0; i < books.size(); i++) {
            newAuthor = books.get(i);
            newAuthor.setTitle(TITLE + i + 10);
            bookService.update(books.get(0).getId());
        }
        Assertions.assertEquals(18, books.size());
    }

    @Test
    @Order(3)
    public void deleteAll() {
        List<Book> books = bookService.read();
        for (Book value : books) {
            bookService.delete(value);
        }
        books = bookService.read();
        for (Book book : books) {
            Assertions.assertNull(book);
        }
    }

    @Test
    @Order(4)
    public void readAuthors() {
        Assertions.assertEquals(bookService.read().size(), 0);
    }

    @Test
    @Order(5)
    public void readAuthorBooks() {
        Author author = new Author();
        author.setFirstName(FIRST_NAME);
        author.setLastName(LAST_NAME);
        authorService.create(author);

        List<Author> authors = new ArrayList<>();
        authors.add(author);

        author = new Author();
        author.setFirstName(FIRST_NAME);
        author.setLastName(LAST_NAME);
        authorService.create(author);

        authors.add(author);

        Book book = new Book();
        book.setTitle(TITLE);
        bookService.create(book);

        book.setAuthors(authors);

        Assertions.assertEquals(bookService.readList(book).size(), 2);
    }
}