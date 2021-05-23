package ua.com.nkrasnovoronka.app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.nkrasnovoronka.lib.Entity;

@Getter
@Setter
@ToString(callSuper = true)
public class Author extends Entity {
    private String name;

    public Author(String name) {
        this.name = name;
    }
}
