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
public class AuthorCrudTest {

    final static private String FIRST_NAME = "first_name";
    final static private String LAST_NAME = "last_name";

    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final BookService bookService = new BookServiceImpl();

    @Test
    @BeforeAll
    static void CommonTest() {
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setFirstName(FIRST_NAME + i);
            author.setLastName(LAST_NAME + i);
            authorService.create(author);
        }
        List<Author> authors = authorService.read();

        Author newAuthor;
        newAuthor = authors.get(0);
        newAuthor.setFirstName(FIRST_NAME + "UPDATED" + 0);
        authorService.update(newAuthor);

        authors = authorService.read();

        authorService.delete(authors.get(0));
        authorService.delete(authors.get(1));

        authors = authorService.read();

        Assertions.assertEquals(authors.size(), 8);
    }

    @Test
    @Order(1)
    public void create() {
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setFirstName(FIRST_NAME + i);
            author.setLastName(LAST_NAME + i);
            authorService.create(author);
        }
        List<Author> authors = authorService.read();
        Assertions.assertTrue(authors != null && authors.size() == 18);
    }

    @Test
    @Order(2)
    public void update() {
        List<Author> authors = authorService.read();
        Author newAuthor;
        for (int i = 0; i < authors.size(); i++) {
            newAuthor = authors.get(i);
            newAuthor.setFirstName(FIRST_NAME + i + 10);
            authorService.update(newAuthor);
        }
        Assertions.assertEquals(18, authors.size());
    }

    @Test
    @Order(3)
    public void deleteAll() {
        List<Author> authors = authorService.read();
        for (Author value : authors) {
            authorService.delete(value);
        }
        authors = authorService.read();
        for (Author author : authors) {
            Assertions.assertNull(author);
        }
    }

    @Test
    @Order(4)
    public void readAuthors() {
        Assertions.assertEquals(0, authorService.read().size());
    }

    @Test
    @Order(5)
    public void readAuthorBooks() {
        Author author = new Author();
        author.setFirstName(FIRST_NAME);
        author.setLastName(LAST_NAME);
        authorService.create(author);

        Book book = new Book();
        book.setTitle("title1");
        bookService.create(book);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        book = new Book();
        book.setTitle("title2");
        bookService.create(book);
        bookList.add(book);

        author.setBooks(bookList);
        Assertions.assertEquals(authorService.readList(author).size(), 2);
    }
}