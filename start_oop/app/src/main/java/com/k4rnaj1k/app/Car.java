package com.k4rnaj1k.app;

import com.k4rnaj1k.lib.BaseEntity;

public class Car extends BaseEntity {
    private String model;

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' + ';' +
                "number = '" + number + '\'' +
                '}';
    }
}
