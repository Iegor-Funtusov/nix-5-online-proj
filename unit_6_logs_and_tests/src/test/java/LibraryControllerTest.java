import domain.Author;
import domain.Book;
import org.junit.jupiter.api.*;
import service.AuthorService;
import service.BookService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibraryControllerTest {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static AuthorService authorService = new AuthorService();
    private static BookService bookService = new BookService();

    @BeforeAll
    public static void setUp() {
        System.out.println(ANSI_GREEN + "\nTests start: \n" + ANSI_RESET);

        for (int i = 0; i < 5; i++) {
            Author author = new Author();
            author.setFirstName("Автор" + i);
            author.setLastName("Автор" + i);
            authorService.createAuthor(author);
        }

        Author author = new Author();
        author.setFirstName("Ольга");
        author.setLastName("Хлудова");
        authorService.createAuthor(author);

        author = new Author();
        author.setFirstName("Александр");
        author.setLastName("Волков");
        authorService.createAuthor(author);

        Collection<Author> authors = authorService.findAuthors();
        Assertions.assertEquals(authors.size(), 7);
    }

    @Test
    @Order(1)
    public void createBooks() {
        Collection<Author> authors = authorService.findAuthors();
        Author author = authorService.checkAuthor(authors, "Хлудова");
        if (author != null) {
            Book book = new Book();
          //  book.setLastName("Хлудова");
            book.setTitle("Волны над нами");
            book.setYear(1960);
            bookService.createBook(author, book);
        }
        author = authorService.checkAuthor(authors, "Волков");
        if (author != null) {
            Book book = new Book();
        //    book.setLastName("Волков");
            book.setTitle("Волшебник изумрудного города");
            book.setYear(1965);
            bookService.createBook(author, book);
        }
        if (author != null) {
            Book book = new Book();
          //  book.setLastName("Волков");
            book.setTitle("Урфин Джюс и его деревянные солдаты");
            book.setYear(1969);
            bookService.createBook(author, book);
        }
        Collection<Book> books = bookService.findBooks(authors, "Волков");
        Assertions.assertEquals(books.size(), 2);
    }

    @Test
    @Order(2)
    public void createAuthor() {
        Author author = new Author();
        author.setFirstName("Артур");
        author.setLastName("Конан Дойль");
        authorService.createAuthor(author);

        author = new Author();
        author.setFirstName("Жюль");
        author.setLastName("Верн");
        authorService.createAuthor(author);

        Collection<Author> authors = authorService.findAuthors();
        Assertions.assertEquals(authors.size(), 9);
    }

    @Test
    @Order(3)
    public void findAllAuthors() {
        Collection<Author> authors = authorService.findAuthors();
        Assertions.assertEquals(authors.size(), 9);
    }

    @Test
    @Order(4)
    public void createBook() {
        Collection<Author> authors = authorService.findAuthors();
        for (Author author : authors) {
            if (author.getLastName().equals("Хлудова")) {
               /* domain.Book book = new domain.Book();
                book.setTitle("Волны над нами");
                book.setYear(1960);
                bookService.createBook(author, book);
                */
                Book book = new Book();
               // book.setLastName("Хлудова");
                book.setTitle("За голубым порогом");
                book.setYear(1963);
                bookService.createBook(author, book);
                Collection<Book> books = bookService.findBooks(authors, "Хлудова");
                Assertions.assertEquals(books.size(), 2);
            }
        }
    }

    @Test
    @Order(5)
    public void updateAuthor() {
        Collection<Author> authors = authorService.findAuthors();
        for (Author author : authors) {
            if (author.getLastName().equals("Верн")) {
                author.setFirstName("Жюльь");
                author.setLastName("Вернн");
                authorService.updateAuthor(author);
                Assertions.assertEquals("Вернн", author.getLastName());
            }
        }
    }

    @Test
    @Order(6)
    public void removeAuthor() {
       authorService.deleteAuthor("Конан Дойль");
       Collection<Author> authors = authorService.findAuthors();
       Assertions.assertEquals(authors.size(), 8);
    }

    @Test
    @Order(7)
    public void removeBook() {
        Collection<Author> authors = authorService.findAuthors();
        Author author = authorService.checkAuthor(authors, "Хлудова");
        if (author != null) {
            Book book = bookService.bookCheck(author, "За голубым порогом");
            if (book != null) {
                bookService.deleteBook(author, book.getBookId());
            }
            Assertions.assertNull(author.getBooks()[1]);
        }
    }

    @Test
    @Order(8)
    public void removeAllBooksOfAuthor() {
        Collection<Author> authors = authorService.findAuthors();
        Author author = authorService.checkAuthor(authors, "Волков");
        if (author != null) {
            if ((int) Arrays.stream(author.getBooks()).filter(Objects::nonNull).count() != 0) {
                    for (Book book : author.getBooks()) {
                        if (book != null) {
                            bookService.deleteBook(author, book.getBookId());
                        }
                    }
            }
        } else authorService.errorMessage();

        Collection<Book> books = bookService.findBooks(authors, "Волков");
        Assertions.assertNull(books);
    }

    @Test
    @Order(9)
    public void createAuthorEmptyLastName() {
        Author author = new Author();
        author.setFirstName("Ольга");
        author.setLastName("");
        authorService.createAuthor(author);

        Collection<Author> authors = authorService.findAuthors();
        Assertions.assertEquals(authors.size(), 8);
    }

    @Test
    @Order(10)
    public void createAuthorExist() {
        Author author = new Author();
        author.setFirstName("Ольга");
        author.setLastName("Хлудова");
        authorService.createAuthor(author);

        Collection<Author> authors = authorService.findAuthors();
        Assertions.assertEquals(authors.size(), 8);
    }

    @Test
    @Order(11)
    public void createBookExist() {
        Collection<Author> list = authorService.findAuthors();
        for (Author author : list) {
            if (author.getLastName().equals("Хлудова")) {
                Book book = new Book();
              //  book.setLastName("Хлудова");
                book.setTitle("Волны над нами");
                book.setYear(1960);
                bookService.createBook(author, book);
                Collection<Book> books = bookService.findBooks(list, author.getLastName());
                Assertions.assertEquals(books.size(), 1);
            }
        }
    }

    @Test
    @Order(11)
    public void createBookAuthorNotExist() {
        Collection<Author> list = authorService.findAuthors();
        for (Author author : list) {
            if (author.getLastName().equals("Конан Дойль")) {
                Book book = new Book();
             //   book.setLastName("Конан Дойль");
                book.setTitle("Собака Баскервилей");
                book.setYear(1979);
                bookService.createBook(author, book);
                Collection<Book> books = bookService.findBooks(list, author.getLastName());
                Assertions.assertEquals(books.size(), 1);
            }
        }
    }

    @Test
    @Order(12)
    public void deleteBookNotExist() {
        Collection<Author> authors = authorService.findAuthors();
        Author author = authorService.checkAuthor(authors, "Хлудова");
        if (author != null) {
            Book book = bookService.bookCheck(author, "За голубым порогом");
            if (book != null) {
                bookService.deleteBook(author, book.getBookId());
            } else bookService.errorMessage();
            Collection<Book> books = bookService.findBooks(authors,"Хлудова");
            Assertions.assertEquals(books.size(), 1);
        }
    }

    @AfterAll
    public static void close() {
        System.out.println(ANSI_GREEN + "\nTests finished \n" + ANSI_RESET);
    }
}
