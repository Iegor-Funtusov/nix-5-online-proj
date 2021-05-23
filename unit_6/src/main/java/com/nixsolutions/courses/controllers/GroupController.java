package com.nixsolutions.courses.controllers;

import com.nixsolutions.courses.data.Group;
import com.nixsolutions.courses.services.GroupService;

import javax.management.InstanceAlreadyExistsException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GroupController {

    private final GroupService groupService = new GroupService();
    private final StudentController studentController = new StudentController();
    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private void create() throws IOException {
        try {
            System.out.println("Enter maximum size of the group:");
            Group group = new Group(Integer.parseInt(reader.readLine()));
            System.out.println("Enter  the group name (it must be unique):");
            group.setName(reader.readLine());
            groupService.create(group);
        } catch (NumberFormatException e) {
            System.out.println("Wrong format. Number is expected");
        } catch (InstanceAlreadyExistsException e) {
            System.out.println("Group with the same name already exists");
        }
    }

    private void read() throws IOException {
        System.out.println("Enter name of group you want to read:");
        System.out.println(groupService.read(reader.readLine()));
    }

    private void update() throws IOException {
        System.out.println("Enter group name you want to change:");
        Group group = groupService.read(reader.readLine());
        System.out.println("What to change?\n1 - name\n2 - list of students");
        switch (reader.readLine()) {
            case "1":
                System.out.println("Enter new name:");
                group.setName(reader.readLine());
                break;
            case "2":
                studentController.readConsole(group, reader);
                break;
        }
        groupService.update(group);
    }

    private void delete() throws IOException {
        System.out.println("Enter group name you want to delete:");
        groupService.delete(reader.readLine());
        System.out.println("Group is deleted");
    }

    private void readAll() {
        Group[] groups = groupService.readAll();
        for (Group group : groups) {
            System.out.println(group);
        }
    }

    private void printOptions() {
        System.out.println("Choose option:\n0 - exit\n1 - create group\n2 - read group\n3 - update group\n4 - delete group\n5 - read all groups");
    }

    public void readConsole() throws IOException {
        printOptions();
        String input;
        while ((input = reader.readLine()) != null) {
            switch (input) {
                case "0":
                    System.exit(0);
                case "1":
                    create();
                    break;
                case "2":
                    read();
                    break;
                case "3":
                    update();
                    break;
                case "4" :
                    delete();
                    break;
                case "5":
                    readAll();
                    break;
            }
            printOptions();
        }
    }


}
