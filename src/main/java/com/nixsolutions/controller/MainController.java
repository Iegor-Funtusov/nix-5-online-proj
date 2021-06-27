package com.nixsolutions.controller;

import com.nixsolutions.model.Author;
import com.nixsolutions.model.Book;
import com.nixsolutions.service.AuthorBookServiceImpl;
import com.nixsolutions.util.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainController {

    public static void run(){

        AuthorBookServiceImpl authorBookService = new AuthorBookServiceImpl();
        Scanner scanner = new Scanner(System.in);
        authorBookService.createFiles();

        while (true) {

            System.out.println("Please, choose the operation and input 1-5 to " +
                    "select and then press Enter.\n" +
                    "\n1 - Works with Authors." +
                    "\n2 - Works with Books." +
                    "\n3 - Combined Operation." +
                    "\n4 - Run COMMON TEST." +
                    "\n5 - Exit.");
            String a = scanner.next();
            char[] cur = a.toCharArray();
            switch (cur[0]) {
                case '1': {

                    System.out.println("Нou have chosen to work with Authors." +
                            "\n Please, choose the operation with Authors and input 1-5 to " +
                            "select and then press Enter.\n" +
                            "\n1 - Create Author." +
                            "\n2 - Change Author's Data." +
                            "\n3 - Delete Author." +
                            "\n4 - Get all info on Authors." +
                            "\n5 - Exit.");
                    a = scanner.next();
                    cur = a.toCharArray();
                    switch (cur[0]) {
                        case '1': {

                            Author author = new Author();
                            System.out.println("Please input Author's First Name and press Enter.");
                            author.setFirstName(UserInput.stringInput());
                            System.out.println("Please input Author's Last Name and press Enter.");
                            author.setLastName(UserInput.stringInput());
                            author.setIsVisible(true);
                            authorBookService.createAuthor(author);
                            System.out.println("Author created!");
                            break;
                        }
                        case '2': {

                            System.out.println("Please, input Author's Id and Press Enter!");
                            String id = UserInput.stringInput();
                            if ((authorBookService.findAuthorById(id)) == null) {
                                System.out.println("Wrong Id!");
                                break;
                            }
                            Author author = authorBookService.findAuthorById(id);
                            System.out.println("\n Please, choose what you want to do and input 1-3 to " +
                                    "select and then press Enter.\n" +
                                    "\n1 - Change First Name." +
                                    "\n2 - Change Last Name." +
                                    "\n3 - Exit");
                            String str = scanner.next();
                            char[] cur_e = str.toCharArray();
                            switch (cur_e[0]) {
                                case '1': {

                                    System.out.println("Please, input new First Name and press Enter.");
                                    author.setFirstName(UserInput.stringInput());
                                    authorBookService.updateAuthor(author);
                                    System.out.println(" First Name has been changed to " + author.getFirstName());
                                    break;
                                }
                                case '2': {

                                    System.out.println("Please, input new Last Name and press Enter.");
                                    author.setLastName(UserInput.stringInput());
                                    authorBookService.updateAuthor(author);
                                    System.out.println(" Last Name has been changed to " + author.getLastName());
                                    break;
                                }
                                case '3': {
                                    System.exit(0);
                                    break;
                                }
                                default:
                                    System.out.println("Wrong Input!");
                            }
                            break;
                        }
                        case '3': {

                            System.out.println("Please, input Author's Id and press Enter.");
                            try {
                                authorBookService.deleteAuthor(scanner.next());
                            } catch (RuntimeException ex) {
                                System.err.println("Wrong Id!");
                            }
                            System.out.println("Author was deleted!");
                            break;
                        }
                        case '4': {

                            List<Author> authors = authorBookService.readAllAuthors();
                            for (Author author : authors) {
                                System.out.println(author);
                            }
                            break;
                        }
                        case '5': {

                            break;
                        }
                        default: {

                            System.out.println("Wring Input! Try again!");
                            break;
                        }
                    }
                    break;
                }
                case '2': {

                    System.out.println("Нou have chosen to work with Authors." +
                            "\n Please, choose the operation with Authors and input 1-5 to " +
                            "select and then press Enter.\n" +
                            "\n1 - Create Book." +
                            "\n2 - Change Book's Data." +
                            "\n3 - Delete Book." +
                            "\n4 - Get all info on Books." +
                            "\n5 - Exit.");

                    a = scanner.next();
                    cur = a.toCharArray();

                    switch (cur[0]) {
                        case '1': {

                            Book book = new Book();
                            System.out.println("Please, input Book's name and press Enter.");
                            book.setTitle(UserInput.stringInput());
                            book.setIsVisible(true);

                            List<String> authorId = new ArrayList<>();
                            System.out.println("Please, input a number of Authors (must be more then 1)" +
                                    " and press Enter.:");
                            int i = scanner.nextInt();
                            for (int j = 0; j < i; j++) {
                                System.out.println("Please, input Author's Id and press Enter");
                                String s = UserInput.stringInput();
                                authorId.add(s);
                            }
                            book.setAuthorList(authorId);
                            authorBookService.createBook(book,authorId);
                            System.out.println("Book created!");
                            break;
                        }
                        case '2': {

                            System.out.println("Please, input Book's Id and press Enter!");
                            String id = UserInput.stringInput();
                            if ((authorBookService.findBookById(id)) == null) {
                                System.out.println("Wrong Id!");
                                break;
                            }
                            Book book = authorBookService.findBookById(id);
                            System.out.println("\n Please, choose what you want to do and input 1-2 to " +
                                    "select and then press Enter.\n" +
                                    "\n1 - Change Title." +
                                    "\n3 - Exit");
                            String b = scanner.next();
                            char[] cur_e = b.toCharArray();
                            switch (cur_e[0]) {
                                case '1': {

                                    System.out.println("Please, input new Book's name and press Enter.");
                                    book.setTitle(UserInput.stringInput());
                                    authorBookService.updateBook(book);
                                    System.out.println(" Title has been changed to " + book.getTitle());
                                    break;
                                }
                                case '2': {

                                    System.exit(0);
                                    break;
                                }
                                default:

                                    System.out.println("Wrong Input!");
                            }
                            break;
                        }
                        case '3': {

                            System.out.println("Please, input Book's Id and press Enter.");
                            try {
                                authorBookService.deleteBook(UserInput.stringInput());
                            } catch (RuntimeException ex) {
                                System.err.println("Wrong Id!");
                            }
                            break;
                        }
                        case '4': {

                            List<Book> books = authorBookService.readAllBooks();
                            for (Book book : books) {
                                System.out.println(book);
                            }
                            break;
                        }
                        case '5': {

                            System.exit(0);
                            break;
                        }
                        default: {

                            System.out.println("Wrong input! Try again!");
                        }
                    }
                    break;
                }
                case '3': {

                    System.out.println("Нou have chosen to work with Combined Operations." +
                            "\n Please, choose the operation and input 1-3 to " +
                            "select and then press Enter.\n" +
                            "\n1 - Find Book by Author." +
                            "\n2 - Find Author by Book." +
                            "\n3 - Exit");
                    a = scanner.next();
                    cur = a.toCharArray();
                    switch (cur[0]) {
                        case '1': {

                            System.out.println("Please, input Author's id and press Enter.");
                            String authorId = UserInput.stringInput();
                            if(authorBookService.findAuthorById(authorId) == null){
                                System.out.println("Can't find Author with id - " + authorId);
                                break;
                            }
                            List<Book> books = authorBookService.findBookByAuthor(authorId);
                            for (Book book : books) {
                                System.out.println(book);
                            }
                            if (books.get(0) == null){
                                System.out.println("This author has no books!");
                                break;
                            }
                            break;
                        }
                        case '2': {

                            System.out.println("Please, input Book's id and press Enter.");
                            String bookId = UserInput.stringInput();
                            if(authorBookService.findBookById(bookId) == null){
                                System.out.println("Can't find Books with id" + bookId);
                                break;
                            }
                            List<Author> authors = authorBookService.findAuthorByBook(bookId);
                            for (Author author : authors) {
                                System.out.println(author);
                            }
                            break;
                        }
                        case '3': {

                            System.exit(0);
                            break;
                        }
                        default: {

                            System.out.println("Wrong input! Try again!");
                        }
                    }
                    break;
                }
                case '4': {
                    System.out.println("Creating of Authors.");
                    for (int i = 0; i < 10; i++) {
                        Author author = new Author();
                        author.setFirstName("First Name" + i);
                        author.setLastName("Last Name" + i);
                        author.setIsVisible(true);
                        authorBookService.createAuthor(author);
                    }
                    System.out.println("Reading of Authors from the file.");
                    List<Author> authors = authorBookService.readAllAuthors();
                    for (Author author : authors) {
                        System.out.println(author);
                    }

                    System.out.println("Creating of Books");
                    for (int i = 0; i < 5; i++) {
                        Author author = authorBookService.readAllAuthors().get(i);
                        Author author2 = authorBookService.readAllAuthors().get((i*2));
                        List<String> authorId = new ArrayList<>();
                        authorId.add(author.getId());
                        authorId.add(author2.getId());
                        Book book = new Book();
                        book.setTitle("Book" + i);
                        book.setIsVisible(true);
                        book.setAuthorList(authorId);
                        authorBookService.createBook(book, authorId);
                    }

                    System.out.println("Reading of Books from the file.");
                    List<Book> books = authorBookService.readAllBooks();
                    for (Book book : books) {
                        System.out.println(book);
                    }

                    System.out.println("Read Book by Id. ");
                    Book book = authorBookService.readAllBooks().get(5);
                    System.out.println(book);
                    List<String> authorId = book.getAuthorList();
                    for (String id : authorId) {
                        Author author = authorBookService.findAuthorById(id);
                        System.out.println(author);
                    }

                    System.out.println("///////////////////////////////////////////////////////////////////////////\n");
                    System.out.println("Find Author by Book.");
                    System.out.println("Book's id - " + book.getId());
                    List<Author> authorByBook= authorBookService.findAuthorByBook(book.getId());
                    for (Author author : authorByBook) {
                        System.out.println(author);
                    }
                    String idByBook = authorByBook.get(0).getId();
                    System.out.println(" Find Book by Author.");
                    System.out.println("Authors Id - " + idByBook);
                    List<Book> BookByAuthor = authorBookService.findBookByAuthor(idByBook);
                    for (Book book1 : BookByAuthor) {
                        System.out.println(book1);
                    }

                    System.out.println("/////////////////////////////////////////////////////////////////////////\n\n");

                    System.out.println(authorBookService.findBookById(book.getId()));
                    System.out.println("Changing Book's Title.");
                    book.setTitle("New Title");
                    authorBookService.updateBook(book);
                    System.out.println("Reading form the file.");
                    System.out.println(authorBookService.findBookById(book.getId()));
                    System.out.println("Deleting Book.");
                    authorBookService.deleteBook(book.getId());
                    System.out.println("Reading from the file.");
                    System.out.println(authorBookService.findBookById(book.getId()));
                    System.out.println("________________________________________________________\n\n");
                    System.out.println("Origin Author: ");
                    System.out.println(authorBookService.findAuthorById(idByBook));
                    System.out.println("Changing Author's First and Last Name.");
                    Author newAuthor = authorBookService.findAuthorById(idByBook);
                    newAuthor.setFirstName("Vlad");
                    newAuthor.setLastName("Shahno");
                    authorBookService.updateAuthor(newAuthor);
                    System.out.println("Reading Author from the file.");
                    System.out.println(authorBookService.findAuthorById(idByBook));
                    System.out.println("Deleting Author.");
                    authorBookService.deleteBook(idByBook);
                    System.out.println("Reading from the file.");
                    System.out.println(authorBookService.findAuthorById(idByBook));
                    break;
                }
                case '5': {

                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Wrong input! Try again!");
                    break;
                }
            }
        }
    }
}
