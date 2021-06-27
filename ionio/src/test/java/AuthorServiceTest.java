import org.junit.jupiter.api.*;
import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.service.AuthorService;
import ua.nkrasnovoronka.service.BookService;
import ua.nkrasnovoronka.service.impl.AuthorServiceImpl;
import ua.nkrasnovoronka.service.impl.BookServiceImpl;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthorServiceTest {

    private static AuthorService authorService = new AuthorServiceImpl();
    private static BookService bookService = new BookServiceImpl();

    @BeforeAll
    public static void setUp() {
        for (int i = 1; i <= 10; i++) {
            Author author = new Author();
            author.setFirstName("FirstName " + i);
            author.setLastName("LastName " + i);
            authorService.create(author);
        }
    }

    @Test
    @Order(1)
    void createAuthor() {
        Author author = new Author();
        author.setId(11L);
        author.setFirstName("Create author test");
        author.setLastName("Create author test");
        authorService.create(author);
        Author authorByName = authorService.getAuthorById(11L);
        Assertions.assertEquals(author, authorByName);
    }

    @Test
    @Order(2)
    void shouldThrowAnExceptionIfAuthorIsNull() {
        assertThrows(NullPointerException.class, () -> authorService.create(null));
    }

    @Test
    @Order(3)
    void shouldThrowExceptionIfRemovedAuthorNotExists() {
        assertThrows(Exception.class, () -> authorService.removeAuthorById(12L));
    }

    @Test
    @Order(4)
    void getAllAuthors() {
        assertFalse(authorService.getAllAuthors().isEmpty());
    }

    @Test
    @Order(5)
    void updateAuthorTest() {
        Author authorByName = authorService.getAuthorById(1L);
        authorByName.setFirstName("updated");
        authorService.updateAuthor(1L, authorByName);
        assertEquals(authorByName.getFirstName(), authorService.getAuthorById(1L).getFirstName());
    }

    @Test
    @Order(6)
    void shouldThrowExceptionIfUpdatedAuthorNotExists() {
        assertThrows(Exception.class, () -> authorService.updateAuthor(12L, null));
    }

    @Test
    @Order(7)
    void getAuthorById() {
        Author author = new Author();
        author.setId(12L);
        author.setFirstName("get");
        authorService.create(author);
        Assertions.assertEquals(author.getFirstName(), authorService.getAuthorById(12L).getFirstName());
    }

    @Test
    @Order(8)
    void shouldThrowExceptionIfGetAuthorIsNull() {
        assertThrows(NullPointerException.class, () -> authorService.getAuthorById(null));
    }

    @Test
    @Order(9)
    void shouldThrowExceptionIfGetAllAuthorsBookAuthorNameIsNull() {
        assertThrows(NullPointerException.class, () -> authorService.getAllAuthorBooks(null));
    }

    @Test
    @Order(10)
    void removeAuthorById() {
        authorService.removeAuthorById(1L);
        boolean b = authorService.getAllAuthors().stream().anyMatch(author -> author.getId().equals(1L));
        assertFalse(b);
    }

    @Test
    @Order(11)
    void getAllAuthorsBooks() {
        createAuthorWithBooks();
        Collection<Book> with_books = authorService.getAllAuthorBooks(13L);
        Assertions.assertEquals(1, with_books.size());
    }

    private void createAuthorWithBooks() {
        Author author = new Author();
        author.setId(13L);
        author.setFirstName("book");
        authorService.create(author);
        Book book1 = new Book();
        book1.setBookTitle("get");
        book1.addAuthorToBook(author.getId());
        bookService.create(book1);
    }
}
