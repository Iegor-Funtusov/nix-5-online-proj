package services;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import data.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static services.BookService.checkFileBook;

public class AuthorService {
    public final static String AUTHORS = "authors.csv";
    private final static String INVISIBLE = "INVISIBLE";
    private final static String VISIBLE = "VISIBLE";
    private static int indexInFile = 0;

    private static final Logger loggerInfo = LoggerFactory.getLogger("infoAuthor");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warnAuthor");
    private static final Logger loggerError = LoggerFactory.getLogger("errorAuthor");

    public static void create(Author author){ // right creating with adding of books, right index when create
        loggerInfo.info("Adding the Author: " + author.getFirstName() + " " + author.getLastName());
        List<String[]> csv = new ArrayList<>();
        String string = author.getListOfBooks();
        String[] Books = string.split(",");

        checkFileBook();
        checkFileAuthor();

        String[] currentAuthor = new String[5];
        currentAuthor[0] = String.format("%d", ++indexInFile);
        currentAuthor[1] = author.getFirstName();

        currentAuthor[2] = author.getLastName();
        String fullName = currentAuthor[1]+ " " + currentAuthor[2];
        currentAuthor[4] = VISIBLE;

        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            boolean flag1 = false;
            int counter = 0;
            List<String[]> read = reader.readAll();
            String newBooks = findBooks(read, Books, currentAuthor);
            for(String[] r : read) {
                if (r[1].equalsIgnoreCase(currentAuthor[1].trim()) && r[2].equalsIgnoreCase(currentAuthor[2].trim())) {
                    if(!newBooks.isBlank() && newBooks.charAt(0) == ',')
                        newBooks = newBooks.substring(1);
                    r[3] += newBooks;
                    currentAuthor[3] = r[3];
                    currentAuthor[0] = r[0];
                    flag1 = true;
                    break;
                }
                counter++;
            }
            if(counter == read.size()){
                currentAuthor[3] = author.getListOfBooks();
                csv.add(currentAuthor);
            }
            if(flag1 == true){
                loggerWarn.warn("Author \"" + fullName + "\" already exists");
                System.out.println("Such author already exists. New books (if such ones were in input) were added");
            }
        } catch (IOException | CsvException e) {
            loggerError.error("Error when program tried to read rows in file");
            e.printStackTrace();
        }
            try (CSVWriter writer = new CSVWriter(new FileWriter(AUTHORS, true))) {
                writer.writeAll(csv);
            } catch (IOException e) {
                loggerError.error("Error when program tried to write new Author");
                e.printStackTrace();
            }
//        int counter = 0;
//        try(CSVReader reader = new CSVReader(new FileReader(BOOKS));) {
//            for (int i = 0; i < Books.length; i++) {
//                List<String[]> read = reader.readAll();
//                for(String[] r : read) {
//                    if(r[0].equalsIgnoreCase(Books[i].trim())){
//                        if(!r[1].contains(fullName)){
//                            Book book = new Book();
//                            book.setName(Books[i].trim());
//                            book.setListOfAuthors(fullName);
//                            BookService.create(book);
//                        }
//                        continue;
//                    }
//                    counter++;
//                }
//                if(counter == read.size()){
//                    Book book = new Book();
//                    book.setName(Books[i].trim());
//                    book.setListOfAuthors(fullName);
//                    BookService.create(book);
//                }
//                counter = 0;
//            }
//        } catch (IOException | CsvException e) {
//            loggerError.error("Error when program tried to read rows in file");
//            e.printStackTrace();
//        }
        loggerInfo.info("Author was added");
    }

    public static void checkFileAuthor(){
        List<String[]> csv = new ArrayList<>();
        if(!(new File(AUTHORS).exists())){
            loggerWarn.warn("Creating of file");
            String[] header = {"ID", "first name", "last name", "list of books", "visibility"};
            csv.add(header);
            try(CSVWriter writer = new CSVWriter(new FileWriter(AUTHORS, true))) {
                writer.writeAll(csv);
            } catch (IOException e) {
                loggerError.error("Error when program tried to write header");
                e.printStackTrace();
            }
            loggerWarn.warn("File was created");
        }
    }

    private static String findBooks(List<String[]> read, String[] Books, String[] currentAuthor) {
        String newBooks = "";
        for (String[] r : read) {
            if (r[1].equalsIgnoreCase(currentAuthor[1].trim()) && r[2].equalsIgnoreCase(currentAuthor[2].trim())) {
                for (int i = 0; i < Books.length; i++) {
                    if (!r[3].contains(Books[i])) {
                        newBooks += "," + Books[i];
                    }
                    if(i == Books.length-1){
                        return newBooks;
                    }
                }
            }
        }
        return newBooks;
    }

    public static void readAll(){
        if(!checkFileAuthors()){
            return;
        }
        loggerInfo.info("begin of reading of complete file");
//        String authors = "";
        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            List<String[]> read = reader.readAll();
//            for(String[] r : read){
//                String[] row = r[1].split(",");
//                for (int i = 0; i < row.length; i++) {
//                    if(!authors.contains(row[i])){
//                        authors += row[i]+",";
//                    }
//                }
//            }
//            String[] onlyAuthors = authors.split(",");
//            for (int i = 0; i < onlyAuthors.length; i++) {
//                System.out.println(onlyAuthors[i]);
//            }
            for (String[] r : read) {
                if(!(r[4].contains(INVISIBLE)))
                    System.out.println(r[0] + " | " + r[1] + " | " + r[2] + " | " + r[3]);
            }
        } catch (IOException | CsvException e) {
            loggerError.error("Error when program tried to read rows in file");
            e.printStackTrace();
        }
        loggerInfo.info("end of reading of complete file");
    }

    public static void readAll(String name){
        if(!checkFileAuthors()){
            return;
        }
        String[] fullName = name.split(" ");
        loggerInfo.info("begin of reading of complete file");
        String books = "";
        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[1].contains(fullName[0]) && r[2].contains(fullName[1]) &&
                        !(r[4].contains(INVISIBLE))){
                    books += r[3];
                }
            }
            String[] arrBooks = books.split(",");
            if(arrBooks.length == 0){
                loggerWarn.warn("Such author doesn't exist: " + name);
                System.out.println("Such author doesn't exist");
                return;
            }
            System.out.println("All books of this author: ");
            for (int i = 0; i < arrBooks.length; i++) {
                System.out.println(arrBooks[i]);
            }
        } catch (IOException | CsvException e) {
            loggerError.error("Error when program tried to find rows in file related with the certain name");
            e.printStackTrace();
        }
        loggerInfo.info("end of reading of complete file");
    }

    public static void update(String bookName, String authorName, String newInput, int choice){ // check exist such book or author
        if(!checkFileAuthors()){
            return;
        }
        String[] input = authorName.split(" ");
        loggerInfo.info("begin of updating data in file");
        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            int counter = 0;
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[1].equalsIgnoreCase(input[0]) && r[2].equalsIgnoreCase(input[1]) && !r[4].contains(INVISIBLE)){
                    if(choice == 1) {
                        if(r[3].contains(bookName)){
                            r[3] = r[3].replace(bookName, newInput);
                            break;
                        }
                    }
                    if(choice == 2){
                        input = newInput.split(" ");
                        r[1] = input[0];
                        r[2] = input[1];
                        break;
                    }
                }
                counter++;
            }
            if(counter == read.size()){
                loggerWarn.warn("Such record doesn't exist");
                System.out.println("Such record doesn't exist");
                return;
            }
            FileWriter sw = new FileWriter(AUTHORS);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(read);
            writer.close();
