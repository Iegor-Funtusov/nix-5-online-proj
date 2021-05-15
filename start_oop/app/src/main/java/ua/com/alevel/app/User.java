package ua.com.alevel.app;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.lib.BaseEntity;

@Getter
@Setter
public class User extends BaseEntity {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
