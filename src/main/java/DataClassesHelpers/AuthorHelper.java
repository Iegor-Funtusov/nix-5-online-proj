package DataClassesHelpers;

import DataClasses.Author;
import DataClasses.Book;

import java.util.ArrayList;

public class AuthorHelper {
    //МБ СЮДА ПОТОМ ВЫВНЕСТИ ChooseAuthor
    //Добавить удаление

    public static Author addBookToAuthor(Author author, Book bookToAdd){
        //Добавить проверку на то, что такая книга у автора уже есть
        ArrayList<String> allBooks = new ArrayList<>(author.getBooks());
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
                return author;
            }
        }
        throw new RuntimeException("Such author does not contain this book");
    }
}
