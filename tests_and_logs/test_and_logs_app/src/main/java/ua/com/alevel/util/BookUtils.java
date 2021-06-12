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

public class BookUtils {

    public static Book createBook(BufferedReader reader) {
        Book book;
        while (true) {
            book = getBook(reader);
            if(book != null)
                break;
            System.out.println("Something went wrong. Try again.");
        }
        return book;
    }

    private static Book getBook(BufferedReader reader) {
        String title;
        try {
            System.out.print("Please enter book title: ");
            title = reader.readLine();//
            return new Book(title);
        }catch (NumberFormatException e) {
        System.out.println(e.getMessage());
        }catch (IOException e) {
        System.out.println(e.getMessage());
        }
        return null;
    }

    public static Book readBook(BookService bookService, BufferedReader reader) {
        Book readBook;
        while (true) {
            try {
                System.out.println("Please enter book id:");
                System.out.print("--> ");
                readBook = bookService.read(reader.readLine());
            } catch (RuntimeException | IOException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if(readBook == null){
                System.out.println("Invalid id!");
                continue;
            }
            break;
        }
        return readBook;
    }

    public static void updateBook(Book readBook, BookService bookService, BufferedReader reader) {
        System.out.println("Enter new parameters for the book.");
        Book bookForUpdate = BookUtils.createBook(reader);
        bookForUpdate.setId(readBook.getId());
        bookService.update(bookForUpdate);
        System.out.println("User updated!");
    }

    public static void printAllBooks(BookService bookService){
        Collection<Book> books = bookService.read();
        if (books.size() == 0) {
            System.out.println("Empty!");
            return;
        }
        System.out.println("Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void addAuthors(Book readBook, BookService bookService, AuthorService authorService, BufferedReader reader) {
        System.out.println("Choose exist author or add new author before!");
        String flag;
        do{
            Author author = AuthorUtil.readAuthor(authorService, reader);
            if(verifyAdding(author, readBook, reader)) {
                readBook.getAuthors().add(author);
                author.getBooks().add(readBook);
                bookService.update(readBook);
                authorService.update(author);
            }
            System.out.print("Enter \"1\" to continue adding ->");
            try {
                flag =reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }while(flag.equals("1"));
    }

    private static boolean verifyAdding(Author author, Book book, BufferedReader reader ) {
        System.out.println("Do you want to add this author - " + author +
                "\nto this book - " + book +
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

    public static void deleteBook(Book book, BookService bookService, AuthorService authorService) {
        List<Author> authors = book.getAuthors();
        for (Author author : authors) {
            Iterator<Book> iter = author.getBooks().listIterator();
            while (iter.hasNext()){
                if (iter.next().equals(book)) {
                    iter.remove();
                }
            }
            authorService.update(author);
        }
        bookService.delete(book);
    }

    public static void remove(Book bookForRemoving, Author author) {
        removeAuthor(bookForRemoving, author);
        AuthorUtil.removeBook(author, bookForRemoving);
    }

    public static void removeAuthor(Book bookForRemoving, Author author) {
        List<Author> authors = bookForRemoving.getAuthors();
        Iterator<Author> iterA = authors.listIterator();
        while (iterA.hasNext()){
            if (iterA.next().equals(author)) {
                iterA.remove();
            }
        }
    }
}
