package com.k4rnaj1k.app;

import com.k4rnaj1k.lib.CrudProcess;
import com.k4rnaj1k.lib.CrudProcessFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class CarService {

    private CrudProcess<Car> carCrudProcess = CrudProcessFactory.getInstance().getCrudProcess();

    public void create(Car car) {
        carCrudProcess.create(car);
    }

    public Collection<Car> findAll() {
        return carCrudProcess.read();
    }

    public String getCarNum(String num) throws Exception {
        Collection<Car> cars = carCrudProcess.read();
        for (Car car :
                cars) {
            if (car.getNumber().equals(num)) {
                return car.getId();
            }
        }
        throw new Exception("A car with this number doesn't exist");
    }

    public boolean checkCarNum(String num) {
        boolean exists = false;
        Collection<Car> cars = carCrudProcess.read();
        if(cars.size()==0){
            return false;
        }
        for (Car car :
                cars) {
            if (car.getNumber().equals(num)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public void updateCar(Car car) {
        carCrudProcess.update(car);
    }

    public void delete(String id) {
        carCrudProcess.delete(id);
    }
}