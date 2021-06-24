package dao;

import com.opencsv.CSVReader;
import data.Author;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVWriter;
import data.Book;
import type.FileType;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;


public class AllDao {


//For All________________________________________________
    public void initFiles() {
        List<String[]> csvDataAuthor = new ArrayList<>();
        String[] headerA = {"ID", "FirstName", "LastName", "Books", "Visible"};
        csvDataAuthor.add(headerA);
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_AUTHORS.getPath(),true))) {
            csvWriter.writeAll(csvDataAuthor);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String[]> csvDataBooks = new ArrayList<>();
        String[] headerB = {"ID", "Title", "Authors", "Visible"};
        csvDataBooks.add(headerB);
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_BOOKS.getPath(),true))) {
            csvWriter.writeAll(csvDataBooks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String allId(List<String> id) {
        StringBuilder ids = new StringBuilder();
        if(id!=null){
            for (String i : id) {
                ids.append(i).append("|");
            }
        }
        return ids.toString();
    }
    private List<String> parseId(String str){
        List<String> fin = new ArrayList<>();
        String[] masId = str.split("\\|");
        for (String s : masId) {
            if (s!=null){
                fin.add(s);
            }
        }
        return fin;
    }





//For Author______________________________________________

    public void createAuthor(Author author){
        List<String[]> csvData = new ArrayList<>();

        author.setId(generateIdAuthor(UUID.randomUUID().toString()));
        String[] currentAuthor = new String[5];
        currentAuthor[0] = author.getId();
        currentAuthor[1] = author.getFirstName();
        currentAuthor[2] = author.getLastName();
        currentAuthor[3] = allId(author.getBooks());
        currentAuthor[4] = Boolean.toString(author.getVisibleFlag());
        csvData.add(currentAuthor);

        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_AUTHORS.getPath(),true))) {
            csvWriter.writeAll(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public List<Author> reedAllA(){
        List<Author> authors = new ArrayList<>();

        try(CSVReader reader = new CSVReader(new FileReader(FileType.FILE_AUTHORS.getPath()))) {
            List<String[]> res = reader.readAll();
            for (String[] re : res) {
                if (re[0].equals("ID")){
                    continue;
                }
                Author a = new Author();
                a.setId(re[0]);
                a.setFirstName(re[1]);
                a.setLastName(re[2]);
                a.setBooks(parseId(re[3]));
                a.setVisibleFlag(Boolean.parseBoolean(re[4]));
                authors.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return authors;
    }

    public Author findAuthorById(String id) {
        Author a = new Author();

        try (CSVReader reader = new CSVReader(new FileReader(FileType.FILE_AUTHORS.getPath()))) {
            List<String[]> res = reader.readAll();
            for (String[] re : res) {
                if (re[0].equals(id)) {

                    a.setId(re[0]);
                    a.setFirstName(re[1]);
                    a.setLastName(re[2]);
                    a.setBooks(parseId(re[3]));
                    a.setVisibleFlag(Boolean.parseBoolean(re[4]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

    public void updateAuthor(Author author){
        List<String[]> newList = new ArrayList<>();
        String[] upd = new String[5];
        int i = 0;
        if (findAuthorById(author.getId()) == null) {
            throw new RuntimeException("Такого автора нет");
        }
        else {
            try(CSVReader reader = new CSVReader(new FileReader(FileType.FILE_AUTHORS.getPath()))) {
                List<String[]> res = reader.readAll();
                newList = res;
                for (String[] re : res) {
                    if (re[0].equals(author.getId())){
                        i = res.indexOf(re);
                        upd[0] = author.getId();
                        upd[1] = author.getFirstName();
                        upd[2] = author.getLastName();
                        upd[3] = allId(author.getBooks());
                        upd[4] = Boolean.toString(author.getVisibleFlag());
                        break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            newList.set(i,upd);
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_AUTHORS.getPath()))) {
                csvWriter.writeAll(newList);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void deleteAuthor(String id){
        Author author = findAuthorById(id);
        if (author.getVisibleFlag()){
            author.setVisibleFlag(false);
            updateAuthor(author);
        }
        else{
            System.out.println("Этот автор итак удален.");
        }
    }

    public List<Author> findAutByBook(String id){
        List<Author> authors = new ArrayList<>();
        Book book = findBookById(id);
        List<String> autIds = book.getAuthors();
        for (String autId : autIds) {
            if(autId==null){
                continue;
            }
            Author author = findAuthorById(autId);
            authors.add(author);
        }
        return authors;
    }

    private String generateIdAuthor(String id) {
        if(findAuthorById(id).getId() == null){
            return id;
        }
        else {
            return generateIdAuthor(UUID.randomUUID().toString());
        }
    }


//For Book _____________________________________________________

    public void createBook(Book book, List<String> authorsId){
        Author author = findAuthorById(authorsId.get(0));
        if(author.getId() == null){
            System.out.println("Автора с таким ИД нет");
        }
        else {
            book.setId(generateIdBook(UUID.randomUUID().toString()));
            List<String[]> csvData = new ArrayList<>();
            String[] currentBook = new String[4];
            currentBook[0] = book.getId();
            currentBook[1] = book.getTitle();
            currentBook[2] = allId(authorsId);
            currentBook[3] = Boolean.toString(book.getVisibleFlag());
            csvData.add(currentBook);
//            добавление в книгу авторов
            for (String s : authorsId) {
                if(s == null){
                    continue;
                }
                Author author1 = findAuthorById(s);
                if(author1!=null){
                    List<String> bookList = author1.getBooks();
                    bookList.add(book.getId());
                    updateAuthor(author1);
                }

            }
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_BOOKS.getPath(),true))) {
                csvWriter.writeAll(csvData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Book> reedAllB(){
        List<Book> books = new ArrayList<>();

        try(CSVReader reader = new CSVReader(new FileReader(FileType.FILE_BOOKS.getPath()))) {
            List<String[]> res = reader.readAll();
            for (String[] re : res) {
                if (re == null || re[0].equals("ID")){
                    continue;
                }
                Book b = new Book();
                b.setId(re[0]);
                b.setTitle(re[1]);
                b.setAuthors(parseId(re[2]));
                b.setVisibleFlag(Boolean.parseBoolean(re[3]));

                books.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    public Book findBookById(String id) {
        Book b = new Book();

        try (CSVReader reader = new CSVReader(new FileReader(FileType.FILE_BOOKS.getPath()))) {
            List<String[]> res = reader.readAll();
            for (String[] re : res) {
                if (re[0].equals(id)) {
                    b.setId(re[0]);
                    b.setTitle(re[1]);
                    b.setAuthors(parseId(re[2]));
                    b.setVisibleFlag(Boolean.parseBoolean(re[3]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }

    public void updateBook(Book book){
        List<String[]> newList = new ArrayList<>();
        String[] upd = new String[4];
        int i = 0;
        if (findBookById(book.getId()) == null) {
            throw new RuntimeException("Такой книги нет");
        }
        else {
            try(CSVReader reader = new CSVReader(new FileReader(FileType.FILE_BOOKS.getPath()))) {
                List<String[]> res = reader.readAll();
                newList = res;
                for (String[] re : res) {
                    if (re[0].equals(book.getId())){
                        i = res.indexOf(re);
                        upd[0] = book.getId();
                        upd[1] = book.getTitle();

                        upd[2] = allId(book.getAuthors());
                        upd[3] = Boolean.toString(book.getVisibleFlag());
                        break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            newList.set(i,upd);
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_BOOKS.getPath()))) {
                csvWriter.writeAll(newList);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void deleteBook(String id){
        Book book = findBookById(id);
        if (book.getVisibleFlag()){
            book.setVisibleFlag(false);
            updateBook(book);
        }
        else{
            System.out.println("Эта книга итак удалена.");
        }
    }

    public List<Book> findBookByAut(String id){
        List<Book> books = new ArrayList<>();
        Author author = findAuthorById(id);
        List<String> bookIds = author.getBooks();
        for (String bId : bookIds) {
            if(bId==null){
                continue;
            }
            Book book =findBookById(bId);
            books.add(book);
        }
        return books;
    }

    private String generateIdBook(String id) {
        if(findBookById(id).getId() == null){
            return id;
        }
        else {
            return generateIdBook(UUID.randomUUID().toString());
        }
    }




}


//    private List<Book> fromIdsToBooks(String ids){
//        List<Book> books = new ArrayList<>();
//        if (ids == null || ids.length() < 2 ){
//            System.out.println("У данного автора нет книг");
//        }else {
//            String[] masId = ids.split("\\|");
//            for (String s : masId) {
//                if(s==null || s.length() <1){
//                    continue;
//                }
//                Book b = findBookById(s);
//                b.setAuthors(null);
//                books.add(b);
//            }
//        }
//        return  books;
//    }
//
//    private List<Author> fromIdsToAuthor(String ids){
//        List<Author> authors = new ArrayList<>();
//        if (ids == null || ids.length() < 2 ){
//            System.out.println("У данной книги нет авторов");
//        }else {
//            String[] masId = ids.split("\\|");
//            for (String s : masId) {
//                Author a = findAuthorById(s);
//                a.setBooks(null);
//                authors.add(a);
//            }
//        }
//        return  authors;
//    }
//    private String idOfBooks(List<String> books) {
//        StringBuilder ids = new StringBuilder();
//        if(books!=null){
//            for (Book book : books) {
//                if (book != null) {
//                    ids.append(book.getId()).append("|");
//                }
//            }
//        }
//
//        return ids.toString();
//    }