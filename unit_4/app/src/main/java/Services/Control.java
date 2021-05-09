package Services;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Control {

    public static StudentService studentService = new StudentService();

    public static void controlConsole () {
        System.out.println("What would you like to do?(don't input info in brackets. Right input: Create)\n" +
                "Create (create a record)\n" +
                "Update (update a record)\n" +
                "Delete (delete a record)\n" +
                "Read all (show all records)\n" +
                "Read (show only 1 record)\n" +
                "Exit (exit from the program)");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.toLowerCase();

        while (true) {
            switch (input) {
                case "create" : {
                    creating();
                } break;
                case "update" : {
                    update();
                } break;
                case "delete" : {
                    delete();
                } break;
                case "read all": {
                    readingAll();
                } break;
                case "read": {
                    read();
                } break;
                case "exit":
                {
                    System.exit(0);
                }
                default: {
                    System.out.println("Incorrect input. Input again");
                }break;
            }
            System.out.println("Next action (CRUD)");
            input = input();
        }
    }

    private static void creating(){
        Student student = new Student();
        String name;
        int course;
        System.out.print("Input name of student: ");
        name = name();
        System.out.print("Input the course of student: ");
        course = course();
        student.setName(name);
        student.setCourse(course);
        studentService.create(student);
    }

    private static void update(){
        String name, newName, updateInput;
        int newCourse, counter = 0;
        boolean flag;
        Scanner sc = new Scanner(System.in);
        Collection<Student> list = studentService.list();
        System.out.println("Input name where you want to change data");
        name = name();
        System.out.println("Input what you want to change (name or course)");
        updateInput = sc.nextLine();
        updateInput = updateInput.toLowerCase();
        flag = true;
        while (flag) {
            switch (updateInput) {
                case "name": {
                    for (Student student1 : list) {
                        if (student1.getName().equals(name)) {
                            System.out.println("Input new name");
                            newName = name();
                            student1.setName(newName);
                            studentService.update(student1);
                            counter++;
                        }
                    }
                    flag = false;
                }
                break;
                case "course": {
                    for (Student student1 : list) {
                        if (student1.getName().equals(name)) {
                            System.out.println("Input new course");
                            newCourse = updatedCourse(student1.getCourse());
                            student1.setCourse(newCourse);
                            studentService.update(student1);
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
            System.out.println("Record with such name doesn't exist");
    }

    private static void delete() {
        int counter = 0;
        String name;
        Collection<Student> list = studentService.list();
        System.out.println("Input name that you want to delete");
        name = name();
        for (Student student1 : list) {
            if (student1.getName().equals(name)) {
                studentService.delete(student1.getId());
                counter++;
                break;
            }
        }
        if (counter == 0)
            System.out.println("Record with such name doesn't exist");
    }

    private static void read (){
        String name;
        Collection<Student> list = studentService.list();
        int counter = 0;
        System.out.println("Input name that you want to find");
        name = name();
        for (Student student1 : list) {
            if (student1.getName().equals(name)) {
                System.out.println(studentService.read(student1.getId()));
                counter++;
            }
        }
        if (counter == 0)
            System.out.println("Record with such name doesn't exist");
    }

    private static void readingAll(){
        Collection<Student> list = studentService.list();
        list.forEach(System.out::println);
    }

    private static String input(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.toLowerCase();
        return input;
    }

    private static String name(){
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        char letter;
        while (name.length() > 50){
            System.out.println("Your name is too long. Input again");
            name = sc.nextLine();
        }
        while(name.isEmpty())
        {
            System.out.println("Your input is empty. Input name");
            name = sc.nextLine();
        }
        int k = name.length();
        for (int i = 0; i < k; i++){
            letter = name.charAt(i);
            if(Character.isDigit(letter)) {
                System.out.println("Name can't contain digits. Input again");
                i = 0;
                name = sc.nextLine();
                k = name.length();
            }
        }
        return name;
    }

    public static int course(){
        Scanner sc = new Scanner(System.in);
        int course = checkCourse();
        while (course == -1) {
            course = checkCourse();
        }
        return course;
    }

    public static int checkCourse(){
        Scanner sc = new Scanner(System.in);
        int course;
        try {
            course = sc.nextInt();
            while (course <= 0 || course > 4) {
                System.out.println("Course can be from 1 to 4. Input again");
                course = sc.nextInt();
            }
            return course;
        } catch (InputMismatchException ex) {
            System.out.println("Your entity is not a number. Input number");
        }
        return -1;
    }

    public static int updatedCourse(int oldCourse){
        int newCourse = course();
        while (newCourse-oldCourse != 1) {
            System.out.println("you can only go from course to course without jumping (e.g. from 1 to 2 or from 3 to 4)\n Input again");
            newCourse = course();
        }
        return newCourse;
    }
}
