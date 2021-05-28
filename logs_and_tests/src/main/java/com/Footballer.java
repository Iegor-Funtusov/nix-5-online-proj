package com;

public class Footballer {
    private String foot_id;
    private String foot_name;
    private int foot_age;

    public Footballer(String foot_name, int foot_age) {
        this.foot_name = foot_name;
        this.foot_age = foot_age;
    }

    public String getFoot_id() {
        return foot_id;
    }

    public void setFoot_id(String foot_id) {
        this.foot_id = foot_id;
    }

    public String getFoot_name() {
        return foot_name;
    }

    public void setFoot_name(String foot_name) {
        this.foot_name = foot_name;
    }

    public Integer getFoot_age() {
        return foot_age;
    }

    public void setFoot_age(int foot_age) {
        this.foot_age = foot_age;
    }

    @Override
    public String toString() {
        return "com.Footballer{" +
                "foot_id='" + foot_id + '\'' +
                ", foot_name='" + foot_name + '\'' +
                ", foot_age='" + foot_age + '\'' +
                '}';
    }
}
