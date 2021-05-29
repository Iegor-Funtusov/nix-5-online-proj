package com.k4rnaj1k.Dao;


import com.k4rnaj1k.DataClasses.Author;

public class BookDao {
        public void addBook(Author author, AuthorDao authorDao, Author.Book book) throws Exception {
                Author[] authors = authorDao.findAll();
                int i = 0;
                for (; i < authors.length && !authors[i].getName().equals(author.getName()); i++) {
                }
                if(i < authors.length){
                        Author.Book[] temp = new Author.Book[authors[i].getBooks().length+1];
                        System.arraycopy(authors[i].getBooks(), 0, temp, 0, authors.length-1);
                        Author.Book[] books = authors[i].getBooks();
                        temp[temp.length-1] = book;
                        authors[i].setBooks(temp);
                        authorDao.setAuthors(authors);
                }else{
                        throw new Exception("Sorry, there's no such author.");
                }
        }

        public void deleteBook(AuthorDao authorDao, Author.Book book) throws Exception{
                int bookIndex = -1;
                int authorsIndex = 0;
                for (; authorsIndex < authorDao.findAll().length && bookIndex==-1; authorsIndex++) {
                        Author.Book[] books = authorDao.findAll()[authorsIndex].getBooks();
                        for (int j = 0; j < authorDao.findAll()[authorsIndex].getBooks().length; j++) {
                                if(books[j].getName().equals(book.getName())){
                                        bookIndex = j;
                                }
                        }
                }
                if(bookIndex!=-1){
                        Author.Book[] temp = new Author.Book[authorDao.findAll().length-1];
                        boolean flag = false;
                        Author[] authors = authorDao.findAll();
                        Author author = authors[authorsIndex-1];
                        Author.Book[] books = author.getBooks();
                        books[bookIndex] = null;
                        for (int j = 0; j < author.getBooks().length; j++) {
                                if(books[j] == null){
                                        flag = true;
                                        continue;
                                }if(flag){
                                        temp[j-1] = books[j];
                                        continue;
                                }
                                temp[j] = books[j];
                        }
                        authors[authorsIndex-1].setBooks(temp);
                        authorDao.setAuthors(authors);
                }else{
                        throw new Exception("There's no such book.");
                }
        }
}
