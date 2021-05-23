package com.nixsolutions.courses.controllers;

import com.nixsolutions.courses.data.Group;
import com.nixsolutions.courses.data.Student;
import com.nixsolutions.courses.services.GroupService;
import com.nixsolutions.courses.services.StudentService;

import java.io.BufferedReader;
import java.io.IOException;

public class StudentController {

    private final StudentService studentService = new StudentService();
//    private final GroupService groupService = new GroupService();
    BufferedReader reader;

    private void create(Group group) throws IOException {
        Student student = new Student();
        System.out.println("Enter name of the student:");
        student.setName(reader.readLine());
        System.out.println("Enter age:");
        student.setAge(Integer.parseInt(reader.readLine()));
        student.setGroupName(group.getName());
        studentService.create(student, group);
        System.out.println("Student created:");
        System.out.println(student);
    }

    private void read(Group group) throws IOException {
        System.out.println("Enter id of student you want to read:");
        System.out.println(studentService.read(reader.readLine(), group));
    }

    private void update(Group group) throws IOException {
        System.out.println("Enter id of student you want to change:");
        Student student = studentService.read(reader.readLine(), group);
        System.out.println("What to change?\n1 - name\n2 - age\n3 - group");
        switch (reader.readLine()) {
            case "1":
                System.out.println("Enter new name:");
                student.setName(reader.readLine());
                break;
            case "2":
                System.out.println("Enter new age:");
                student.setAge(Integer.parseInt(reader.readLine()));
                break;
        }
        studentService.update(student, group);
    }

    private void delete(Group group) throws IOException {
        System.out.println("Enter id of student you want to delete:");
        studentService.delete(reader.readLine(), group);
        System.out.println("Student is deleted");
    }

    private void readAll(Group group) {
        Student[] students = studentService.readAll(group);
        for (Student item : students) {
            System.out.println(item);
//            System.out.println(item.getName() + "    " + item.getAge() + "     " + item.getGroupName() + "       " + item.getId());
        }
    }

    private void printOptions() {
        System.out.println("Choose option:\n0 - exit\n1 - create student\n2 - read student\n3 - update student\n4 - delete student\n5 - read all students");
    }

    public void readConsole(Group group, BufferedReader reader) throws IOException {
        this.reader = reader;
//        studentService = new StudentService(group);
        printOptions();
        String input;
        while (!(input = reader.readLine()).equals("0")) {
            switch (input) {
                case "1":
                    create(group);
                    break;
                case "2":
                    read(group);
                    break;
                case "3":
                    update(group);
                    break;
                case "4" :
                    delete(group);
                    break;
                case "5":
                    readAll(group);
            }
            printOptions();
        }
    }

}
