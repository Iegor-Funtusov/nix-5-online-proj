package com.k4rnaj1k.DataClasses;

public class Author {
    private Book[] books = new Book[0];

    public Author(String name) throws Exception {
            setName(name);
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Author.Book[] getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) throws Exception {
        if (!name.isEmpty())
            this.name = name;
        else {
            throw new Exception("Author's name can't be blank");
        }
    }

    private String name;

    public static class Book {
        private String name;

        public String getName() {
            return name;
        }

        public Book(String name) throws Exception {
            setName(name);
        }

        public void setName(String name) throws Exception {
            if (!name.isEmpty())
                this.name = name;
            else {
                throw new Exception("Book's name can't be blank");
            }
        }
    }
}
