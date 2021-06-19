package ua.com.alevel.lib;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Book;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrudServiceImplTest {

    private static final CrudService<Book> crudService = new CrudServiceImpl<>();
    private static Object[] entities;

    @BeforeAll
    public static void setUp(){
        for (int i = 0; i < 5; i++) {
            Book book = new Book("test" + i);
            crudService.create(book);
        }
        Assertions.assertEquals(crudService.read().length, 5);
    }

    @Test
    @Order(1)
    public void create(){
        Book book = new Book("test6");
        crudService.create(book);
        entities = crudService.read();
        String titleForTest = ( (Book) entities[entities.length-1]).getTitle();
        Assertions.assertEquals(titleForTest, "test6");
    }

    @Test
    @Order(2)
    public void read(){
       Assertions.assertArrayEquals(entities, crudService.read());
    }


    @Test
    @Order(3)
    public void update(){
        entities = crudService.read();
        Book bookForUpdate = ( (Book) entities[0]);
        String id = bookForUpdate.getId();
        bookForUpdate.setTitle("TestU");
        crudService.update(bookForUpdate);
        Book updatedBook = crudService.read(id);
        Assertions.assertEquals(updatedBook.getTitle(), bookForUpdate.getTitle());
    }

    @Test
    @Order(4)
    public void delete(){
        entities = crudService.read();
        Book bookForDelete = ( (Book) entities[0]);
        crudService.delete(bookForDelete.getId());
        entities = crudService.read();
        Book bookForTest = ( (Book) entities[0]);
        Assertions.assertNotEquals(bookForDelete.getId(), bookForTest.getId());
    }

    @Test
    @Order(5)
    public void readById(){
        entities = crudService.read();
        Book bookForRead = ( (Book) entities[0]);
        Book readBook = crudService.read(bookForRead.getId());
        Assertions.assertEquals(bookForRead.getId(), readBook.getId());
    }

}
