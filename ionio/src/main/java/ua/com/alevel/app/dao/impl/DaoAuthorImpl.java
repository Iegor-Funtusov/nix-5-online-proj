package ua.com.alevel.app.dao.impl;

import org.apache.commons.beanutils.BeanUtils;
import ua.com.alevel.app.dao.DaoAuthor;
import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DaoAuthorImpl implements DaoAuthor {

    private List<Author> authorList = new ArrayList<>();

    @Override
    public void create(Author author) {
        author.setId(generateId(UUID.randomUUID().toString()));
        authorList.add(author);
    }

    private String generateId(String id) {
        if (authorList.stream().anyMatch(el -> el.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }

    @Override
    public void update(Author author) {
        Author current = findById(author.getId());
        try {
            BeanUtils.copyProperties(current, author);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<Author> read() {
        return authorList
                .stream()
                .filter(Author::isVisible)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> readList(Author author) {
        return author.getBooks();
    }

    @Override
    public void delete(Author author) {
        author.setVisible(false);
    }

    private Author findById(String id) {
        Author current = authorList
                .stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);
        if (current == null) {
            throw new RuntimeException("This id does not exist");
        }
        return current;
    }
}
