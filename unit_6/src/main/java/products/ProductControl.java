package products;

import manufacturers.Manufacturer;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ProductControl {
    public static ProductService productService = new ProductService();
    public static void productControl(Manufacturer manufacturer){
        Scanner sc = new Scanner(System.in);
        while (true) {
        System.out.println("What would you like to do?\n" +
                "1 >> create a product\n" +
                "2 >> update a product\n" +
                "3 >> delete a product\n" +
                "4 >> show all products\n" +
                "5 >> show only 1 product\n" +
                "6 >> return to manufacturers\n" +
                "7 >> exit from the program\n");
        String input = sc.nextLine();
        input = input.toLowerCase();
        switch (input) {
            case "1" : {
                creating(manufacturer);
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
                read();
            } break;
            case "6":{
                return;
            }
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


    private static void creating(Manufacturer manufacturer){
        Product product = new Product();
        String name;
        double price;
        System.out.print("Input a product: ");
        name = name();
        System.out.print("Input the price of product: ");
        price = price();
        product.setName(name);
        product.setPrice(price);
        product.setManufId(manufacturer.getId());
        product.setManufName(manufacturer.getName());
        productService.create(product);
    }

    private static void update(){
        String name, newName, updateInput;
        double newPrice;
        int counter = 0;
        boolean flag;
        Scanner sc = new Scanner(System.in);
        Collection<Product> list = productService.find();
        System.out.println("Input name of product where you want to change data");
        name = name();
        System.out.println("Input what you want to change (name of product or price)\n" +
                "1 >> name of product\n" +
                "2 >> price\n");
        updateInput = sc.nextLine();
        updateInput = updateInput.toLowerCase();
        flag = true;
        while (flag) {
            switch (updateInput) {
                case "1": {
                    for (Product product : list) {
                        if (product.getName().equals(name)) {
                            System.out.println("Input new name of product");
                            newName = name();
                            product.setName(newName);
                            productService.update(product);
                            counter++;
                        }
                    }
                    flag = false;
                }
                break;
                case "2": {
                    for (Product product : list) {
                        if (product.getName().equals(name)) {
                            System.out.println("Input new price");
                            newPrice = price();
                            product.setPrice(newPrice);
                            productService.update(product);
                            counter++;
                        }
                    }
                    flag = false;
                }
                break;
                default: {
                    System.out.println("Incorrect input. Input again");
                    updateInput = sc.nextLine();
                    updateInput = updateInput.toLowerCase();
                    counter = 0;
                }
            }
        }
        if (counter == 0)
            System.out.println("Such product doesn't exist");
    }

    private static void delete() {
        int counter = 0;
        String name;
        Collection<Product> list = productService.find();
        System.out.println("Input name of product that you want to delete");
        name = name();
        for (Product product : list) {
            if (product.getName().equals(name)) {
                productService.delete(product.getId());
                counter++;
            }
        }
        if (counter == 0)
            System.out.println("Such product doesn't exist");
    }

    private static void read (){
        String name;
        Collection<Product> list = productService.find();
        int counter = 0;
        System.out.println("Input name of product that you want to find");
        name = name();
        for (Product product : list) {
            if (product.getName().equals(name)) {
                System.out.println(productService.read(product.getId()));
                counter++;
            }
        }
        if (counter == 0)
            System.out.println("Such product doesn't exist");
    }

    private static void readingAll(){
        Collection<Product> list = productService.find();
        list.forEach(System.out::println);
    }

    private static String name(){
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        while (name.length() > 50){
            System.out.println("Name of product is too long. Input again");
            name = sc.nextLine();
        }
        while(name.isEmpty())
        {
            System.out.println("Your input is empty. Input product");
            name = sc.nextLine();
        }
        return name;
    }

    public static double price(){
        Scanner sc = new Scanner(System.in);
        double price = checkPrice();
        while (price == -1) {
            price = checkPrice();
        }
        return price;
    }

    public static double checkPrice(){
        Scanner sc = new Scanner(System.in);
        double price;
        try {
            price = sc.nextDouble();
            while (price <= 0 || price > 10000000) {
                System.out.println("Price can be from 0 to 10000000. Input again");
                price = sc.nextDouble();
            }
            return price;
        } catch (InputMismatchException ex) {
            System.out.println("Your entity was not a number or you inputted number was with point. Input number with or without comma");
        }
        return -1;
    }
}
