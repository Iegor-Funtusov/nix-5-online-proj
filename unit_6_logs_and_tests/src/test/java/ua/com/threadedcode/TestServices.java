package ua.com.threadedcode;

import org.junit.jupiter.api.*;
import ua.com.threadedcode.dao.ICrudProcess;
import ua.com.threadedcode.dao.crudObject.CrudObject;
import ua.com.threadedcode.entity.Author;
import ua.com.threadedcode.entity.Book;
import java.util.Collection;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestServices {
    private ICrudProcess<Book> bookService = CrudObject.getInstance();
    private ICrudProcess<Author> authorService = CrudObject.getInstance();
//    private ICrudProcess<Author> authorService = CrudFactory.getInstance().getCrudProcess();
//    private ICrudProcess<Book> bookService = CrudFactory.getInstance().getCrudProcess();

    @Test
    @Order(1)
    public void createAuthor() {
        Author author = new Author("Рей", "Брэдбери");
        Author author1 = new Author("Даниел", "Киз");
        Author author2 = new Author("Харуки", " Мураками");

        authorService.createAuthor(author);
        authorService.createAuthor(author1);
        authorService.createAuthor(author2);

        Assertions.assertEquals(3, authorService.getAllAuthors().size(),
                "in storage should be created 3 authors");
    }

    @Test
    @Order(2)
    public void createBook() {
        Book book = new Book("Вино из одуванчиков");
        Book book1 = new Book("Цветы для Элджернона");
        Book book2 = new Book("Слушай песню ветра");

        bookService.createBook(book);
        bookService.createBook(book1);
        bookService.createBook(book2);

        Assertions.assertEquals(3, bookService.getAllBooks().size(),
                "in storage should be created 3 books");
    }

    @Test
    @Order(16)
    public void getAllAuthors() {
        Author author = new Author("Рей", "Брэдбери");
        Author author1 = new Author("Даниел", "Киз");
        Author author2 = new Author("Харуки", " Мураками");

        authorService.createAuthor(author);
        authorService.createAuthor(author1);
        authorService.createAuthor(author2);

        Object[] authors = new Object[]{author, author1, author2};

        Collection<Author> authorsObject = authorService.getAllAuthors();
        Assertions.assertEquals(authorsObject, authorService.getAllAuthors());
    }

    @Test
    @Order(15)
    public void getAllBooks() {
        Book book = new Book("Вино из одуванчиков");
        Book book1 = new Book("Цветы для Элджернона");
        Book book2 = new Book("Слушай песню ветра");

        bookService.createBook(book);
        bookService.createBook(book1);
        bookService.createBook(book2);

        Object[] books = new Object[]{book, book1, book2};
        Collection<Book> booksObject = bookService.getAllBooks();

        Assertions.assertEquals(booksObject, bookService.getAllBooks());
    }

    @Test
    @Order(13)
    public void addAuthorDuplicate() {
        Author author = new Author("Рей", "Брэдбери");
        Author author1 = new Author("Рей", "Брэдбери");

        authorService.createAuthor(author);
        authorService.createAuthor(author1);

        Assertions.assertEquals(authorService.getAuthorByName("Рей"), author);
    }

    @Test
    @Order(14)
    public void addBookDuplicate() {
        Book book = new Book("Вино из одуванчиков");
        Book book1 = new Book("Вино из одуванчиков");

        bookService.createBook(book);
        bookService.createBook(book1);

        Assertions.assertEquals(bookService.findBookByTitle("Вино из одуванчиков"), book);
    }

    @Test
    @Order(5)
    public void updateAuthor() {
        Author author = new Author("Энди", "Вейер");
        authorService.createAuthor(author);

        author.setFirstName("ТЕСТ");
        authorService.createAuthor(author);

        Assertions.assertEquals(authorService.getAuthorByName("ТЕСТ").getId(), author.getId());
    }

    @Test
    @Order(6)
    public void updateBook() {
        Book book = new Book("Марсианин");
        bookService.createBook(book);
        book.setTitle("ТЕСТ");
        bookService.updateBook(book);

        Assertions.assertEquals(bookService.findBookByTitle("ТЕСТ").getId(), book.getId());
    }

    @Order(10)
    public void deleteAuthor() {
        Author author = new Author("Рей", "Брэдбери");
        authorService.createAuthor(author);
        int storageSize = authorService.getAllAuthors().size();

        authorService.deleteAuthor("Рей");

        Assertions.assertEquals((storageSize - 1), authorService.getAllAuthors().size(),
                "author without any linked books should be deleted and storage size should be equal pres. size - 1");
    }

    @Test
    @Order(18)
    public void deleteBook() {
        Book book = new Book("Марсианин2");
        bookService.createBook(book);
        int storageSize = bookService.getAllBooks().size();

        bookService.deleteBook("Марсианин2");

        Assertions.assertEquals((storageSize - 1), bookService.getAllBooks().size(),
                "book without any linked authors should be deleted and storage size should be equal pres. size - 1");
    }

    @Test
     public void deleteBookWithLinkedAuthor() {
        Author author = new Author("Энди6", "Вейер6");
        Author author1 = new Author("Энди7", "Вейер7");
        Book book = new Book("Марсианин6");

        authorService.createAuthor(author);
        authorService.createAuthor(author1);
        bookService.createBook(book);

        book.setAuthorId(author.getId());
        book.setAuthorId(author1.getId());

        bookService.updateBook(book);

        bookService.deleteBookWithAuthor("Марсианин6", "Энди6");

        Object[] ids = bookService.findBookByTitle("Марсианин6").getAuthorId();
        for (Object id : ids) {
            Assertions.assertNotEquals(id, author.getId());
        }
    }

    @Test
    public void deleteAuthorWithLinkedBook() {
        Author author = new Author("Рей4", "Брэдбери4");
        Book book = new Book("Марсианин4");

        authorService.createAuthor(author);
        bookService.createBook(book);

        author.setBookId(book.getId());

        authorService.updateAuthor(author);
        int storageSize = authorService.getAllAuthors().size();

        authorService.deleteAuthor("Рей4");

        Assertions.assertEquals((storageSize - 1), authorService.getAllAuthors().size(),
                "author with linked books should be deleted and any book should not be deleted");
    }
}
