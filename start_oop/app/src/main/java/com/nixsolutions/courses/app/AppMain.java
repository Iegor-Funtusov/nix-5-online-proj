package com.nixsolutions.courses.app;

import java.util.Collection;

public class AppMain {

    public static void main(String[] args) {
        System.out.println("AppMan.main");

        BookService bookService = new BookService();

        Book book1 = new Book();
        book1.setYear(1937);
        book1.setTitle("The Hobbit");

        Book book2 = new Book();
        book2.setYear(2015);
        book2.setTitle("Big Magic");

        bookService.create(book1);
        bookService.create(book2);

        Collection<Book> list = bookService.readAll();
        list.forEach(System.out::println);

        for (Book book : list) {
            if(book.getTitle().equals("Big Magic")) {
                book.setYear(2018);
                bookService.update(book);
            }
        }

        list = bookService.readAll();
        list.forEach(System.out::println);
    }

}
