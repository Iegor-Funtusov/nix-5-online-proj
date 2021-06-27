package DataClassesHelpers;

import DataClasses.Author;
import DataClasses.Book;

import java.util.ArrayList;

public class BookHelper {

    public static Book addAuthorToBook(Book book, Author authorToAdd){
        ArrayList<String> allAuthors = new ArrayList<>(book.getAuthors());

        if(allAuthors.size() != 0){
            if(allAuthors.get(0).equals("")){
                allAuthors.remove(0);
            }

            //Если такой автор уже есть у книги
            if(allAuthors.stream().anyMatch(author -> author.equals(authorToAdd.getFullName()))){
                return book;
            }
        }

        allAuthors.add(authorToAdd.getFullName());
        book.setAuthors(allAuthors);
        return book;
    }


    public static Book deleteAuthorFromBook(Book book, Author authorToDelete){
        ArrayList<String> allAuthors = new ArrayList<>(book.getAuthors());
        for (String item : allAuthors) {
            if(item.equals(authorToDelete.getFullName())){
                allAuthors.remove(authorToDelete.getFullName());
                book.setAuthors(allAuthors);
                return book;
            }
        }
        throw new RuntimeException("Such book does not contain this author");
    }
}
