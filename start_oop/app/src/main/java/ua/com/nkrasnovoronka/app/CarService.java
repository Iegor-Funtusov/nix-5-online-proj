package ua.com.nkrasnovoronka.app;

import ua.com.nkrasnovoronka.lib.Crud;
import ua.com.nkrasnovoronka.lib.CrudProcessorFactory;

import java.util.Collection;

public class CarService {
    private Crud<Car> carCrud = CrudProcessorFactory.getInstance().getCrudProcessorImpl();

    public void create(Car car) {
        carCrud.create(car);
    }

    public void update(Car car) {
        carCrud.update(car);
    }

    public void delete(String id) {
        carCrud.delete(id);
    }

    public Collection<Car> readAll() {
        return carCrud.readAll();
    }

    public Car read(String id) {
        return carCrud.read(id);
    }

}
