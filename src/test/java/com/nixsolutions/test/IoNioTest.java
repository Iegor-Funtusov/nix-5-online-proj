package com.nixsolutions.test;

import com.nixsolutions.service.AuthorBookServiceImpl;
import com.nixsolutions.model.Author;
import com.nixsolutions.model.Book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IoNioTest {
    private static final AuthorBookServiceImpl authorBookService = new AuthorBookServiceImpl();

    @BeforeAll
    public static void setUp(){
        authorBookService.createFiles();
        for (int i = 0; i < 5; i++) {
            Author author = new Author();
            author.setFirstName("First" + i);
            author.setLastName("Last" + i);
            author.setIsVisible(true);
            authorBookService.createAuthor(author);
        }
        Assertions.assertTrue(authorBookService.readAllAuthors().size()!=0);
    }

    @Test
    @Order(1)
    public void findAllAuthor() {
        int count = 0;
        List<Author> authors = authorBookService.readAllAuthors();
        for (Author author : authors) {
            if(author.getId() != null){
                count++;
            }
        }
        Assertions.assertEquals(5, count);
    }

    @Test
    @Order(2)
    public void createAuthor() {

        Author author = new Author();
        author.setFirstName("Vlad");
        author.setLastName("Shahno");
        author.setIsVisible(true);
        authorBookService.createAuthor(author);
        int count = 0;
        List<Author> authors = authorBookService.readAllAuthors();
        for (Author a : authors) {
            if(a.getId() != null){
                count++;
            }
        }
        Assertions.assertEquals(6, count);



    }
    @Test
    @Order(3)
    public void AuthorById(){
        Author author = authorBookService.readAllAuthors().get(1);
        String authorId = author.getId();
        author = authorBookService.findAuthorById(authorId);
        Assertions.assertFalse(author == null);

    }

    @Test
    @Order(4)
    public void updateAuthor(){
        Author author = authorBookService.readAllAuthors().get(0);
        String authorId = author.getId();
        author.setFirstName("Vlad");
        authorBookService.updateAuthor(author);
        author = authorBookService.findAuthorById(authorId);
        Assertions.assertTrue(author.getFirstName().equals("Vlad"));

    }

    @Test
    @Order(5)
    public void deleteAuthor() {
        Author author = authorBookService.readAllAuthors().get(0);
        String authorId = author.getId();
        authorBookService.deleteAuthor(authorId);
        author = authorBookService.readAllAuthors().get(0);
        Assertions.assertEquals(false,author.getIsVisible() );
    }

    @Test
    @Order(6)
    public void createBoot() {
        for (int i = 0; i < 5; i++) {
            Book book = new Book();
            book.setTitle("newTitle" + i);
            book.setIsVisible(true);
            List<String> list = new ArrayList<>();
            list.add(authorBookService.readAllAuthors().get(i).getId());
            book.setAuthorList(list);
            authorBookService.createBook(book,list);
        }

        int count = 0;
        int authorCount = 0;
        List<Book> book = authorBookService.readAllBooks();
        for (Book b : book) {
            if(b.getId() != null){
                count++;
                if(b.getAuthorList().get(0) != null){
                    authorCount ++;
                }
            }
        }
        Assertions.assertTrue(count == 5 && authorCount == 5 );
    }


    @Test
    @Order(7)
    public void findAllBook() {
        int count = 0;
        List<Book> books = authorBookService.readAllBooks();
        for (Book b : books) {
            if(b.getId()!=null){
                count++;
            }
        }
        Assertions.assertEquals(5, count);
    }

    @Test
    @Order(8)
    public void BookById() {
        Book book = authorBookService.readAllBooks().get(1);
        String bookId = book.getId();
        book = authorBookService.findBookById(bookId);
        Assertions.assertFalse(book == null);
    }

    @Test
    @Order(9)
    public void updateBook(){
        Book book = authorBookService.readAllBooks().get(1);
        String aId = book.getId();
        book.setTitle("Shine");
        authorBookService.updateBook(book);
        book = authorBookService.findBookById(aId);
        Assertions.assertTrue(book.getTitle().equals("Shine"));

    }

    @Test
    @Order(10)
    public void deleteBook() {
        Book book = authorBookService.readAllBooks().get(1);
        String aId = book.getId();
        authorBookService.deleteBook(aId);
        book = authorBookService.readAllBooks().get(1);
        Assertions.assertEquals(false,book.getIsVisible() );
    }

    @Test
    @Order(11)
    public  void bookByAuthor(){
        Author author = authorBookService.readAllAuthors().get(1);
        String id = author.getId();
        List<Book> books = authorBookService.findBookByAuthor(id);
        Assertions.assertNotNull(books.get(0));
    }

    @Test
    @Order(12)
    public  void authorByBook(){
        Book book = authorBookService.readAllBooks().get(1);
        String aId = book.getId();
        List<Author> authors = authorBookService.findAuthorByBook(aId);
        Assertions.assertNotNull(authors.get(0));
    }
}
