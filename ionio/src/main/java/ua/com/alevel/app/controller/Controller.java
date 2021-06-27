package ua.com.alevel.app.controller;

import ua.com.alevel.app.csv.CsvDb;
import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.entity.Book;
import ua.com.alevel.app.service.AuthorService;
import ua.com.alevel.app.service.BookService;
import ua.com.alevel.app.service.impl.AuthorServiceImpl;
import ua.com.alevel.app.service.impl.BookServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static final CsvDb csvDb = new CsvDb();
    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final BookService bookService = new BookServiceImpl();
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void menu() throws IOException {
        String input;
        csvDb.initFiles();
        printOptions();
        while ((input = reader.readLine()) != null) {
            try {
                switch (input) {
                    case "0":
                        return;
                    case "1":
                        createAuthor();
                        break;
                    case "2":
                        updateAuthor();
                        break;
                    case "3":
                        deleteAuthor();
                        break;
                    case "4":
                        showAuthor();
                        break;
                    case "5":
                        findAuthorsByBook();
                        break;
                    case "6":
                        createBook();
                        break;
                    case "7":
                        updateBook();
                        break;
                    case "8":
                        deleteBook();
                        break;
                    case "9":
                        showBook();
                        break;
                    case "10":
                        findBooksByAuthor();
                        break;
                    case "11":
                        commonTest();
                        break;
                    default:
                        System.out.println("Your input is wrong. Please, try again.");
                }
            } catch (RuntimeException exception) {
                System.out.println("Something went wrong. Please, try again");
            }
            printOptions();
        }
    }

    private static void createAuthor() throws IOException {
        Author author = new Author();
        System.out.println("Enter first name:");
        author.setFirstName(reader.readLine());
        System.out.println("Enter last name:");
        author.setLastName(reader.readLine());
        author.setVisibleFlag(true);
        authorService.create(author);
    }

    private static void updateAuthor() throws IOException {
        System.out.println("Enter id");
        String id = reader.readLine();
        if ((authorService.read(id)) == null) {
            System.out.println("Wrong id");
        }
        Author author = authorService.read(id);
        System.out.println("1 -> change first name\n2 -> change last name\n0 - exit");
        switch (reader.readLine()) {
            case "1": {
                System.out.println("Enter new first name");
                author.setFirstName(reader.readLine());
                authorService.update(author);
                break;
            }
            case "2": {
                System.out.println("Enter new last name");
                author.setLastName(reader.readLine());
                authorService.update(author);
                break;
            }
            case "0": {
                System.exit(0);
                break;
            }
            default:
                System.out.println("Wrong input");
        }
    }

    private static void deleteAuthor() {
        System.out.println("Enter id");
        try {
            authorService.delete(reader.readLine());
        } catch (RuntimeException | IOException ex) {
            System.err.println("Wrong id");
        }
    }

    private static void showAuthor() {
        List<Author> authors = authorService.read();
        for (Author author : authors) {
            System.out.println(author);
        }
    }

    private static void createBook() throws IOException {
        Book book = new Book();
        System.out.println("Enter title:");
        book.setTitle(reader.readLine());
        book.setVisibleFlag(true);

        List<String> autId = new ArrayList<>();
        System.out.println("Enter amount of authors:");
        int i = Integer.parseInt(reader.readLine());
        for (int j = 0; j < i; j++) {
            System.out.println("Enter id of author");
            String s = reader.readLine();
            autId.add(s);
        }
        book.setAuthors(autId);
        bookService.create(book,autId);
    }

    private static void updateBook() throws IOException {
        System.out.println("Enter id");
        String id = reader.readLine();
        if ((bookService.read(id)) == null) {
            System.out.println("Wrong id");
        }
        Book book = bookService.read(id);
        System.out.println("Enter new title");
        book.setTitle(reader.readLine());
        bookService.update(book);
    }

    private static void deleteBook() {
        System.out.println("Enter id");
        try {
            bookService.delete(reader.readLine());
        } catch (RuntimeException | IOException ex) {
            System.err.println("Wrong id");
        }
    }

    private static void showBook() {
        List<Book> books = bookService.read();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void findBooksByAuthor() throws IOException {
        System.out.println("Enter author`s id:");
        String id = reader.readLine();
        if(authorService.read(id)==null){
            System.out.println("Wrong id");
        }
        List<Book> books = bookService.readByAuthor(id);
        for (Book book : books) {
            System.out.println(book);
        }
        if (books.get(0) == null){
            System.out.println("This author has no books");
        }
    }

    private static void findAuthorsByBook() throws IOException {
        System.out.println("Enter book`s id:");
        String id = reader.readLine();
        if(bookService.read(id)==null){
            System.out.println("Wrong id");
        }
        List<Author> authors = authorService.readByBook(id);
        for (Author author : authors) {
            System.out.println(author);
        }
    }

    private static void commonTest() {
        System.out.println("CREATE 10 AUTHORS");
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setFirstName("first_name" + i);
            author.setLastName("last_name" + i);
            author.setVisibleFlag(true);
            authorService.create(author);
        }
        System.out.println("READ AUTHORS");
        List<Author> authors = authorService.read();
        for (Author author : authors) {
            System.out.println(author);
        }

        System.out.println("===================================================================================");

        System.out.println("CREATE 10 BOOKS");
        for (int i = 0; i < 10; i++) {
            Author author = authorService.read().get(i);
            Author author2 = authorService.read().get((i*2));
            List<String> id = new ArrayList<>();
            id.add(author.getId());
            id.add(author2.getId());
            Book book = new Book();
            book.setTitle("title" + i);
            book.setVisibleFlag(true);
            book.setAuthors(id);
            bookService.create(book, id);
        }
        System.out.println("READ BOOKS");
        List<Book> books = bookService.read();
        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("===================================================================================");

        System.out.println("READ BOOK BY ID");
        Book book = bookService.read().get(3);
        System.out.println(book);
        System.out.println("READ AUTHORS OF THIS BOOK");
        List<String> authorId = book.getAuthors();
        for (String id : authorId) {
            Author author = authorService.read(id);
            System.out.println(author);
        }

        System.out.println("===================================================================================");

        System.out.println("READ AUTHORS BY BOOK");
        System.out.println("BOOK_ID " + book.getId());
        List<Author> aut= authorService.readByBook(book.getId());
        for (Author author : aut) {
            System.out.println(author);
        }
        String idOfaut = aut.get(0).getId();
        System.out.println("READ AUTHOR`S BOOKS");
        System.out.println("AUTHOR_ID " + idOfaut);
        List<Book> bookList = bookService.readByAuthor(idOfaut);
        for (Book book1 : bookList) {
            System.out.println(book1);
        }

        System.out.println("===================================================================================");

        System.out.println("EXAMPLE CRUD BOOK");
        System.out.println(bookService.read(book.getId()));
        System.out.println("UPDATE BOOK");
        book.setTitle("UPDATE");
        bookService.update(book);
        System.out.println("READ BOOK");
        System.out.println(bookService.read(book.getId()));
        System.out.println("DELETE BOOK");
        bookService.delete(book.getId());
        System.out.println("READ BOOK");
        System.out.println(bookService.read(book.getId()));

        System.out.println("===================================================================================");

        System.out.println("EXAMPLE CRUD AUTHOR");
        System.out.println(authorService.read(idOfaut));
        System.out.println("UPDATE AUTHOR");
        Author author123 = authorService.read(idOfaut);
        author123.setFirstName("UPDATE_FIRST");
        author123.setLastName("UPDATE_LAST");
        authorService.update(author123);
        System.out.println("READ AUTHOR");
        System.out.println(authorService.read(idOfaut));
        System.out.println("DELETE AUTHOR");
        bookService.delete(idOfaut);
        System.out.println("READ AUTHOR");
        System.out.println(authorService.read(idOfaut));
    }

    public static void printOptions() {
        System.out.println("1 -> create author\n" +
                "2 -> update author\n" +
                "3 -> delete author\n" +
                "4 -> show authors\n" +
                "5 -> show authors by book\n" +
                "6 -> create book\n" +
                "7 -> update book\n" +
                "8 -> delete book\n" +
                "9 -> show books\n" +
                "10 -> show books by author\n" +
                "11 -> COMMON TEST\n" +
                "0 -> exit");
    }
}