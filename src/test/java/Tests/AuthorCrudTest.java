package Tests;

import DataClasses.Author;
import Services.AuthorService;
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
public class AuthorCrudTest {
    private static AuthorService authorService = new AuthorService(TestsPathsConfigs.AUTHORS_TEST_FILE.getPath());
    private static int AUTHOR_QUANTITY;             //Мб попробовать потом перенести в константы

    @BeforeAll
    public static void setUp(){
        Path pathToCSV = Paths.get(TestsPathsConfigs.AUTHORS_TEST_FILE.getPath());
        try {
            Files.deleteIfExists(pathToCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AUTHOR_QUANTITY =  (int) (Math.random() * 10) + 1; //Будет сгенерировано не менее одного автора
        try {
            for(int i = 0; i < AUTHOR_QUANTITY; i++) {
                Author author = new Author();
                author.setFirstName(DefaultValues.DEFAULT_AUTHOR_FIRSTNAME);
                author.setLastName(DefaultValues.DEFAULT_AUTHOR_LASTNAME + (i + 1));
                List<String> books = new ArrayList<>();
                books.add(DefaultValues.DEFAULT_AUTHOR_BOOK + (i + 1));
                author.setBooks(books);
                authorService.createAuthor(author);
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
    public void createAuthorTest(){
        try {
            Author authorToAdd = new Author();
            authorToAdd.setFirstName(DefaultValues.DEFAULT_AUTHOR_FIRSTNAME);
            authorToAdd.setLastName(DefaultValues.DEFAULT_AUTHOR_LASTNAME);
            List<String> books = new ArrayList<>();
            books.add(DefaultValues.DEFAULT_AUTHOR_BOOK);
            authorToAdd.setBooks(books);
            authorService.createAuthor(authorToAdd);

            Assertions.assertNotEquals(authorService.read().size(), AUTHOR_QUANTITY);
            AUTHOR_QUANTITY = authorService.read().size();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(2)
    public void readAuthorTest1(){
        try {
            String fullName = DefaultValues.DEFAULT_AUTHOR_FIRSTNAME + " " + DefaultValues.DEFAULT_AUTHOR_LASTNAME;
            Author author = authorService.findAuthorByName(fullName);
            Assertions.assertNotNull(author);
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
    public void readAuthorTest2(){
        try {
            String fullName = DefaultValues.DEFAULT_AUTHOR_FIRSTNAME + " " + DefaultValues.DEFAULT_AUTHOR_LASTNAME;
            Author author1 = authorService.findAuthorByName(fullName);
            Author author2 = authorService.read(author1.getId());

            Assertions.assertNotNull(author1);
            Assertions.assertNotNull(author2);
            Assertions.assertEquals(author1.getId(), author2.getId());

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
    public void readAllAuthorsTest(){
        try {
            List<Author> allAuthors = authorService.read();
            Assertions.assertEquals(allAuthors.size(), AUTHOR_QUANTITY);

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
    public void updateAuthorTest(){
        try {
            String fullName = DefaultValues.DEFAULT_AUTHOR_FIRSTNAME + " " + DefaultValues.DEFAULT_AUTHOR_LASTNAME;
            Author authorToUpd = authorService.findAuthorByName(fullName);
            Author prevVersion = authorService.findAuthorByName(fullName);

            authorToUpd.setLastName(DefaultValues.DEFAULT_AUTHOR_LASTNAME + Math.random());
            authorService.updateAuthor(authorToUpd);
            Author updatedAuthor = authorService.read(authorToUpd.getId());

            Assertions.assertEquals(prevVersion.getId(), updatedAuthor.getId());
            Assertions.assertNotEquals(prevVersion.getFullName(), updatedAuthor.getFullName());

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(6)
    public void deleteAuthorTest(){
        try {
            List<Author> allAuthors = authorService.read();
            int randomAuthorIndex = (int)(Math.random() * (allAuthors.size() - 1));
            Author authorToDel = allAuthors.get(randomAuthorIndex);
            authorService.deleteAuthor(authorToDel);
            Assertions.assertNotEquals(authorService.read().size(), AUTHOR_QUANTITY);

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}


