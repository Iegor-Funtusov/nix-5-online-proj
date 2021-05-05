package com.nixcourse.app;

import com.nixcourse.lib.VectorCrudService;

public class Main {

    public static void main(String[] args) {
        BuildingService buildingService = new BuildingService();

        Building home = new Building(6);
        Building work = new Building(3);

        buildingService.create(home);
        buildingService.create(work);

        // Check-up proper deletion
        System.out.println(buildingService.read());
        buildingService.delete(home.getId());
        System.out.println(buildingService.read());
    }
}
