import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import data.Author;
import data.Book;
import org.junit.jupiter.api.*;
import services.LibraryService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static services.AuthorService.AUTHORS;
import static services.BookService.BOOKS;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class crudTest {

    @BeforeAll
    public static void createNewFiles(){
        Path path = Paths.get(BOOKS);
        Path path1 = Paths.get(AUTHORS);
        try {
            Files.delete(path);
            Files.delete(path1);
        } catch (IOException e) { }
        Assertions.assertFalse(Files.exists(path) && Files.exists(path1));
    }

    @Test
    @Order(1)
    public void createBook(){
        Book book = new Book();
        book.setName("testBook");
        String authors = "author1 author1, author2 author2";
        book.setListOfAuthors(authors);
        LibraryService.createBook(book);
        boolean flag1 = false;
        if(new File(BOOKS).exists()){
            flag1 = true;
        }
        Assertions.assertTrue(flag1 && findBooks("testBook", "author1 author1, author2 author2"));
    }

    @Test
    @Order(2)
    public void readAllBooks(){
        int counter = 0;
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                counter++;
            }
            Assertions.assertTrue(counter == 2);
        } catch (IOException | CsvException e) { }
    }

    @Test
    @Order(3)
    public void readAuthorsOfBook(){
        String[] authors;
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[1].equalsIgnoreCase("testBook")){

                    authors = r[2].split(",");
                    Assertions.assertTrue(authors.length == 2);
                }
            }
        } catch (IOException | CsvException e) { }
    }

    @Test
    @Order(4)
    public void updateBook(){
        LibraryService.updateBook("testBook", "author1 author1, author2 author2", "testTestBook", 1);
        Assertions.assertTrue(findBooks("testTestBook", "author1 author1, author2 author2"));
    }

    @Test
    @Order(5)
    public void deleteBook(){
        LibraryService.deleteBook("testTestBook", "author author");
        Assertions.assertFalse(findBooks("testTestBook", "author author"));
    }

    @Test
    @Order(6)
    public void createAuthor(){
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setListOfBooks("Book1, Book2, Book3");
        LibraryService.createAuthor(author);
        Assertions.assertTrue(findAuthors("FirstName", "LastName", "Book1, Book2, Book3"));
    }

    @Test
    @Order(7)
    public void readAllAuthors(){
        int counter = 0;
        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(!r[3].equals("INVISIBLE"))
                    counter++;
            }
            System.out.println(counter);
            Assertions.assertTrue(counter == 4);
        } catch (IOException | CsvException e) { }
    }

    @Test
    @Order(8)
    public void readAuthorsBooks(){
        String books = "";
        int counter = 0;
        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[1].contains("FirstName") && r[2].contains("LastName")){
                    books += r[3];
                }
            }
            String[] arrBooks = books.split(",");
            for (int i = 0; i < arrBooks.length; i++) {
                counter++;
            }
            Assertions.assertTrue(counter == 3);
        } catch (IOException | CsvException e) { }
    }

    @Test
    @Order(9)
    public void updateAuthor(){
        LibraryService.updateAuthor("Book1", "FirstName LastName", "testTestBook", 1);
        LibraryService.updateAuthor("", "FirstName LastName", "author author", 2);
        Assertions.assertTrue(findAuthors("author", "author", "testTestBook, Book2, Book3"));
    }

    @Test
    @Order(10)
    public void deleteAuthor(){
        LibraryService.deleteAuthor("author author");
        Assertions.assertFalse(findAuthors("author", "author", "testTestBook, Book2, Book3"));

    }

    @Test
    @Order(11)
    public void checkExceptionWithNull(){
        assertThrows(NullPointerException.class, () -> LibraryService.createBook(null));
        assertThrows(NullPointerException.class, () -> LibraryService.createAuthor(null));
    }

    private boolean findBooks(String name, String authors){
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[1].equalsIgnoreCase(name) && r[2].equalsIgnoreCase(authors) && !r[3].contains("INVISIBLE")){
                    return true;
                }
            }
        } catch (IOException | CsvException e) { }
        return false;
    }

    private boolean findAuthors(String firstName, String lastName, String books){
        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[1].equalsIgnoreCase(firstName) && r[2].equalsIgnoreCase(lastName)
                        && r[3].equalsIgnoreCase(books) && !r[4].contains("INVISIBLE")){
                    return true;
                }
            }
        } catch (IOException | CsvException e) { }
        return false;
    }
}
