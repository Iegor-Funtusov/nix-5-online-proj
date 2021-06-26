package Decorators;

import DataClasses.Book;
import FileWork.Constants;
import java.util.Arrays;
import java.util.List;

public class FormatBook {

    public static Book fromStringArrToBook(String []data){
        Book book = new Book();

        book.setId(data[Constants.ID]);
        book.setBookName(data[Constants.BOOK_NAME]);
        String authorsInString = data[Constants.BOOK_AUTHORS];

        String []allAuthors = authorsInString.split(";");
        book.setAuthors(Arrays.asList(allAuthors.clone()));

        return book;
    }


    public static String[] fromBookToStringArr(Book book){
        String []data = new String[Constants.BOOK_FIELDS_QUANTITY];

        data[Constants.ID] = book.getId();
        data[Constants.BOOK_NAME] = book.getBookName();

        StringBuilder authorsInString = new StringBuilder("");
        List<String> authors = book.getAuthors();
        for(String item : authors){
            authorsInString.append(item).append(";");
        }

        data[Constants.BOOK_AUTHORS] = authorsInString.toString();
        data[Constants.BOOK_VISIBLE] = "true";
        return data;
    }

}