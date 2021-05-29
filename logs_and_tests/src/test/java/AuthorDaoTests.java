import com.k4rnaj1k.DataClasses.Author;
import com.k4rnaj1k.Service.AuthorService;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorDaoTests {
    private static final AuthorService authorDao = new AuthorService();

    @BeforeAll
    static void disableExceptionsStackTrace() {
        Thread.setDefaultUncaughtExceptionHandler(null);
    }

    @Test
    @Order(1)
    public void createAuthor() throws Exception {
        Author author = new Author("Orwell");
        authorDao.create(author);
        Assertions.assertEquals(authorDao.findAll().length, 1);
    }

    @Test
    @Order(2)
    public void removeAuthor() throws Exception {
        Author author = new Author("Orwell");
        authorDao.delete(author);
        Assertions.assertEquals(authorDao.findAll().length, 0);
    }

    @Test
    @Order(3)
    public void removefAuthor(){
        try {
            Author author = new Author("Conan Doyle");
            authorDao.delete(author);
        } catch (Exception ex) {
            Assertions.assertEquals(authorDao.getDao().findAll().length, 0);
        }
    }
}
