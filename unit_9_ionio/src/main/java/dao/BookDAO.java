package dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import csvfile.CSVFile;
import domain.Author;
import domain.Book;
import type.FileType;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookDAO {

    AuthorDAO authorDAO = new AuthorDAO();
    CSVFile CSV_FILE = new CSVFile();

    public void createBook(List<String> authorsId, Book book) {
        Author author = authorDAO.readAuthor(authorsId.get(0));
        if (author.getAuthorId() == null) {
            System.out.println("Автора с таким id нет!");
        }
        else {
            book.setBookId(generateId(UUID.randomUUID().toString()));
            List<String[]> CSVData = new ArrayList<>();
            String[] b = new String[4];
            b[0] = book.getBookId();
            b[1] = book.getTitle();
            b[2] = CSV_FILE.allIds(authorsId);
            b[3] = Boolean.toString(book.isVisible());
            CSVData.add(b);
            for (String str : authorsId) {
                if (str == null) {
                    continue;
                }
                Author a = authorDAO.readAuthor(str);
                if (a != null) {
                    List<String> bookList = a.getBooks();
                    bookList.add(book.getBookId());
                    authorDAO.updateAuthor(a);
                }
            }
            try (CSVWriter writer = new CSVWriter(new FileWriter(FileType.BOOKS.getPATH(),true))) {
                writer.writeAll(CSVData);
            } catch (IOException e) {
               // e.printStackTrace();
                System.out.println(e + "Ошибка записи в файл!");
            }
        }
    }

    public List<Book> readBooks(){
        List<Book> books = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(FileType.BOOKS.getPATH()))) {
            List<String[]> results = reader.readAll();
            for (String[] r : results) {
                if (r == null || r[0].equals("BookId")) {
                    continue;
                }
                Book b = new Book();
                b.setBookId(r[0]);
                b.setTitle(r[1]);
                b.setAuthors(CSV_FILE.parseId(r[2]));
                b.setVisible(Boolean.parseBoolean(r[3]));
                books.add(b);
            }
        } catch (Exception e) {
           // e.printStackTrace();
            System.out.println(e + "Ошибка чтения файлв!");
        }
        return books;
    }

    public Book readBook(String id) {
        Book b = new Book();
        try (CSVReader reader = new CSVReader(new FileReader(FileType.BOOKS.getPATH()))) {
            List<String[]> results = reader.readAll();
            for (String[] r : results) {
                if (r[0].equals(id)) {
                    b.setBookId(r[0]);
                    b.setTitle(r[1]);
                    b.setAuthors(CSV_FILE.parseId(r[2]));
                    b.setVisible(Boolean.parseBoolean(r[3]));
                }
            }
        } catch (Exception e) {
          //  e.printStackTrace();
            System.out.println(e + "Ошибка чтения файлв!");
        }
        return b;
    }

    public void updateBook(Book book) {
        List<String[]> list = new ArrayList<>();
        String[] b = new String[4];
        int index = 0;
        if (readBook(book.getBookId()) == null) {
           // throw new RuntimeException
            System.out.println("Такой книги нет!");
        }
        else {
            try (CSVReader reader = new CSVReader(new FileReader(FileType.BOOKS.getPATH()))) {
                List<String[]> results = reader.readAll();
                list = results;
                for (String[] r : results) {
                    if (r[0].equals(book.getBookId())) {
                        index = results.indexOf(r);
                        b[0] = book.getBookId();
                        b[1] = book.getTitle();
                        b[2] = CSV_FILE.allIds(book.getAuthors());
                        b[3] = Boolean.toString(book.isVisible());
                        break;
                    }
                }
            } catch (Exception e) {
               // e.printStackTrace();
                System.out.println(e + "Ошибка чтения файлв!");
            }
            list.set(index, b);
            try (CSVWriter writer = new CSVWriter(new FileWriter(FileType.BOOKS.getPATH()))) {
                writer.writeAll(list);
            } catch (IOException e) {
              //  e.printStackTrace();
                System.out.println(e + "Ошибка записи в файл!");
            }
        }
    }

    public void deleteBook(String id) {
        Book book = readBook(id);
        if (book.isVisible()) {
            book.setVisible(false);
            updateBook(book);
        }
        else {
            System.out.println("Эта книга уже удалена!");
        }
    }

    public List<Book> readBooksByAuthor(String id){
        List<Book> books = new ArrayList<>();
        Author author = authorDAO.readAuthor(id);
        List<String> booksIds = author.getBooks();
        for (String bookId : booksIds) {
            if (bookId == null) {
                continue;
            }
            Book book = readBook(bookId);
            books.add(book);
        }
        return books;
    }

   private String generateId(String id) {
        if (readBook(id).getBookId() == null) {
            return id;
        }
        else {
            return generateId(UUID.randomUUID().toString());
        }
    }
}
