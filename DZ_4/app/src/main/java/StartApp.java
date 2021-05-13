import java.util.Scanner;

public class StartApp {
    public static void runApp() {
        System.out.println("welcome to the CRUD application");
        StudentService studentService = new StudentService();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Пожалуйста , укажите какую операцию вы хотите выполнить:\nA - добавить студента\nE - изменить даные студента" +
                    "\nD - удалить студента из БД\nI - получить инфо про всех студентов\n_____________\nX- выход");
            String a = scanner.next();
            char[] cur = a.toCharArray();

            switch (cur[0]) {
                case 'A': {
                    Student student = new Student();
                    System.out.println("Введите имя и нажмите Enter:");
                    student.setName(scanner.next());
                    System.out.println("Введите возраст и нажмите Enter:");
                    student.setAge(scanner.nextInt());
                    System.out.println("Введите студ.билет и нажмите Enter:");
                    student.setStud_card(scanner.nextInt());
                    studentService.create(student);
                    System.out.println("Студент добавлен");
                    System.out.println("_____________________________________________");

                    break;
                }


                case 'E': {


                    System.out.println("Введите ID студента");
                    String id = scanner.next();
                    Student student = new Student();
                    if ((student = studentService.read(id)) == null) {
                        System.out.println("Неверный ID, попробуйте снова");
                        break;
                    }

                    System.out.println("Что вы хотите поменять?\nN-имя\nA-возраст\nC-студ.билет\nХ - выйти");
                    String b = scanner.next();
                    char[] cur_e = b.toCharArray();
                    switch (cur_e[0]) {
                        case 'N': {
                            System.out.println("Введите новое имя");
                            student.setName(scanner.next());
                            studentService.update(student);
                            System.out.println("Успешно");
                            System.out.println("_____________________________________________");
                            break;
                        }
                        case 'A': {
                            System.out.println("Введите новый возраст");
                            student.setAge(scanner.nextInt());
                            studentService.update(student);
                            System.out.println("Успешно");
                            System.out.println("_____________________________________________");
                            break;
                        }
                        case 'C': {
                            System.out.println("Введите новый студ.билет");
                            student.setStud_card(scanner.nextInt());
                            studentService.update(student);
                            System.out.println("Успешно");
                            System.out.println("_____________________________________________");
                            break;
                        }
                        case 'X': {
                            System.exit(0);
                            break;
                        }
                        default:
                            System.out.println("Неверно ввели");

                    }
                    break;

                }


                case 'D': {
                    System.out.println("Введите ID студента");
                    try {
                        studentService.delete(scanner.next());
                    } catch (RuntimeException ex) {
                        System.err.println("Неверно введен ID, попробуйте снова");
                    }
                    System.out.println("Успешно");
                    break;
                }


                case 'I': {
                    System.out.println(studentService.read());
                    break;
                }


                case 'X': {
                    System.out.println("Goodbye");
                    System.exit(0);
                    break;
                }


                default: {
                    System.out.println("Ни один вариант не совпадает с предлагаемыми!!!");
                }

            }
            scanner.nextLine();
        }
    }


}
