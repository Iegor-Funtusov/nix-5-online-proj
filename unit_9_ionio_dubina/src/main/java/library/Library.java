package library;

import author.Author;
import book.Book;

public class Library {
        private Book book;
        private Author author;

        public Library(Book book, Author author) {
            this.book = book;
            this.author = author;
        }

        public Library(Author author, Book book) {
            this.book = book;
            this.author = author;
        }

        @Override
        public String toString() {
            return "AuthorsWithBooks{" +
                    " book id=" + book.getId() +
                    " book title=" + book.getTitle() +
                    "\n author id=" + author.getId() +
                    ", author name=" + author.getName() +
                    '}';
        }
    }
