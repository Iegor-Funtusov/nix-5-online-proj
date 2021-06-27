import com.k4rnaj1k.BookStoreController;
import com.k4rnaj1k.Dao.AuthorDao;
import com.k4rnaj1k.Dao.BookDao;
import com.k4rnaj1k.Dao.Impl.AuthorDaoImpl;
import com.k4rnaj1k.Dao.Impl.BookDaoImpl;
import com.k4rnaj1k.Service.BookStoreService;
import com.k4rnaj1k.Service.Impl.BookStoreImpl;
import com.k4rnaj1k.entities.Author;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Scanner;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {
    private static BookStoreService service = new BookStoreImpl();
    private static final AuthorDao authorDao = new AuthorDaoImpl();
    private static final BookDao bookDao = new BookDaoImpl();


    @BeforeAll
    public static void createAuthor(){
        BookStoreController.initCSV("authors.csv", new String[]{"id", "name", "surname", "books", "visible"});
        BookStoreController.initCSV("books.csv", new String[]{"id", "name", "authors", "visible"});
        Author[] authors = new Author[]{
                new Author("Conan", "Doyle", "Sherlock Holmes"),
                new Author("Robert", "Martin", "Designing Object-Oriented C++ Applications Using the Booch Method; " +
                        "Agile Software Development, Principles, Patterns, and Practices"),
                new Author("Antoine", "Saint-Exupery", "The Little Prince")
        };
        for (int i = 0; i < authors.length; i++) {
            String[] add = new String[]{authors[i].getName()+"\n"+authors[i].getSurname()+"\n"
                    +authors[i].getBooklist().split("; ").length+"\n"};
            Arrays.stream(authors[i].getBooklist().split("; ")).forEach(book -> add[0] = add[0].concat(book+"\n"));
            service.createAuthor(new Scanner(add[0]));
        }
        Assertions.assertEquals(authorDao.findAll().size(),3);
        service.createAuthor(new Scanner("Conan\nDoyle"));
    }
    @Test
    @Order(1)
    public void getBooksAndAuthors(){
        Assertions.assertEquals(authorDao.findAll().size(),3);
        Assertions.assertEquals(bookDao.findAll().size(), 4);
    }

    @Test
    @Order(2)
    public void removeAndCreateBook(){
        service.removeBook(new Scanner("The Little Prince"));
        Assertions.assertEquals(bookDao.findAll().stream().filter(book -> book.getVisible().equals("true")).toArray().length, 3);
        service.createBook(new Scanner("The Little Prince\n1\nAntoine\nSaint-Exupery"));
        Assertions.assertEquals(bookDao.findAll().stream().filter(book -> book.getVisible().equals("true")).toArray().length, 4);
    }
    //void createBook(Scanner s);
    //
    //    void createAuthor(Scanner s);
    //
    //    void getBooksAuthors(Scanner s);
    //
    //    void getAuthorsBooks(Scanner s);
    //
    //    void getBooks();
    //
    //    void getAuthors();
    //
    //    void updateAuthor(Scanner s);
    //
    //    void updateBook(Scanner s);
    //
    //    void removeAuthor(Scanner s);
    //
    //    void removeBook(Scanner s);
}
