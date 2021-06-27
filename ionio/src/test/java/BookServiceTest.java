import com.k4rnaj1k.Dao.AuthorDao;
import com.k4rnaj1k.Dao.BookDao;
import com.k4rnaj1k.Dao.Impl.AuthorDaoImpl;
import com.k4rnaj1k.Dao.Impl.BookDaoImpl;
import com.k4rnaj1k.Service.BookStoreService;
import com.k4rnaj1k.Service.Impl.BookStoreImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Scanner;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {
    private static final BookStoreService service = new BookStoreImpl();
    private static final AuthorDao authorDao = new AuthorDaoImpl();
    private static final BookDao bookDao = new BookDaoImpl();


    @BeforeAll
    public static void createAuthor(){
        service.createAuthor(new Scanner("Some\nAuthor\n1\nSherlock"));
        Assertions.assertEquals(authorDao.findAll().size(),1);
        Assertions.assertEquals(bookDao.findAll().size(), 1);
    }
}
