package com;

import java.util.Scanner;

public class Controller {
    public static void AppRun() {
        System.out.println("Добро пожаловать в ФУТБОЛЬНЫЙ CRUD");
        AllService allService = new AllService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nA - операции с командами(crud)" +
                    "\nB - опреации с футболистами(crud)" +
                    "\nC - общие операции(добавить футболиста в команду, удалить футболиста из команды)" +
                    "\n_____________" +
                    "\nX- выход");
            String a = scanner.next();
            char[] cur = a.toCharArray();

            switch (cur[0]) {
                case 'A': {

                        System.out.println("Работа с командами:" +
                                "\nA - добавить доманду" +
                                "\nB - изменить даные команды" +
                                "\nC - удалить команду из БД" +
                                "\nD - получить инфо про все команды" +
                                "\n_____________\nX- выход");
                        a = scanner.next();
                        cur = a.toCharArray();

                        switch (cur[0]) {
                            case 'A': {
                                System.out.println("Введите размер команды и нажмите Enter:");
                                int s = scanner.nextInt();
                                Team team = new Team(s);
                                System.out.println("Введите название и нажмите Enter:");
                                team.setT_name(scanner.next());
                                allService.createTeam(team);
                                System.out.println("Команда создана");
                                System.out.println("_____________________________________________");
                                break;
                            }
                            case 'B': {
                                System.out.println("Введите ID команды");
                                String id = scanner.next();
                                if ((allService.findTeamById(id)) == null) {
                                    System.out.println("Неверный ID, попробуйте снова");
                                    break;
                                }
                                Team team = allService.findTeamById(id);
                                System.out.println("N-изменить название\nХ - выйти");
                                String b = scanner.next();
                                char[] cur_e = b.toCharArray();
                                switch (cur_e[0]) {
                                    case 'N': {
                                        System.out.println("Введите новое название");
                                        team.setT_name(scanner.next());
                                        allService.updateTeam(team);
                                        System.out.println("Успешно изменено на " + team.getT_name());
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
                            case 'C': {
                                System.out.println("Введите ID команды");
                                try {
                                    allService.deleteTeam(scanner.next());
                                } catch (RuntimeException ex) {
                                    System.err.println("Неверно введен ID, попробуйте снова");
                                }
                                System.out.println("Успешно");
                                break;
                            }
                            case 'D': {
                                Team[] teams = allService.findAllTeam();
                                for (Team team : teams) {
                                    if (team != null) {
                                        System.out.println(team);
                                    }
                                }
                                break;
                            }
                            case 'X': {
                                System.out.println("Переход в главное меню...");
                                break;
                            }
                            default: {
                                System.out.println("Ни один вариант не совпадает с предлагаемыми!!!");
                                break;
                            }
                        }
                        break;

                }


                case 'B': {
                    System.out.println("Работа с футболистами:" +
                            "\nA - добавить футболиста" +
                            "\nB - изменить даные футболиста" +
                            "\nC - удалить футболиста из БД" +
                            "\nD - получить инфо про всех футболистов" +
                            "\n_____________\nX- выход");

                    a = scanner.next();
                    cur = a.toCharArray();

                    switch (cur[0]) {
                        case 'A': {
                            System.out.println("Введите имя и нажмите Enter:");
                            String na = scanner.next();
                            System.out.println("Введите возраст и нажмите Enter:");
                            int ag = scanner.nextInt();
                            Footballer footballer = new Footballer(na,ag);
                            allService.createFoot(footballer);
                            System.out.println("Футболист добавлен");
                            System.out.println("_____________________________________________");
                            break;
                        }
                        case 'B': {
                            System.out.println("Введите ID футболиста");
                            String id = scanner.next();
                            if ((allService.findFootById(id)) == null) {
                                System.out.println("Неверный ID, попробуйте снова");
                                break;
                            }
                            Footballer footballer = allService.findFootById(id);
                            System.out.println("\nN-изменить имя\nA-изменить возраст\nХ - выйти");
                            String b = scanner.next();
                            char[] cur_e = b.toCharArray();
                            switch (cur_e[0]) {
                                case 'N': {
                                    System.out.println("Введите новое имя");
                                    footballer.setFoot_name(scanner.next());
                                    allService.updateFoot(footballer);
                                    System.out.println("Успешно изменено на " + footballer.getFoot_name());
                                    System.out.println("_____________________________________________");
                                    break;
                                }
                                case 'A': {
                                    System.out.println("Введите новый возраст");
                                    footballer.setFoot_age(scanner.nextInt());
                                    allService.updateFoot(footballer);
                                    System.out.println("Успешно изменено на " + footballer.getFoot_age());
                                    System.out.println("_____________________________________________");
                                    break;
                                }
                                case 'X': {
                                    System.out.println("Переход в главное меню...");

                                }
                                default:
                                    System.out.println("Неверно ввели");
                                    break;
                            }
                            break;

                        }
                        case 'C': {
                            System.out.println("Введите ID футболиса");
                            try {
                                allService.deleteFoot(scanner.next());
                            } catch (RuntimeException ex) {
                                System.err.println("Неверно введен ID, попробуйте снова");
                            }
                            System.out.println("Успешно");
                            break;
                        }
                        case 'D': {
                            Footballer[] footballers = allService.findAllFoot();
                            for (Footballer foot : footballers) {
                                if (foot != null) {
                                    System.out.println(foot);
                                }
                            }
                            break;
                        }
                        case 'X': {
                            System.out.println("Переход в главное меню...");
                            System.exit(0);
                            break;
                        }
                        default: {
                            System.out.println("Ни один вариант не совпадает с предлагаемыми!!!");
                        }
                    }
                    break;
                }


                case 'C': {
                    System.out.println("Работа с общими операциями:" +
                            "\nA - добавить футболиста в команду(Нужны ИД футболиста и команды)" +
                            "\nB - удалить футболиста из команды(Нужен ИД футболиста)" +
                            "\n_____________\nX- выход");
                    a = scanner.next();
                    cur = a.toCharArray();

                    switch (cur[0]) {
                        case 'A': {
                            System.out.println("Введите ИД футболиста и нажмите Enter:");
                            String na = scanner.next();
                            if(allService.findFootById(na)==null){
                                System.out.println("Футболиста с таким ИД нет");
                                break;
                            }
                            Footballer footballer = allService.findFootById(na);


                            System.out.println("Введите ИД команды и нажмите Enter:");
                            na = scanner.next();
                            if(allService.findTeamById(na)==null){
                                System.out.println("Команды с таким ИД нет");
                                break;
                            }
                            Team team = allService.findTeamById(na);

                            allService.pushFootToTeam(footballer,team);
                            System.out.println("Операция успешна");
                            System.out.println(allService.findTeamById(na));

                            System.out.println("_____________________________________________");
                            break;
                        }
                        case 'B': {
                            System.out.println("Введите ИД футболиста и нажмите Enter:");
                            String na = scanner.next();
                            if(allService.findFootById(na)==null){
                                System.out.println("Футболиста с таким ИД нет");
                                break;
                            }
                            System.out.println("Футболист удален из команды  "+allService.deleteFootFromTeam(na));
                            break;

                        }
                        case 'X': {
                            System.out.println("Переход в главное меню...");
                            System.exit(0);
                            break;
                        }
                        default: {
                            System.out.println("Ни один вариант не совпадает с предлагаемыми!!!");
                        }
                    }
                    break;
                }


                case 'X': {
                    System.out.println("Goodbye");
                    System.exit(0);
                    break;
                }


                default: {
                    System.out.println("Неверно ввели");
                    break;
                }

            }
        }

    }

}


