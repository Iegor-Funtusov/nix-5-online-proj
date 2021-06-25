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
    /*
    >> correct input main.Controller checks (methods list(), name(), FirstLastName() and listOfBooks())
     */
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
    public void ifNoFileExists(){
        System.out.println("Responses of program when user try to work with non-existent file with books\n" +
                "(file will be created when one book will be created)");
        LibraryService.readAllBooks();
        LibraryService.readAllBooks("name");
        LibraryService.updateBook("qq", "qq", "qq", 1);
        LibraryService.deleteBook("qq", "qq");
        System.out.println("\nResponses of program when user try to work with non-existent file with authors\n" +
                "(file will be created when one author will be created)");
        LibraryService.readAllAuthors();
        LibraryService.readAllAuthors("name");
        LibraryService.updateAuthor("qq", "qq", "qq", 1);
        LibraryService.deleteAuthor("qq");
    }

    @Test
    @Order(2)
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
    @Order(3)
    public void creatingOfBooks(){
        System.out.println("Response of program when user try to create the same record");
        for (int i = 0; i < 2; i++) {
            Book book = new Book();
            book.setName("testBook");
            book.setListOfAuthors("author author");
            LibraryService.createBook(book);
        }
        //some authors
        Book book = new Book();
        book.setName("testBook with some authors");
        book.setListOfAuthors("qq qq, pp pp");
        LibraryService.createBook(book); // record will be used in authors deleting
    }

    @Test
    @Order(4)
    public void creatingOfAuthors(){
        System.out.println("Response of program when user try to create the same record");
        for (int i = 0; i < 2; i++) {
            Author author = new Author();
            author.setFirstName("FirstName");
            author.setLastName("LastName");
            author.setListOfBooks("Book");
            LibraryService.createAuthor(author);
        }

        // some books
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName with some books");
        author.setListOfBooks("Book1, Book2");
        LibraryService.createAuthor(author);

        // without books (author exists only in file of authors)
        author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName without books");
        author.setListOfBooks("");
        LibraryService.createAuthor(author);
    }

    @Test
    @Order(5)
    public void findingAllAuthorsOfCertainBook(){
        System.out.println("Response of program when user try to find non-existent book");
        LibraryService.readAllBooks("dssdfsdf");
    }

    @Test
    @Order(6)
    public void findingAllBooksOfCertainAuthor(){
        System.out.println("Response of program when user try to find non-existent book");
        LibraryService.readAllAuthors("qqqqq");
    }

    @Test
    @Order(7)
    public void updatingOfBook(){
        System.out.println("Response of program when user try to update non-existent book");
        LibraryService.updateBook("qq", "qq", "qq", 1);

        System.out.println("Response of program when user try to update book but such book already exists");
        LibraryService.updateBook("testBook", "author author", "testBook", 1);

        //Check that in file with authors data was changed too when user changed it in file with books
        LibraryService.updateBook("testBook", "author author", "testBookUPDATED", 1);

        Assertions.assertTrue(findAuthors("author", "author", "testBookUPDATED"));
    }

    @Test
    @Order(8)
    public void updatingOfAuthor(){
        System.out.println("Response of program when user try to update non-existent author");
        LibraryService.updateAuthor("qq", "qq qq", "qq", 1);

        System.out.println("Response of program when user try to update author but such book already exists");
        LibraryService.updateAuthor("testBook", "author author", "testBook", 1);
        LibraryService.updateAuthor("testBook", "author author", "author author", 2);

        //Check that in file with books data was changed too when user changed it in file with authors
        LibraryService.updateAuthor("Book1", "FirstName1 LastName1", "Book1UPDATED", 1);
        LibraryService.updateAuthor("Book1UPDATED", "FirstName1 LastName1", "FirstName1UPDATED LastName1UPDATED", 2);

        Assertions.assertTrue(findBooks("Book1UPDATED", "FirstName1UPDATED LastName1UPDATED"));
    }

    @Test
    @Order(8)
    public void deletingOfBook(){
        System.out.println("Response of program when user try to delete non-existent book or deleted one");
        LibraryService.deleteAuthor("qqq qqq");
    }

    @Test
    @Order(9)
    public void deletingOfAuthor(){
        System.out.println("Response of program when user try to delete non-existent author or deleted");
        LibraryService.deleteAuthor("qqq qqq");
        // check when book has some authors: if deleted only one of them, book will not be deleted, else - will be deleted
        LibraryService.deleteAuthor("pp pp");
        Assertions.assertTrue(findBooks("testBook with some authors", "qq qq"));
        LibraryService.deleteAuthor("qq qq");
        Assertions.assertFalse(findBooks("testBook with some authors", "qq qq"));
    }

    private boolean findBooks(String name, String authors){
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[1].equalsIgnoreCase(name) && r[2].contains(authors) && !r[3].contains("INVISIBLE")){
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
