package com.nixcourse.app;

import com.nixcourse.lib.CrudCollectionService;
import com.nixcourse.lib.CrudFactory;

import java.util.Collection;

public class BuildingService {

    CrudCollectionService<Building> buildingCrud = CrudFactory.getInstance().getCrudCollectionService();
//    CrudProcess<Building> buildingCrud = new DefaultCrudProcess<>(); // evil

    public void create(Building building) {
        buildingCrud.create(building);
    }

    public void update(Building building) {
        buildingCrud.update(building);
    }

    public void delete(String id) {
        buildingCrud.delete(id);
    }

    public Collection<Building> read() {
        return buildingCrud.read();
    }

    public Building read(String id) {
        return buildingCrud.read(id);
    }
}
