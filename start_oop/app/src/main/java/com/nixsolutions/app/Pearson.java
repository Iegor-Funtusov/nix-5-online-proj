package com.nixsolutions.app;

import com.nixsolutions.BaseEntity;

public class Pearson extends BaseEntity {

    String firstName;
    int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Pearson{" +
                "id='" + super.getId() + '\'' +
                "name='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }
}
