package com.k4rnaj1k;

import com.k4rnaj1k.Service.Impl.BookStoreImpl;
import com.k4rnaj1k.Service.BookStoreService;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookStoreController {
    public void start() {
        BookStoreService service = new BookStoreImpl();
        Scanner s = new Scanner(System.in);
        while(true){
        System.out.println("""
                What'd u like to do next?
                1 - create an author.
                2 - update an author.
                3 - get authors list
                4 - get books list
                5 - get all author's books
                6 - get all book's authors
                7 - create a book
                8 - update a book
                9 - remove author
                10 - remove book
                T - start common test.""");
        switch (s.nextLine()){
            case "1": service.createAuthor(s);break;
            case "2": service.updateAuthor(s);break;
            case "3": service.getAuthors();break;
            case "4": service.getBooks();break;
            case "5": service.getAuthorsBooks(s);break;
            case "6": service.getBooksAuthors(s);break;
            case "7": service.createBook(s);break;
            case "8": service.updateBook(s);break;
            case "9": service.removeAuthor(s); break;
            case "10": service.removeBook(s); break;
            case "T": service.autotest(service); break;
        }
    }
    }


    public BookStoreController() {
        String[] authorsHeaderData = {"id", "name", "surname", "books", "visible"};
        initCSV("authors.csv", authorsHeaderData);
        System.out.println("Initialized authors.csv");
        String[] booksHeaderData = {"id", "name", "authors", "visible"};
        initCSV("books.csv", booksHeaderData);
        System.out.println("Initialized books.csv");
    }

    public static void initCSV(String file, String[] header){
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] fileHeader = reader.readNext();
            for (int i = 0; i < header.length; i++) {
                if (!fileHeader[i].equals(header[i]))
                    throw new CsvValidationException();
            }
        } catch (IOException | CsvValidationException e) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(file, false))) {
                List<String[]> headerData = new ArrayList<>();
                headerData.add(header);
                writer.writeAll(headerData);
            } catch (IOException r) {
                System.out.printf("There was an exception during the initialization of the %s file.", file);
            }
        }
    }
}
