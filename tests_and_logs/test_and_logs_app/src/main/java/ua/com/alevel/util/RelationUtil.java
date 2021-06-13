package ua.com.alevel.util;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.Relation;
import ua.com.alevel.service.RelationService;

import java.io.BufferedReader;
import java.io.IOException;


public class RelationUtil {

    public static void createRelation(Book book, Author author, RelationService relationService) {
        Relation relation = new Relation(book, author);
        relationService.create(relation);
    }

    public static void printAllRelations(RelationService relationService) {
        try {
            Object[]  relations = relationService.read();
            for (Object relation : relations) {
                System.out.println(relation);
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean isRelationExist(Book book, Author author, Object[] relations) {
        for (Object relation : relations) {
            if (((Relation) relation).getBook().getId().equals(book.getId())
                    || ((Relation) relation).getAuthor().getId().equals(author.getId()))
                return true;
        }
        return false;
    }

    public static void printAllAuthorsForBook(Book book, Object[] relations) {
        System.out.println(book);
        for (Object relation : relations) {
            if (((Relation) relation).getBook().getId().equals(book.getId())) {
                System.out.println("\t" + ((Relation) relation).getAuthor());
            }
        }
    }

    public static void printAllBooksForAuthor(Author author, Object[] relations) {
        System.out.println(author);
        for (Object relation : relations) {
            if (((Relation) relation).getAuthor().getId().equals(author.getId())) {
                System.out.println("\t" + ((Relation) relation).getAuthor());
            }
        }
    }

    public static Relation readRelation(RelationService relationService, BufferedReader reader) throws IOException, RuntimeException {
        System.out.println("Please enter the relation id :");
        System.out.print("--> ");
        return relationService.read(reader.readLine());
    }

    public static void deleteRelation(Relation relation, RelationService relationService) {
        try {
            relationService.delete(relation);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteRelationByBookAndAuthor(Book book, Author author, RelationService relationService)
                throws RuntimeException {
        Object[] relations = relationService.read();
        for (Object relation : relations) {
            if (((Relation) relation).getBook().getId().equals(book.getId()) && ((Relation) relation).getAuthor().getId().equals(author.getId())) {
                RelationUtil.deleteRelation((Relation) relation, relationService);
            }
        }
    }
}