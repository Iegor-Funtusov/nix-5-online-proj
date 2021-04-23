package ua.com;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

public class User {

    private int id;
    private String name;
    private String surname;

    public User(){
    }

    public User(String name, String surname) {
        this.id = new RandomDataGenerator().nextInt(1, 100);;
        this.name = StringUtils.upperCase(name);
        this.surname = StringUtils.upperCase(surname);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void print(){
        System.out.println("First Name: " + this.name + "\nSurname: " + this.surname);
    }

}