package ua.com.alevel.app;

import lombok.SneakyThrows;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class AppMain {

    private static final BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        callInterface();
    }

    private static void callInterface() {
        printWelcomeMassage();
        while (true){
            printMenu();
            chooseOperation();
        }
    }

    private static void printMenu() {
        System.out.print("Please, choose what do you want to do:" +
                "\n 1 - create user and add it to data base" +
                "\n 2 - read user from database by its id" +
                "\n 3 - print all users" +
                "\n 0 - exit" +
                "\n --> ");
    }

    @SneakyThrows
    private static void chooseOperation() {
        switch (reader.readLine()){
            case "1": {
                userService.create(createUser());
                //printAllUsers();
                break;
            }
            case "2": {
                printAllUsers();
                readUser();
                break;
            }
            case "3": {
                printAllUsers();
                break;
            }
            case "0": {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Invalid choice. Try again.");
                break;
            }
        }
    }

    private static void readUser() {
        User readUser;
        while (true) {
            try {
                System.out.println("Please enter user id for user you want to read:");
                System.out.print("--> ");
                readUser = userService.read(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            if(readUser == null){
                System.out.println("Invalid id!");
                continue;
            }else {
                System.out.println("Read user: " + readUser);
                chooseReadUserOperation(readUser);
            }
            break;
        }
    }

    @SneakyThrows
    private static void chooseReadUserOperation(User readUser) {
        boolean flag = true;
        while (flag) {
            System.out.print("What do you want to do with read user?" +
                    "\n 1 - update" +
                    "\n 2 - delete" +
                    "\n 3 - nothing" +
                    "\n --> ");
            switch (reader.readLine()) {
                case "1": {
                    System.out.println("Enter new parameters for user.");
                    User userForUpdate = createUser();
                    userForUpdate.setId(readUser.getId());
                    userService.update(userForUpdate);
                    System.out.println("User updated!");
                    flag = false;
                    break;
                }
                case "2": {
                    userService.delete(readUser.getId());
                    System.out.println("User deleted!");
                    flag = false;
                    break;
                }
                case "3": {
                    flag = false;
                    break;
                }
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private static void printAllUsers(){
        Collection<User> users = userService.read();
        if (users.size() == 0) {
            System.out.println("Empty!");
            return;
        }
        System.out.println("Users:");
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    private static User createUser() {
        User user;
        while (true) {
            user = getUser();
            if(user != null)
                break;
            System.out.println("Invalid name or age. Try again.");
        }
        return user;
    }

    private static User getUser() {
        String name;
        int age;
        try {
            System.out.print("Please enter user name: ");
            name = reader.readLine();
            System.out.print("Please enter user age: ");
            age = Integer.parseInt(reader.readLine());
            return new User(name, age);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void printWelcomeMassage() {
        System.out.println("Hello, this program do crud with users entities.");
    }


}
