package ua.com.alevel.util;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Relation;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.RelationService;

import java.io.BufferedReader;
import java.io.IOException;

public class AuthorUtil {

    public static Author createAuthor(BufferedReader reader)
            throws IOException, NumberFormatException {
        return getAuthor(reader);
    }

    public static Author getAuthor(BufferedReader reader)
            throws IOException, NumberFormatException {
        System.out.print("Please enter Author name: ");
        String name = reader.readLine();
        System.out.print("Please enter Author age: ");
        int age = Integer.parseInt(reader.readLine());//
        return new Author(name, age);
    }

    public static Author readAuthor(AuthorService authorService, BufferedReader reader)
            throws IOException, RuntimeException {
        System.out.println("Please enter the author id :");
        System.out.print("--> ");
        return authorService.read(reader.readLine());
    }

    public static void updateAuthor(Author readAuthor, AuthorService authorService, BufferedReader reader)
            throws IOException, NumberFormatException {
        System.out.println("Enter new parameters for the author.");
        Author authorForUpdate = AuthorUtil.createAuthor(reader);
        authorForUpdate.setId(readAuthor.getId());
        authorService.update(authorForUpdate);
    }

    public static void printAllAuthors(AuthorService authorService){
        try{
            Object[] authors = authorService.read();
            for (Object author : authors) {
                System.out.println(author);
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAuthor(Author author, AuthorService authorService, RelationService relationService)
            throws RuntimeException{
        Object[] relations = relationService.read();
        for (Object relation : relations) {
            if (((Relation) relation).getAuthor().getId().equals(author.getId())) {
                relationService.delete((Relation) relation);
            }
        }
        authorService.delete(author);
    }
}
