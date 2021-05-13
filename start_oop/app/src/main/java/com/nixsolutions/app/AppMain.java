package com.nixsolutions.app;

import java.util.Scanner;
import java.util.Collection;

public class AppMain {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            PearsonService pearsonService = new PearsonService();
            Collection<Pearson> list;
            boolean cycle = true;

            while(cycle){
                System.out.println("What would you like to get?");
                System.out.println("1 -> add pearson");
                System.out.println("2 -> update pearson");
                System.out.println("3 -> delete pearson");
                System.out.println("4 -> read pearson by name");
                System.out.println("5 -> exit");
                list = pearsonService.read();
                list.forEach(System.out::println);
                int choice = scanner.nextInt();

                switch (choice){
                    case 1: {
                        Pearson pearson = new Pearson();
                        System.out.println("Enter Pearson's name: ");
                        String name = scanner.next();
                        pearson.setFirstName(name);
                        System.out.println("Enter Person's age: ");
                        int age = scanner.nextInt();
                        pearson.setAge(age);
                        pearsonService.create(pearson);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter Pearson's name: ");
                        String name = scanner.next();
                        System.out.println("Enter Person's age: ");
                        int age = scanner.nextInt();
                        for(Pearson pearson : list){
                            if(pearson.getFirstName().equals(name)){
                                pearson.setAge(age);
                                pearsonService.update(pearson);
                                break;
                            }
                        }
                        if(list.stream().noneMatch(e -> e.getFirstName().equals(name))){
                            System.out.println("Customer with " + name  + " doesn`t exist");
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("Enter Pearson's name: ");
                        String name = scanner.next();
                        if(list.stream().noneMatch(e -> e.getFirstName().equals(name))){
                            System.out.println("Customer with " + name  + " doesn`t exist");
                            break;
                        }
                        for(Pearson pearson : list){
                            if(pearson.getFirstName().equals(name)){
                                pearsonService.delete(pearson.getId());
                                break;
                            }
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("Enter Pearson's name: ");
                        String name = scanner.next();
                        for (Pearson c : list) {
                            if (c.getFirstName().equals(name)) {
                                System.out.println(pearsonService.read(c.getId()));
                                break;
                            }
                        }
                        if(list.stream().noneMatch(e -> e.getFirstName().equals(name))){
                            System.out.println("Customer with " + name  + " doesn`t exist");
                        }
                        break;
                    }
                    case 5: {
                        cycle = false;
                        break;
                    }
                    default: {
                        System.out.println("Please, enter number from 1 to 5 to make a choice : " );
                        break;
                    }
                }
            }
        }
    }
