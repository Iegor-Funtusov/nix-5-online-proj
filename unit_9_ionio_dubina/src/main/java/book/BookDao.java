package book;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Arrays;
import java.util.UUID;

public class BookDao {

    private Book[] books = new Book[10];
    private int iter = 0;

    public void create(Book book) {
        book.setId(generateId(book.getId()));
        if (iter + 1 > books.length) {
            books = Arrays.copyOf(books, books.length + 1);
        }
        books[iter] = book;
        iter++;
    }

    public void update(Book book) {
        Book current = findById(book.getId());
        if (current == null) {
            return;
        }
        try {
            BeanUtils.copyProperties(current, book);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Book[] readAll() {
        return books;
    }

    public void delete(String id) {
        Book current = findById(id);
        if (current == null) {
            return;
        }
        Book[] newBooks = new Book[books.length - 1];
        for (int i = 0, k = 0; i < iter; i++) {
            if (id.equals(books[i].getId())) {
                continue;
            }
            newBooks[k++] = books[i];
        }
        books = Arrays.copyOf(newBooks, books.length - 1);
        iter--;
    }

    public Book findById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    private String generateId(String id) {
        return UUID.randomUUID().toString();
    }
}