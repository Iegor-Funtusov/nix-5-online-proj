package services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import data.Author;
import data.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static services.AuthorService.AUTHORS;
import static services.BookService.BOOKS;

public class LibraryService {

    private static final Logger loggerWarnBook = LoggerFactory.getLogger("warnBook");
    private static final Logger loggerErrorBook = LoggerFactory.getLogger("errorBook");

    private static final Logger loggerWarnAuthor = LoggerFactory.getLogger("warnAuthor");
    private static final Logger loggerErrorAuthor = LoggerFactory.getLogger("errorAuthor");


    public static void createBook(Book book){

        // написать Common test
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
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            boolean flag = false;
            for (int i = 0; i < Books.length; i++) {
                List<String[]> read = reader.readAll();
                for(String[] r : read) {
                    if(r[1].equalsIgnoreCase(Books[i].trim()) && r[2].contains(fullName)){
                        flag = true;
                    }
                }
                if(flag == false){
                    Book book = new Book();
                    book.setName(Books[i].trim());
                    book.setListOfAuthors(fullName);
                    BookService.create(book);
                }
            }
        } catch (IOException | CsvException e) {
            loggerErrorAuthor.error("Error when program tried to read rows in file");
            e.printStackTrace();
        }
    }

    public static void readAllBooks(){
        if(!checkFileBooks()){
            return;
        }
        BookService.readAll();
    }

    public static void readAllBooks(String name){
        if(!checkFileBooks()){
            return;
        }
        BookService.readAll(name);
    }

    public static void readAllAuthors(){
        if(!checkFileAuthors()){
            return;
        }
        AuthorService.readAll();
    }

    public static void readAllAuthors(String name){
        if(!checkFileAuthors()){
            return;
        }
        AuthorService.readAll(name);
    }

    public static void updateBook(String bookName, String authorName, String newInput, int choice){
        if(!checkFileBooks() || !checkFileAuthors()){
            return;
        }
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
        if(!checkFileBooks() || !checkFileAuthors()){
            return;
        }
        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            String[] fullName = newInput.split(" ");
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if (choice == 2 && r[1].equalsIgnoreCase(fullName[0])
                        && r[2].equalsIgnoreCase(fullName[1]) && !r[4].contains("INVISIBLE")) {
                    loggerWarnAuthor.warn("Such author already exists. Nothing will be changed");
                    System.out.println("Such author already exists. Nothing will be changed");
                    return;
                }
            }
            AuthorService.update(bookName, authorName, newInput, choice);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if (r[2].contains(authorName)) {
                    if(choice == 1) {
                        BookService.update(bookName, authorName, newInput, choice);
                        break;
                    }
                    else{
                        BookService.update(r[1], authorName, newInput, 2);
                        break;
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
        if(!checkFileBooks() || !checkFileAuthors()){
            return;
        }
        BookService.delete(bookName, authorName, true);
    }

    public static void deleteAuthor(String name){
        if(!checkFileBooks() || !checkFileAuthors()){
            return;
        }
        AuthorService.delete(name);
        name = name.trim();
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[2].equalsIgnoreCase(name)){
                    BookService.delete(r[1], r[2], true);
                }
                else if(r[2].contains(name)){
                    if(checkAuthorsVisibility(r[2], name) == false){
                        BookService.delete(r[1], r[2], false);
                    }
                    else
                        BookService.delete(r[1], r[2], true);
                }
            }
        } catch (IOException | CsvException e) {
            loggerErrorAuthor.error("Error when program tried to read/write rows in file (updating data (Library-deleteAuthor))");
            e.printStackTrace();
        }
    }

    private static boolean checkAuthorsVisibility(String Authors, String name){
        String[] authors = Authors.split(", ");
        String[] nameF = name.split(" ");
        for (int i = 0; i < authors.length; i++) {
            try (CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
                List<String[]> read = reader.readAll();
                authors[i].trim();
                String[] fullName = authors[i].split(" ");
                for (String[] r : read) {
                    if (r[1].equalsIgnoreCase(fullName[0]) && r[2].equalsIgnoreCase(fullName[1]) &&
                            !r[1].equalsIgnoreCase(nameF[0]) && !r[2].equalsIgnoreCase(nameF[1])) {
                        if (r[4].equals("VISIBLE"))
                            return false;
                    }
                }
            } catch (IOException | CsvException e) {
                loggerErrorAuthor.error("Error when program tried to read/write rows in file (updating data (checkAuthorsVis))");
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean checkFileBooks(){
        if(!(new File(BOOKS).exists())){
            loggerWarnBook.warn("File doesn't exist");
            System.out.println("File doesn't exists. Please, create book or author.");
            return false;
        }
        return true;
    }

    public static boolean checkFileAuthors(){
        if(!(new File(AUTHORS).exists())){
            loggerWarnAuthor.warn("File doesn't exist");
            System.out.println("File doesn't exists. Please, create book or author.");
            return false;
        }
        return true;
    }
}
