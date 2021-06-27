import org.junit.jupiter.api.*;
import ua.com.alevel.app.csv.CsvDb;
import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.entity.Book;
import ua.com.alevel.app.service.AuthorService;
import ua.com.alevel.app.service.BookService;
import ua.com.alevel.app.service.impl.AuthorServiceImpl;
import ua.com.alevel.app.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CRUDTest {

    private static final CsvDb csvDb = new CsvDb();
    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final BookService bookService = new BookServiceImpl();

    @BeforeAll
    public static void start(){
        csvDb.initFiles();
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setFirstName("first_name" + i);
            author.setLastName("last_name" + i);
            author.setVisibleFlag(true);
            authorService.create(author);
        }
        Assertions.assertTrue(authorService.read().size()!=0);
    }

    @Test
    @Order(1)
    public void readAuthors() {
        int cnt = 0;
        List<Author> authors = authorService.read();
        for (Author author : authors) {
            if(author.getId()!=null){
                cnt++;
            }
        }
        Assertions.assertEquals(10, cnt);
    }

    @Test
    @Order(2)
    public void createAuthor() {
        Author author = new Author();
        author.setFirstName("FIRST");
        author.setLastName("LAST");
        author.setVisibleFlag(true);
        authorService.create(author);
        int cnt = 0;
        List<Author> authors = authorService.read();
        for (Author a : authors) {
            if(a.getId()!=null){
                cnt++;
            }
        }
        Assertions.assertEquals(11, cnt);
    }
    @Test
    @Order(3)
    public void readAuthorById(){
        Author author = authorService.read().get(1);
        String aId = author.getId();
        author = authorService.read(aId);
        Assertions.assertNotNull(author);
    }

    @Test
    @Order(4)
    public void updateAuthor(){
        Author author = authorService.read().get(0);
        String id = author.getId();
        author.setFirstName("UPDATED");
        authorService.update(author);
        author = authorService.read(id);
        Assertions.assertEquals(author.getFirstName(), "UPDATED");

    }

    @Test
    @Order(5)
    public void deleteAuthor() {
        Author author = authorService.read().get(0);
        String id = author.getId();
        authorService.delete(id);
        author = authorService.read().get(0);
        Assertions.assertFalse(author.getVisibleFlag());
    }

    @Test
    @Order(6)
    public void createBooks() {
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle("TITLE" + i);
            book.setVisibleFlag(true);
            List<String> list = new ArrayList<>();
            list.add(authorService.read().get(i).getId());
            book.setAuthors(list);
            bookService.create(book,list);
        }
        int bookAmount = 0;
        int authorsAmount = 0;
        List<Book> book = bookService.read();
        for (Book b : book) {
            if(b.getId()!=null){
                bookAmount++;
                if(b.getAuthors().get(0)!= null){
                    authorsAmount++;
                }
            }
        }
        Assertions.assertTrue(bookAmount == 10 && authorsAmount == 10 );
    }

    @Test
    @Order(7)
    public void readBooks() {
        int bookAmount = 0;
        List<Book> books = bookService.read();
        for (Book b : books) {
            if(b.getId()!=null){
                bookAmount++;
            }
        }
        Assertions.assertEquals(10, bookAmount);
    }

    @Test
    @Order(8)
    public void readBookById() {
        Book book = bookService.read().get(1);
        String aId = book.getId();
        book = bookService.read(aId);
        Assertions.assertNotNull(book);
    }

    @Test
    @Order(9)
    public void updateBook(){
        Book book = bookService.read().get(1);
        String id = book.getId();
        book.setTitle("UPDATED");
        bookService.update(book);
        book = bookService.read(id);
        Assertions.assertEquals(book.getTitle(), "UPDATED");
    }

    @Test
    @Order(10)
    public void deleteBook() {
        Book book = bookService.read().get(1);
        String aId = book.getId();
        bookService.delete(aId);
        book = bookService.read().get(1);
        Assertions.assertFalse(book.getVisibleFlag());
    }

    @Test
    @Order(11)
    public  void  getBooksBYAuthor(){
        Author author = authorService.read().get(1);
        String id = author.getId();
        List<Book> books = bookService.readByAuthor(id);
        Assertions.assertNotNull(books.get(0));
    }

    @Test
    @Order(12)
    public  void  getAuthorsByBook(){
        Book book = bookService.read().get(1);
        String aId = book.getId();
        List<Author> authors = authorService.readByBook(aId);
        Assertions.assertNotNull(authors.get(0));
    }
}
