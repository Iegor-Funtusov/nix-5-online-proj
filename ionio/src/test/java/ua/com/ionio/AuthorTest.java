package ua.com.ionio;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.*;
import ua.com.ionio.dao.DaoAuthor;
import ua.com.ionio.entity.Author;
import ua.com.ionio.file.CreateFile;
import ua.com.ionio.file.FileType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorTest {
    private static DaoAuthor daoAuthor;
    private static CreateFile createFile;

    @BeforeAll
    private static void setUp(){
        daoAuthor = new DaoAuthor();
        createFile = new CreateFile();
        createFile.delete();
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setFirstname("author" + i);
            author.setLastname("author" + i);
            List<String> books = new ArrayList<String>();
            books.add("1"+ i);
            books.add("2");
            author.setListBooks(books);
            daoAuthor.create(author);
        }
    }

    @Test
    @Order(1)
    public void createAuthor(){
        List<String[]> allAuthors = null;
        List<String> books = new ArrayList<String>();
        books.add("1");
        books.add("2");
        Author author = new Author("Ivan", "Hols", books);
        daoAuthor.create(author);
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_AUTHOR.getPath()))) {
            allAuthors = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        assertEquals(11, allAuthors.size());
    }

    @Test
    @Order(2)
    public void readAllAuthor() {
        List<String[]> allAuthors = null;
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_AUTHOR.getPath()))) {
            allAuthors = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        assertEquals(11, allAuthors.size());
    }

    @Test
    @Order(3)
    public void readAuthorById() {
        List<String> books = new ArrayList<String>();
        books.add("10");
        books.add("2");
        Author author = daoAuthor.findAuthorById("1");
        assertEquals("1", author.getId());
        assertEquals("author0", author.getFirstname());
        assertEquals("author0", author.getLastname());
        assertEquals(books, author.getListBooks());
        assertTrue(author.isIsvisableAuthor());
    }

    @Test
    @Order(4)
    public void deleteAuthorById() {
        daoAuthor.delete("1");
        List<String[]> allAuthors = null;
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_AUTHOR.getPath()))) {
            allAuthors = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        assertEquals(11, allAuthors.size());
        assertEquals(10, daoAuthor.findAllAuthors().size());
    }

    @Test
    @Order(5)
    public void updateAuthor(){
        Author beforeUpdate = daoAuthor.findAuthorById("2");
        Author author = new Author();
        author.setId("2");
        author.setFirstname("Updated");
        author.setLastname(beforeUpdate.getLastname());
        author.setListBooks(beforeUpdate.getListBooks());
        author.setIsvisableAuthor(beforeUpdate.isIsvisableAuthor());
        daoAuthor.update(author);
        Author afterUpdate = daoAuthor.findAuthorById("2");
        assertEquals("Updated", afterUpdate.getFirstname());
    }

    @AfterAll
    private static void delete(){
     File dir = new File("authors.csv");
     dir.delete();
    }
 }
