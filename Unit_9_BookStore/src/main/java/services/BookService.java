package services;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import data.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static services.AuthorService.checkFileAuthor;


public class BookService {

    public final static String BOOKS = "books.csv";

    private final static String INVISIBLE = "INVISIBLE";
    private final static String VISIBLE = "VISIBLE";
    private static int indexInFile = 0;

    private static final Logger loggerInfo = LoggerFactory.getLogger("infoBook");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warnBook");
    private static final Logger loggerError = LoggerFactory.getLogger("errorBook");

    public static void create(Book book){
        loggerInfo.info("Adding the book: " + book.getName());
        String[] currentBook = new String[4];
        currentBook[0] = String.format("%d", ++indexInFile);
        currentBook[1] = book.getName().trim();
        currentBook[2] = book.getListOfAuthors();;
        currentBook[3] = VISIBLE;

        checkFileBook();
        checkFileAuthor();

        List<String[]> csv = new ArrayList<>();
//        if(!(new File(BOOKS).exists())){
//            loggerWarn.warn("Creating of file");
//            String[] header = {"name", "list of authors"};
//            csv.add(header);
//            csv.add(currentBook);
//            try(CSVWriter writer = new CSVWriter(new FileWriter(BOOKS, true))) {
//                writer.writeAll(csv);
//            } catch (IOException e) {
//                loggerError.error("Error when program tried to write header");
//                e.printStackTrace();
//            }
//            loggerWarn.warn("File was created");
//            flag1 = true;
//        }
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[1].equalsIgnoreCase(currentBook[1].trim()) && r[2].equalsIgnoreCase(currentBook[2].trim())){
                    loggerWarn.warn("Book \"" + r[1] + "\" already exists");
                    System.out.println("Book\"" + r[1] + "\" already exists");
                    return;
                }
            }
        } catch (IOException | CsvException e) {
            loggerError.error("Error when program tried to read rows in file");
            e.printStackTrace();
        }

        csv.add(currentBook);

//        if(flag1 == false) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(BOOKS, true))) {
                writer.writeAll(csv);
            } catch (IOException e) {
                loggerError.error("Error when program tried to write new Author");
                e.printStackTrace();
            }
//        }
//        else {
//            try (CSVWriter writer = new CSVWriter(new FileWriter(AUTHORS))) {
//                writer.writeAll(csv);
//            } catch (IOException e) {
//                loggerError.error("Error when program tried to write new Author");
//                e.printStackTrace();
//            }
//        }

//        try (CSVWriter writer = new CSVWriter(new FileWriter(BOOKS, true))) {
//            writer.writeAll(csv);
//        } catch (IOException e) {
//            loggerError.error("Error when program tried to write new Book");
//            e.printStackTrace();
//        }
//        try(CSVReader reader = new CSVReader(new FileReader(AUTHORS))) {
//            boolean flag = false;
//            for (int i = 0; i < authors.length; i++) {
//                List<String[]> read = reader.readAll();
//                for(String[] r : read) {
//                    if (r[1].equalsIgnoreCase(authors[i].trim())) {
//                        flag = true;
//                    }
//                }
//                if (flag == false) {
//                    Author author = new Author();
//                    String[] fullName = authors[i].trim().split(" ");
//                    if(fullName.length != 2){
//                        loggerWarn.warn("Incorrect input. Input must contain first and last names");
//                        System.out.println("Incorrect input. Input must contain first and last names");
//                        return;
//                    }
//                    author.setFirstName(fullName[0]);
//                    author.setLastName(fullName[1]);
//                    author.setListOfBooks(currentBook[0]);
//                    AuthorService.create(author);
//                }
//                flag = false;
//            }
//        } catch (IOException | CsvException e) {
//            loggerError.error("Error when program tried to read rows in file");
//            e.printStackTrace();
//        }

        loggerInfo.info("Book was added");
    }

    public static void checkFileBook(){
        List<String[]> csv = new ArrayList<>();
        if(!(new File(BOOKS).exists())){
            loggerWarn.warn("Creating of file");
            String[] header = {"ID", "name", "list of authors", "visibility"};
            csv.add(header);
            try(CSVWriter writer = new CSVWriter(new FileWriter(BOOKS, true))) {
                writer.writeAll(csv);
            } catch (IOException e) {
                loggerError.error("Error when program tried to write header");
                e.printStackTrace();
            }
            loggerWarn.warn("File was created");
        }
    }

    public static void readAll(){
        if(!checkFileBooks()){
            return;
        }
        loggerInfo.info("begin of reading of complete file");
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(!r[3].contains(INVISIBLE))
                    System.out.println(r[0] + " | " + r[1] + " | " + r[2]);
            }
        } catch (IOException | CsvException e) {
            loggerError.error("Error when program tried to read rows in file");
            e.printStackTrace();
        }
        loggerInfo.info("end of reading of complete file");
    }

    public static void readAll(String name){
        if(!checkFileBooks()){
            return;
        }
        loggerInfo.info("begin of reading of complete file");
        int counter = 0;
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[1].equalsIgnoreCase(name) && !r[3].contains(INVISIBLE)){
                    System.out.println("Authors: " + r[2]);
                    break;
                }
                counter++;
            }
            if(counter == read.size()){
                loggerWarn.warn("Such book doesn't exist: " + name);
                System.out.println("Such book doesn't exist");
                return;
            }
        } catch (IOException | CsvException e) {
            loggerError.error("Error when program tried to find rows in file related with the certain name of book");
            e.printStackTrace();
        }
    }

    public static void update(String bookName, String authorName, String newInput, int choice){// check exist such book or author
        if(!checkFileBooks()){
            return;
        }
        loggerInfo.info("begin of updating data in file");
        int counter = 0;
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[1].equalsIgnoreCase(bookName) && r[2].contains(authorName) && !r[3].contains(INVISIBLE)){
                    if(choice == 1) {
                        r[1] = newInput;
                        break;
                    }
                    if(choice == 2){
                        r[2] = newInput;
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
            FileWriter sw = new FileWriter(BOOKS);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(read);
            writer.close();
//            if(choice == 1)
//                AuthorService.update(bookName,authorName, newInput, choice);
        } catch (IOException | CsvException e) {
            loggerError.error("Error when program tried to read/write rows in file (updating data)");
            e.printStackTrace();
        }
        loggerInfo.info("end of updating data in file");
    }

    public static void delete(String bookName, String authorName){
        if(!checkFileBooks()){
            return;
        }
        loggerWarn.warn("begin of deleting");
        int counter = 0;
        boolean flag = false;
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS));) {
            List<String[]> allElements = reader.readAll();
            for (String[] r : allElements) {
                if(r[1].equalsIgnoreCase(bookName) && r[2].contains(authorName) &&
                        !(r[3].contains(INVISIBLE))) {
                    r[3] = INVISIBLE;
//                    allElements.remove(r);
                    flag = true;
                    break;
                }
                counter++;
            }
            if(counter == allElements.size() && flag != true){
                loggerWarn.warn("Such record doesn't exist in file");
                System.out.println("Such record doesn't exist in file");
                return;
            }
            FileWriter sw = new FileWriter(BOOKS);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(allElements);
            writer.close();
        } catch (CsvException | IOException e) {
            loggerError.error("Error when program tried to read/write rows in file (deleting)");
            e.printStackTrace();
        }
        loggerWarn.warn("end of deleting");
    }

    public static boolean checkFileBooks(){
        if(!(new File(BOOKS).exists())){
            loggerWarn.warn("File doesn't exist");
            System.out.println("File doesn't exists. Please, create book or author.");
            return false;
        }
        return true;
    }
}
