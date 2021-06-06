package ua.com.threadedcode.cli;

import ua.com.threadedcode.entity.Author;
import ua.com.threadedcode.entity.Book;
import ua.com.threadedcode.service.AuthorService;
import ua.com.threadedcode.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class CommandLine {
    private AuthorService authorService = new AuthorService();
    private BookService bookService = new BookService();

    public void showMenu() {
        System.out.print("======================================== \n" +
                "0: exit, 1: create author, 2: create book \n" +
                "3. show all authors, 4. show all books,   \n" +
                "5. find book by author, 6: link book to author \n" +
                "7: link author to book, 8: edit author firstname and lastname\n" +
                "9: edit book title, 10: delete author  \n" +
                "11: delete book, 12: delete book with linked authors  \n" +
                ">"
        );
    }

    public String readConsole() {
        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = reader.readLine();

            if (input.equals("")) {
                System.out.println(" input is empty, add some data");
                run();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    private Author addAuthor() {
        String input = readConsole();
        String[] inputs = input.split(",");
        try {
            return new Author(inputs[0], inputs[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" name can't be empty, use ',' to separate firstname and lastname");
            run();
        }
        return null;
    }

    private Book addBook() {
        String input = readConsole();
        return new Book(input);
    }

    private void print(Collection list) {
        list.forEach(System.out::println);
    }

    private void menu() {
        String input = readConsole();
        switch (input) {
            case "0":
                break;
            case "1":
                System.out.print("Enter firstname and lastname, separate them by ',' \n>");
                authorService.createAuthor(addAuthor());
                run();
                break;
            case "2":
                System.out.print("Enter book title \n>");
                bookService.createBook(addBook());
                run();
                break;
            case "3":
                print(authorService.getAllAuthors());
                run();
                break;
            case "4":
                print(bookService.getAllBooks());
                run();
                break;
            case "5":
                System.out.print("Enter author firstname \n>");
                input = readConsole();
                System.out.print("All book by author's " + input + "\n");
                try {
                    print(authorService.findBooksByAuthorsFirstName(input));
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                run();
                break;
            case "6":
                System.out.println("enter book title \n>");
                input = readConsole();
                try {
                    Book book = bookService.findBookByTitle(input);
                    System.out.print("enter author firstname \n>");
                    input = readConsole();
                    Author author = authorService.getAuthorByName(input);
                    book.setAuthorId(author.getId());
                    bookService.updateBook(book);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                run();
                break;
            case "7":
                System.out.print("enter author firstname \n>");
                input = readConsole();
                try {
                    Author author = authorService.getAuthorByName(input);
                    System.out.println("enter book title \n>");
                    input = readConsole();
                    Book book = bookService.findBookByTitle(input);
                    author.setBookId(book.getId());
                    authorService.updateAuthor(author);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                run();
                break;
            case "8":
                System.out.print("Enter author firstname \n>");
                input = readConsole();
                try {
                    Author author = authorService.getAuthorByName(input);
                    System.out.print("Enter new  firstname and lastname, separate them by ',' \n>");
                    input = readConsole();
                    String[] inputs = input.split(",");
                    author.setFirstName(inputs[0]);
                    author.setLastName(inputs[0]);
                    authorService.updateAuthor(author);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                run();
                break;
            case "9":
                System.out.print("Enter book title \n>");
                input = readConsole();
                try {
                    Book book = bookService.findBookByTitle(input);
                    book.setTitle(input);
                    bookService.updateBook(book);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                run();
                break;
            case "10":
                System.out.print("Enter author firstname \n>");
                input = readConsole();
                try {
                    authorService.deleteAuthor(input);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                run();
                break;
            case "11":
                System.out.print("Enter book title \n>");
                input = readConsole();
                try {
                    bookService.deleteBook(input);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                run();
                break;
            case "12":
                System.out.print("Enter book title \n>");
                String title = readConsole();
                System.out.print("Enter author firstname \n>");
                String name = readConsole();
                try {
                    bookService.deleteBookWithAuthor(title, name);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                run();
                break;
        }
    }

    public void run() {
        System.out.print("0: exit, 1: show menu \n>");
        String input = readConsole();
        while (input != null) {
            switch (input) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    showMenu();
                    menu();
                    break;
            }
            run();
        }
    }
}
