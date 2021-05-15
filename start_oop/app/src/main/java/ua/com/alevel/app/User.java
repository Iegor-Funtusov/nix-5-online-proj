package ua.com.alevel.app;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.lib.BaseEntity;

@Getter
@Setter
@ToString
public class User extends BaseEntity {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
