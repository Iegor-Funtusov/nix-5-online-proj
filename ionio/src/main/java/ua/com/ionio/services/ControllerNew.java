package ua.com.ionio.services;

import ua.com.ionio.entity.Author;
import ua.com.ionio.entity.Book;
import ua.com.ionio.file.CreateFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerNew {
    private static AccessService access;
    private CreateFile file;
    BufferedReader reader;

    public ControllerNew(){
     reader = new BufferedReader(new InputStreamReader(System.in));
     access = new AccessService();
     file = new CreateFile();
     file.delete();
     file.init();
    }

    public void run() {
        boolean check = true;
        System.out.println("System have default realisation: ");
        while (check) {
            System.out.println("\n0 -> COMMONTEST \n1 -> add Author \n2 -> update Author\n3 -> delete Author \n" +
                    "4 -> get Author by id \n5 -> get all Authors \n6 -> add Book \n7 -> update Book \n" +
                    "8 -> delete Book \n9 -> get Book by id \n10 -> get all Books\n11 -> get all books by author id " +
                    "\n12 -> get all Authors by Book id \n13 -> exit" +
                    "\nEnter your choise!!! ");
            int res = 0;
            try {
                String choise = reader.readLine();
                res = Integer.parseInt(choise);
            } catch (IOException | IllegalArgumentException exception) {
                System.out.println("Incorrect input. Try once more");
                continue;
            }
            switch(res) {
                case 0: {
                    defaultrealistion();
                    break;
                }
                case 1: {
                    System.out.println("Enter Firstname");
                    try {
                        String firstname = reader.readLine();
                        System.out.println("Enter Lastname");
                        String secondname = reader.readLine();
                        if(!firstname.equals("") && !secondname.equals("")){
                            createAuthor(firstname, secondname);
                        }
                        else{
                            System.out.println("Can not create: empty field");
                        }
                    } catch (IOException e) {
                        System.out.println("Incorrect input. Try once more");
                    }
                    break;
                }
                case 2: {
                    System.out.println("What would you like to update? \n1 -> Firstname \n2 -> Lastname");
                    int res_ = 0;
                    try {
                        String choise = reader.readLine();
                        res_ = Integer.parseInt(choise);
                    } catch (IOException | IllegalArgumentException exception) {
                        System.out.println("Incorrect input. Try once more");
                        continue;
                    }
                    switch (res_){
                        case 1: {
                            System.out.println("Enter Author id");
                            try {
                                String id = reader.readLine();
                                Author author = access.getAuthorById(id);
                                System.out.println("Enter Firstname");
                                String firstname = reader.readLine();
                                if(!firstname.equals("")) {
                                    author.setFirstname(firstname);
                                    access.updateAuthor(author);
                                    break;
                                }
                            }
                            catch (IOException e) {
                                System.out.println("Incorrect input. Try once more");
                                break;
                            }
                            catch (IllegalArgumentException ec){
                                System.out.println(ec.getMessage());
                                break;
                            }
                        }
                        case 2: {
                            System.out.println("Enter Author id");
                            try {
                                String id = reader.readLine();
                                Author author = access.getAuthorById(id);
                                System.out.println("Enter Lastname");
                                String lastname = reader.readLine();
                                if(!lastname.equals("")) {
                                    author.setLastname(lastname);
                                    access.updateAuthor(author);
                                    break;
                                }
                            }
                            catch (IOException e) {
                                System.out.println("Incorrect input. Try once more");
                                break;
                            }
                            catch (IllegalArgumentException ec){
                                System.out.println(ec.getMessage());
                                break;
                            }
                        }
                        default: {System.out.println("Make your choise again!");}
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter Author id to delete it");
                    try {
                        String id = reader.readLine();
                        beforedeleteAuthor(id);
                        access.deleteAuthor(id);
                    }
                    catch (IOException e) {
                        System.out.println("Incorrect input. Try once more");
                    }
                    catch (IllegalArgumentException ec){
                        System.out.println(ec.getMessage());
                    }
                    break;
                }
                case 4: {
                    System.out.println("Enter Author id to get it");
                    try {
                        String id = reader.readLine();
                        Author author = access.getAuthorById(id);
                        if(author.isIsvisableAuthor()){
                            System.out.println(author);
                        }
                        else {
                            throw new IllegalArgumentException("Author doesn't exist");
                        }
                    }
                    catch (IOException e) {
                        System.out.println("Incorrect input. Try once more");
                    }
                    catch (IllegalArgumentException ec){
                        System.out.println(ec.getMessage());
                    }
                    break;
                }
                case 5: {
                    printAuthors();
                    break;
                }
                case 6: {
                    System.out.println("Enter Title");
                    try {
                        String title = reader.readLine();
                        System.out.println("Enter Authors: at least 1, use space between authors");
                        String authors = reader.readLine();
                        String[] strs = authors.trim().split("\\s+");
                        List<String> toadd = new ArrayList<String>();
                        toadd.addAll(Arrays.asList(strs));
                        if(title!=null && !toadd.get(0).equals("")){
                            createBook(title, toadd);
                        }
                    } catch (IOException e) {
                        System.out.println("Incorrect input. Try once more");
                    }
                    catch (IllegalArgumentException ec){
                        System.out.println(ec.getMessage());
                    }
                    break;
                }
                case 7: {
                    System.out.println("What would you like to update? \n1 -> Title \n2 -> Authors list");
                    int res0 = 0;
                    try {
                        String choise = reader.readLine();
                        res0 = Integer.parseInt(choise);
                    } catch (IOException | IllegalArgumentException exception) {
                        System.out.println("Incorrect input. Try once more");
                        continue;
                    }
                    switch (res0){
                        case 1: {
                            System.out.println("Enter Book id");
                            try {
                                String id = reader.readLine();
                                Book book = access.getBookById(id);
                                if(book.isIsvisableBook()){
                                System.out.println("Enter new Title");
                                String tilte = reader.readLine();
                                if(tilte!=null) {
                                    book.setTitle(tilte);
                                    access.updateBook(book);
                                    break;
                                    }
                                }
                                else{
                                    throw new IllegalArgumentException("This book doesn`t exist");
                                }
                            }
                            catch (IOException e) {
                                System.out.println("Incorrect input. Try once more");
                                break;
                            }
                            catch (IllegalArgumentException ec){
                                System.out.println(ec.getMessage());
                                break;
                            }
                        }
                        case 2: {
                            System.out.println("Enter Book id");
                            try {
                                String id = reader.readLine();
                                Book book = access.getBookById(id);
                                if(book.isIsvisableBook()) {
                                    System.out.println("Enter Authors` id");
                                    String authors = reader.readLine();
                                    String[] strs = authors.trim().split("\\s+");
                                    List<String> toadd = new ArrayList<String>();
                                    toadd.addAll(Arrays.asList(strs));
                                    if (!toadd.get(0).equals("")) {
                                        updateBook(id, toadd);
                                    }
                                }
                                else {
                                    throw new IllegalArgumentException("This book doesn`t exist");
                                }
                                break;
                            }
                            catch (IOException e) {
                                System.out.println("Incorrect input. Try once more");
                                break;
                            }
                            catch (IllegalArgumentException ec){
                                System.out.println(ec.getMessage());
                                break;
                            }
                        }
                        default: {System.out.println("Make your choise again!");}
                    }
                    break;
                }
                case 8: {
                        System.out.println("Enter Book id to delete it");
                        try {
                            String id = reader.readLine();
                            Book book = access.getBookById(id);
                            beforedeleteBook(id);
                            access.deleteBook(id);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        catch (IllegalArgumentException ec){
                            System.out.println(ec.getMessage());
                        }
                    break;
                }
                case 9: {
                    System.out.println("Enter Book id to get it");
                    try {
                        String id = reader.readLine();
                        Book book = access.getBookById(id);
                        if(book.isIsvisableBook()){
                            System.out.println(book);
                        }
                        else {
                            throw new IllegalArgumentException("Book doesn't exist");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    catch (IllegalArgumentException ec){
                        System.out.println(ec.getMessage());
                    }
                    break;
                }
                case 10: {
                    printBooks();
                    break;
                }
                case 11: {
                    System.out.println("Enter Author id");
                    try {
                        String id = reader.readLine();
                        if(access.getBookbyAuthor(id) != null){
                            printListBook(access.getBookbyAuthor(id));
                        }
                    }
                        catch (IOException e) {
                        e.printStackTrace();
                    }
                        catch (IllegalArgumentException ec){
                        System.out.println(ec.getMessage());
                    }
                    break;
                }
                case 12: {
                    System.out.println("Enter Book id");
                    try {
                        String id = reader.readLine();
                        if(access.getAuthorbyBook(id) != null){
                            printListAuthor(access.getAuthorbyBook(id));
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    catch (IllegalArgumentException ec){
                        System.out.println(ec.getMessage());
                    }
                    break;
                }
                case 13: {
                    check = false;
                    break;
                }
                default: {
                    System.out.println("Make your choise again!");
                }
            }
        }
    }

    public void defaultrealistion(){
        createAuthor("Benjamin", "Evans");
        createAuthor("Alexander", "Duma");
        createAuthor("Victor", "Hugo");
        createAuthor("David", "Flanagan");
        createAuthor("Leo", "Rid");
        createAuthor("Andr", "Gold");
        System.out.println("_______________________________________________________");
        System.out.println("Added 6 authors: ");
        printAuthors();
        List<String> authors = new ArrayList<String>();
        authors.add("3");
        createBook("Notre Dame", authors);
        authors.clear();
        authors.add("2");
        createBook("The Qeeen necklace", authors);
        authors.clear();
        authors.add("1");
        createBook("General info", authors);
        authors.add("4");
        createBook("Java in a Nutshell", authors);
        System.out.println("_______________________________________________________");
        System.out.println("Added 4 books: ");
        printBooks();
        System.out.println("Updated 4 authors: added books ");
        printAuthors();
        authors.clear();
        authors.add("5");
        authors.add("6");
        updateBook("1", authors);
        System.out.println("_______________________________________________________");
        System.out.println("Updated 1 book: added two authors");
        System.out.println(access.getBookById("1"));
        System.out.println("Updated 2 author: added books ");
        System.out.println(access.getAuthorById("5"));
        System.out.println(access.getAuthorById("6"));
        System.out.println("_______________________________________________________");
        System.out.println("Updated 1 book: changed title");
        Book book = access.getBookById("1");
        book.setTitle(book.getTitle()+"UPDATED");
        access.updateBook(book);
        System.out.println(access.getBookById("1"));
        System.out.println("_______________________________________________________");
        System.out.println("Updated 1 author: chabged firstname and lastname");
        Author author = access.getAuthorById("1");
        author.setFirstname(author.getFirstname()+"UPDATED");
        author.setLastname(author.getLastname()+"UPDATED");
        access.updateAuthor(author);
        System.out.println(access.getAuthorById("1"));
        System.out.println("_______________________________________________________");
        System.out.println("Delete author: id 2");
        beforedeleteAuthor("2");
        access.deleteAuthor("2");
        System.out.println("All books: ");
        printBooks();
        System.out.println("All authors ");
        printAuthors();
        System.out.println("_______________________________________________________");
        System.out.println("Delete book: id 3 (author has many books, so he is visible)");
        beforedeleteBook("3");
        access.deleteBook("3");
        System.out.println("All books: ");
        printBooks();
        System.out.println("All authors ");
        printAuthors();
        System.out.println("_______________________________________________________");
        System.out.println("Get authors by book id 1" );
        if(access.getAuthorbyBook("1") != null){
            printListAuthor(access.getAuthorbyBook("1"));
        }
        System.out.println("_______________________________________________________");
        System.out.println("Get books by author 1" );
        if(access.getBookbyAuthor("1") != null){
            printListBook(access.getBookbyAuthor("1"));
        }
    }

    private void printListAuthor(List<Author> objects){
        for (Author o : objects) {
            System.out.println(o);
        }
    }

    private void printListBook(List<Book> objects){
        for (Book o : objects) {
            System.out.println(o);
        }
    }

    public void createAuthor(String firstName, String lastName){
        List<String> books = new ArrayList<String>();
        Author author1 = new Author(firstName, lastName, books);
        access.createAuthor(author1);
    }

    public void createBook(String title, List<String> authors){
                Book book = new Book(title, authors);
                access.createBook(book);
                    for (String a: authors) {
                        if(access.getAuthorById(a)!=null) {
                            Author beforeUpdate = access.getAuthorById(a);
                            List<String> books = beforeUpdate.getListBooks();
                            if (books.get(0).equals("")) {
                                books.set(0, access.getAllBooks().get(access.getAllBooks().size() - 1).getId());
                            } else {
                                books.add(access.getAllBooks().get(access.getAllBooks().size() - 1).getId());
                            }
                            beforeUpdate.setListBooks(books);
                            access.updateAuthor(beforeUpdate);
                        }
                }
    }

    public void printAuthors(){
        List<Author> authors = access.getAllAuthors();
        for (Author a: authors) {
            System.out.println(a);
        }
    }

    private void beforedeleteAuthor(String id){
        List<String> books = access.getAuthorById(id).getListBooks();
        if(books.get(0).equals(""))
        for (String s: books) {
            if(access.getBookById(s)!=null && access.getBookById(s).getListAuthors().size() == 1 &&
                    access.getBookById(s).getListAuthors().get(0).equals(id)){
                Book book = access.getBookById(s);
                book.setIsvisableBook(false);
                access.updateBook(book);
            }

        }
    }

    private Author ifExist(String id){
        try{
            Author author = access.getAuthorById(id);
            return author;
        }
        catch (IllegalArgumentException e){
            return null;
        }
    }

    private void beforedeleteBook(String id){
        List<String> authors = access.getBookById(id).getListAuthors();
        for (String s: authors) {
            if(ifExist(s)!=null) {
                if (access.getAuthorById(s).isIsvisableAuthor() && access.getAuthorById(s).getListBooks().size() == 1 &&
                        access.getAuthorById(s).getListBooks().get(0).equals(id)) {
                    Author author = access.getAuthorById(s);
                    author.setIsvisableAuthor(false);
                    access.updateAuthor(author);
                }
            }
        }
    }

    public void printBooks(){
        List<Book> books = access.getAllBooks();
        for (Book b: books) {
            System.out.println(b);
        }
    }

    public void updateBook(String id, List<String> authors){
        Book book = access.getBookById(id);
        List<String> authorsBeforeUpdate = book.getListAuthors();
        for (String a: authors) {
            boolean isUbique = true;
            for (String s: authorsBeforeUpdate) {
                if(a.equals(s) || access.getAuthorById(a)==null){
                    isUbique = false;
                }
            }
            if(isUbique){
               authorsBeforeUpdate.add(a);
               book.setListAuthors(authorsBeforeUpdate);
               access.updateBook(book);
            }
            Author beforeUpdate = access.getAuthorById(a);
            List<String> books = beforeUpdate.getListBooks();
            if(books!=null){
                for(int i = 0; i < books.size(); i++){
                    boolean unique = true;
                    for (String b: books){
                        if(b.equals(id)){
                            unique = false;
                        }
                    }
            if(unique){
                if(books.get(0).equals("")){
                    books.set(0, id);
                }
                else {
                    books.add(id);
                }
            }
            beforeUpdate.setListBooks(books);
            access.updateAuthor(beforeUpdate);
                }
            }
        }
    }
}


