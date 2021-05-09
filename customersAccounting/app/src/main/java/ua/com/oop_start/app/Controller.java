package ua.com.oop_start.app;

import java.util.Collection;
import java.util.Scanner;

public class Controller {
    public static void getFunctions(){
        Scanner scanner = new Scanner(System.in);
        CustomerService customerService = new CustomerService();
        Collection<Customer> set;
        boolean cycle = true;

        while(cycle){
         System.out.println("What would you like to get?");
         System.out.println("1 -> add customer");
         System.out.println("2 -> update customer");
         System.out.println("3 -> delete customer");
         System.out.println("4 -> read customer by name");
         System.out.println("5 -> exit");
         set = customerService.readAll();
         set.forEach(System.out::println);
         int chioce = scanner.nextInt();

         switch (chioce){
             case 1: {
                 Customer customer = new Customer();
                 System.out.println("Enter customer name: ");
                 String name = scanner.next();
                 customer.setName(name);
                 System.out.println("Enter the sum of purchase: ");
                 int sum = scanner.nextInt();
                 customer.setSumofpurchase(sum);
                 customerService.create(customer);
                 break;
             }
             case 2: {
                 System.out.println("Enter customer name: ");
                 String name = scanner.next();
                 System.out.println("Enter the sum of NEXT purchase: ");
                 int sum = scanner.nextInt();
                 for(Customer c : set){
                     if(c.getName().equals(name)){
                         c.setSumofpurchase(c.getSumofpurchase() + sum);
                         customerService.update(c);
                         break;
                     }
                 }
                 if(set.stream().noneMatch(e -> e.getName().equals(name))){
                     System.out.println("Customer with " + name  + " doesn`t exist");
                 }
                 break;
             }
             case 3: {
                 System.out.println("Enter customer name: ");
                 String name = scanner.next();
                 if(set.stream().noneMatch(e -> e.getName().equals(name))){
                     System.out.println("Customer with " + name  + " doesn`t exist");
                     break;
                 }
                 for(Customer c : set){
                     if(c.getName().equals(name)){
                         customerService.delete(c.getId());
                         break;
                     }
                 }
                 break;
             }
             case 4: {
                 System.out.println("Enter customer name: ");
                 String name = scanner.next();
                 for (Customer c : set) {
                     if (c.getName().equals(name)) {
                         System.out.println(customerService.read(c.getId()));
                         break;
                     }
                 }
                 if(set.stream().noneMatch(e -> e.getName().equals(name))){
                     System.out.println("Customer with " + name  + " doesn`t exist");
                 }
                 break;
             }
             case 5: {
                 cycle = false;
                 break;
             }
             default: {
                 System.out.println("Please, enter number from 1 to 5 : " );
                 break;
             }
         }
         }
     }
    }

