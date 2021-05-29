package dao;

import domain.Author;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class AuthorDAO {
    private static final int CAPACITY = 5;
    private Author[] authors = new Author[CAPACITY];
    private int size = 0;

    public void createAuthor(Author author) {
        if (size == authors.length) {
            increaseCapacity();
        }
        author.setAuthorId(generateId(UUID.randomUUID().toString()));
        authors[size++] = author;
    }

   public void updateAuthor(Author author) {
       if (StringUtils.isNotBlank(author.getAuthorId())) {
           Author current = getAuthorById(author.getAuthorId());
           if (current == null) {
               throw new RuntimeException("Автор не существует!");
           }
           try {
               BeanUtils.copyProperties(current, author);
           } catch (IllegalAccessException illegalAccessException) {
               illegalAccessException.printStackTrace();
           } catch (InvocationTargetException invocationTargetException) {
               invocationTargetException.printStackTrace();
           }
       }
       else {
           throw new RuntimeException("Автор не существует!");
       }
    }

    public void deleteAuthor(String authorId) {
        if (StringUtils.isNotBlank(authorId)) {
            Author current = getAuthorById(authorId);
            if (current == null) {
                throw new RuntimeException("Автор не существует!");
            }
           for (int i = 0; i < authors.length; i++) {
                if (((Author) authors[i]).getAuthorId().equals(authorId)) {
                    authors[i] = null;
                    break;
                }
            }
            Author[] temp = new Author[authors.length];
            for (int i = 0, j = 0; i < authors.length; i++) {
                if (authors[i] != null) {
                    temp[j] = authors[i];
                    j++;
                }
            }
            authors = Arrays.copyOf(temp, temp.length);
        }
        else {
            throw new RuntimeException("Автор не существует!");
        }
    }

    public Collection<Author> findAuthors() {
        return Arrays.stream(authors).map(author -> ((Author) author)).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public Author findAuthors(String authorId) {
        if (StringUtils.isNotBlank(authorId)) {
            Author current = getAuthorById(authorId);
            if (current == null) {
                throw new RuntimeException("Автор не существует!");
            }
            return current;
        }
        else {
            throw new RuntimeException("Автор не существует!");
        }
    }

    public boolean ifExistLastName(String lastName) {
        return Arrays.stream(authors).filter(Objects::nonNull).anyMatch(author -> author.getLastName().equals(lastName));
    }

    public Author findByLastName(String lastName) {
        return Arrays.stream(authors).filter(author -> author.getLastName().equals(lastName)).findAny().orElse(null);
    }
    private Author getAuthorById(String authorId) {
        return (Author) Arrays.stream(authors)
            .filter(author -> ((Author) author).getAuthorId().equals(authorId))
            .findAny()
            .orElse(null);
    }

    private String generateId(String authorId) {
        if (Arrays.stream(authors)
            .filter(Objects::nonNull)
            .anyMatch(author -> Objects.equals(((Author) author).getAuthorId(), authorId))) {
            return generateId(UUID.randomUUID().toString());
        }
        return authorId;
    }

    private void increaseCapacity() {
        int IncreasedCapacity = authors.length * 2;
        authors = Arrays.copyOf(authors, IncreasedCapacity);
    }
}
