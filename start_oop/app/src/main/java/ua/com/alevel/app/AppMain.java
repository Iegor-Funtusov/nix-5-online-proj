package ua.com.alevel.app;

import java.util.Collection;
import java.util.List;

public class AppMain {

    public static void main(String[] args) {

        UserService userService = new UserService();

        User user = new User();
        user.setAge(20);
        user.setName("qq");
        userService.create(user);

        user = new User();
        user.setAge(25);
        user.setName("ww");
        userService.create(user);

        Collection<User> list = userService.read();
        list.forEach(System.out::println);

        for (User user1 : list) {
            if(user1.getName().equals("qq")){
                user1.setName("qq UPDATE");
                userService.update(user1);
            }
        }

        userService.read();
        list.forEach(System.out::println);
    }
}
