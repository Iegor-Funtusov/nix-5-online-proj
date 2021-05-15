import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StringTest {
    public static void main(String[] args) throws IOException {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Выберите операцию со строкой или нажмите 'q' для выхода: ");
        System.out.println("1 - реверс строки");
        System.out.println("2 - реверс по указанной подстроке");
        System.out.println("3 - реверс по индексам");
        System.out.println("4 - реверс по символам");
        System.out.println("5 - реверс по строкам");
        String choice = inputUser.next();
        while (!"q".equals(choice)) {
            switch (choice) {
                case "1":
                    reverseString();
                    break;
                case "2":
                    reverseSubstring();
                    break;
                case "3":
                    reverseStringByIndexes();
                    break;
                case "4":
                    reverseStringByChars();
                    break;
                case "5":
                    reverseStringByStrings();
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный выбор!");
            }
            System.out.println("Выберите операцию со строкой или нажмите 'q' для выхода: ");
            System.out.println("1 - реверс строки");
            System.out.println("2 - реверс по указанной подстроке");
            System.out.println("3 - реверс по индексам");
            System.out.println("4 - реверс по символам");
            System.out.println("5 - реверс по строкам");
            choice = inputUser.next();
        }
    }

    public static void reverseString() throws IOException {
        BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите строку: ");
        String string = inputUser.readLine();
        System.out.println(ReverseString.reverseString(string));
    }

    public static void reverseSubstring() throws IOException {
        BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите строку: ");
        String string = inputUser.readLine();
        System.out.print("Введите подстроку: ");
        String substring = inputUser.readLine();
        System.out.println(ReverseString.reverseString(string, substring));
    }

    public static void reverseStringByIndexes() throws IOException {
        BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите строку: ");
        String string = inputUser.readLine();
        System.out.print("Введите первый индекс: ");
        int firstIndex = Integer.parseInt(inputUser.readLine());
        System.out.print("Введите последний индекс: ");
        int lastIndex = Integer.parseInt(inputUser.readLine());
        System.out.println(ReverseString.reverseString(string, firstIndex, lastIndex));
    }

    public static void reverseStringByChars() throws IOException {
        BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите строку: ");
        String string = inputUser.readLine();
        System.out.print("Введите первый символ: ");
        char firstChar = inputUser.readLine().charAt(0);
        System.out.print("Введите последний символ: ");
        char lastChar = inputUser.readLine().charAt(0);
        System.out.println(ReverseString.reverseString(string, firstChar, lastChar));
    }

    public static void reverseStringByStrings() throws IOException {
        BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите строку: ");
        String string = inputUser.readLine();
        System.out.print("Введите первую подстроку: ");
        String firstString = inputUser.readLine();
        System.out.print("Введите последнюю подстроку: ");
        String lastString = inputUser.readLine();
        System.out.println(ReverseString.reverseString(string, firstString, lastString));
    }
}
