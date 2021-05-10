package com.nixsolutions.courses.app;

import com.nixsolutions.courses.app.data.Book;
import com.nixsolutions.courses.app.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class BookAPI {

    public static BufferedReader reader;
    public static BookService bookService;

    public static void createItem() throws IOException {
        System.out.println("Enter book`s title: ");
        String title = reader.readLine();
        System.out.println("Enter year, when it was published:");
        int year = Integer.parseInt(reader.readLine());
        bookService.create(new Book(title, year));
    }

    public static void updateItem() throws IOException {
        System.out.println("Book`s title you want to change: ");
        Collection<Book> list = bookService.readAll();
        for (Book book : list) {
            if (book.getTitle().equals(reader.readLine())) {
                System.out.println("What to change?\n1 - title\n2 - year");
                switch (reader.readLine()) {
                    case "1":
                        System.out.println("Enter title: ");
                        book.setTitle(reader.readLine());
                        break;
                    case "2":
                        System.out.println("Enter year: ");
                        book.setYear(Integer.parseInt(reader.readLine()));
                }
                bookService.update(book);
                break;
            }
        }

    }

    public static void deleteItem() throws IOException {
        System.out.println("Book`s title you want to delete: ");
        Collection<Book> list = bookService.readAll();
        for (Book book : list) {
            if (book.getTitle().equals(reader.readLine())) {
                bookService.delete(book.getId());
                break;
            }
        }
    }

    public static void printItem() throws IOException {
        System.out.println("Enter book id you want to read:");
        bookService.read(reader.readLine());
    }

    public static void printAllItems() {
        Collection<Book> list = bookService.readAll();
        list.forEach(System.out::println);
    }

    public static void run() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        bookService = new BookService();
        String input;
        try {
            System.out.println("Choose option:\n0 - exit\n1 - create new book\n2 - update book\n3 - delete book\n4 - print specific book\n5 - print all books");
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "0":
                        System.exit(0);
                    case "1":
                        createItem();
                        break;
                    case "2":
                        updateItem();
                        break;
                    case "3":
                        deleteItem();
                        break;
                    case "4":
                        printItem();
                    case "5":
                        printAllItems();

                }
                System.out.println("Choose option:\n0 - exit\n1 - create new book\n2 - update book\n3 - delete book\n4 - print specific book\n5 - print all books");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
