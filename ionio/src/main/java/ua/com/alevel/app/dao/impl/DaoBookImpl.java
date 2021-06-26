package ua.com.alevel.app.dao.impl;

import org.apache.commons.beanutils.BeanUtils;
import ua.com.alevel.app.dao.DaoBook;
import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DaoBookImpl implements DaoBook {

    List<Book> bookList = new ArrayList<>();

    @Override
    public void create(Book book) {
        book.setId(generateId(UUID.randomUUID().toString()));
        bookList.add(book);
    }

    @Override
    public void update(String id) {
        Book current = findById(id);
        try {
            BeanUtils.copyProperties(current, findById(id));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<Book> read() {
        return bookList
                .stream()
                .filter(Book::isVisible)
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> readList(Book book) {
        return book.getAuthors();
    }

    @Override
    public void delete(Book book) {
        book.setVisible(false);
    }

    private String generateId(String id) {
        if (bookList.stream().anyMatch(el -> el.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }

    private Book findById(String id) {
        Book current = bookList.stream().filter(e -> e.getId().equals(id)).findAny().orElse(null);
        if (current == null) {
            throw new RuntimeException("This id does not exist");
        }
        return current;
    }
}
