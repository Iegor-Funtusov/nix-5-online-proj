package ua.com.alevel.util;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AuthorUtil {

    public static Author createAuthor(BufferedReader reader) {
        Author Author;
        while (true) {
            Author = getAuthor(reader);
            if(Author != null)
                break;
            System.out.println("Something went wrong. Try again.");
        }
        return Author;
    }

    public static Author getAuthor(BufferedReader reader) {
        String name;
        int age;//
        try {
            System.out.print("Please enter Author name: ");
            name = reader.readLine();
            System.out.print("Please enter Author age: ");
            age = Integer.parseInt(reader.readLine());//
            return new Author(name, age);
        }catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Author readAuthor(AuthorService authorService, BufferedReader reader) {
        Author readAuthor;
        while (true) {
            try {
                System.out.println("Please enter the author id :");
                System.out.print("--> ");
                readAuthor = authorService.read(reader.readLine());
            } catch (RuntimeException | IOException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if(readAuthor == null){
                System.out.println("Invalid id!");
                continue;
            }
            break;
        }
        return readAuthor;
    }

    public static void updateAuthor(Author readAuthor, AuthorService authorService, BufferedReader reader) {
        System.out.println("Enter new parameters for the author.");
        Author authorForUpdate = AuthorUtil.createAuthor(reader);
        authorForUpdate.setId(readAuthor.getId());
        authorService.update(authorForUpdate);
    }

    public static void printAllAuthors(AuthorService authorService){
        Collection<Author> authors = authorService.read();
        if (authors.size() == 0) {
            System.out.println("Empty!");
            return;
        }
        System.out.println("Authors:");
        for (Author author : authors) {
            System.out.println(author);
        }
    }

    public static void addBooks(Author author, BookService bookService, AuthorService authorService, BufferedReader reader) {
        System.out.println("Choose exist books or add new books before!");
        String flag = "";
        do{
            Book book = BookUtils.readBook(bookService, reader);
            if(verifyAdding(author, book, reader)) {
                author.getBooks().add(book);
                book.getAuthors().add(author);
                authorService.update(author);
            }
            System.out.print("Enter \"1\" to continue adding ->");
            try {
                flag = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }while(flag.equals("1"));
    }

    private static boolean verifyAdding(Author author, Book book, BufferedReader reader ) {
        System.out.println("Do you want to add this book - " + book +
                "\nto this author - " + author + "?" +
                "\n1 - yes" +
                "\n2 - no" +
                "\n-> ");
        try {
            if (reader.readLine().equals("1"))
                return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static void deleteAuthor(Author author, BookService bookService, AuthorService authorService) {
        List<Book> books = author.getBooks();
        for (Book book : books) {
            Iterator<Author> iter = book.getAuthors().listIterator();
            while (iter.hasNext()){
                if (iter.next().equals(author)) {
                    iter.remove();
                }
            }
            bookService.update(book);
        }
        authorService.delete(author);
    }

    public static void remove(Author author, Book bookForRemoving) {
        removeBook(author, bookForRemoving);
        BookUtils.removeAuthor(bookForRemoving, author);
    }

    public static void removeBook(Author author, Book bookForRemoving) {
        List<Book> books = author.getBooks();
        Iterator<Book> iterB = books.listIterator();
        while (iterB.hasNext()){
            if (iterB.next().equals(bookForRemoving)) {
                iterB.remove();
            }
        }
    }

}
