package services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import data.Author;
import data.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static services.AuthorService.AUTHORS;
import static services.BookService.BOOKS;

public class LibraryService { // add creating, fix and add deleting

    private static final Logger loggerWarnBook = LoggerFactory.getLogger("warnBook");
    private static final Logger loggerErrorBook = LoggerFactory.getLogger("errorBook");

    private static final Logger loggerErrorAuthor = LoggerFactory.getLogger("errorAuthor");


    public static void createBook(Book book){
        BookService.create(book);

        String Authors = book.getListOfAuthors();
        String[] authors = Authors.split(",");
        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            boolean flag = false;
            for (int i = 0; i < authors.length; i++) {
                List<String[]> read = reader.readAll();
                for(String[] r : read) {
                    if (r[2].equalsIgnoreCase(authors[i].trim())) {
                        flag = true;
                    }
                }
                if (flag == false) {
                    Author author = new Author();
                    String[] fullName = authors[i].trim().split(" ");
                    if(fullName.length != 2){
                        loggerWarnBook.warn("Incorrect input. Input must contain first and last names");
                        System.out.println("Incorrect input. Input must contain first and last names");
                        return;
                    }
                    author.setFirstName(fullName[0]);
                    author.setLastName(fullName[1]);
                    author.setListOfBooks(book.getName());
                    AuthorService.create(author);
                }
                flag = false;
            }
        } catch (IOException | CsvException e) {
            loggerErrorBook.error("Error when program tried to read rows in file");
            e.printStackTrace();
        }
    }

    public static void createAuthor(Author author){
        AuthorService.create(author);

        String string = author.getListOfBooks();
        String[] Books = string.split(",");
        String fullName = author.getFirstName()+ " " + author.getLastName();
        int counter = 0;
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS));) {
            for (int i = 0; i < Books.length; i++) {
                List<String[]> read = reader.readAll();
                for(String[] r : read) {
                    if(r[1].equalsIgnoreCase(Books[i].trim())){
                        if(!r[2].contains(fullName)){
                            Book book = new Book();
                            book.setName(Books[i].trim());
                            book.setListOfAuthors(fullName);
                            BookService.create(book);
                        }
                        continue;
                    }
                    counter++;
                }
                if(counter == read.size()){
                    Book book = new Book();
                    book.setName(Books[i].trim());
                    book.setListOfAuthors(fullName);
                    BookService.create(book);
                }
                counter = 0;
            }
        } catch (IOException | CsvException e) {
            loggerErrorAuthor.error("Error when program tried to read rows in file");
            e.printStackTrace();
        }
    }

    public static void readAllBooks(){
        BookService.readAll();
    }

    public static void readAllBooks(String name){
        BookService.readAll(name);
    }

    public static void readAllAuthors(){
        AuthorService.readAll();
    }

    public static void readAllAuthors(String name){
        AuthorService.readAll(name);
    }

    public static void updateBook(String bookName, String authorName, String newInput, int choice){
        BookService.update(bookName, authorName, newInput, choice);
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if (r[1].equalsIgnoreCase(newInput) && r[2].contains(authorName)) {
                    if (choice == 1) {
                        String[] authors = r[2].split(",");
                        for (int i = 0; i < authors.length; i++) {
                            AuthorService.update(bookName, authors[i].trim(), newInput, choice);
                        }
                    }
                }
            }
        }
        catch (IOException | CsvException e){
            loggerErrorBook.error("Error when program tried to read/write rows in file (updating data (Library))");
            e.printStackTrace();
        }
    }


    public static void updateAuthor(String bookName, String authorName, String newInput, int choice){
        AuthorService.update(bookName, authorName, newInput, choice);
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if (r[2].contains(authorName)) {
                    if(choice == 1)
                        BookService.update(bookName, authorName, newInput, choice);
                    else{
                        BookService.update(r[1], authorName, newInput, 2);
                    }
                }
            }
        }
        catch (IOException | CsvException e){
            loggerErrorAuthor.error("Error when program tried to read/write rows in file (updating data (Library))");
            e.printStackTrace();
        }
    }

    public static void deleteBook(String bookName, String authorName){
        BookService.delete(bookName, authorName);
    }

    public static void deleteAuthor(String name){
        AuthorService.delete(name);
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[2].equalsIgnoreCase(name)){
                    BookService.delete(r[1], r[2]);
                }
                else if(r[2].contains(name)){
                    if(checkAuthorsVisibility(r[2], name) == true){
                        BookService.delete(r[1], r[2]);
                    }
                }
            }
        } catch (IOException | CsvException e) {
            loggerErrorAuthor.error("Error when program tried to read/write rows in file (updating data (Library-deleteAuthor))");
            e.printStackTrace();
        }
    }

    private static boolean checkAuthorsVisibility(String Authors, String name){
    String[] authors = Authors.split(",");
        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            for (int i = 0; i < authors.length; i++) {
                List<String[]> read = reader.readAll();
                for (String[] r : read) {
                    if(r[2].equalsIgnoreCase(authors[i].trim())) {
                        if(!r[4].equals("INVISIBLE"))
                            return false;
                    }
                }
            }
        }
        catch (IOException | CsvException e){
            loggerErrorAuthor.error("Error when program tried to read/write rows in file (updating data (checkAuthorsVis))");
            e.printStackTrace();
        }
        return true;
    }
}
