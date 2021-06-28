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

public class AuthorDAO {

    CSVFile CSV_FILE = new CSVFile();

    public void createAuthor(Author author) {
        List<String[]> CSVData = new ArrayList<>();
        author.setAuthorId(generateId(UUID.randomUUID().toString()));
        String[] a = new String[5];
        a[0] = author.getAuthorId();
        a[1] = author.getFirstName();
        a[2] = author.getLastName();
        a[3] = CSV_FILE.allIds(author.getBooks());
        a[4] = Boolean.toString(author.isVisible());
        CSVData.add(a);
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.AUTHORS.getPATH(),true))) {
            csvWriter.writeAll(CSVData);
        } catch (IOException e) {
           // e.printStackTrace();
            System.out.println(e + "Ошибка записи в файл!");
        }
    }

    public List<Author> readAuthors(){
        List<Author> authors = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(FileType.AUTHORS.getPATH()))) {
            List<String[]> results = reader.readAll();
            for (String[] r : results) {
                if (r[0].equals("AuthorId")) {
                    continue;
                }
                Author a = new Author();
                a.setAuthorId(r[0]);
                a.setFirstName(r[1]);
                a.setLastName(r[2]);
                a.setBooks(CSV_FILE.parseId(r[3]));
                a.setVisible(Boolean.parseBoolean(r[4]));
                authors.add(a);
            }
        } catch (Exception e) {
           // e.printStackTrace();
            System.out.println(e + "Ошибка чтения из файла!");
        }
        return authors;
    }

    public Author readAuthor(String id) {
        Author a = new Author();
        try (CSVReader reader = new CSVReader(new FileReader(FileType.AUTHORS.getPATH()))) {
            List<String[]> results = reader.readAll();
            for (String[] r : results) {
                if (r[0].equals(id)) {
                    a.setAuthorId(r[0]);
                    a.setFirstName(r[1]);
                    a.setLastName(r[2]);
                    a.setBooks(CSV_FILE.parseId(r[3]));
                    a.setVisible(Boolean.parseBoolean(r[4]));
                }
            }
        } catch (Exception e) {
          //  e.printStackTrace();
            System.out.println(e + "Ошибка чтения из файла!");
        }
        return a;
    }

   public void updateAuthor(Author author) {
       List<String[]> list = new ArrayList<>();
       String[] a = new String[5];
       int index = 0;
       if (readAuthor(author.getAuthorId()) == null) {
           throw new RuntimeException("Такого автора нет!");
       }
       else {
           try (CSVReader reader = new CSVReader(new FileReader(FileType.AUTHORS.getPATH()))) {
               List<String[]> results = reader.readAll();
               list = results;
               for (String[] r : results) {
                   if (r[0].equals(author.getAuthorId())){
                       index = results.indexOf(r);
                       a[0] = author.getAuthorId();
                       a[1] = author.getFirstName();
                       a[2] = author.getLastName();
                       a[3] = CSV_FILE.allIds(author.getBooks());
                       a[4] = Boolean.toString(author.isVisible());
                       break;
                   }
               }
           } catch (Exception e) {
              // e.printStackTrace();
               System.out.println(e + "Ошибка чтения из файла!");
           }
           list.set(index, a);
           try (CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.AUTHORS.getPATH()))) {
               csvWriter.writeAll(list);
           } catch (IOException e) {
             //  e.printStackTrace();
               System.out.println(e + "Ошибка записи в файл!");
           }
       }
   }

    public void deleteAuthor(String id) {
        Author author = readAuthor(id);
        if (author.isVisible()) {
            author.setVisible(false);
            updateAuthor(author);
        }
        else {
            System.out.println("Автор уже удалён!");
        }
    }

    public List<Author> readAuthors(String id) {
        List<Author> authors = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.readBook(id);
        List<String> authorsIds = book.getAuthors();
        for (String authorId : authorsIds) {
            if (authorId == null) {
                continue;
            }
            Author author = readAuthor(authorId);
            authors.add(author);
        }
        return authors;
    }

    private String generateId(String authorId) {
        if (readAuthor(authorId).getAuthorId() == null) {
            return authorId;
        }
        else {
            return generateId(UUID.randomUUID().toString());
        }
    }
}
