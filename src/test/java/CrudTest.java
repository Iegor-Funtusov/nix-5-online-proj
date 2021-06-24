import Service.AllService;
import data.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class CrudTest {

    private static AllService allService = new AllService();

    @BeforeAll
    public static void setUp(){
        allService.initFiles();
        for (int i = 0; i < 5; i++) {
            Author author = new Author();
            author.setFirstName("name" + i);
            author.setLastName("lastname" + i);
            author.setVisibleFlag(true);
            allService.createAuthor(author);
        }
        Assertions.assertTrue(allService.readAllAuthor().size()!=0);
    }

    @Test
    @Order(1)
    public void findAllFoot() {
        int cnt = 0;
        List<Author> authors = allService.readAllAuthor();
        for (Author author : authors) {
            if(author.getId()!=null){
                cnt++;
            }
        }
        Assertions.assertTrue(cnt==5);
    }

}
