package ua.com.alevel.app;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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

    @Override
    public String toString() {
        return "User " + super.toString() +
                "name= " + name +
                ", age= " + age;
    }
}
