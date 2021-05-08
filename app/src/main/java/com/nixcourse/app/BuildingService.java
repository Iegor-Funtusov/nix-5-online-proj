package com.nixcourse.app;

import com.nixcourse.lib.BaseEntity;
import com.nixcourse.lib.ICrudCollectionService;
import com.nixcourse.lib.CrudFactory;

import java.util.Collection;

public class BuildingService {

    ICrudCollectionService buildingCrud = CrudFactory.getInstance().getCrudCollectionService();

    public void create(Building building) {
        buildingCrud.create(building);
    }

    public void update(Building building) {
        buildingCrud.update(building);
    }

    public void delete(String id) {
        buildingCrud.delete(id);
    }

    public Collection read() {
        return buildingCrud.read();
    }

    public BaseEntity read(String id) {
        return buildingCrud.read(id);
    }
}
