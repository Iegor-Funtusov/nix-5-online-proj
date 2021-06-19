package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.Relation;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RelationServiceTest {

    private static final RelationService relationService = new RelationService();
    private static Object[] entities;

    @BeforeAll
    public static void setUp(){
        for (int i = 0; i < 5; i++) {
            Book book = new Book("test" + i);
            Author author = new Author("test" + i, i);
            Relation relation = new Relation(book, author);
            relationService.create(relation);
        }
        Assertions.assertEquals(relationService.read().length, 5);
    }

    @Test
    @Order(1)
    public void create(){
        Book book = new Book("testB" );
        Author author = new Author("testA", 111);
        Relation relation = new Relation(book, author);
        relationService.create(relation);
        entities = relationService.read();
        book = ( (Relation) entities[entities.length-1]).getBook();
        author = ( (Relation) entities[entities.length-1]).getAuthor();
        Assertions.assertEquals(book.getTitle(), "testB");
        Assertions.assertEquals(author.getName(), "testA");
        Assertions.assertEquals(author.getAge(), 111);
    }

    @Test
    @Order(2)
    public void read(){
        Assertions.assertArrayEquals(entities, relationService.read());
    }

    @Test
    @Order(3)
    public void readById(){
        entities = relationService.read();
        Relation relation = ( (Relation) entities[0]);
        Relation readRelation = relationService.read(relation.getId());
        Assertions.assertEquals(readRelation.getId(), relation.getId());
    }

    @Test
    @Order(4)
    public void update(){
        entities = relationService.read();
        Relation relationForUpdate = ( (Relation) entities[0]);
        Book book = new Book("testBU" );
        Author author = new Author("testAU", 1);
        relationForUpdate.setBook(book);
        relationForUpdate.setAuthor(author);
        relationService.update(relationForUpdate);
        Relation updatedRelation = relationService.read(relationForUpdate.getId());
        Assertions.assertTrue(updatedRelation.getBook().getTitle().equals("testBU")
                && updatedRelation.getAuthor().getName().equals("testAU")
                && updatedRelation.getAuthor().getAge() == 1 );
    }

    @Test
    @Order(5)
    public void delete(){
        entities = relationService.read();
        Relation relation = ( (Relation) entities[0]);
        relationService.delete(relation);
        entities = relationService.read();
        Relation relationForTest = ( (Relation) entities[0]);
        Assertions.assertNotEquals(relation.getId(), relationForTest.getId());
    }

}
