import Service.AllService;
import data.Author;
import data.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class CrudTest {

    private static final AllService allService = new AllService();

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
    public void findAllAuthor() {
        int cnt = 0;
        List<Author> authors = allService.readAllAuthor();
        for (Author author : authors) {
            if(author.getId()!=null){
                cnt++;
            }
        }
        Assertions.assertEquals(5, cnt);
    }

    @Test
    @Order(2)
    public void createAuthor() {

        Author author = new Author();
        author.setFirstName("vasia");
        author.setLastName("don");
        author.setVisibleFlag(true);
        allService.createAuthor(author);
        int cnt = 0;
        List<Author> authors = allService.readAllAuthor();
        for (Author a : authors) {
            if(a.getId()!=null){
                cnt++;
            }
        }
        Assertions.assertEquals(6, cnt);



    }
    @Test
    @Order(3)
    public void AutById(){
        Author author = allService.readAllAuthor().get(1);
        String aId = author.getId();
        author = allService.findAuthorById(aId);
        Assertions.assertFalse(author == null);

    }

    @Test
    @Order(4)
    public void updateAuthor(){
        Author author = allService.readAllAuthor().get(0);
        String aId = author.getId();
        author.setFirstName("aaa");
        allService.updateAuthor(author);
        author = allService.findAuthorById(aId);
        Assertions.assertTrue(author.getFirstName().equals("aaa"));

    }

    @Test
    @Order(5)
    public void deleteAuthor() {
        Author author = allService.readAllAuthor().get(0);
        String aId = author.getId();
        allService.deleteAuthor(aId);
        author = allService.readAllAuthor().get(0);
        Assertions.assertEquals(false,author.getVisibleFlag() );
    }

    @Test
    @Order(6)
    public void createBoot() {
        for (int i = 0; i < 5; i++) {
            Book book = new Book();
            book.setTitle("test" + i);
            book.setVisibleFlag(true);
            List<String> list = new ArrayList<>();
            list.add(allService.readAllAuthor().get(i).getId());
            book.setAuthors(list);
            allService.createBook(book,list);
        }

        int cnt = 0;
        int cntA = 0;
        List<Book> book = allService.readAllBook();
        for (Book b : book) {
            if(b.getId()!=null){
                cnt++;
                if(b.getAuthors().get(0)!= null){
                    cntA ++;
                }
            }
        }
        Assertions.assertTrue(cnt == 5 && cntA == 5 );

    }


    @Test
    @Order(7)
    public void findAllBook() {
        int cnt = 0;
        List<Book> books = allService.readAllBook();
        for (Book b : books) {
            if(b.getId()!=null){
                cnt++;
            }
        }
        Assertions.assertEquals(5, cnt);
    }

    @Test
    @Order(8)
    public void BookById() {
        Book book = allService.readAllBook().get(1);
        String aId = book.getId();
        book = allService.findBookById(aId);
        Assertions.assertFalse(book == null);
    }

    @Test
    @Order(9)
    public void updateBook(){
        Book book = allService.readAllBook().get(1);
        String aId = book.getId();
        book.setTitle("asd");
        allService.updateBook(book);
        book = allService.findBookById(aId);
        Assertions.assertTrue(book.getTitle().equals("asd"));

    }
    @Test
    @Order(10)
    public void deleteBook() {
        Book book = allService.readAllBook().get(1);
        String aId = book.getId();
        allService.deleteBook(aId);
        book = allService.readAllBook().get(1);
        Assertions.assertEquals(false,book.getVisibleFlag() );
    }


    @Test
    @Order(11)
    public  void  bookByAut(){
        Author author = allService.readAllAuthor().get(1);
        String id = author.getId();
        List<Book> books = allService.findBookByAut(id);

        Assertions.assertNotNull(books.get(0));

    }


    @Test
    @Order(12)
    public  void  autByAut(){
        Book book = allService.readAllBook().get(1);
        String aId = book.getId();
        List<Author> authors = allService.findAutByBook(aId);

        Assertions.assertNotNull(authors.get(0));
    }










}
