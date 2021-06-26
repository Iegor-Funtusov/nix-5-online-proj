package Decorators;

import DataClasses.Author;
import FileWork.Constants;

import java.util.Arrays;
import java.util.List;


public class FormatAuthor {

    public static Author fromStringArrToAuthor(String[] data) {
        Author author = new Author();

        author.setId(data[Constants.ID]);
        author.setFirstName(data[Constants.AUTHOR_FIRSTNAME]);
        author.setLastName(data[Constants.AUTHOR_LASTNAME]);
        String booksInString = data[Constants.AUTHOR_BOOKS];

        String []allBooks = booksInString.split(";");
        author.setBooks(Arrays.asList(allBooks.clone()));

        return author;
    }


    public static String[] fromAuthorToStringArr(Author author) {
        String[] data = new String[Constants.AUTHOR_FIELDS_QUANTITY];

        data[Constants.ID] = author.getId();
        data[Constants.AUTHOR_FIRSTNAME] = author.getFirstName();
        data[Constants.AUTHOR_LASTNAME] = author.getLastName();

        StringBuilder booksInString = new StringBuilder("");
        List<String> books = author.getBooks();
        for(String item : books){
            booksInString.append(item).append(";");
        }

        data[Constants.AUTHOR_BOOKS] = booksInString.toString();
        data[Constants.AUTHOR_VISIBLE] = "true";

        return data;
    }
}