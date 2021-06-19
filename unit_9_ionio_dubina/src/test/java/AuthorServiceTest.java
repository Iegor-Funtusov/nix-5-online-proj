import author.Author;
import author.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorServiceTest {

    final static private String NAME = "Hol";
    private static final AuthorService AUTHOR_SERVICE = new AuthorService();

    @Test
    @Order(1)
    public void create() {
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setName(NAME + i);
            author.setAge(i);
            AUTHOR_SERVICE.create(author);
        }
        Author[] authors = AUTHOR_SERVICE.readAll();
        Assertions.assertTrue(authors != null && authors.length == 10);
    }

    @Test
    @Order(2)
    public void update() {
        Author[] authors = AUTHOR_SERVICE.readAll();
        Author newAuthor;
        for (int i = 0; i < authors.length; i++) {
            newAuthor = authors[i];
            newAuthor.setName(NAME + i + 10);
            AUTHOR_SERVICE.update(newAuthor);
        }
        Assertions.assertTrue(authors != null && authors.length == 10);
    }

    @Test
    @Order(3)
    public void deleteAll() {
        Author[] authors = AUTHOR_SERVICE.readAll();
        for (int i = 0; i < authors.length; i++) {
            AUTHOR_SERVICE.delete(authors[i].getId());
        }
        authors = AUTHOR_SERVICE.readAll();
        for (int i = 0; i < authors.length; i++) {
            Assertions.assertNull(authors[i]);
        }
    }

    @Test
    @Order(4)
    public void find() {
        Author author = new Author();
        author.setName("Hello");
        author.setAge(12);
        AUTHOR_SERVICE.create(author);

        Assertions.assertSame(author, AUTHOR_SERVICE.findById(author.getId()));
    }
}