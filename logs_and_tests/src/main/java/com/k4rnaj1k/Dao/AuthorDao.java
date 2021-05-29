package com.k4rnaj1k.Dao;

import com.k4rnaj1k.DataClasses.Author;

import java.util.Arrays;

public class AuthorDao {
    private Author[] authors = new Author[0];

    public void createAuthor(Author author) throws Exception {
        if (Arrays.stream(authors).anyMatch(author1 -> author1.getName().equals(author.getName()))) {
            throw new Exception("This author already exists");
        } else {
            addAuthor(author);
        }
    }

    private void addAuthor(Author author) {
        Author[] temp = new Author[authors.length + 1];
        copyArr(authors, temp);
        temp[temp.length - 1] = author;
        authors = temp;
    }

    public void deleteAuthor(Author author) throws Exception {
        int i = 0;
        for (; i < authors.length && !authors[i].getName().equals(author.getName()); i++) {
        }
        if (i < authors.length) {
            authors[i] = null;
            Author[] temp = new Author[authors.length - 1];
            boolean flag = false;
            for (int j = 0; j < authors.length; j++) {
                if(authors[j] == null){
                    flag = true;
                    continue;
                }if(flag){
                    temp[j-1] = authors[j];
                    continue;
                }
                temp[j] = authors[j];
            }
            authors = temp;
        } else {
            throw new Exception("Error, author not found");
        }
    }

    public Author[] findAll() {
        return authors;
    }

    public void setAuthors(Author[] authors){
        this.authors = authors;
    }

    private void copyArr(Object[] from, Object[] to) {
        System.arraycopy(from, 0, to, 0, from.length);
    }
}
