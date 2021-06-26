package CrudCSV;
import DataClasses.Book;
import FileWork.Constants;
import FileWork.FileWorker;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;

public class BookCrudProcess <B> implements CrudProcess<DataClasses.Book>{
    private String PATH;

    public BookCrudProcess(String path){
        this.PATH = path;
    }


    @Override
    public void create(Book bookToAdd){
        isEmptyPath();
        List<String[]> csvData = FileWorker.readFromFile(PATH);
        if(csvData.isEmpty()){
            csvData.add(generateHeader());
        }

        if(bookToAdd.getId() == null){
            String id = generateID(UUID.randomUUID().toString(), read());
            bookToAdd.setId(id);
        }

        String[] bookData = Decorators.FormatBook.fromBookToStringArr(bookToAdd);
        csvData.add(bookData);
        FileWorker.writeToFile(PATH, csvData);
    }


    @Override
    public void update(Book bookToUpd){
        isEmptyPath();

        List<String[]> csvData = FileWorker.readFromFile(PATH);

        for(int i = 0; i < csvData.size(); i++){
            String []bookData = csvData.get(i);

            if(bookData[Constants.ID].equals(bookToUpd.getId())){
                String []bookInString = Decorators.FormatBook.fromBookToStringArr(bookToUpd);
                csvData.set(i, bookInString);
                FileWorker.writeToFile(PATH, csvData);
                return;
            }
        }

        throw new RuntimeException("Book does not exist");
    }


    @Override
    public void delete(String id){
        isEmptyPath();
        List<String[]> csvData = FileWorker.readFromFile(PATH);

        for(String[] bookData : csvData){
            if(bookData[Constants.ID].equals(id) && !bookData[Constants.BOOK_VISIBLE].equals("false")){
                bookData[Constants.BOOK_VISIBLE] = "false";
                FileWorker.writeToFile(PATH, csvData);
                return;
            }
        }
        throw new RuntimeException("Book does not exist");
    }


    @Override
    public List<Book> read(){
        isEmptyPath();

        List<String[]> csvData = FileWorker.readFromFile(PATH);
        List<Book> books = new ArrayList<>();

        for(int i = 0; i < csvData.size(); i++){
            String[] data = csvData.get(i);
            //Если визибл флаг не опущен
            if(!data[Constants.BOOK_VISIBLE].equals("false")){
                Book book = Decorators.FormatBook.fromStringArrToBook(data);
                books.add(book);
            }
        }

        return books;
    }


    @Override
    public Book read(String id){
        isEmptyPath();
        List<Book> books =  read();
        for(Book item : books){
            if(item.getId().equals(id)){
                return item;
            }
        }

        throw new RuntimeException("Book does not exist");
    }


    public String getPATH() {
        isEmptyPath();
        return PATH;
    }


    public void setPATH(String PATH) {
        this.PATH = PATH;
    }


    private void isEmptyPath(){
        if(!StringUtils.isNotBlank(PATH)){
            throw new RuntimeException("Path is empty");
        }
    }


    private String generateID(String id, List<Book> books){
        if(books.isEmpty()){
            return id;
        }
        if(books.stream().anyMatch(e -> e.getId().equals(id))){
            return generateID(UUID.randomUUID().toString(), books);
        }
        return id;
    }

    private String[] generateHeader(){
        String []header = new String[Constants.BOOK_FIELDS_QUANTITY];
        header[Constants.ID] = "id";
        header[Constants.BOOK_NAME] = "book name";
        header[Constants.BOOK_AUTHORS] = "authors";
        header[Constants.BOOK_VISIBLE] = "visible";
        return header;
    }
}
