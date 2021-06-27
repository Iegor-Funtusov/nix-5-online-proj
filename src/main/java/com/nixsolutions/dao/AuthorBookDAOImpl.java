package com.nixsolutions.dao;

import com.nixsolutions.model.Author;
import com.nixsolutions.model.Book;
import com.nixsolutions.type.FileType;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class AuthorBookDAOImpl implements AuthorBookDAO {

    public void createAuthorBookCsv() {

        List<String[]> AuthorCsv = new ArrayList<>();
        String[] authorHeader = {"ID", "FirstName", "LastName", "Books", "isVisible"};
        AuthorCsv.add(authorHeader);
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_AUTHORS.getPath(),true))) {
            csvWriter.writeAll(AuthorCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String[]> BookCsv = new ArrayList<>();
        String[] bookHeader = {"ID", "Title", "Authors", "isVisible"};
        BookCsv.add(bookHeader);
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_BOOKS.getPath(),true))) {
            csvWriter.writeAll(BookCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAuthorBookId(List<String> id) {

        StringBuilder iD = new StringBuilder();
        if(id!=null){
            for (String i : id) {
                iD.append(i).append("|");
            }
        }
        return iD.toString();
    }

    private List<String> parseIdToStr(String string){

        List<String> list = new ArrayList<>();
        String[] id = string.split("\\|");
        for (String s : id) {
            if (s!=null){
                list.add(s);
            }
        }
        return list;
    }

    @Override
    public void createAuthor(Author author){

        List<String[]> csvType = new ArrayList<>();
        author.setId(createAuthorId(UUID.randomUUID().toString()));
        String[] newAuthor = new String[5];
        newAuthor[0] = author.getId();
        newAuthor[1] = author.getFirstName();
        newAuthor[2] = author.getLastName();
        newAuthor[3] = getAuthorBookId(author.getBookList());
        newAuthor[4] = Boolean.toString(author.getIsVisible());
        csvType.add(newAuthor);

        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_AUTHORS.getPath(),true))) {
            csvWriter.writeAll(csvType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Author> reedAllAuthors(){

        List<Author> authors = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(FileType.FILE_AUTHORS.getPath()))) {
            List<String[]> str = reader.readAll();
            for (String[] field : str) {
                if (field[0].equals("ID")){
                    continue;
                }
                Author author = new Author();
                author.setId(field[0]);
                author.setFirstName(field[1]);
                author.setLastName(field[2]);
                author.setBookList(parseIdToStr(field[3]));
                author.setIsVisible(Boolean.parseBoolean(field[4]));
                authors.add(author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public Author findAuthorById(String id) {

        Author author = new Author();
        try (CSVReader reader = new CSVReader(new FileReader(FileType.FILE_AUTHORS.getPath()))) {
            List<String[]> str = reader.readAll();

            for (String[] field : str) {
                if (field[0].equals(id)) {
                    author.setId(field[0]);
                    author.setFirstName(field[1]);
                    author.setLastName(field[2]);
                    author.setBookList(parseIdToStr(field[3]));
                    author.setIsVisible(Boolean.parseBoolean(field[4]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public void updateAuthor(Author author){

        List<String[]> strList = new ArrayList<>();
        String[] updatedAuthor = new String[5];
        int i = 0;
        if (findAuthorById(author.getId()) == null) {
            throw new RuntimeException("No such Author!");
        }
        else {
            try(CSVReader reader = new CSVReader(new FileReader(FileType.FILE_AUTHORS.getPath()))) {
                List<String[]> newStrList = reader.readAll();
                strList = newStrList;
                for (String[] str : newStrList) {
                    if (str[0].equals(author.getId())){
                        i = newStrList.indexOf(str);
                        updatedAuthor[0] = author.getId();
                        updatedAuthor[1] = author.getFirstName();
                        updatedAuthor[2] = author.getLastName();
                        updatedAuthor[3] = getAuthorBookId(author.getBookList());
                        updatedAuthor[4] = Boolean.toString(author.getIsVisible());
                        break;
                    }
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            strList.set(i,updatedAuthor);
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_AUTHORS.getPath()))) {
                csvWriter.writeAll(strList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAuthor(String id){

        Author author = findAuthorById(id);
        if (author.getIsVisible()){
            author.setIsVisible(false);
            updateAuthor(author);
        }
        else{
            System.out.println("Can't find or Delete Author with Id - " + id);
        }
    }

    @Override
    public List<Author> findAuthorByBook(String id){

        List<Author> authors = new ArrayList<>();
        Book book = findBookById(id);
        List<String> autIds = book.getAuthorList();
        for (String autId : autIds) {
            if(autId==null){
                continue;
            }
            Author author = findAuthorById(autId);
            authors.add(author);
        }
        return authors;
    }

    @Override
    public void createBook(Book book, List<String> authorsId){

        Author author = findAuthorById(authorsId.get(0));
        if(author.getId() == null){
            System.out.println("No Author with Id - " + authorsId);
        }
        else {
            book.setId(createBookId(UUID.randomUUID().toString()));
            List<String[]> BookCsv = new ArrayList<>();
            String[] newBook = new String[4];
            newBook[0] = book.getId();
            newBook[1] = book.getTitle();
            newBook[2] = getAuthorBookId(authorsId);
            newBook[3] = Boolean.toString(book.getIsVisible());
            BookCsv.add(newBook);
            for (String str : authorsId) {
                if(str == null){
                    continue;
                }
                Author newAuthor = findAuthorById(str);
                if(newAuthor!=null){
                    List<String> bookList = newAuthor.getBookList();
                    bookList.add(book.getId());
                    updateAuthor(newAuthor);
                }

            }
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_BOOKS.getPath(),true))) {
                csvWriter.writeAll(BookCsv);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Book> reedAllBooks(){

        List<Book> booksList = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(FileType.FILE_BOOKS.getPath()))) {
            List<String[]> str = reader.readAll();
            for (String[] field : str) {
                if (field == null || field[0].equals("ID")){
                    continue;
                }
                Book newBook = new Book();
                newBook.setId(field[0]);
                newBook.setTitle(field[1]);
                newBook.setAuthorList(parseIdToStr(field[2]));
                newBook.setIsVisible(Boolean.parseBoolean(field[3]));
                booksList.add(newBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return booksList;
    }

    @Override
    public Book findBookById(String id) {

        Book book = new Book();

        try (CSVReader reader = new CSVReader(new FileReader(FileType.FILE_BOOKS.getPath()))) {
            List<String[]> str = reader.readAll();
            for (String[] fields : str) {
                if (fields[0].equals(id)) {
                    book.setId(fields[0]);
                    book.setTitle(fields[1]);
                    book.setAuthorList(parseIdToStr(fields[2]));
                    book.setIsVisible(Boolean.parseBoolean(fields[3]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void updateBook(Book book){

        List<String[]> newStrList = new ArrayList<>();
        String[] updatedBook = new String[4];
        int i = 0;
        if (findBookById(book.getId()) == null) {
            throw new RuntimeException("No such Book!");
        }
        else {
            try(CSVReader reader = new CSVReader(new FileReader(FileType.FILE_BOOKS.getPath()))) {
                List<String[]> str = reader.readAll();
                newStrList = str;
                for (String[] fields : str) {
                    if (fields[0].equals(book.getId())){
                        i = str.indexOf(fields);
                        updatedBook[0] = book.getId();
                        updatedBook[1] = book.getTitle();
                        updatedBook[2] = getAuthorBookId(book.getAuthorList());
                        updatedBook[3] = Boolean.toString(book.getIsVisible());
                        break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            newStrList.set(i,updatedBook);
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.FILE_BOOKS.getPath()))) {
                csvWriter.writeAll(newStrList);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void deleteBook(String id){
        Book book = findBookById(id);
        if (book.getIsVisible()){
            book.setIsVisible(false);
            updateBook(book);
        }
        else{
            System.out.println("Can't find or Delete Book with Id - " + id);
        }
    }

    @Override
    public List<Book> findBookByAuthor(String id){
        List<Book> books = new ArrayList<>();
        Author author = findAuthorById(id);
        List<String> bookId = author.getBookList();
        for (String strId : bookId) {
            if(strId == null){
                continue;
            }
            Book book =findBookById(strId);
            books.add(book);
        }
        return books;
    }
}
