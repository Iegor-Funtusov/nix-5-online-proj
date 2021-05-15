import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Brackets {
    public static void controle(){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Choose the way you want to input data (random, by hand)");
            String input = scanner.nextLine();
            input = input.toLowerCase();
            switch (input) {
                case "by hand": {
                    System.out.println("Input your string");
                    Scanner sc = new Scanner(System.in);
                    String string = sc.nextLine();
                    String brackets = "";
                    for (int i = 0; i < string.length(); i++) {
                        if (string.charAt(i) == '(' || string.charAt(i) == '[' || string.charAt(i) == '{' ||
                                string.charAt(i) == ')' || string.charAt(i) == ']' || string.charAt(i) == '}')
                            brackets += string.charAt(i);
                    }

                    if (validator(brackets))
                        System.out.println("Valid string");
                    else
                        System.out.println("Invalid string");

                    System.out.println();
                }break;
                case "random":{
                    char[] chars = "()[]{}".toCharArray();
                    char[] bracket = new char[4];
                    Random random = new Random();
                    for (int i = 0; i < 4; i++) {
                        char c = chars[random.nextInt(chars.length)];
                        bracket[i] = c;
                    }
                    String brackets = new String(bracket);
                    System.out.println(brackets);
                    if (validator(brackets))
                        System.out.println("Valid string");
                    else
                        System.out.println("Invalid string");

                    System.out.println();
                }break;
                default:{
                    System.out.println("Incorrect input");
                }break;
            }
            System.out.println("Do you want to continue? (yes, no)");
            String choice = scanner.nextLine();
            switch (choice){
                case "no":
                {
                    return;
                }
            }
            System.out.println();
        }
    }

    static boolean validator(String string)
    {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < string.length(); i++)
        {
            char bracket = string.charAt(i);

            if (bracket == '(' || bracket == '[' || bracket == '{')
            {
                stack.push(bracket);
                continue;
            }

            if (stack.isEmpty())
                return false;

            char checking;
            switch (bracket) {
                case ')':
                    checking = stack.pop();
                    if (checking == '{' || checking == '[')
                        return false;
                    break;

                case '}':
                    checking = stack.pop();
                    if (checking == '(' || checking == '[')
                        return false;
                    break;

                case ']':
                    checking = stack.pop();
                    if (checking == '(' || checking == '{')
                        return false;
                    break;
            }
        }
        return (stack.isEmpty());
    }
}
