package DataClassesHelpers;

import DataClasses.Author;
import DataClasses.Book;

import java.util.ArrayList;
import java.util.List;

public class BookHelper {

    public static Book addAuthorToBook(Book book, Author authorToAdd){
        //Добавить проверку на то что такой автор уже есть у книги
        ArrayList<String> allAuthors = new ArrayList<>(book.getAuthors());
        allAuthors.add(authorToAdd.getFullName());
        book.setAuthors(allAuthors);
        return book;
    }

    public static Book deleteAuthorFromBook(Book book, Author authorToDelete){
        ArrayList<String> allAuthors = new ArrayList<>(book.getAuthors());
        for (String item : allAuthors) {
            if(item.equals(authorToDelete.getFullName())){
                allAuthors.remove(authorToDelete.getFullName());
                return book;
            }
        }
        throw new RuntimeException("Such book does not contain this author");
    }
}
