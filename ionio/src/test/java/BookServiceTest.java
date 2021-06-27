import com.k4rnaj1k.BookStoreController;
import com.k4rnaj1k.Dao.AuthorDao;
import com.k4rnaj1k.Dao.BookDao;
import com.k4rnaj1k.Dao.Impl.AuthorDaoImpl;
import com.k4rnaj1k.Dao.Impl.BookDaoImpl;
import com.k4rnaj1k.Service.BookStoreService;
import com.k4rnaj1k.Service.Impl.BookStoreImpl;
import com.k4rnaj1k.entities.Author;
import com.k4rnaj1k.entities.Book;
import com.opencsv.CSVWriter;
import org.junit.jupiter.api.*;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {
    private static BookStoreService service = new BookStoreImpl();
    private static final AuthorDao authorDao = new AuthorDaoImpl();
    private static final BookDao bookDao = new BookDaoImpl();

    @BeforeAll
    public static void removeCSV(){//Особо ни на что не влияет
        try{
            Files.deleteIfExists(Paths.get("authors.csv"));
            Files.deleteIfExists(Paths.get("books.csv"));
        }catch (IOException e){
            System.out.println("An exception occured during the removal of the csv files.");
        }
    }

    @Test
    @Order(1)
    public void createAuthor(){
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
        }

    @Test
    @Order(2)
    public void createBook(){
        service.createBook(new Scanner("The Little Prince\n1\nAntoine\nSaint-Exupery"));
        Assertions.assertEquals(bookDao.findAll().stream().filter(book -> book.getVisible().equals("true")).toArray().length, 4);
        Assertions.assertNotNull(bookDao.find("The Little Prince"));
    }


    @Test
    @Order(3)
    public void removeBook(){
        service.removeBook(new Scanner("The Little Prince"));
        Assertions.assertEquals(bookDao.findAll().stream().filter(book -> book.getVisible().equals("true")).toArray().length, 3);

    }

    @Test
    @Order(4)
    public void getAllBooks(){
        Assertions.assertEquals(bookDao.findAll().size(), 4);
    }

    @Test
    @Order(5)
    public void getAllAuthors(){
        Assertions.assertEquals(authorDao.findAll().size(),3);
    }

    @Test
    @Order(6)
    public void getBooksByAuthor(){
        Author author = new Author();
        author.setName("Antoine");
        author.setSurname("Saint-Exupery");
        Assertions.assertNotEquals(authorDao.find(author), null);
        Assertions.assertEquals(authorDao.find(author).getBooklist(), "The Little Prince; ");
    }

    @Test
    @Order(7)
    public void getAuthorsByBook(){
        Assertions.assertNotEquals(bookDao.find("The Little Prince"), null);
        Book book = bookDao.find("The Little Prince");
        Assertions.assertEquals(book.getAuthors(), "Antoine Saint-Exupery; ");
    }

    @Test
    @Order(8)
    public void updateAuthor(){
        Author author = new Author();
        author.setName("Some");
        author.setSurname("Author");
        author.setBooklist("Some book; ");
        authorDao.create(author);
        Author updated = new Author();
        updated.setName("Updated");
        updated.setSurname("Author");
        authorDao.update(authorDao.find(author), updated);
        Assertions.assertNull(authorDao.find(author));
        Assertions.assertNotEquals(authorDao.find(updated), null);
        Assertions.assertEquals(authorDao.findAll().size(), 4);
    }

    @Test
    @Order(9)
    public void removeAuthor(){
        Author current = new Author();
        current.setName("Updated");
        current.setSurname("Author");
        current.setVisible("false");
        authorDao.update(authorDao.find(current), current);
        Assertions.assertEquals(authorDao.findAll().stream().filter(author -> author.getVisible().equals("true")).toArray().length, 3);
    }

    @Test
    @Order(10)
    public void updateBook(){
        Book current = bookDao.find("The Little Prince");
        service.updateBook(new Scanner("The Little Prince\nThe little Prince\n1\nAntoine\nSaint-Exupery"));
        Assertions.assertNull(bookDao.find("The Little Prince"));
        Assertions.assertNotEquals(bookDao.find("The little Prince"), null);
        service.updateBook(new Scanner("The little Prince\nThe Little Prince\n1\nAntoine\nSaint-Exupery"));
        Assertions.assertNull(bookDao.find("The little Prince"));
    }
}
