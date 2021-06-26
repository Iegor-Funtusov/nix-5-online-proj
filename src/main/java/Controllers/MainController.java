package Controllers;

import Configs.PathConfigs;
import DataClasses.Author;
import DataClasses.Book;
import Services.AuthorService;
import Services.BookService;
import Validation.Validator;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class MainController {
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bf;
    private final String AGREE_INPUT = "1";

    public MainController(){
        authorService = new AuthorService(PathConfigs.AUTHORS_FILE.getPath());
        bookService = new BookService(PathConfigs.BOOKS_FILE.getPath());
        bf = new BufferedReader(new InputStreamReader(System.in));
    }



    public void userInterface(){
        while(true){
            try{
                System.out.println("""
                        Choose thr option:\s
                        1-create book, 2-update book, 3-get info, 4-get all books, 5-delete book, Q-exit program\s
                        6-create author, 7-update author, 8-get info, 9-get all authors, 0-delete author, Q-exit program""");

                String choose = bf.readLine();
                switch (choose.toUpperCase()){
                    case "1" -> createBook();
                    case "2" -> updateBook();
                    case "3" -> readBook();
                    case "4" -> readAllBooks();
                    case "5" -> deleteBook();
                    case "6" -> createAuthor();
                    case "7" -> updateAuthor();
                    case "8" -> readAuthor();
                    case "9" -> readAllAuthors();
                    case "0" -> deleteAuthor();
                    case "Q" -> System.exit(1);
                    default -> System.out.println("Incorrect value entered");
                }

            } catch (IOException e){
                System.out.println("Incorrect value entered");
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }


    private void createBook(){
        try{
            Book newBook = new Book();
            System.out.println("Enter name of the book:");
            String bookName = bf.readLine();
            newBook.setBookName(bookName);
            System.out.println("Choose the author");
            Author author = chooseAuthor();
            if(author == null){
                System.out.println("The book could not be created");
                return;
            }
            bookService.addAuthorToBook(newBook, author);
            bookService.createBook(newBook);

            authorService.addBookToAuthor(author, newBook);
            authorService.updateAuthor(author);
            System.out.println("Successfully created. " + newBook.toString() + "\n");

            //Несколько авторов или один?
            //System.out.println("1-Choose the author from existing. 2-create new author");


        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }


    private void readBook(){
        try{
            System.out.println("Enter name of book info about which do you want to get:");
            String bookName = bf.readLine();
            Book foundBook = bookService.findBookByName(bookName);
            System.out.println("Successfully found. " + foundBook.toString());

        } catch (IOException e){
            System.out.println("Incorrect value entered");
        }  catch (CsvException e){
            System.out.println(e.getMessage());
        }  catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void readAllBooks(){
        try {
            List<Book> allBooks = bookService.read();
            allBooks.forEach(System.out::println);
            System.out.println("\n");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void deleteBookFromAuthors(Book bookToUpd) throws IOException, CsvException {
        List<String> allAuthors = new ArrayList<>(bookToUpd.getAuthors());
        for (String item : allAuthors){
            Author authorToUpd = authorService.findAuthorByName(item);
            authorToUpd = authorService.deleteBookFromAuthor(authorToUpd, bookToUpd);
            authorService.updateAuthor(authorToUpd);
        }
    }

    private void addBooksToAuthors(Book bookToUpd) throws IOException, CsvException {
        List<String> allAuthors = new ArrayList<>(bookToUpd.getAuthors());
        for (String item : allAuthors){
            Author authorToUpd = authorService.findAuthorByName(item);
            authorToUpd = authorService.addBookToAuthor(authorToUpd, bookToUpd);
            authorService.updateAuthor(authorToUpd);
        }
    }




    private void updateBook(){
        try {
            System.out.println("Choose the book which you want to update:");
            Book bookToUpd = chooseBook();
            System.out.println("You chose " + bookToUpd.toString());
            System.out.println("What do you want to update? 1-name of book, 2-add author, 3-delete author, 0-exit");
            String choose = bf.readLine();
            switch (choose){
                case "1" -> {
                    //Удаляю у авторов книги старое имя книги
                    deleteBookFromAuthors(bookToUpd);       //НЕ УДАЛИЛОСЬ СТАРОЕ НАЗВАНИЕ КНИГИ

                    System.out.println("Enter new book's name:");
                    String newBookName = bf.readLine();
                    bookToUpd.setBookName(newBookName);
                    bookService.updateBook(bookToUpd);      //Обновляю книгу

                    //Добавляю авторам книгу с новым именем
                    addBooksToAuthors(bookToUpd);
                }

                case "2" -> {
                    System.out.println("Choose the author which you want to add:");
                    Author authorToAdd = chooseAuthor();
                    if(authorToAdd == null){
                        System.out.println("Incorrect value entered");
                        return;
                    }
                    bookToUpd = bookService.addAuthorToBook(bookToUpd, authorToAdd);
                    bookService.updateBook(bookToUpd);

                    authorToAdd = authorService.addBookToAuthor(authorToAdd, bookToUpd);
                    authorService.updateAuthor(authorToAdd);
                }
                case "3" -> {
                    //МОГУ УДАЛЯТЬ ИЗ КНИГИ ТОЛЬКО ТОГО АВТОРА, КОТОРЫЙ ЕСТЬ В КНИГЕ

//                    if(bookToUpd.getAuthors().size() == 1){
//                        System.out.println("You can not delete author from this book, because this book has only one author");
//                        return;
//                    }
//                    System.out.println("Choose the author which you want to delete:");
//                    Author authorToDelete = chooseAuthor();
//                    if(authorToDelete == null){
//                        System.out.println("Incorrect value entered");
//                        return;
//                    }
//                    bookToUpd = bookService.deleteAuthorFromBook(bookToUpd, authorToDelete);
                }
                case "0" -> System.exit(1);
                default -> System.out.println("Incorrect value entered");
            }
            System.out.println("Successfully updated. Do you want to see the result? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                System.out.println(bookToUpd.toString());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void deleteBook(){
        try{
            System.out.println("Choose the book which you want to delete:");
            Book bookToDelete = chooseBook();
            bookService.deleteBook(bookToDelete);
            System.out.println("Successfully deleted");

        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }



//МБ Предлагать создать новую книгу если такой ещё нет
    private void createAuthor(){
        try{
            Author newAuthor = new Author();

            System.out.println("Enter firstname of the author:");
            String firstname = bf.readLine();
            newAuthor.setFirstName(firstname);

            System.out.println("Enter lastname of the author:");
            String lastname = bf.readLine();
            newAuthor.setLastName(lastname);


            System.out.println("Do you want to add book to the author? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                Book bookToAdd = chooseBook();
                if(bookToAdd != null){
                    authorService.addBookToAuthor(newAuthor,bookToAdd);
                    bookService.addAuthorToBook(bookToAdd, newAuthor);
                    bookService.updateBook(bookToAdd);
                }
                else{
                    System.out.println("Author will be added without books");
                }
            }

            authorService.createAuthor(newAuthor);
            System.out.println("Successfully created. " + newAuthor.toString() + "\n");

        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }


    private void readAuthor(){
        try{
            System.out.println("Enter full name of the author information about which you want to get:");
            String fullName = bf.readLine();

        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void readAllAuthors(){
        try {
            List<Author> allAuthors = authorService.read();
            allAuthors.forEach(System.out::println);
            System.out.println("\n");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }



    private void deleteAuthorFromBooks(Author authorToUpd) throws IOException, CsvException {
        List<String> allBooks = new ArrayList<>(authorToUpd.getBooks());
        for(String item : allBooks){
            Book bookToUpd = bookService.findBookByName(item);
            bookToUpd = bookService.deleteAuthorFromBook(bookToUpd, authorToUpd);
            bookService.updateBook(bookToUpd);
        }
    }


    private void addAuthorToBooks(Author authorToUpd) throws IOException, CsvException {
        List<String> allBooks = new ArrayList<>(authorToUpd.getBooks());
        for(String item : allBooks){
            Book bookToAdd = bookService.findBookByName(item);
            authorToUpd = authorService.addBookToAuthor(authorToUpd, bookToAdd);
            authorService.updateAuthor(authorToUpd);
        }
    }


    private void updateAuthor(){
        try {
            System.out.println("Choose thr author which you want to update:");
            Author authorToUpd = chooseAuthor();
            System.out.println("You chose: " + authorToUpd.toString());
            System.out.println("1-update firstname, 2-update lastname, 3-add book, 4-delete book, 0-exit");
            String choose = bf.readLine();
            switch (choose){
                case "1" -> {
                    //Вылетает ексепшн
                    deleteAuthorFromBooks(authorToUpd);
                    System.out.println("Enter new author's firstname");
                    String newFirstName = bf.readLine();
                    authorToUpd.setFirstName(newFirstName);
                    authorService.updateAuthor(authorToUpd);
                    addAuthorToBooks(authorToUpd);
                }
                case "2" -> {
                    deleteAuthorFromBooks(authorToUpd);
                    System.out.println("Enter new author's lastname");
                    String newLastName = bf.readLine();
                    authorToUpd.setLastName(newLastName);
                    authorService.updateAuthor(authorToUpd);
                    addAuthorToBooks(authorToUpd);
                }
                case "3" -> {
                    System.out.println("Choose the book which you want to add to the author:");
                    Book bookToAdd = chooseBook();
                    if(bookToAdd == null){
                        System.out.println("Incorrect vale entered");
                        return;
                    }
                    authorToUpd = authorService.addBookToAuthor(authorToUpd, bookToAdd);
                    bookToAdd = bookService.addAuthorToBook(bookToAdd, authorToUpd);
                    bookService.updateBook(bookToAdd);
                }
                case "4" -> {
                    //Могу удалять только те, которые есть у автора, а не вообще все
                    //Предусмотреть что модет быть без книг
                    System.out.println("Deleting book");
                }
                case "0" -> System.exit(1);
                default -> System.out.println("Incorrect value entered");
            }
            System.out.println("Successfully updated. Do you want to see the result? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                System.out.println(authorToUpd.toString());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void deleteAuthor(){
        try{
            System.out.println("Choose the author which you want to delete:");
            Author authorToDelete = chooseAuthor();
            List<String> allBooks = authorToDelete.getBooks();
            authorService.deleteAuthor(authorToDelete);
            for (String item : allBooks) {
                Book bookToModify = bookService.findBookByName(item);
                bookToModify = bookService.deleteAuthorFromBook(bookToModify, authorToDelete);
                bookService.updateBook(bookToModify);
            }

            System.out.println("Successfully deleted");

        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }



    private Author chooseAuthor(){
        try {
            List<Author> allAuthors = authorService.read();
            int index = 1;
            for (Author author : allAuthors) {
                System.out.println("#" + index + " " + author.toString());
                index++;
            }
            System.out.println("Choose index of the author:");
            index = Integer.parseInt(bf.readLine());
            index--;    //Т.к. для пользователя индексация начинается с единицы
            Validator.validateIndexOfElement(index, allAuthors);
            return allAuthors.get(index);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    private Book chooseBook() throws IOException, CsvException {
        try{
            List<Book> allBooks = bookService.read();
            int index = 1;
            for (Book book : allBooks) {
                System.out.println("#" + index + " " + book.toString());
                index++;
            }
            System.out.println("Choose index of the book:");
            index = Integer.parseInt(bf.readLine());
            index--;    //Т.к. для пользователя индексация начинается с единицы
            Validator.validateIndexOfElement(index, allBooks);
            return allBooks.get(index);
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (CsvException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
