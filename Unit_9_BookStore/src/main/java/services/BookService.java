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
    private static int indexInFile;

    private static final Logger loggerInfo = LoggerFactory.getLogger("infoBook");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warnBook");
    private static final Logger loggerError = LoggerFactory.getLogger("errorBook");

    public static void create(Book book){
        loggerInfo.info("Adding the book: " + book.getName());
        String[] currentBook = new String[4];
        currentBook[1] = book.getName().trim();
        currentBook[2] = book.getListOfAuthors();;
        currentBook[3] = VISIBLE;

        if(currentBook[1].isBlank() || currentBook[2].isBlank())
            return;

        checkFileBook();
        checkFileAuthor();

        List<String[]> csv = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(r[1].equalsIgnoreCase(currentBook[1].trim()) && r[2].equalsIgnoreCase(currentBook[2].trim())
                        && !r[3].contains(INVISIBLE)){
                    loggerWarn.warn("Book \"" + r[1] + "\" already exists");
                    System.out.println("Book \"" + r[1] + "\" already exists");
                    return;
                }
            }
            indexInFile = read.size();
        } catch (IOException | CsvException e) {
            loggerError.error("Error when program tried to read rows in file");
            e.printStackTrace();
        }


        currentBook[0] = String.format("%d", indexInFile);
        csv.add(currentBook);


        try (CSVWriter writer = new CSVWriter(new FileWriter(BOOKS, true))) {
            writer.writeAll(csv);
        } catch (IOException e) {
            loggerError.error("Error when program tried to write new Author");
            e.printStackTrace();
        }
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

    public static void update(String bookName, String authorName, String newInput, int choice){
        loggerInfo.info("begin of updating data in file");
        int counter = 0;
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS))) {
            List<String[]> read = reader.readAll();
            for (String[] r : read) {
                if(choice == 1 && r[1].equalsIgnoreCase(newInput) && r[2].contains(authorName) && !r[3].contains(INVISIBLE)){
                    loggerWarn.warn("Such book already exists. Nothing will be changed");
                    System.out.println("Such book already exists. Nothing will be changed");
                    return;
                }
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
                System.out.println("Such record doesn't exist in book file");
                return;
            }
            FileWriter sw = new FileWriter(BOOKS);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(read);
            writer.close();
        } catch (IOException | CsvException e) {
            loggerError.error("Error when program tried to read/write rows in file (updating data)");
            e.printStackTrace();
        }
        loggerInfo.info("end of updating data in file");
    }

    public static void delete(String bookName, String authorName, boolean check){
        loggerWarn.warn("begin of deleting");
        int counter = 0;
        boolean flag = false;
        try(CSVReader reader = new CSVReader(new FileReader(BOOKS));) {
            List<String[]> allElements = reader.readAll();
            for (String[] r : allElements) {
                if(r[1].equalsIgnoreCase(bookName) && r[2].contains(authorName) &&
                        !(r[3].contains(INVISIBLE)) && check == true) {
                    r[3] = INVISIBLE;
//                    allElements.remove(r);
                    flag = true;
                    break;
                }
                if(r[1].equalsIgnoreCase(bookName) && r[2].contains(authorName) &&
                        !(r[3].contains(INVISIBLE)) && check == false){
                    flag = true;
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
}
