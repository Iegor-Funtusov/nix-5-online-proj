package ua.com.ionio;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.*;
import ua.com.ionio.dao.DaoBook;
import ua.com.ionio.entity.Book;
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
public class BookTest {
    private static DaoBook daoBook;
    private static CreateFile createFile;

    @BeforeAll
    private static void setUp(){
        daoBook = new DaoBook();
        createFile = new CreateFile();
        createFile.delete();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle("book" + i);
            List<String> authors = new ArrayList<String>();
            authors.add("1"+ i);
            authors.add("2");
            book.setListAuthors(authors);
            daoBook.create(book);
        }
    }

    @Test
    @Order(1)
    public void createBook(){
        List<String[]> allBooks = null;
        List<String> authors = new ArrayList<String>();
        authors.add("1");
        authors.add("2");
        Book book = new Book( "Hols", authors);
        daoBook.create(book);
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_BOOKS.getPath()))) {
            allBooks = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        assertEquals(11, allBooks.size());
    }

    @Test
    @Order(2)
    public void readAllBooks() {
        List<String[]> allBooks = null;
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_BOOKS.getPath()))) {
            allBooks = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        assertEquals(11, allBooks.size());
    }

    @Test
    @Order(3)
    public void readBookById() {
        List<String> authors = new ArrayList<String>();
        authors.add("10");
        authors.add("2");
        Book book = daoBook.findBookById("1");
        assertEquals("1", book.getId());
        assertEquals("book0", book.getTitle());
        assertEquals(authors, book.getListAuthors());
        assertTrue(book.isIsvisableBook());
    }

    @Test
    @Order(4)
    public void deleteAuthorById() {
        daoBook.delete("1");
        List<String[]> allBooks = null;
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_BOOKS.getPath()))) {
            allBooks = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        assertEquals(11, allBooks.size());
        assertEquals(10, daoBook.findAllBooks().size());
    }

    @Test
    @Order(5)
    public void updateBook(){
        Book beforeUpdate = daoBook.findBookById("2");
        Book book = new Book();
        book.setId("2");
        book.setTitle("Updated");
        book.setListAuthors(beforeUpdate.getListAuthors());
        book.setIsvisableBook(beforeUpdate.isIsvisableBook());
        daoBook.update(book);
        Book afterUpdate = daoBook.findBookById("2");
        assertEquals("Updated", afterUpdate.getTitle());
    }

    @AfterAll
    private static void delete(){
        File dir = new File("books.csv");
        dir.delete();
    }
}
