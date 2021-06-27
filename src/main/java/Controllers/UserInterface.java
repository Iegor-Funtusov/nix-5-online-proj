package Controllers;

import Configs.PathConfigs;
import DataClasses.Author;
import DataClasses.Book;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    private final MainController mainController;
    private final BufferedReader bf;
    private final String AGREE_INPUT = "1";
    private static final Logger infoLog = LoggerFactory.getLogger("info");
    private static final Logger warningLog = LoggerFactory.getLogger("warn");
    private static final Logger errorLog = LoggerFactory.getLogger("error");

    public UserInterface(){
        mainController = new MainController();
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public void userUI(){
        while(true){
            try{
                System.out.println("""
                        Choose the option:\s
                        1-create book, 2-update book, 3-get info, 4-get all books, 5-delete book, Q-exit program\s
                        6-create author, 7-update author, 8-get info, 9-get all authors, 0-delete author, Q-exit program\s
                        To run the common test press T""");

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
                    case "T" -> commonTestRun();
                    case "Q" -> System.exit(1);
                    default -> {
                        System.out.println("Incorrect value entered");
                        return;
                    }
                }

            } catch (IOException e){
                errorLog.error("IOException in user ui");
                System.out.println("Incorrect value entered");
            } catch (RuntimeException e){
                errorLog.error("RuntimeException in user ui");
                System.out.println(e.getMessage());
            }
        }
    }


    private void createBook(){
        try{
            infoLog.info("Try to add new book");

            Book newBook = new Book();
            System.out.println("Enter name of the book:");
            String bookName = bf.readLine();
            newBook.setBookName(bookName);
            System.out.println("Choose the author");
            Author author = mainController.chooseAuthor();
            mainController.createBook(newBook, author);
            System.out.println("Book was successfully created. " + newBook.toString() + "\n");

            infoLog.info("Book was successfully added");

        } catch (IOException e){
            errorLog.error("IOException in createBook");
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            errorLog.error("RuntimeException in createBook");
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            errorLog.error("CsvException in createBook");
            System.out.println(e.getMessage());
        }
    }


    private void readBook(){
        try{
            infoLog.info("Try to read one book");

            System.out.println("Enter name of book info about which do you want to get:");
            String bookName = bf.readLine();
            Book foundBook = mainController.readBook(bookName);
            System.out.println("Successfully found. " + foundBook.toString());

            infoLog.info("Book was successfully red");

        } catch (IOException e){
            errorLog.error("IOException in readBook");
            System.out.println("Incorrect value entered");
        }  catch (CsvException e){
            errorLog.error("CsvException in readBook");
            System.out.println(e.getMessage());
        }  catch (RuntimeException e){
            errorLog.error("RuntimeException in readBook");
            System.out.println(e.getMessage());
        }
    }


    private void readAllBooks(){
        try {
            infoLog.info("Try to read all books");

            List<Book> allBooks = mainController.readAllBooks();
            allBooks.forEach(System.out::println);
            System.out.println();

            infoLog.info("All books were successfully read");

        } catch (IOException e) {
            errorLog.error("IOException in readAllBooks");
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            errorLog.error("CsvException in readAllBooks");
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            errorLog.error("RuntimeException in readAllBooks");
            System.out.println(e.getMessage());
        }
    }

    private void updateBook(){
        try {
            warningLog.warn("Try to update the book");

            System.out.println("Choose the book which you want to update:");
            Book bookToUpd = mainController.chooseBook();
            System.out.println("You chose " + bookToUpd.toString());
            System.out.println("What do you want to update? 1-name of book, 2-add author, 3-delete author, 0-exit");
            String choose = bf.readLine();
            switch (choose){
                case "1" -> {
                    infoLog.info("Updating book: adding name");

                    System.out.println("Enter new book's name:");
                    String newBookName = bf.readLine();
                    mainController.updateBookName(bookToUpd, newBookName);
                }

                case "2" -> {
                    infoLog.info("Updating book: adding author");

                    System.out.println("Choose the author which you want to add:");
                    Author authorToAdd = mainController.chooseAuthor();
                    mainController.updateBookAddAuthor(bookToUpd, authorToAdd);
                }
                case "3" -> {
                    infoLog.info("Updating book: deleting author");

                    if(bookToUpd.getAuthors().size() == 1){
                        System.out.println("You can not delete author from this book, because this book has only one author");
                        return;
                    }
                    System.out.println("Choose the author which you want to delete:");
                    Author authorToDel = mainController.chooseAuthor();
                    mainController.updateBookDeleteAuthor(bookToUpd, authorToDel);
                }
                case "0" -> System.exit(1);
                default -> {
                    System.out.println("Incorrect value entered");
                    return;
                }
            }

            infoLog.info("Successfully updated");

            System.out.println("Successfully updated. Do you want to see the result? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                System.out.println(bookToUpd.toString());
            }

        } catch (IOException e) {
            errorLog.error("IOException in updateBook");
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            errorLog.error("CsvException in updateBook");
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            errorLog.error("RuntimeException in updateBook");
            System.out.println(e.getMessage());
        }
    }


    private void deleteBook(){
        try{
            warningLog.warn("Try to delete the book");

            System.out.println("Choose the book which you want to delete:");
            Book bookToDel = mainController.chooseBook();
            mainController.deleteBook(bookToDel);
            System.out.println("Successfully deleted");

            infoLog.info("Successfully deleted");

        } catch (IOException e){
            errorLog.error("IOException in deleteBook");
            System.out.println("Incorrect value entered");
        } catch (CsvException e) {
            errorLog.error("CsvException in deleteBook");
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            errorLog.error("RuntimeException in deleteBook");
            System.out.println(e.getMessage());
        }
    }


    private void createAuthor(){
        try{
            infoLog.info("Try to create author");

            Author newAuthor = new Author();

            System.out.println("Enter firstname of the author:");
            String firstname = bf.readLine();
            newAuthor.setFirstName(firstname);

            System.out.println("Enter lastname of the author:");
            String lastname = bf.readLine();
            newAuthor.setLastName(lastname);

            System.out.println("Do you want to add book to the author? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                Book bookToAdd = mainController.chooseBook();
                mainController.createAuthor(newAuthor, bookToAdd);
            } else{
                System.out.println("Author will be added without books");
                mainController.createAuthor(newAuthor);
            }

            System.out.println("Author was successfully created. " + newAuthor.toString() + "\n");
            infoLog.info("Author was successfully created");


        } catch (IOException e){
            errorLog.error("IOException in createAuthor");
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            errorLog.error("RuntimeException in createAuthor");
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            errorLog.error("CsvException in createAuthor");
            System.out.println(e.getMessage());
        }
    }


    private void readAuthor(){
        try{
            infoLog.info("Try to read one author");

            System.out.println("Enter full name of the author information about which you want to get:");
            String fullName = bf.readLine();
            Author author = mainController.readAuthor(fullName);
            System.out.println("Successfully found. " + author.toString());

            infoLog.info("Author was successfully red");

        } catch (IOException e){
            errorLog.error("IOException in readAuthor");
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            errorLog.error("RuntimeException in readAuthor");
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            errorLog.error("CsvException in readAuthor");
            System.out.println(e.getMessage());
        }
    }


    private void readAllAuthors(){
        try {
            infoLog.info("Try to read all authors");

            List<Author> allAuthors = mainController.readAllAuthors();
            allAuthors.forEach(System.out::println);
            System.out.println();

            infoLog.info("All authors were successfully red");

        } catch (IOException e) {
            errorLog.error("IOException in readAllAuthors");
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            errorLog.error("CsvException in readAllAuthors");
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            errorLog.error("RuntimeException in readAllAuthors");
            System.out.println(e.getMessage());
        }
    }


    private void updateAuthor(){
        try {
            warningLog.warn("Try to update author");

            System.out.println("Choose thr author which you want to update:");
            Author authorToUpd = mainController.chooseAuthor();
            System.out.println("You chose: " + authorToUpd.toString());
            System.out.println("1-update firstname, 2-update lastname, 3-add book, 4-delete book, 0-exit");
            String choose = bf.readLine();
            switch (choose){
                case "1" -> {
                    infoLog.info("Updating author: changing firstname");

                    System.out.println("Enter new author's firstname");
                    String newFirstName = bf.readLine();
                    mainController.updateAuthorFirstName(authorToUpd, newFirstName);
                }
                case "2" -> {
                    infoLog.info("Updating author: changing lastname");

                    System.out.println("Enter new author's lastname");
                    String newLastName = bf.readLine();
                    mainController.updateAuthorLastName(authorToUpd, newLastName);
                }
                case "3" -> {
                    infoLog.info("Updating author: adding book");

                    System.out.println("Choose the book which you want to add to the author:");
                    Book bookToAdd = mainController.chooseBook();
                    mainController.updateAuthorAddBook(authorToUpd, bookToAdd);
                }
                case "4" -> {
                    infoLog.info("Updating author: deleting book");

                    if(authorToUpd.getBooks().size() == 0){
                        System.out.println("Author does not contain any book");
                        return;
                    }
                    System.out.println("Choose the book which you want to delete from the author:");
                    Book bookToDel = mainController.chooseBook();
                    mainController.updateAuthorDeleteBook(authorToUpd, bookToDel);
                }
                case "0" -> System.exit(1);
                default -> {
                    System.out.println("Incorrect value entered");
                    return;
                }
            }

            infoLog.info("Successfully updated");

            System.out.println("Successfully updated. Do you want to see the result? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                System.out.println(authorToUpd.toString());
            }

        } catch (IOException e) {
            errorLog.error("IOException in updateAuthor");
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            errorLog.error("CsvException in updateAuthor");
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            errorLog.error("RuntimeException in updateAuthor");
            System.out.println(e.getMessage());
        }
    }


    private void deleteAuthor(){
        try{
            warningLog.warn("Try to delete author");

            System.out.println("Choose the author which you want to delete:");
            Author authorToDelete = mainController.chooseAuthor();
            mainController.deleteAuthor(authorToDelete);
            System.out.println("Successfully deleted");

            infoLog.info("Author was successfully deleted");

        } catch (IOException e){
            errorLog.error("IOException in deleteAuthor");
            System.out.println("Incorrect value entered");
        } catch (CsvException e) {
            errorLog.error("CsvException in deleteAuthor");
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            errorLog.error("RuntimeException in deleteAuthor");
            System.out.println(e.getMessage());
        }
    }


    private void commonTestRun() {
        try {
            warningLog.warn("Common test started");

            List<Author> allAuthors;
            List<Book> allBooks;

            System.out.println("Common test:");
            System.out.println("___________________________________________________________________");

            infoLog.info("Generating authors in common test");
            System.out.println("Generating authors WITHOUT books");
            for (int i = 0; i < 10; i++) {
                Author newAuthor = new Author();
                newAuthor.setFirstName("Firstname " + (i + 1));
                newAuthor.setLastName("Lastname " + (i + 1));
                mainController.createAuthor(newAuthor);
            }
            System.out.println("Successfully created authors");
            infoLog.info("Successfully generated authors common test");

            System.out.println("___________________________________________________________________");
            System.out.println("All created authors:");
            allAuthors = mainController.readAllAuthors();
            allAuthors.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            infoLog.info("Generating books in common test");
            System.out.println("Generating books:");
            for(int i = 0; i < 10; i++){
                Book newBook = new Book();
                newBook.setBookName("BookName " + (i+1));
                List<String> authors = new ArrayList<>();
                Author authorOfBook = mainController.readAllAuthors().get(i);
                mainController.createBook(newBook, authorOfBook);
            }
            System.out.println("Successfully created books");
            infoLog.info("Successfully generated books in common test");

            System.out.println("___________________________________________________________________");
            System.out.println("All created books:");
            allBooks = mainController.readAllBooks();
            allBooks.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            System.out.println("All created authors WITH books:");
            allAuthors = mainController.readAllAuthors();
            allAuthors.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            warningLog.warn("Updating authors in common test");

            System.out.println("Updating authors:");
            System.out.println("Changing firstname of the author");
            allAuthors = mainController.readAllAuthors();
            for(int i = 0; i < 10; i++){
                Author authorToUpd = allAuthors.get(i);
                String newFirstName = "UpdatedFirstname" + (i+1);
                mainController.updateAuthorFirstName(authorToUpd, newFirstName);
            }
            System.out.println("Successfully updated authors");
            infoLog.info("Authors successfully updated in common test");

            System.out.println("___________________________________________________________________");
            System.out.println("All updated authors:");
            allAuthors = mainController.readAllAuthors();
            allAuthors.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            warningLog.warn("Updating books in common test");

            System.out.println("Updating books:");
            System.out.println("Adding new author to book with +1 index");
            allBooks = mainController.readAllBooks();
            for(int i = 0; i < 10; i++){
                Book bookToUpd = allBooks.get(i);
                Author authorToAdd;

                if(i == 9){
                    authorToAdd = allAuthors.get(0);
                } else {
                    authorToAdd = allAuthors.get(i + 1);
                }
                mainController.updateBookAddAuthor(bookToUpd, authorToAdd);
            }
            System.out.println("Successfully updated books");
            infoLog.info("Books successfully updated in common test");

            System.out.println("___________________________________________________________________");
            System.out.println("All updated books:");
            allBooks = mainController.readAllBooks();
            allBooks.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            warningLog.warn("Deleting books in common test");
            System.out.println("Deleting books:");
            System.out.println("Delete 5 first books");
            allBooks = mainController.readAllBooks();
            for(int i = 0; i < 5; i++){
                Book bookToDel = allBooks.get(i);
                mainController.deleteBook(bookToDel);
            }
            System.out.println("Successfully deleted");
            infoLog.info("Successfully deleted books in common test");

            System.out.println("___________________________________________________________________");
            System.out.println("All remaining books:");
            allBooks = mainController.readAllBooks();
            allBooks.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            warningLog.warn("Deleting authors in common test");
            System.out.println("Deleting authors:");
            System.out.println("Delete 5 first authors");
            allAuthors = mainController.readAllAuthors();
            for(int i = 0; i < 5; i++){
                Author authorToDel = allAuthors.get(i);
                mainController.deleteAuthor(authorToDel);
            }
            System.out.println("Successfully deleted");
            infoLog.info("Successfully deleted books in common test");

            System.out.println("___________________________________________________________________");
            System.out.println("All remaining authors:");
            allAuthors = mainController.readAllAuthors();
            allAuthors.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            System.out.println("End of common test");
            System.out.println("___________________________________________________________________");

            warningLog.warn("Deleting files in common test");
            System.out.println("Do you want to delete common test files? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                Path pathToBooksCSV = Paths.get(PathConfigs.BOOKS_FILE.getPath());
                Path pathToAuthorsCSV = Paths.get(PathConfigs.AUTHORS_FILE.getPath());

                Files.deleteIfExists(pathToBooksCSV);
                Files.deleteIfExists(pathToAuthorsCSV);

                System.out.println("Successfully deleted");
                System.out.println("___________________________________________________________________");
                infoLog.info("Files in common test were successfully deleted");
            }

        } catch (IOException e) {
            errorLog.error("IOException in common test");
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            errorLog.error("CsvException in common test");
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            errorLog.error("RuntimeException in common test");
            System.out.println(e.getMessage());
        }
    }

}