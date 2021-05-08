package com.nixcourse.app;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        BuildingService buildingService = new BuildingService();

        Building home = new Building(6);
        Building work = new Building(3);

        buildingService.create(home);
        buildingService.create(work);

        Collection<Building> buildings = buildingService.read();
        buildings.forEach(System.out::println);

        for (var building : buildings) {
            if (building.getFloors() == 6) {
                buildingService.delete(building.getId());
            }
        }

        buildings = buildingService.read();
        buildings.forEach(System.out::println);
    }
}
