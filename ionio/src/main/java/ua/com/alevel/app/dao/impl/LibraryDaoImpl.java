package ua.com.alevel.app.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import ua.com.alevel.app.csv.CsvDb;
import ua.com.alevel.app.dao.LibraryDao;
import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.entity.Book;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LibraryDaoImpl implements LibraryDao {

    CsvDb csvDb = new CsvDb();

    public void createAuthor(Author author){
        List<String[]> csvData = new ArrayList<>();

        author.setId(generateIdAuthor(UUID.randomUUID().toString()));
        String[] currentAuthor = new String[5];
        currentAuthor[0] = author.getId();
        currentAuthor[1] = author.getFirstName();
        currentAuthor[2] = author.getLastName();
        currentAuthor[3] = csvDb.allId(author.getBooks());
        currentAuthor[4] = Boolean.toString(author.getVisibleFlag());
        csvData.add(currentAuthor);

        try(CSVWriter csvWriter = new CSVWriter(new FileWriter("authors.txt",true))) {
            csvWriter.writeAll(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Author> readAuthor(){
        List<Author> authors = new ArrayList<>();

        try(CSVReader reader = new CSVReader(new FileReader("authors.txt"))) {
            List<String[]> res = reader.readAll();
            for (String[] re : res) {
                if (re[0].equals("ID")){
                    continue;
                }
                Author a = new Author();
                a.setId(re[0]);
                a.setFirstName(re[1]);
                a.setLastName(re[2]);
                a.setBooks(csvDb.parseId(re[3]));
                a.setVisibleFlag(Boolean.parseBoolean(re[4]));
                authors.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return authors;
    }

    public Author readAuthor(String id) {
        Author a = new Author();

        try (CSVReader reader = new CSVReader(new FileReader("authors.txt"))) {
            List<String[]> res = reader.readAll();
            for (String[] re : res) {
                if (re[0].equals(id)) {

                    a.setId(re[0]);
                    a.setFirstName(re[1]);
                    a.setLastName(re[2]);
                    a.setBooks(csvDb.parseId(re[3]));
                    a.setVisibleFlag(Boolean.parseBoolean(re[4]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

    public void updateAuthor(Author author){
        List<String[]> newList = new ArrayList<>();
        String[] upd = new String[5];
        int i = 0;
        if (readAuthor(author.getId()) == null) {
            throw new RuntimeException("Такого автора нет");
        }
        else {
            try(CSVReader reader = new CSVReader(new FileReader("authors.txt"))) {
                List<String[]> res = reader.readAll();
                newList = res;
                for (String[] re : res) {
                    if (re[0].equals(author.getId())){
                        i = res.indexOf(re);
                        upd[0] = author.getId();
                        upd[1] = author.getFirstName();
                        upd[2] = author.getLastName();
                        upd[3] = csvDb.allId(author.getBooks());
                        upd[4] = Boolean.toString(author.getVisibleFlag());
                        break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            newList.set(i,upd);
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter("authors.txt"))) {
                csvWriter.writeAll(newList);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void deleteAuthor(String id){
        Author author = readAuthor(id);
        if (author.getVisibleFlag()){
            author.setVisibleFlag(false);
            updateAuthor(author);
        }
        else{
            System.out.println("Этот автор итак удален.");
        }
    }

    public List<Author> readByBookAuthor(String id){
        List<Author> authors = new ArrayList<>();
        Book book = readBook(id);
        List<String> autIds = book.getAuthors();
        for (String autId : autIds) {
            if(autId==null){
                continue;
            }
            Author author = readAuthor(autId);
            authors.add(author);
        }
        return authors;
    }

    private String generateIdAuthor(String id) {
        if(readAuthor(id).getId() == null){
            return id;
        }
        else {
            return generateIdAuthor(UUID.randomUUID().toString());
        }
    }

    public void createBook(Book book, List<String> authorsId){
        Author author = readAuthor(authorsId.get(0));
        if(author.getId() == null){
            System.out.println("Автора с таким ИД нет");
        }
        else {
            book.setId(generateIdBook(UUID.randomUUID().toString()));
            List<String[]> csvData = new ArrayList<>();
            String[] currentBook = new String[4];
            currentBook[0] = book.getId();
            currentBook[1] = book.getTitle();
            currentBook[2] = csvDb.allId(authorsId);
            currentBook[3] = Boolean.toString(book.getVisibleFlag());
            csvData.add(currentBook);
            for (String s : authorsId) {
                if(s == null){
                    continue;
                }
                Author author1 = readAuthor(s);
                if(author1!=null){
                    List<String> bookList = author1.getBooks();
                    bookList.add(book.getId());
                    updateAuthor(author1);
                }
            }
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter("books.txt",true))) {
                csvWriter.writeAll(csvData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Book> readBook(){
        List<Book> books = new ArrayList<>();

        try(CSVReader reader = new CSVReader(new FileReader("books.txt"))) {
            List<String[]> res = reader.readAll();
            for (String[] re : res) {
                if (re == null || re[0].equals("ID")){
                    continue;
                }
                Book b = new Book();
                b.setId(re[0]);
                b.setTitle(re[1]);
                b.setAuthors(csvDb.parseId(re[2]));
                b.setVisibleFlag(Boolean.parseBoolean(re[3]));

                books.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book readBook(String id) {
        Book b = new Book();

        try (CSVReader reader = new CSVReader(new FileReader("books.txt"))) {
            List<String[]> res = reader.readAll();
            for (String[] re : res) {
                if (re[0].equals(id)) {
                    b.setId(re[0]);
                    b.setTitle(re[1]);
                    b.setAuthors(csvDb.parseId(re[2]));
                    b.setVisibleFlag(Boolean.parseBoolean(re[3]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public void updateBook(Book book){
        List<String[]> newList = new ArrayList<>();
        String[] upd = new String[4];
        int i = 0;
        if (readBook(book.getId()) == null) {
            throw new RuntimeException("Такой книги нет");
        }
        else {
            try(CSVReader reader = new CSVReader(new FileReader("books.txt"))) {
                List<String[]> res = reader.readAll();
                newList = res;
                for (String[] re : res) {
                    if (re[0].equals(book.getId())){
                        i = res.indexOf(re);
                        upd[0] = book.getId();
                        upd[1] = book.getTitle();

                        upd[2] = csvDb.allId(book.getAuthors());
                        upd[3] = Boolean.toString(book.getVisibleFlag());
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            newList.set(i,upd);
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter("books.txt"))) {
                csvWriter.writeAll(newList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteBook(String id){
        Book book = readBook(id);
        if (book.getVisibleFlag()){
            book.setVisibleFlag(false);
            updateBook(book);
        }
        else{
            System.out.println("Эта книга итак удалена.");
        }
    }

    public List<Book> readByAuthorBook(String id){
        List<Book> books = new ArrayList<>();
        Author author = readAuthor(id);
        List<String> bookIds = author.getBooks();
        for (String bId : bookIds) {
            if(bId==null){
                continue;
            }
            Book book = readBook(bId);
            books.add(book);
        }
        return books;
    }

    private String generateIdBook(String id) {
        if(readBook(id).getId() == null){
            return id;
        }
        else {
            return generateIdBook(UUID.randomUUID().toString());
        }
    }
}
