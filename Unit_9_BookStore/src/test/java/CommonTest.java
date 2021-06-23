import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import data.Author;
import data.Book;
import org.junit.jupiter.api.*;
import services.LibraryService;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static services.AuthorService.AUTHORS;
import static services.BookService.BOOKS;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CommonTest {
    @Test
    @Order(1)
    public void createEntities(){
        Path path = Paths.get(BOOKS);
        Path path1 = Paths.get(AUTHORS);
        try {
            Files.delete(path);
            Files.delete(path1);
        } catch (IOException e) { }

        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setName("testBook" + i);
            book.setListOfAuthors("author author" + i);
            LibraryService.createBook(book);

            Author author = new Author();
            author.setFirstName("FirstName" + i);
            author.setLastName("LastName" + i);
            author.setListOfBooks("Book"+i);
            LibraryService.createAuthor(author);
        }
        int counter = 0;
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                counter++;
            }
            Assertions.assertTrue(counter == 21);

        } catch (IOException | CsvException e) { }
        counter = 0;
        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                counter++;
            }
            Assertions.assertTrue(counter == 21);

        } catch (IOException | CsvException e) { }
    }

    @Test
    @Order(2)
    public void creatingOfBooks(){
        
    }
}
