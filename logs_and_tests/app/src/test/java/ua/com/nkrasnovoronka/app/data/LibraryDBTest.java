package ua.com.nkrasnovoronka.app.data;


import org.junit.jupiter.api.*;
import ua.com.nkrasnovoronka.app.model.Author;
import ua.com.nkrasnovoronka.app.model.Book;
import ua.com.nkrasnovoronka.app.service.AuthorService;
import ua.com.nkrasnovoronka.app.service.BookService;
import ua.com.nkrasnovoronka.app.service.impl.AuthorServiceImpl;
import ua.com.nkrasnovoronka.app.service.impl.BookServiceImpl;
import ua.com.nkrasnovoronka.lib.Crud;

import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibraryDBTest {
    private static LibraryDB libraryDB = LibraryDB.getInstance();
    private static AuthorService authorService = new AuthorServiceImpl();
    private static BookService bookService = new BookServiceImpl();

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < 10; i++) {
            Author author = new Author(String.valueOf(i));
            authorService.create(author);
            Book book = new Book(String.valueOf(i));
            book.setAuthorId(author.getId());
            bookService.create(book);
        }
    }

    @Test
    @Order(1)
    public void getAllBooksFromDB(){
        Collection<Book> allBooks = libraryDB.readAllBooks();
        assertEquals(10, allBooks.size());
    }

    @Test
    @Order(2)
    public void getAllAuthors(){
        Collection<Author> authors = libraryDB.readAllAuthors();
        assertEquals(10, authors.size());
    }
    @Test
    @Order(3)
    public void getAllBooksByAuthorName(){
        Collection<Book> allBooksByAuthorsName = libraryDB.getAllBooksByAuthorsName("1");
        assertEquals(1, allBooksByAuthorsName.size());
    }

    @Test
    @Order(4)
    public void shouldThrowExceptionIfGetAllBooksAuthorNameIsNull(){
        assertThrows(RuntimeException.class, () -> libraryDB.getAllBooksByAuthorsName(null));
    }

    @Test
    @Order(5)
    public void shouldThrowExceptionIfAuthorNameIsNull(){
        assertThrows(NullPointerException.class, () -> libraryDB.getAuthorByName(null));
    }

    @Test
    @Order(6)
    public void shouldThrowExceptionIfAuthorDoesntExists(){
        assertThrows(RuntimeException.class, () -> libraryDB.getAuthorByName("doesn`t exists"));
    }

    @Test
    @Order(7)
    public void getBookByName(){
        Book bookByName = libraryDB.getBookByName("1");
        assertNotNull(bookByName);
        assertEquals("1", bookByName.getName());
    }
    @Test
    @Order(8)
    public void shouldThrowExceptionIfBookDoesntExists(){
        assertThrows(RuntimeException.class, () -> libraryDB.getBookByName("doesn`t exists"));
    }

    @Test
    @Order(9)
    public void shouldThrowExceptionIfBookIsNull(){
        assertThrows(NullPointerException.class, () -> libraryDB.getBookByName(null));
    }

    @Test
    @Order(10)
    public void removeBook(){
        Book book = libraryDB.removeBook("2");
        Collection<Book> books = libraryDB.readAllBooks();
        assertFalse(books.contains(book));
    }

    @Test
    @Order(11)
    public void shouldThrowExceptionIfRemovedBookIsNull(){
        assertThrows(NullPointerException.class, () -> libraryDB.removeBook(null));
    }




}
