package Controller;

import manufacturers.Manufacturer;
import manufacturers.ManufacturerService;
import products.Product;
import products.ProductControl;
import products.ProductService;

import java.util.Collection;
import java.util.Scanner;

public class Control {

    public static ManufacturerService manufacturerService = new ManufacturerService();

    public static void controlConsole () {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("What would you like to do?\n" +
                    "1 >> add new manufacturer\n" +
                    "2 >> update a manufacturer\n" +
                    "3 >> delete a manufacturer\n" +
                    "4 >> show all manufacturers\n" +
                    "5 >> work with products of existing manufacturer\n" +
                    "6 >> show all products of manufacturer\n" +
                    "7 >> exit from the program\n");
            String input = sc.nextLine();
            input = input.toLowerCase();
            switch (input) {
                case "1" : {
                    creating();
                } break;
                case "2" : {
                    update();
                } break;
                case "3" : {
                    delete();
                } break;
                case "4": {
                    readingAll();
                } break;
                case "5": {
                    goToProducts();
                } break;
                case "6":{
                    read();
                }break;
                case "7":
                {
                    System.exit(0);
                }
                default: {
                    System.out.println("Incorrect input. Input again");
                }break;
            }
            System.out.println("Next action (CRUD)");
        }
    }

    private static void creating(){
        Manufacturer manufacturer = new Manufacturer();
        String name;
        System.out.print("Input name of manufacturer: ");
        name = name();
        manufacturer.setName(name);
        manufacturerService.create(manufacturer);
    }

    private static void update(){
        String name, newName, updateInput;
        int counter = 0;
        boolean flag;
        Scanner sc = new Scanner(System.in);
        Collection<Manufacturer> list = manufacturerService.find();
        System.out.println("Input name of manufacturer where you want to change data");
        name = name();
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals(name)) {
                System.out.println("Input new name of manufacturer");
                newName = name();
                manufacturer.setName(newName);
                manufacturerService.update(manufacturer);
                counter++;
                break;
            }
        }
        if (counter == 0)
            System.out.println("Such manufacturer doesn't exist");
    }

    private static void delete() {
        int counter = 0;
        String name;
        Collection<Manufacturer> list = manufacturerService.find();
        System.out.println("Input name of manufacturer that you want to delete");
        name = name();
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals(name)) {
                manufacturerService.delete(manufacturer.getId());
                counter++;
                break;
            }
        }
        if (counter == 0)
            System.out.println("Such manufacturer doesn't exist");
    }

    private static void goToProducts(){
        String name;
        Collection<Manufacturer> list = manufacturerService.find();
        int counter = 0;
        System.out.println("Input the name of manufacturer with which you want to work");
        name = name();
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals(name)) {
                ProductControl.productControl(manufacturer);
                counter++;
                break;
            }
        }
        if (counter == 0)
            System.out.println("Such manufacturer doesn't exist");
    }

    private static void read (){
        ProductService productService = new ProductService();
        String name;
        Collection<Manufacturer> list = manufacturerService.find();
        Collection<Product> list1 = productService.find();
        int counter = 0;
        System.out.println("Input name of manufacturer that you want to find");
        name = name();
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals(name)) {
                for(Product product : list1){
                    if(product.getManufId().equals(manufacturer.getId())){
                        System.out.println(productService.read(product.getId()));
                    }
                }
                System.out.println("Such manufacturer doesn't have products");
                counter++;
                break;
            }
        }
        if (counter == 0)
            System.out.println("Such manufacturer doesn't exist");
    }

    private static void readingAll(){
        Collection<Manufacturer> list = manufacturerService.find();
        list.forEach(System.out::println);
    }

    private static String name(){
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        while (name.length() > 50){
            System.out.println("Name of manufacturer is too long. Input again");
            name = sc.nextLine();
        }
        while(name.isEmpty())
        {
            System.out.println("Your input is empty. Input manufacturer");
            name = sc.nextLine();
        }
        return name;
    }
}
