package ua.com.nkrasnovoronka.app.model;

import lombok.ToString;
import ua.com.nkrasnovoronka.lib.Entity;

//@Getter
//@Setter
@ToString
public class Book extends Entity {
    private String name;
    private String authorId;


    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
