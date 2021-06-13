import domain.Calendar;
import domain.Date;
import exception.InvalidDateFormatException;
import service.Addition;
import service.Deletion;
import service.Delta;
import utils.DateInput;
import utils.DateOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalendarController {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void readConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Вас приветствует календарь!");
        System.out.println("Выберите действие или нажмите '0' для выхода: ");
        System.out.println("1 - найти разницу между датами");
        System.out.println("2 - добавить время к дате");
        System.out.println("3 - вычесть время из даты");
        System.out.println("4 - сравнение дат");
        try {
            String input = reader.readLine();
            while (!"0".equals(input)) {
                switch (input) {
                    case "1":
                        deltaDates(reader);
                        break;
                    case "2":
                        addTime(reader);
                        break;
                    case "3":
                        delTime(reader);
                        break;
                    case "4":
                        comparisonDates(reader);
                        break;
                    default:
                        System.out.println("Неизвестный выбор!");
                }
                System.out.println("Выберите действие или нажмите '0' для выхода: ");
                System.out.println("1 - найти разницу между датами");
                System.out.println("2 - добавить время к дате");
                System.out.println("3 - вычесть время из даты");
                System.out.println("4 - сравнение дат");
                input = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода!");
        }
    }

    private void deltaDates(BufferedReader reader) {
        System.out.println("Найти разницу между датами или '0' для выхода: ");
        System.out.println("1 - в секундах");
        System.out.println("2 - в минутах");
        System.out.println("3 - в часах");
        System.out.println("4 - в годах");
        System.out.println("5 - в месяцах");
        System.out.println("6 - в днях");
        try {
            String input = reader.readLine();
            while (!"0".equals(input)) {
                switch (input) {
                    case "1":
                        deltaSec(reader);
                        break;
                    case "2":
                        deltaMin(reader);
                        break;
                    case "3":
                        deltaHours(reader);
                        break;
                    case "4":
                        deltaYears(reader);
                        break;
                    case "5":
                        deltaMonths(reader);
                        break;
                    case "6":
                        deltaDays(reader);
                        break;
                    default:
                        System.out.println("Неизвестный выбор!");
                }
                System.out.println("Найти разницу между датами или '0' для выхода: ");
                System.out.println("1 - в секундах");
                System.out.println("2 - в минутах");
                System.out.println("3 - в часах");
                System.out.println("4 - в годах");
                System.out.println("5 - в месяцах");
                System.out.println("6 - в днях");
                input = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода!");
        }
    }

    private void addTime(BufferedReader reader) {
        System.out.println("Добавить время или '0' для выхода: ");
        System.out.println("1 - секунды");
        System.out.println("2 - минуты");
        System.out.println("3 - часы");
        System.out.println("4 - годы");
        System.out.println("5 - месяцы");
        System.out.println("6 - дни");
        try {
            String input = reader.readLine();
            while (!"0".equals(input)) {
                switch (input) {
                    case "1":
                        addSec(reader);
                        break;
                    case "2":
                        addMin(reader);
                        break;
                    case "3":
                        addHours(reader);
                        break;
                    case "4":
                        addYears(reader);
                        break;
                    case "5":
                        addMonths(reader);
                        break;
                    case "6":
                        addDays(reader);
                        break;
                    default:
                        System.out.println("Неизвестный выбор!");
                }
                System.out.println("Добавить время или '0' для выхода: ");
                System.out.println("1 - секунды");
                System.out.println("2 - минуты");
                System.out.println("3 - часы");
                System.out.println("4 - годы");
                System.out.println("5 - месяцы");
                System.out.println("6 - дни");
                input = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода!");
        }
    }

    private void delTime(BufferedReader reader) {
        System.out.println("Вычесть время или '0' для выхода: ");
        System.out.println("1 - секунды");
        System.out.println("2 - минуты");
        System.out.println("3 - часы");
        System.out.println("4 - годы");
        System.out.println("5 - месяцы");
        System.out.println("6 - дни");
        try {
            String input = reader.readLine();
            while (!"0".equals(input)) {
                switch (input) {
                    case "1":
                        delSec(reader);
                        break;
                    case "2":
                        delMin(reader);
                        break;
                    case "3":
                        delHours(reader);
                        break;
                    case "4":
                        delYears(reader);
                        break;
                    case "5":
                        delMonths(reader);
                        break;
                    case "6":
                        delDays(reader);
                        break;
                    default:
                        System.out.println("Неизвестный выбор!");
                }
                System.out.println("Вычесть время или '0' для выхода: ");
                System.out.println("1 - секунды");
                System.out.println("2 - минуты");
                System.out.println("3 - часы");
                System.out.println("4 - годы");
                System.out.println("5 - месяцы");
                System.out.println("6 - дни");
                input = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода!");
        }
    }

    public static String formatInput(BufferedReader reader) throws IllegalArgumentException {
        System.out.println("1 - dd/mm/yy");
        System.out.println("2 - m/d/yyyy");
        System.out.println("3 - mmm-d-yy");
        System.out.println("4 - /m/   (Пример: 1 февраля 0 года 0 часов 0 минут 0 секунд)");
        System.out.println("5 - yyyy mm:ss   (Пример: 1 января 1979 года 0 часов 59 минут 59 секунд)");
        System.out.println("6 - /m/yy hh:mm:ss   (Пример: 1 мая 1979 года 0 часов 59 минут 0 секунд)");
        System.out.println("7 - dd-mmm-yyyy hh:mm");
        System.out.println("8 - dd/mm/yyyy hh:mm:ss");
        String result = " ";
        try {
            String input = reader.readLine();
            switch (input) {
                case "1":
                    result = "dd/mm/yy";
                    break;
                case "2":
                    result = "m/d/yyyy";
                    break;
                case "3":
                    result = "mmm-d-yy";
                    break;
                case "4":
                    result = "/m/";
                    break;
                case "5":
                    result = "yyyy mm:ss";
                    break;
                case "6":
                    result = "/m/yy hh:mm:ss";
                    break;
                case "7":
                    result = "dd-mmm-yyyy hh:mm";
                    break;
                case "8":
                    result = "dd/mm/yyyy hh:mm:ss";
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный формат!");
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода!");
        }
        return result;
    }


    public static String formatOutput(BufferedReader reader) throws IllegalArgumentException {
        System.out.println("1 - dd/mm/yy");
        System.out.println("2 - m/d/yyyy");
        System.out.println("3 - mmm-d-yy");
        System.out.println("4 - dd-mmm-yyyy hh:mm");
        System.out.println("5 - dd-mmm-yyyy hh:mm:ss");
        String result = " ";
        try {
            String input = reader.readLine();
            switch (input) {
                case "1":
                    result = "dd/mm/yy";
                    break;
                case "2":
                    result = "m/d/yyyy";
                    break;
                case "3":
                    result = "mmm-d-yy";
                    break;
                case "4":
                    result = "dd-mmm-yyyy hh:mm";
                    break;
                case "5":
                    result = "dd-mmm-yyyy hh:mm:ss";
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный формат!");
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода!");
        }
        return result;
    }

    public void deltaSec(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода первой даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Первая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Выберите формат ввода второй даты: ");
            format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d2 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Вторая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2.getDate()));
            long sec = Delta.getNumberOfSeconds(d1, d2);
            System.out.println("Разница между датами в секундах: " + sec);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            //  System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void deltaMin(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода первой даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Первая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Выберите формат ввода второй даты: ");
            format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d2 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Вторая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2.getDate()));
            long minutes = Delta.getNumberOfMinutes(d1, d2);
            System.out.println("Разница между датами в минутах: " + minutes);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            // System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void deltaHours(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода первой даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Первая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Выберите формат ввода второй даты: ");
            format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d2 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Вторая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2.getDate()));
            long hours = Delta.getNumberOfHours(d1, d2);
            System.out.println("Разница между датами в чвсах: " + hours);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            //  System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void deltaYears(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода первой даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Первая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Выберите формат ввода второй даты: ");
            format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d2 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Вторая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2.getDate()));
            long years = Delta.getNumberOfYears(d1, d2);
            System.out.println("Разница между датами в годах: " + years);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            //  System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void deltaMonths(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода первой даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Первая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Выберите формат ввода второй даты: ");
            format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d2 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Вторая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2.getDate()));
            long months = Delta.getNumberOfMonths(d1, d2);
            System.out.println("Разница между датами в месяцах: " + months);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            // System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void deltaDays(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода первой даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Первая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Выберите формат ввода второй даты: ");
            format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d2 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Вторая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2.getDate()));
            long days = Delta.getNumberOfDays(d1, d2);
            System.out.println("Разница между датами в днях: " + days);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            //  System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void delSec(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество секунд: ");
            Scanner scanner = new Scanner(System.in);
            int seconds = scanner.nextInt();
            Date d2 = Deletion.delSeconds(seconds, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            //  System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            //  System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void delMin(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество минут: ");
            Scanner scanner = new Scanner(System.in);
            int minutes = scanner.nextInt();
            Date d2 = Deletion.delMinutes(minutes, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            //  System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            // System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void delHours(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество часов: ");
            Scanner scanner = new Scanner(System.in);
            int hours = scanner.nextInt();
            Date d2 = Deletion.delHours(hours, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            //  System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            //  System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void delYears(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество лет: ");
            Scanner scanner = new Scanner(System.in);
            int years = scanner.nextInt();
            Date d2 = Deletion.delYears(years, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            //  System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            //   System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void delMonths(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество месяцев: ");
            Scanner scanner = new Scanner(System.in);
            int months = scanner.nextInt();
            Date d2 = Deletion.delMonths(months, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            // System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            // System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void delDays(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество дней: ");
            Scanner scanner = new Scanner(System.in);
            int days = scanner.nextInt();
            Date d2 = Deletion.delDays(days, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            // System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            //  System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void addSec(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество секунд: ");
            Scanner scanner = new Scanner(System.in);
            int seconds = scanner.nextInt();
            Date d2 = Addition.addSeconds(seconds, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            //  System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            //  System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void addMin(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество минут: ");
            Scanner scanner = new Scanner(System.in);
            int minutes = scanner.nextInt();
            Date d2 = Addition.addMinutes(minutes, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            //  System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            //  System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void addHours(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество часов: ");
            Scanner scanner = new Scanner(System.in);
            int hours = scanner.nextInt();
            Date d2 = Addition.addHours(hours, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            //  System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            //  System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void addYears(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество лет: ");
            Scanner scanner = new Scanner(System.in);
            int years = scanner.nextInt();
            Date d2 = Addition.addYears(years, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            //  System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            // System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void addMonths(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество месяцев: ");
            Scanner scanner = new Scanner(System.in);
            int months = scanner.nextInt();
            Date d2 = Addition.addMonths(months, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            // System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            // System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void addDays(BufferedReader reader) {
        try {
            System.out.println("Выберите формат ввода даты: ");
            String format = formatInput(reader);
            System.out.println("Введите дату в формате: " + format);
            Calendar d1 = DateInput.readConsole(format);
            System.out.println("Выберите формат вывода даты: ");
            String formatOutput = formatOutput(reader);
            System.out.println("Исходная дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d1.getDate()));
            System.out.println("Введите количество дней: ");
            Scanner scanner = new Scanner(System.in);
            int days = scanner.nextInt();
            Date d2 = Addition.addDays(days, d1.getDate());
            System.out.println("Выберите формат вывода даты: ");
            formatOutput = formatOutput(reader);
            System.out.println("Новая дата: ");
            System.out.println(DateOutput.formatDateToString(formatOutput, d2));
        } catch (InputMismatchException e) {
            // System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void comparisonDates(BufferedReader reader) {
        try {
            System.out.println("Введите количество дат, которые желаете сравнить: ");
            Scanner scanner = new Scanner(System.in);
            int count = scanner.nextInt();
            Calendar[] dates = new Calendar[count];
            for (int i = 0; i < count; i++) {
                System.out.println("Выберите формат ввода даты: ");
                String format = CalendarController.formatInput(reader);
                System.out.println("Введите дату в формате: " + format);
                Calendar date = DateInput.readConsole(format);
                System.out.println("Выберите формат вывода даты: ");
                String formatOutput = formatOutput(reader);
                System.out.println(DateOutput.formatDateToString(formatOutput, date.getDate()));
                System.out.println();
                dates[i] = date;
            }

            System.out.println("Выберите формат вывода исходных дат: ");
            String formatOutput = CalendarController.formatOutput(reader);

            System.out.println("Перечень дат:");
            for (Calendar date : dates) {
                if (date != null)
                    System.out.println(DateOutput.formatDateToString(formatOutput, date.getDate()));
                else System.out.println("Не с чем сравнивать!");
            }
            System.out.println();
            comparisonBigger(reader, dates);
            comparisonSmaller(reader, dates);

        } catch (InputMismatchException e) {
            // System.out.println("Неверное число!");
            System.out.println(ANSI_RED + e + ": Неверное число!" + ANSI_RESET);
        } catch (InvalidDateFormatException | IllegalArgumentException e) {
            // System.out.println(e.getMessage());
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }

    public void comparisonBigger(BufferedReader reader, Calendar[] dates) {
        try {
            System.out.println("Выберите формат вывода дат по возрастанию: ");
            String formatOutput = formatOutput(reader);
            Calendar.sortBigger(dates);
            System.out.println("Перечень дат по возрастанию:");
            for (Calendar date : dates) {
                if (date != null)
                    System.out.println(DateOutput.formatDateToString(formatOutput, date.getDate()));
                else System.out.println("Не с чем сравнивать!");
            }
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void comparisonSmaller(BufferedReader reader, Calendar[] dates) {
        try {
            System.out.println("Выберите формат вывода дат по убыванию: ");
            String formatOutput = formatOutput(reader);
            Calendar.sortSmaller(dates);
            System.out.println("Перечень дат по возрастанию:");
            for (Calendar date : dates) {
                if (date != null)
                    System.out.println(DateOutput.formatDateToString(formatOutput, date.getDate()));
                else System.out.println("Не с чем сравнивать!");
            }
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
