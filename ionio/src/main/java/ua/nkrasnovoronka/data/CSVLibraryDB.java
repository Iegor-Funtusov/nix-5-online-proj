package ua.nkrasnovoronka.data;

import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.util.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class CSVLibraryDB implements LibraryDB {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public static final String CSV_DIRECTORY = "src/main/resources/csvdb";
    public static final String AUTHOR_CSV = "src/main/resources/csvdb/author.csv";
    public static final String BOOK_CSV = "src/main/resources/csvdb/book.csv";

    private final List<String[]> authorHeader = Collections.singletonList(new String[]{"id", "firstName", "lastName", "visible"});
    private final List<String[]> bookHeader = Collections.singletonList(new String[]{"id", "title", "genre", "visible"});


    private long authorId = 1;
    private long bookId = 1;

    private static CSVLibraryDB instance;

    private CSVLibraryDB() {
        initFiles();
    }


    private void initFiles() {
        try {
            loggerInfo.info("Initialize csv files");
            Files.createDirectories(Path.of(CSV_DIRECTORY));
            writeHeaders();
        } catch (IOException e) {
            e.printStackTrace();
            loggerError.error("Initialization error");
        }
    }

    private void writeHeaders() {
        loggerInfo.info("Writing headers to csv files");
        try(CSVWriter authorWriter = new CSVWriter(new FileWriter(AUTHOR_CSV));
            CSVWriter bookWriter = new CSVWriter(new FileWriter(BOOK_CSV))) {
            authorWriter.writeAll(authorHeader);
            bookWriter.writeAll(bookHeader);
            loggerInfo.info("headers has been written to csv files");
        } catch (IOException e) {
            e.printStackTrace();
            loggerError.error("Cannot write header to files");
        }
    }

    public static CSVLibraryDB getInstance() {
        if (instance != null) {
            return instance;
        }
        return new CSVLibraryDB();
    }

    @Override
    public void createAuthor(Author author){
        try(CSVWriter authorWriter = new CSVWriter(new FileWriter(AUTHOR_CSV, true))) {
            authorWriter.writeNext(Util.authorToStringArray(author, authorId++));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createBook(Book book){
        try(CSVWriter bookWriter = new CSVWriter(new FileWriter(BOOK_CSV, true))) {
            bookWriter.writeNext(Util.bookToStringArray(book, bookId++));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
