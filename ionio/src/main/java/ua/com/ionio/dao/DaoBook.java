package ua.com.ionio.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ua.com.ionio.entity.Book;
import ua.com.ionio.file.FileType;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaoBook {

    int id = 0;

    public void create(Book book){
        if(book!=null){
            id++;
            List<String[]> csvData = new ArrayList<>();
            String[] currentBook = new String[]
                    { Integer.toString(id),
                            //UUID.randomUUID().toString(),
                            book.getTitle(),
                            book.getListAuthors().toString(),
                            "true"};
            csvData.add(currentBook);
            try(CSVWriter csvWriter = new CSVWriter(
                    new FileWriter(FileType.CSV_BOOKS.getPath(),true))) {
                csvWriter.writeAll(csvData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new IllegalArgumentException("Book is empty!");
        }
    }

    public void update(Book book){
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_BOOKS.getPath()))) {
            List<String[]> allBooks = reader.readAll();
            for (int i = 1; i < allBooks.size(); i++) {
                if (allBooks.get(i)[0].equals(book.getId())) {
                    String[] s = {book.getId(), book.getTitle(),
                            book.getListAuthors().toString(),
                            String.valueOf(book.isIsvisableBook())};
                    allBooks.set(i,s);
                }
            }
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.CSV_BOOKS.getPath()))) {
                csvWriter.writeAll(allBooks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id){
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_BOOKS.getPath()))) {
            List<String[]> allBooks = reader.readAll();
            for (int i = 0; i < allBooks.size(); i++) {
                if (allBooks.get(i)[0].equals(id)) {
                    allBooks.get(i)[3] = "false";
                }
            }
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.CSV_BOOKS.getPath()))) {
                csvWriter.writeAll(allBooks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }


    public Book findBookById(String id) {
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_BOOKS.getPath()))) {
            Book current = new Book();
            List<String[]> allBooks = reader.readAll();
            for (String[] book : allBooks) {
                if (book[0].equals(id)) {
                    current.setId(book[0]);
                    current.setTitle(book[1]);
                    current.setListAuthors(stringToList(book[2]));
                    current.setIsvisableBook(Boolean.parseBoolean(book[3]));
                    return current;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("This Book doesn`t exist");
    }

    public List<Book> findAllBooks() {
        List<Book> result = new ArrayList<Book>();
        try (CSVReader reader = new CSVReader(
                new FileReader(FileType.CSV_BOOKS.getPath()))) {
            List<String[]> allBooks = reader.readAll();
            for(int i = 1; i < allBooks.size(); i++){
                if(allBooks.get(i)[3].equals("true")){
                    Book book = new Book();
                    book.setId(allBooks.get(i)[0]);
                    book.setTitle(allBooks.get(i)[1]);
                    book.setListAuthors(stringToList(allBooks.get(i)[2]));
                    book.setIsvisableBook(Boolean.parseBoolean(allBooks.get(i)[3]));
                    result.add(book);
                }
            }
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<String> stringToList(String s){
        List<String> myList = new ArrayList<String>();
        s = s.substring(1, s.length()-1);
        String[] strings = s.split(",");
        for(int i = 0; i < strings.length; i++){
            String withoutSpace = strings[i].replaceAll(" ", "");
            myList.add(withoutSpace);
        }
        return myList;
    }

}
