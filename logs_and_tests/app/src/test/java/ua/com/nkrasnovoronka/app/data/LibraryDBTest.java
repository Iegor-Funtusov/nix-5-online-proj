package ua.com.nkrasnovoronka.app.data;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import ua.com.nkrasnovoronka.app.model.Author;
import ua.com.nkrasnovoronka.app.model.Book;
import ua.com.nkrasnovoronka.app.service.AuthorService;
import ua.com.nkrasnovoronka.app.service.BookService;
import ua.com.nkrasnovoronka.app.service.impl.AuthorServiceImpl;
import ua.com.nkrasnovoronka.app.service.impl.BookServiceImpl;

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

}
