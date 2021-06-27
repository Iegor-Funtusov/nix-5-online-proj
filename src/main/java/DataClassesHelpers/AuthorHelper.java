package DataClassesHelpers;

import DataClasses.Author;
import DataClasses.Book;

import java.util.ArrayList;

public class AuthorHelper {

    public static Author addBookToAuthor(Author author, Book bookToAdd){
        ArrayList<String> allBooks = new ArrayList<>(author.getBooks());
        if(allBooks.size() != 0){
            if(allBooks.get(0).equals("")){
                allBooks.remove(0);
            }
            //Если у автора уже есть такая книга
            if(allBooks.stream().anyMatch(book -> book.equals(bookToAdd.getBookName()))){
                return author;
            }
        }

        String bookName = bookToAdd.getBookName();
        allBooks.add(bookName);
        author.setBooks(allBooks);
        return author;
    }


    public static Author deleteBookFromAuthor(Author author, Book bookToDelete){
        ArrayList<String> allBooks = new ArrayList<>(author.getBooks());
        for (String item : allBooks) {
            if(item.equals(bookToDelete.getBookName())){
                allBooks.remove(bookToDelete.getBookName());
                author.setBooks(allBooks);
                return author;
            }
        }
        throw new RuntimeException("Such author does not contain this book");
    }
}
