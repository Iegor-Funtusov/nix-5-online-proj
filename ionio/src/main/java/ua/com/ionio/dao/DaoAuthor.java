package ua.com.ionio.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.ionio.entity.Author;
import ua.com.ionio.file.FileType;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaoAuthor {

    private static final Logger loggerError = LoggerFactory.getLogger("error");

    int id = 0;

    public void create(Author author){
        if(author!=null){
            id++;
            List<String[]> csvData = new ArrayList<>();
            String[] currentAuthor = new String[]
                    { Integer.toString(id),
                            //UUID.randomUUID().toString(),
                    author.getFirstname(),
                    author.getLastname(),
                    author.getListBooks().toString(),
                    "true"};
            csvData.add(currentAuthor);
            try(CSVWriter csvWriter = new CSVWriter(
                    new FileWriter(FileType.CSV_AUTHOR.getPath(),true))) {
                csvWriter.writeAll(csvData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            loggerError.error("Author is empty - can not create");
            throw new IllegalArgumentException("Author is empty!");
        }
    }

    public void update(Author author){
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_AUTHOR.getPath()))) {
            List<String[]> allAuthors = reader.readAll();
            for (int i = 1; i < allAuthors.size(); i++) {
                if (allAuthors.get(i)[0].equals(author.getId())) {
                    String[] s = {author.getId(), author.getFirstname(),
                    author.getLastname(), author.getListBooks().toString(),
                    String.valueOf(author.isIsvisableAuthor())};
                   allAuthors.set(i,s);
                }
            }
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.CSV_AUTHOR.getPath()))) {
                csvWriter.writeAll(allAuthors);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id){
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_AUTHOR.getPath()))) {
            List<String[]> allAuthors = reader.readAll();
            for (int i = 0; i < allAuthors.size(); i++) {
                if (allAuthors.get(i)[0].equals(id)) {
                    allAuthors.get(i)[4] = "false";
                }
            }
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.CSV_AUTHOR.getPath()))) {
                csvWriter.writeAll(allAuthors);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public Author findAuthorById(String id) {
        try (CSVReader reader = new CSVReader(new FileReader(FileType.CSV_AUTHOR.getPath()))) {
            Author current = new Author();
            List<String[]> allAuthors = reader.readAll();
            for (String[] author : allAuthors) {
                if (author[0].equals(id) && author[4].equals("true")) {
                    current.setId(author[0]);
                    current.setFirstname(author[1]);
                    current.setLastname(author[2]);
                    current.setListBooks(stringToList(author[3]));
                    current.setIsvisableAuthor(Boolean.parseBoolean(author[4]));
                    return current;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loggerError.error("Author doesn`t exist - can`t get");
        throw new IllegalArgumentException("This Author doesn`t exist");
    }

    public Author findAuthorByNS(String first, String second) {
        try (CSVReader reader = new CSVReader(
                new FileReader(FileType.CSV_AUTHOR.getPath()))) {
            Author current = new Author();
            List<String[]> allAuthors = reader.readAll();
            for (String[] author : allAuthors) {
                if (author[1].equals(first) && author[2].equals(second)) {
                    current.setId(author[0]);
                    current.setFirstname(author[1]);
                    current.setLastname(author[2]);
                    current.setListBooks(stringToList((author[3])));
                    current.setIsvisableAuthor(Boolean.parseBoolean(author[4]));
                    return current;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("This Author doesn`t exist");
    }

    public List<Author> findAllAuthors() {
        List<Author> result = new ArrayList<Author>();
        try (CSVReader reader = new CSVReader(
                new FileReader(FileType.CSV_AUTHOR.getPath()))) {
            List<String[]> allAuthors = reader.readAll();
            for(int i = 1; i < allAuthors.size(); i++){
                if(allAuthors.get(i)[4].equals("true")){
                    Author author = new Author();
                    author.setId(allAuthors.get(i)[0]);
                    author.setFirstname(allAuthors.get(i)[1]);
                    author.setLastname(allAuthors.get(i)[2]);
                    author.setListBooks(stringToList(allAuthors.get(i)[3]));
                    author.setIsvisableAuthor(Boolean.parseBoolean(allAuthors.get(i)[4]));
                    result.add(author);
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
