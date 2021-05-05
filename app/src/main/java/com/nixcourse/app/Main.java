package com.nixcourse.app;

import com.nixcourse.lib.VectorCrudService;

public class Main {

    public static void main(String[] args) {
        Building home = new Building(6);
        Building work = new Building(3);
        Building hotel = new Building(5);

        VectorCrudService<Building> buildingStorage = new VectorCrudService<>();

        // Adding buildings via service
        buildingStorage.create(home);
        buildingStorage.create(work);
        buildingStorage.create(hotel);

        // Check-up proper deletion
        System.out.println(buildingStorage.read());
        buildingStorage.delete(work.getId());
        System.out.println(buildingStorage.read());

        // Getting one of building by id
        System.out.println(buildingStorage.read(hotel.getId()));
    }
}
