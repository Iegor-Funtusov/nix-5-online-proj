package ua.com.alevel.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class Controller {

    public static BufferedReader reader;
    public static TaxiDriverService driverService = new TaxiDriverService();

    public static void menu () {
        reader = new BufferedReader(new InputStreamReader(System.in));
        driverService = new TaxiDriverService();
        String input;

        try {
            System.out.println("Choose the action:\n" +
                    "1 -> Create driver\n" +
                    "2 -> Update driver\n" +
                    "3 -> Delete driver\n" +
                    "4 -> Show all drivers\n" +
                    "5 -> Show one driver\n" +
                    "0 -> Stop the program");
            while ((input = reader.readLine())!= null) {
                switch (input) {
                    case "1" : {
                        create();
                    } break;
                    case "2" : {
                        update();
                    } break;
                    case "3" : {
                        delete();
                    } break;
                    case "4": {
                        showAll();
                    } break;
                    case "5": {
                        show();
                    } break;
                    case "0": {
                        System.exit(0);
                    }
                    default: {
                        System.out.println("Wrong input");
                    }break;

                }
                System.out.println("Choose the action:\n" +
                        "1 -> Create driver\n" +
                        "2 -> Update driver\n" +
                        "3 -> Delete driver\n" +
                        "4 -> Show all drivers\n" +
                        "5 -> Show one driver\n" +
                        "0 -> Stop the program");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void create() throws IOException {
        System.out.print("Input driver`s name: ");
        String name = reader.readLine();
        System.out.print("Input driver`s age: ");
        int age = Integer.parseInt(reader.readLine());
        System.out.print("Input driver`s car: ");
        String car = reader.readLine();
        System.out.print("Input driver`s carColor: ");
        String carColor = reader.readLine();
        driverService.create(new TaxiDriver(age,name,car,carColor));
    }

    public static void update() throws IOException {
        System.out.println("Input driver`s name, you want to update: ");
        Collection<TaxiDriver> list = driverService.read();
        String value = reader.readLine();
        for (TaxiDriver driver : list) {
            if (driver.getName().equals(value)) {
                System.out.println("Choose the field to update:\n" +
                        "1 -> age\n" +
                        "2 -> name\n" +
                        "3 -> car\n" +
                        "4 -> car_color\n");
                switch (reader.readLine()) {
                    case "1":
                        System.out.print("Input new driver`s age: ");
                        driver.setAge(Integer.parseInt(reader.readLine()));
                        break;
                    case "2":
                        System.out.print("Input new driver`s name: ");
                        driver.setName(reader.readLine());
                        break;
                    case "3":
                        System.out.print("Input new driver`s car: ");
                        driver.setCar(reader.readLine());
                        break;
                    case "4":
                        System.out.print("Input new driver`s car color: ");
                        driver.setCarColor(reader.readLine());
                        break;
                }
                driverService.update(driver);
                break;
            }
        }
    }

    public static void delete() throws IOException {
        System.out.println("Input driver`s name, you want to delete: ");
        Collection<TaxiDriver> list = driverService.read();
        String value = reader.readLine();
        for (TaxiDriver driver : list) {
            if (driver.getName().equals(value)) {
                driverService.delete(driver.getId());
                break;
            }
        }
    }

    public static void showAll() {
        Collection<TaxiDriver> list = driverService.read();
        list.forEach(System.out::println);
    }

    public static void show() throws IOException {
        System.out.println("Input driver`s name, you want to see: ");
        Collection<TaxiDriver> list = driverService.read();
        String value = reader.readLine();
        for (TaxiDriver driver : list) {
            if (driver.getName().equals(value)) {
                System.out.println(driverService.read(driver.getId()));
                break;
            }
        }
    }
}
