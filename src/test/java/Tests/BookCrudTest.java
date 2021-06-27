package Tests;

import DataClasses.Book;
import Services.BookService;
import TestsConfigs.TestsPathsConfigs;
import TestsConstants.DefaultValues;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookCrudTest {
    private static final BookService bookService = new BookService(TestsPathsConfigs.BOOKS_TEST_FILE.getPath());
    private static int BOOK_QUANTITY;


    @BeforeAll
    public static void setUp(){
        //Удаляю тестовый файл, если вдруг он был создан во время предыдущих запусков тестов
        Path pathToCSV = Paths.get(TestsPathsConfigs.BOOKS_TEST_FILE.getPath());
        try {
            Files.deleteIfExists(pathToCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BOOK_QUANTITY = (int) (Math.random() * 10) + 1;     //Будет сгенерированно не менее одной книги

            try {
                for(int i = 0; i < BOOK_QUANTITY; i++) {
                    Book book = new Book();
                    book.setBookName(DefaultValues.DEFAULT_BOOK_NAME + (i + 1));
                    List<String> authors = new ArrayList<>();
                    authors.add(DefaultValues.DEFAULT_BOOK_AUTHOR + (i + 1));
                    book.setAuthors(authors);
                    bookService.createBook(book);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvException e) {
                e.printStackTrace();
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
    }


    @Test
    @Order(1)
    public void createBookTest(){
        try {
            Book bookToAdd = new Book();
            bookToAdd.setBookName(DefaultValues.DEFAULT_BOOK_NAME);
            List<String> authors = new ArrayList<>();
            authors.add(DefaultValues.DEFAULT_BOOK_AUTHOR);
            bookToAdd.setAuthors(authors);
            bookService.createBook(bookToAdd);

            Book check = bookService.findBookByName(bookToAdd.getBookName());
            Assertions.assertNotNull(check);
            Assertions.assertEquals(check.getId(), bookToAdd.getId());
            BOOK_QUANTITY = bookService.read().size();

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void readBookTest1(){
        try {
            Book book = bookService.findBookByName(DefaultValues.DEFAULT_BOOK_NAME);
            Assertions.assertNotNull(book);

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(3)
    public void readBookTest2(){
        try {
            Book book1 = bookService.findBookByName(DefaultValues.DEFAULT_BOOK_NAME);
            Book book2 = bookService.read(book1.getId());

            Assertions.assertNotNull(book1);
            Assertions.assertNotNull(book2);
            Assertions.assertEquals(book1.getId(), book2.getId());

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(4)
    public void readAllBooksTest(){
        try {
            List<Book> allBooks = bookService.read();
            Assertions.assertEquals(allBooks.size(), BOOK_QUANTITY);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(5)
    public void updateBookTest(){
        try {
            Book bookToUpd = bookService.findBookByName(DefaultValues.DEFAULT_BOOK_NAME);
            Book prevVersion = bookService.findBookByName(DefaultValues.DEFAULT_BOOK_NAME);

            bookToUpd.setBookName(DefaultValues.DEFAULT_BOOK_NAME + Math.random());
            bookService.updateBook(bookToUpd);
            Book updatedBook = bookService.read(bookToUpd.getId());

            Assertions.assertEquals(prevVersion.getId(), updatedBook.getId());
            Assertions.assertNotEquals(prevVersion.getBookName(), updatedBook.getBookName());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(6)
    public void deleteBookTest(){
        try {
            List<Book> allBooks = bookService.read();
            int randomBookIndex = (int)(Math.random() * (allBooks.size() - 1));
            Book bookToDel = allBooks.get(randomBookIndex);
            bookService.deleteBook(bookToDel);
            Assertions.assertNotEquals(bookService.read().size(), BOOK_QUANTITY);

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

}










