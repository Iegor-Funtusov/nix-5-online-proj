package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Author;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorServiceTest {

    private static final AuthorService authorCrudService = new AuthorService();
    private static Object[] entities;

    @BeforeAll
    public static void setUp(){
        for (int i = 0; i < 5; i++) {
            Author author = new Author("test" + i, i);
            authorCrudService.create(author);
        }
        Assertions.assertEquals(authorCrudService.read().length, 5);
    }

    @Test
    @Order(1)
    public void create(){
        Author author = new Author("test6", 6);
        authorCrudService.create(author);
        entities = authorCrudService.read();
        String titleForTest = ( (Author) entities[entities.length-1]).getName();
        Assertions.assertEquals(titleForTest, "test6");
    }

    @Test
    @Order(2)
    public void read(){
        Assertions.assertArrayEquals(entities, authorCrudService.read());
    }

    @Test
    @Order(3)
    public void readById(){
        entities = authorCrudService.read();
        Author author = ( (Author) entities[0]);
        Author readAuthor = authorCrudService.read(author.getId());
        Assertions.assertEquals(readAuthor.getId(), author.getId());
    }

    @Test
    @Order(4)
    public void update(){
        entities = authorCrudService.read();
        Author authorForUpdate = ( (Author) entities[0]);
        String id = authorForUpdate.getId();
        authorForUpdate.setAge(111);
        authorForUpdate.setName("TESTa");
        authorCrudService.update(authorForUpdate);
        Author updatedAuthor  = authorCrudService.read(id);
        Assertions.assertTrue(updatedAuthor.getName().equals("TESTa")
                && updatedAuthor.getAge() == 111);
    }

    @Test
    @Order(5)
    public void delete(){
        entities = authorCrudService.read();
        Author author = ( (Author) entities[0]);
        authorCrudService.delete(author);
        entities = authorCrudService.read();
        Author testAuthor = ( (Author) entities[0]);
        Assertions.assertNotEquals(testAuthor.getId(), author.getId());
    }


}
