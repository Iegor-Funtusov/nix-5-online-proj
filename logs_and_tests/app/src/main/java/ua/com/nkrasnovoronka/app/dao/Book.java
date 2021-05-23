package ua.com.nkrasnovoronka.app.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.nkrasnovoronka.lib.Entity;

@Getter
@Setter
@ToString
public class Book extends Entity {
    private String name;
    private String authorId;


    public Book(String name) {
        this.name = name;
    }
}
