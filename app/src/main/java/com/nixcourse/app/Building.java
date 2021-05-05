package com.nixcourse.app;

import com.nixcourse.lib.BaseEntity;

public class Building extends BaseEntity {
    private int floors;

    Building(int floors) {
        this.floors = floors;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "Building{" +
                "floors=" + floors +
                '}';
    }
}
