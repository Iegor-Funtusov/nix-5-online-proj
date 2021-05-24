package ua.com.nkrasnovoronka.app.service;

import org.junit.jupiter.api.*;
import ua.com.nkrasnovoronka.app.model.Author;
import ua.com.nkrasnovoronka.app.model.Book;
import ua.com.nkrasnovoronka.app.service.impl.AuthorServiceImpl;
import ua.com.nkrasnovoronka.app.service.impl.BookServiceImpl;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorServiceTest {

    private static AuthorService authorService = new AuthorServiceImpl();
    private static BookService bookService = new BookServiceImpl();

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < 10; i++) {
            Author author = new Author(String.valueOf(i));
            authorService.create(author);
        }
    }

    @Test
    @Order(1)
    public void createAuthor(){
        Author author = new Author("created");
        authorService.create(author);
        Author authorByName = authorService.getAuthorByName(author.getName());
        assertEquals(author, authorByName);
    }

    @Test
    @Order(2)
    public void shouldThrowAnExceptionIfAuthorIsNull(){
        assertThrows(NullPointerException.class, () -> authorService.create(null));
    }
    @Test
    @Order(3)
    public void removeAuthorByName(){
        Author author = authorService.removeAuthorByName("1");
        assertFalse(authorService.getAllAuthors().contains(author));
    }

    @Test
    @Order(4)
    public void shouldThrowExceptionIfRemovedAuthorIsNull(){
        assertThrows(NullPointerException.class, () -> authorService.removeAuthorByName(null));
    }

    @Test
    @Order(5)
    public void getAllAuthors(){
        assertFalse(authorService.getAllAuthors().isEmpty());
    }

    @Test
    @Order(6)
    public void updateAuthorTest(){
        Author authorByName = authorService.getAuthorByName("2");
        authorByName.setName("updated");
        authorService.updateAuthor(authorByName);
        assertSame(authorByName, authorService.getAuthorByName("updated"));
    }

    @Test
    @Order(7)
    public void shouldThrowExceptionIfUpdatedAuthorIsNull(){
        assertThrows(NullPointerException.class, () -> authorService.updateAuthor(null));
    }

    @Test
    @Order(8)
    public void getAuthorByName(){
        Author author = new Author("get");
        authorService.create(author);
        assertSame(author, authorService.getAuthorByName("get"));
    }

    @Test
    @Order(9)
    public void shouldThrowExceptionIfGetAuthorIsNull(){
        assertThrows(NullPointerException.class, () -> authorService.getAuthorByName(null));
    }

    @Test
    @Order(10)
    public void getAllAuthorsBooks(){
        createAuthorWithBooks();
        Collection<Book> with_books = authorService.getAllAuthorBooks("with books");
        assertEquals(2, with_books.size());
    }

    @Test
    @Order(11)
    public void shouldThrowExceptionIfGetAllAuthorsBookAuthorNameIsNull(){
     assertThrows(NullPointerException.class, () -> authorService.getAllAuthorBooks(null));
    }

    private void createAuthorWithBooks() {
        Author author = new Author("with books");
        authorService.create(author);
        Book book1 = new Book("book1");
        book1.setAuthorId(author.getId());
        Book book2 = new Book("book2");
        book2.setAuthorId(author.getId());
        bookService.create(book1);
        bookService.create(book2);
    }

}
