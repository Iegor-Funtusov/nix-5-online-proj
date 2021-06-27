package com.k4rnaj1k.Dao.Impl;

import com.k4rnaj1k.Dao.AuthorDao;
import com.k4rnaj1k.Dao.BookDao;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.k4rnaj1k.entities.Book;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private int id;

    public BookDaoImpl() {
        getId();
    }

    private void getId() {
        try (CSVReader reader = new CSVReader(new FileReader("books.csv"))) {
            List<String[]> csvData = reader.readAll();
            id = Integer.parseInt(csvData.get(csvData.size() - 1)[AuthorDao.CSVIndex.ID.ordinal()]);
            id++;
        } catch (Exception e) {
            id = 1;
        }
    }

    @Override
    public ArrayList<Book> findAll() {
        ArrayList<Book> books = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(new File("books.csv")))) {
            for (String[] bookData :
                    reader.readAll()) {
                Book book = new Book();
                book.setName(bookData[CSVIndex.NAME.ordinal()]);
                book.setAuthors(bookData[CSVIndex.AUTHORS.ordinal()]);
                book.setVisible(bookData[CSVIndex.VISIBLE.ordinal()]);
                books.add(book);
            }
            return books;
        } catch (Exception e) {
            System.out.println("Sorry, there was an exception, couldn't parse the file.");
        }
        return null;
    }

    @Override
    public void update(Book current, Book updated) {
        try (
                CSVReader reader = new CSVReader(new FileReader("books.csv"));
        ) {
            List<String[]> data = reader.readAll();
            reader.close();
            CSVWriter writer = new CSVWriter(new FileWriter("books.csv", false));
            data.set(current.getId(), new String[]{String.valueOf(current.getId()), updated.getName(), updated.getAuthors(), updated.getVisible()});
            writer.writeAll(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void create(Book book) {
        try (CSVWriter writer = new CSVWriter(new FileWriter("books.csv", true))) {
            ArrayList<String[]> csvData = new ArrayList<>();
            String[] csvRow = new String[]{String.valueOf(id++), book.getName(), book.getAuthors(), "true"};
            csvData.add(csvRow);
            writer.writeAll(csvData);
        } catch (Exception e) {
            System.out.println("Couldn't create book");
        }
    }


    @Override
    public Book find(String bookName) {
        try (CSVReader reader = new CSVReader(new FileReader("books.csv"))) {
            List<String[]> data = reader.readAll();
            for (String[] book :
                    data) {
                if (book[CSVIndex.NAME.ordinal()].equals(bookName)) {
                    Book res = new Book();
                    res.setId(Integer.parseInt(book[CSVIndex.ID.ordinal()]));
                    res.setName(book[CSVIndex.NAME.ordinal()]);
                    res.setAuthors(book[CSVIndex.AUTHORS.ordinal()]);
                    res.setVisible(book[CSVIndex.VISIBLE.ordinal()]);
                    return res;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
}
