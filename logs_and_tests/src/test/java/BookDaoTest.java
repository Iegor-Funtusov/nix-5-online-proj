import com.k4rnaj1k.DataClasses.Author;
import com.k4rnaj1k.Service.AuthorService;
import com.k4rnaj1k.Service.BookService;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDaoTest {
    static AuthorService authorService = new AuthorService();
    static BookService bookService = new BookService();

    @BeforeAll
    public static void createAuthor() throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(null);
        Author author = new Author("Conan Doyle");
        authorService.create(author);
    }

    @Test
    @Order(1)
    public void addBook() throws Exception {
        Author author = new Author("Conan Doyle");
        Author.Book book = new Author.Book("The Adventures of Sherlock Holmes");
        bookService.create(author, authorService.getDao(), book);
        Assertions.assertEquals(authorService.findAll()[0].getBooks().length, 1);
        Assertions.assertEquals(authorService.findAll()[0].getBooks()[0].getName(), book.getName());
    }

    @Test
    @Order(2)
    public void falseRemoveBook() {
        try {
            Author.Book book = new Author.Book("The adventures of");
            bookService.delete(authorService.getDao(), book);
        } catch (Exception ex) {
            Assertions.assertEquals(authorService.findAll()[0].getBooks().length, 1);
            Assertions.assertEquals(ex.getMessage(), "There's no such book.");
        }
    }

    @Test
    @Order(3)
    public void removeBook() throws Exception {
        Author.Book book = new Author.Book("The Adventures of Sherlock Holmes");
        bookService.delete(authorService.getDao(), book);
        Assertions.assertEquals(authorService.findAll()[0].getBooks().length, 0);
    }

}
