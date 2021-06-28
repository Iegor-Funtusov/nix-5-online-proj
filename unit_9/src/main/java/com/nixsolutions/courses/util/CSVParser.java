package com.nixsolutions.courses.util;

import com.nixsolutions.courses.data.Author;
import com.nixsolutions.courses.data.Book;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CSVParser {

    private static final String LIST_REGEX = ",";

    public static Book readBook(String id) throws NoSuchElementException {
        try (CSVReader reader = new CSVReader(new FileReader(FilePaths.BOOKS.getPath()))) {
            List<String[]> books = reader.readAll();
            for (String[] book : books) {
                if (book[0].equals(id) && Boolean.parseBoolean(book[3])) return parseBook(book);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        throw new NoSuchElementException("No element with such id found");
    }

    public static List<String[]> readAllBooks() {
        List<String[]> data = null;
        try (CSVReader reader = new CSVReader(new FileReader(FilePaths.BOOKS.getPath()))) {
            data = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Author readAuthor(String id) {
        try (CSVReader reader = new CSVReader(new FileReader(FilePaths.AUTHORS.getPath()))) {
            List<String[]> authors = reader.readAll();
            for (String[] author : authors) {
                if (author[0].equals(id) && Boolean.parseBoolean(author[4])) return parseAuthor(author);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        throw new NoSuchElementException("No element with such id found");
    }

    public static List<String[]> readAllAuthors() {
        List<String[]> data = null;
        try (CSVReader reader = new CSVReader(new FileReader(FilePaths.AUTHORS.getPath()))) {
            data = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static Author parseAuthor(String[] data) throws IOException {
        Author author = new Author();
        author.setId(data[0]);
        author.setName(data[1]);
        author.setSurname(data[2]);
        author.setBooks(parseIdsList(data[3]));
        author.setVisible(Boolean.parseBoolean(data[4]));
        return author;
    }

    public static List<Author> parseAllAuthors() throws IOException {
        List<String[]> data = readAllAuthors();
        data.remove(0);
        List<Author> authors = new ArrayList<>();
        for (String[] item : data) {
            if (Boolean.parseBoolean(item[4])) authors.add(parseAuthor(item));
        }
        return authors;
    }

    private static Book parseBook(String[] data) throws IOException {
        Book book = new Book();
        book.setId(data[0]);
        book.setTitle(data[1]);
        book.setAuthors(parseIdsList(data[2]));
        book.setVisible(Boolean.parseBoolean(data[3]));
        return book;
    }

    public static List<Book> parseAllBooks() throws IOException {
        List<String[]> data = readAllBooks();
        data.remove(0);
        List<Book> books = new ArrayList<>();
        for (String[] item : data) {
            if (Boolean.parseBoolean(item[3])) books.add(parseBook(item));
        }
        return books;
    }

    public static List<String> parseIdsList(String data) {
        String[] ids = data.split(LIST_REGEX);
        return new ArrayList<>(Arrays.asList(ids));
    }
}
