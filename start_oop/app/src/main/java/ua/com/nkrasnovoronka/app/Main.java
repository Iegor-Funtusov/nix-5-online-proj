package ua.com.nkrasnovoronka.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static CarService carService = new CarService();
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        String action = "";
        try {
            while (!action.equals("q")) {
                System.out.println("Pleas enter action to do\n1 create Car\n2 update car\n3 delete car\n4 print all cars\n5 read car\ntype q to exit");
                action = bufferedReader.readLine();
                switch (action) {
                    case ("1") -> createCar();
                    case ("2") -> updateCar();
                    case ("3") -> deletingCar();
                    case ("4") -> printAllCars();
                    case ("5") -> readCar();
                    case ("q") -> System.out.println("Closing program");
                    default -> System.err.println("Wrong action!!!!");
                }
            }

        } catch (IOException e) {
            System.err.println("Unexpected error");
        }
    }

    private static void createCar() {
        System.out.println("Creating car...");
        Car car = new Car();
        try {
            System.out.println("Enter car manufacturer");
            car.setManufacturer(bufferedReader.readLine());
            System.out.println("Enter car model");
            car.setModel(bufferedReader.readLine());
            System.out.println("Enter car number");
            car.setSerialNumber(bufferedReader.readLine());
        } catch (IOException e) {
            System.err.println("Something went wrong. Cannot create car entity");
        }
        carService.create(car);
    }

    private static void printAllCars() {
        System.out.println("All cars = " + carService.readAll());
    }

    private static void updateCar() {
        System.out.println("Updating car. Pleas enter car id");
        try {
            String carID = bufferedReader.readLine();
            Car read = carService.read(carID);
            System.out.println("Enter 1 to update manufacturer\n2 mode\n3 serial number");
            String enter = bufferedReader.readLine();
            switch (enter) {
                case ("1"): {
                    System.out.println("Enter new car manufacturer");
                    read.setManufacturer(bufferedReader.readLine());
                    break;
                }
                case ("2"): {
                    System.out.println("Enter new car model");
                    read.setModel(bufferedReader.readLine());
                    break;
                }
                case ("3"): {
                    System.out.println("Enter new car serial number");
                    read.setSerialNumber(bufferedReader.readLine());
                    break;
                }
                default:
                    System.out.println("Wrong enter!!!Nothing will be changed");
            }


        } catch (IOException e) {
            System.err.println("Something went wrong. Cannot update car entity");

        }
    }

    private static void readCar() {
        System.out.println("Pleas enter car id");
        try {
            String carID = bufferedReader.readLine();
            Car read = carService.read(carID);
            System.out.println(read);
        } catch (IOException e) {
            System.err.println("Something went wrong. Cannot read car entity");

        }
    }

    private static void deletingCar() {
        System.out.println("Pleas enter car id to remove from db");
        try {
            String carId = bufferedReader.readLine();
            carService.delete(carId);
            System.out.println("car with id " + carId + " deleted");
        } catch (IOException e) {
            System.err.println("Something went wrong. Cannot delete car");
        }
    }
}
