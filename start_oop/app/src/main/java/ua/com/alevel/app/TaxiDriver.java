package ua.com.alevel.app;

import ua.com.alevel.lib.BaseEntity;

public class TaxiDriver extends BaseEntity {

    private int age;
    private String name;
    private String car;
    private String carColor;

    public TaxiDriver(int age, String name, String car, String carColor) {
        this.age = age;
        this.name = name;
        this.car = car;
        this.carColor = carColor;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TaxiDriver{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", car='" + car + '\'' +
                ", car_color='" + carColor + '\'' +
                '}';
    }
}
