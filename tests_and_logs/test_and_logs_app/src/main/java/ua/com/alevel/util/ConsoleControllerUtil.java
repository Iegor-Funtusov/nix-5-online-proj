package ua.com.alevel.util;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.Relation;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.RelationService;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleControllerUtil {

    public static void createBook(BookService bookService, BufferedReader reader) {
        try {
            bookService.create(BookUtil.createBook(reader));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createAuthor(AuthorService authorService, BufferedReader reader) {
        try {
            authorService.create(AuthorUtil.createAuthor(reader));
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void createRelation(BookService bookService, AuthorService authorService, RelationService relationService, BufferedReader reader) {
        try {
            System.out.println("Please choose the book and the author to create the relation between them.");
            printAllBooks(bookService);
            printAllAuthors(authorService);
            Book book = BookUtil.readBook(bookService, reader);
            Author author = AuthorUtil.readAuthor(authorService, reader);
            RelationUtil.createRelation(book, author, relationService);
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateBook(BookService bookService, BufferedReader reader) {
       try {
            printAllBooks(bookService);
            Book readBook = BookUtil.readBook(bookService, reader);
            BookUtil.updateBook(readBook, bookService, reader);
            System.out.println("Book updated!");
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void updateAuthor(AuthorService authorService, BufferedReader reader) {
        try {
            printAllAuthors(authorService);
            Author readAuthor = AuthorUtil.readAuthor(authorService, reader);
            AuthorUtil.updateAuthor(readAuthor, authorService, reader);
            System.out.println("Author updated!");
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void printAllBooks(BookService bookService) {
        try {
            System.out.println("\t\tBooks");
            BookUtil.printAllBooks(bookService);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    public static void printAllAuthors(AuthorService authorService) {
        try {
            System.out.println("\t\tAuthors");
            AuthorUtil.printAllAuthors(authorService);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    public static void printAllRelations(RelationService relationService) {
        try {
            System.out.println("\t\tRelations");
            RelationUtil.printAllRelations(relationService);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    public static void deleteBook(BookService bookService, RelationService relationService, BufferedReader reader) {
        try{
            printAllBooks(bookService);
            Book book = BookUtil.readBook(bookService, reader);
            BookUtil.deleteBook(book, bookService, relationService);
            System.out.println("Book deleted!");
            printAllBooks(bookService);
        }catch (RuntimeException | IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAuthor(AuthorService authorService, RelationService relationService, BufferedReader reader) {
        try {
            printAllAuthors(authorService);
            Author author = AuthorUtil.readAuthor(authorService, reader);
            AuthorUtil.deleteAuthor(author, authorService, relationService);
            System.out.println("Author deleted!");
            printAllAuthors(authorService);
        }catch (RuntimeException | IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void removeBook(AuthorService authorService, BookService bookService, RelationService relationService, BufferedReader reader) {
        try {
            printAllAuthorsWithBooks(bookService, authorService, relationService);
            System.out.println("Choose the author and his book that you want to remove:");
            Author author = AuthorUtil.readAuthor(authorService, reader);
            RelationUtil.printAllBooksForAuthor(author, relationService.read());
            System.out.println("Which book?");
            Book book = BookUtil.readBook(bookService, reader);
            RelationUtil.deleteRelationByBookAndAuthor(book, author, relationService);
            RelationUtil.printAllBooksForAuthor(author, relationService.read());
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeAuthor(AuthorService authorService, BookService bookService, RelationService relationService, BufferedReader reader) {
        try {
            printAllBooksWithAuthors(bookService, authorService, relationService);
            System.out.println("Choose the book and its author you want to remove:");
            Book book = BookUtil.readBook(bookService, reader);
            RelationUtil.printAllAuthorsForBook(book, relationService.read());
            System.out.println("Which author?");
            Author author = AuthorUtil.readAuthor(authorService, reader);
            System.out.println(author);
            RelationUtil.deleteRelationByBookAndAuthor(book, author, relationService);
            RelationUtil.printAllAuthorsForBook(book, relationService.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printAllBooksWithAuthors(BookService bookService, AuthorService authorService, RelationService relationService) {
        try{
            Object[] books = bookService.read();
            Object[] authors = authorService.read();
            Object[] relations = relationService.read();
            for (int i = 0; i < books.length; i++) {
                if(RelationUtil.isRelationExist((Book) books[i],(Author) authors[i], relations)) {
                    RelationUtil.printAllAuthorsForBook((Book) books[i], relations);
                }
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    public static void printAllAuthorsWithBooks(BookService bookService, AuthorService authorService, RelationService relationService) {
        try{
            Object[] authors = authorService.read();
            Object[] books = bookService.read();
            Object[] relations = relationService.read();
            for (int i = 0; i < authors.length; i++) {
                if(RelationUtil.isRelationExist((Book) books[i],(Author) authors[i], relations)) {
                    RelationUtil.printAllBooksForAuthor((Author) authors[i], relations);
                }
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    public static void deleteRelation(RelationService relationService, BufferedReader reader) {
        try {
            printAllRelations(relationService);
            Relation relation = RelationUtil.readRelation(relationService, reader);
            RelationUtil.deleteRelation(relation, relationService);
            System.out.println("Relation deleted!");
            printAllRelations(relationService);
        }catch (RuntimeException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}
