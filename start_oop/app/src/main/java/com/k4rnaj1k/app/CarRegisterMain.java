package com.k4rnaj1k.app;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class CarRegisterMain {
    static Scanner s;
    static CarService carService = new CarService();

    public static void main(String[] args) {
        s = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("""
                    What do you want to do?
                    1 - Add a car;
                    2 - Get a complete list of cars;
                    3 - Update car;
                    4 - Delete car;
                    Any other number to stop.""");
            int chosen = s.nextInt();
            s.nextLine();
            switch (chosen) {
                case 1:
                    addCar();
                    break;
                case 2:
                    out_cars();
                    break;
                case 3:
                    updateCar();
                    break;
                case 4:
                    deleteCar();
                    break;
                default:
                    run = false;
            }
        }
    }

    public static void addCar() {
        Car car = new Car();
        System.out.println("Please input car's model");
        String model = s.nextLine();
        car.setModel(model);
        System.out.println("Please input car's number");
        String number = s.nextLine();
        while (carService.checkCarNum(number)) {
            System.out.println("A car with this number already exists. Please input another car's number.");
            number = s.nextLine();
        }
        car.setNumber(number);
        carService.create(car);
    }

    public static void out_cars() {
        Collection<Car> cars = carService.findAll();
        for (Car car :
                cars) {
            if (car != null) {
                System.out.println(car.toString());
            }
        }
    }

    public static void deleteCar() {
        System.out.println("Please input the number of the car you want to delete.");
        String number = s.nextLine();
        try {
            carService.delete(carService.getCarNum(number));
        } catch (Exception ex) {
            System.out.println("A car with this number doesn't exist");
        }
    }

    public static void updateCar() {
        try {
            System.out.println("Please input the number of the car you would like to update.");
            String number = s.nextLine();
            Car car = new Car();
            car.setId(carService.getCarNum(number));
            System.out.println("Please input updated model information");
            car.setModel(s.nextLine());
            car.setNumber(number);
            carService.updateCar(car);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