//            if(choice == 1){
//                String[] books = newInput.split(",");
//                for (int i = 0; i < books.length; i++) {
//                    BookService.update(bookName, authorName, books[i], choice);
//                }
//            }
//            else {
//                BookService.update(bookName, authorName, newInput, choice);
//            }
        } catch (IOException | CsvException e) {
            loggerError.error("Error when program tried to read/write rows in file (updating data)");
            e.printStackTrace();
        }
        loggerInfo.info("end of updating data in file");
    }

    public static void delete(String name){
        if(!checkFileAuthors()){
            return;
        }
        loggerWarn.warn("begin of deleting");
        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
            List<String[]> read = reader.readAll();
            String[] fullName = name.split(" ");
            boolean flag = false;
            String[] books;
            for (String[] r : read){
                if(r[1].contains(fullName[0]) && r[2].contains(fullName[1]) &&
                        !(r[4].contains(INVISIBLE))){
                    r[4] = INVISIBLE;
//                    r[1] += " " + INVISIBLE;
                    flag = true;
//                    books = r[2].split(",");
//                    for (int i = 0; i < books.length; i++) {
//                        BookService.delete(books[i], name);
//                    }
                }
            }
            FileWriter sw = new FileWriter(AUTHORS);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(read);
            writer.close();

            if(flag == false){
                System.out.println("Such record doesn't exist");
                return;
            }

        } catch (CsvException | IOException e) {
            loggerError.error("Error when program tried to read/write rows in file (deleting)");
            e.printStackTrace();
        }
        loggerWarn.warn("end of deleting");
    }

    public static boolean checkFileAuthors(){
        if(!(new File(AUTHORS).exists())){
            loggerWarn.warn("File doesn't exist");
            System.out.println("File doesn't exists. Please, create book or author.");
            return false;
        }
        return true;
    }
}
