package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Book;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {

    private static final BookService bookService = new BookService();
    private static Object[] entities;

    @BeforeAll
    public static void setUp(){
        for (int i = 0; i < 5; i++) {
            Book book = new Book("test" + i);
            bookService.create(book);
        }
        Assertions.assertEquals(bookService.read().length, 5);
    }

    @Test
    @Order(1)
    public void create(){
        Book book = new Book("test6");
        bookService.create(book);
        entities = bookService.read();
        String titleForTest = ( (Book) entities[entities.length-1]).getTitle();
        Assertions.assertEquals(titleForTest, "test6");
    }

    @Test
    @Order(2)
    public void read(){
        Assertions.assertArrayEquals(entities, bookService.read());
    }

    @Test
    @Order(3)
    public void readById(){
        entities = bookService.read();
        Book bookForRead = ( (Book) entities[0]);
        Book readBook = bookService.read(bookForRead.getId());
        Assertions.assertEquals(bookForRead.getId(), readBook.getId());
    }


    @Test
    @Order(4)
    public void update(){
        entities = bookService.read();
        Book bookForUpdate = ( (Book) entities[0]);
        String id = bookForUpdate.getId();
        bookForUpdate.setTitle("TestU");
        bookService.update(bookForUpdate);
        Book updatedBook = bookService.read(id);
        Assertions.assertEquals(updatedBook.getTitle(), bookForUpdate.getTitle());
    }

    @Test
    @Order(5)
    public void delete(){
        entities = bookService.read();
        Book bookForDelete = ( (Book) entities[0]);
        bookService.delete(bookForDelete);
        entities = bookService.read();
        Book bookForTest = ( (Book) entities[0]);
        Assertions.assertNotEquals(bookForDelete.getId(), bookForTest.getId());
    }

}
