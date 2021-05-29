package dao;

import domain.Author;
import domain.Book;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class BookDAO {

    private int size = 0;
    Book[] books;

    public void createBook(Author author, Book book) {
        books = author.getBooks();
        if (size == books.length) {
            increaseCapacity();
        }
        book.setBookId(generateId(author, UUID.randomUUID().toString()));
        books[size++] = book;
        author.setBooks(books);
    }

    public void updateBook(Author author, Book book) {
        if (StringUtils.isNotBlank(book.getBookId())) {
            books = author.getBooks();
            Book current = getBookById(author, book.getBookId());
            if (current == null) {
                throw new RuntimeException("Книга не существует!");
            }
            try {
                BeanUtils.copyProperties(current, book);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            } catch (InvocationTargetException invocationTargetException) {
                invocationTargetException.printStackTrace();
            }
        }
        else {
            throw new RuntimeException("Книга не существует!");
        }
    }

    public void deleteBook(Author author, String bookId) {
        if (StringUtils.isNotBlank(bookId)) {
            books = author.getBooks();
            Book current = getBookById(author, bookId);
            if (current == null) {
                throw new RuntimeException("Книга не существует!");
            }
            for (int i = 0; i < books.length; i++) {
                if (books[i] != null) {
                    if (((Book) books[i]).getBookId().equals((bookId))) {
                        books[i] = null;
                        break;
                    }
                }
            }
            Book[] temp = new Book[books.length];
            for (int i = 0, j = 0; i < books.length; i++) {
                if (books[i] != null) {
                    temp[j] = books[i];
                    j++;
                }
            }
            books = Arrays.copyOf(temp, temp.length);
            author.setBooks(books);
        }
        else {
            throw new RuntimeException("Книга не существует!");
        }
    }

    public Collection<Book> findBooks(Author author) {
        books = author.getBooks();
        return Arrays.stream(books).map(book -> ((Book) book)).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public Book findBooks(Author author, String bookId) {
        if (StringUtils.isNotBlank(bookId)) {
            books = author.getBooks();
            Book current = getBookById(author, bookId);
            if (current == null) {
                throw new RuntimeException("Книга не существует!");
            }
            return current;
        }
        else {
            throw new RuntimeException("Книга не существует!");
        }
    }

    public boolean findByTitle(Author author, String title) {
        books = author.getBooks();
        return Arrays.stream(books).filter(Objects::nonNull).anyMatch(book -> book.getTitle().equals(title));
    }

    private Book getBookById(Author author, String bookId) {
       books = author.getBooks();
        return (Book) Arrays.stream(books)
            .filter(Objects::nonNull).filter(book -> ((Book) book).getBookId().equals(bookId))
            .findAny()
            .orElse(null);
    }

    private String generateId(Author author, String bookId) {
        books = author.getBooks();
        if (Arrays.stream(books)
            .filter(Objects::nonNull)
            .anyMatch(book -> Objects.equals(((Book) book).getBookId(), bookId))) {
            return generateId(author, UUID.randomUUID().toString());
        }
        return bookId;
    }

    private void increaseCapacity() {
        int IncreasedCapacity = books.length * 2;
        books = Arrays.copyOf(books, IncreasedCapacity);
    }
}
