package com.nixsolutions.courses.controllers;

import com.nixsolutions.courses.data.Group;
import com.nixsolutions.courses.data.Student;
import com.nixsolutions.courses.services.StudentService;

import java.io.BufferedReader;
import java.io.IOException;

public class StudentController {

    private final StudentService studentService = new StudentService();
    BufferedReader reader;
    private Group group;

    private void create() throws IOException {
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

    private void read() throws IOException {
        System.out.println("Enter id of student you want to read:");
        System.out.println(studentService.read(reader.readLine(), group));
    }

    private void update() throws IOException {
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
        System.out.println("Student updated");
    }

    private void delete() throws IOException {
        System.out.println("Enter id of student you want to delete:");
        studentService.delete(reader.readLine(), group);
        System.out.println("Student deleted");
    }

    private void readAll() {
        Student[] students = studentService.readAll(group);
        for (Student item : students) {
            System.out.println(item);
        }
    }

    private void printOptions() {
        System.out.println("Choose option:\n0 - exit\n1 - create student\n2 - read student\n3 - update student\n4 - delete student\n5 - read all students");
    }

    public void readConsole(Group group, BufferedReader reader) throws IOException {
        this.reader = reader;
        this.group = group;
        printOptions();
        String input;
        while (!(input = reader.readLine()).equals("0")) {
            switch (input) {
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
            }
            printOptions();
        }
    }

}
