package ua.com.alevel.app.controller;

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

public class CrudController {

    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final BookService bookService = new BookServiceImpl();
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void menu() {
        while (true) {
            showMenu();
            try {
                String choose = reader.readLine();

                switch (Integer.parseInt(choose)) {
                    case 0:
                        return;
                    case 1:
                        createBook();
                        break;
                    case 2:
                        createAuthor();
                        break;
                    case 3:
                        updateBook();
                        break;
                    case 4:
                        updateAuthor();
                        break;
                    case 5:
                        deleteBook();
                        break;
                    case 6:
                        deleteAuthor();
                        break;
                    case 7:
                        showBooks();
                        break;
                    case 8:
                        showAuthors();
                        break;
                    case 9:
                        showAuthorsOfBook();
                        break;
                    case 10:
                        showBooksFromAuthor();
                        break;
                    case 11:
                        addListToBook();
                        break;
                    case 12:
                        addListToAuthor();
                        break;
                    case 13:
                        commonTest();
                        break;
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Wrong input");
            }
        }
    }

    private static void addListToAuthor() {
        List<Book> books = new ArrayList<>();
        System.out.println("Enter id of books(\"0\" to stop)");
        try {
            while (true) {
                String id = reader.readLine();
                if (id.equals("0")) {
                    break;
                }
                books.add(findByIdBook(id));
            }
            System.out.println("Enter id of author to add books");
            String id = reader.readLine();
            findByIdAuthor(id).setBooks(books);
        } catch (IOException | RuntimeException e) {
            System.out.println("Wrong input");
        }
    }

    private static void addListToBook() {
        List<Author> authors = new ArrayList<>();
        System.out.println("Enter id of authors(\"0\" to stop)");
        try {
            while (true) {
                String id = reader.readLine();
                if (id.equals("0")) {
                    break;
                }
                authors.add(findByIdAuthor(id));
            }
            System.out.println("Enter id of book to add authors");
            String id = reader.readLine();
            findByIdBook(id).setAuthors(authors);
        } catch (IOException | RuntimeException e) {
            System.out.println("Wrong input");
        }
    }

    private static void showBooksFromAuthor() {
        System.out.println("Enter id of author");
        String id;
        try {
            id = reader.readLine();
            System.out.println(authorService.readList(findByIdAuthor(id)));
        } catch (IOException | RuntimeException e) {
            System.out.println("Wrong input");
        }
    }

    private static void showAuthorsOfBook() {
        System.out.println("Enter id of book");
        String id;
        try {
            id = reader.readLine();
            System.out.println(bookService.readList(findByIdBook(id)));
        } catch (IOException | RuntimeException e) {
            System.out.println("Wrong input");
        }
    }

    private static void showAuthors() {
        System.out.println(authorService.read());
    }

    private static void deleteAuthor() {
        System.out.println("Enter id for delete");
        try {
            String id = reader.readLine();
            authorService.delete(findByIdAuthor(id));
        } catch (IOException | RuntimeException e) {
            System.out.println("Wrong input");
        }
    }

    private static void updateAuthor() {
        System.out.println("Enter id of author");
        try {
            String id = reader.readLine();
            System.out.println("Enter new first name");
            String firstName = reader.readLine();
            System.out.println("Enter new last name");
            String lastName = reader.readLine();
            Author author = findByIdAuthor(id);
            author.setFirstName(firstName);
            author.setLastName(lastName);

            authorService.update(findByIdAuthor(id));
        } catch (IOException | RuntimeException e) {
            System.out.println("Wrong input");
        }
    }

    private static void createAuthor() {
        System.out.println("Enter first name");
        try {
            String firstName = reader.readLine();
            System.out.println("Enter last name");
            String lastName = reader.readLine();

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            authorService.create(author);
        } catch (IOException | RuntimeException e) {
            System.out.println("Wrong input");
        }
    }

    private static void showBooks() {
        System.out.println(bookService.read());
    }

    private static void deleteBook() {
        System.out.println("Enter id for delete");
        try {
            String id = reader.readLine();
            bookService.delete(findByIdBook(id));
        } catch (IOException | RuntimeException e) {
            System.out.println("Wrong input");
        }
    }

    private static void updateBook() {
        System.out.println("Enter id for update");
        try {
            String id = reader.readLine();
            System.out.println("Enter new title");
            String title = reader.readLine();
            Book book = findByIdBook(id);
            book.setTitle(title);
            bookService.update(id);
        } catch (IOException | RuntimeException e) {
            System.out.println("Wrong input");
        }
    }

    private static void createBook() {
        System.out.println("Enter title");
        try {
            String title = reader.readLine();
            Book book = new Book();
            book.setTitle(title);
            bookService.create(book);
        } catch (IOException | RuntimeException e) {
            System.out.println("Wrong input");
        }

    }

    private static void showMenu() {
        System.out.println("1 -> create book\n" +
                "2 -> create author\n" +
                "3 -> update book\n" +
                "4 -> update author\n" +
                "5 -> delete book\n" +
                "6 -> delete author\n" +
                "7 -> show all books\n" +
                "8 -> show all authors\n" +
                "9 -> show all authors from book\n" +
                "10 -> show all books by author\n" +
                "11 -> add authors to book\n" +
                "12 -> add books to author\n" +
                "13 -> AUTO-DEMO for CRUD service\n" +
                "0 -> back to menu");
    }

    private static Book findByIdBook(String id) {
        Book current = bookService.read().stream().filter(e -> e.getId().equals(id)).findAny().orElse(null);
        if (current == null) {
            throw new RuntimeException("This id does not exist");
        }
        return current;
    }

    private static Author findByIdAuthor(String id) {
        Author current = authorService.read().stream().filter(e -> e.getId().equals(id)).findAny().orElse(null);
        if (current == null) {
            throw new RuntimeException("This id does not exist");
        }
        return current;
    }

    private static void commonTest() {
        for (int i = 0; i < 5; i++) {
            Author author = new Author();
            author.setFirstName("first_name" + i);
            author.setLastName("last_name" + i);
            authorService.create(author);
            Book book = new Book();
            book.setTitle("title" + i);
            bookService.create(book);
        }
        List<Author> authors = authorService.read();
        List<Book> books = bookService.read();
        Author newAuthor;
        newAuthor = authors.get(0);
        Book newBook;
        newBook = books.get(0);

        System.out.println("CREATE");
        showAuthors();
        showBooks();

        System.out.println("---------------------------------------------------");
        System.out.println("ADDING BOOKS TO AUTHOR");
        List<Book> bookList = new ArrayList<>();
        bookList.add(newBook);
        newAuthor.setBooks(bookList);
        System.out.println(bookService.readList(findByIdBook(newBook.getId())));

        newAuthor.setFirstName("first_name" + "UPDATED" + 0);
        authorService.update(newAuthor);

        System.out.println("---------------------------------------------------");
        System.out.println("UPDATE FIRST AUTHOR");
        showAuthors();

        authorService.delete(authors.get(0));

        System.out.println("---------------------------------------------------");
        System.out.println("DELETE FIRST AUTHOR");
        showAuthors();

        newBook.setTitle("first_name" + "UPDATED" + 0);
        bookService.update(books.get(0).getId());

        System.out.println("---------------------------------------------------");
        System.out.println("UPDATE FIRST BOOK");
        showBooks();

        bookService.delete(books.get(0));

        System.out.println("---------------------------------------------------");
        System.out.println("DELETE FIRST BOOK");
        showBooks();
    }
}
