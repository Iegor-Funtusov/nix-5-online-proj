package application;

import library.ReverseLibrary;

import java.util.Scanner;

public class ReverseApp {

    private static ReverseLibrary ReverseString;

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String string;
        String testString = "Test string";
        System.out.println("Hello User :)");

        while (true) {
            System.out.println("Please enter number of option what you want to check: \n" +
                    "- 1 - Reverse your string \n" +
                    "- 2 - Reverse by the specified substring \n" +
                    "- 3 - Reverse by index\n" +
                    "- 4 - Reverse by symbol\n" +
                    "- 5 - Reverse with boundaries\n" +
                    "Input any other number for finish session.");
            switch (scanner.next()) {
                case "1":
                    System.out.println("Task 1 selected! Reverse your string.");
                    System.out.println("Please enter how do u want to check: \n" +
                            "- 1 - Automatically \n" +
                            "- 2 - Input string by yourself \n" +
                            "Input any other number for finish session.");
                    switch (scanner.next()) {
                        case "1": {
                            System.out.println("Your initial string is:" + testString);
                            System.out.println("Result: " + ReverseString.reverse(testString));
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        case "2": {
                            System.out.println("Enter your string without spaces:");
                            string = scanner.next();
                            System.out.println("Your initial string is:" + string);
                            System.out.println("Result: " + ReverseString.reverse(string));
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        default:
                            System.exit(0);
                    }
                    break;
                case "2":
                    System.out.println("Task 2 selected! Reverse by the specified substring ");
                    System.out.println("Please enter how do u want to check: \n" +
                            "- 1 - Automatically \n" +
                            "- 2 - Input string by yourself \n" +
                            "Input any other number for finish session.");
                    switch (scanner.next()) {
                        case "1": {
                            System.out.println("Your initial string is: " + testString + "\nYour substring is: str");
                            System.out.println("Result: " + ReverseString.reverse(testString, "str"));
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        case "2": {
                            System.out.println("Enter your string without spaces:");
                            string = scanner.next();
                            String dest;
                            System.out.println("Enter specified substring:");
                            dest = scanner.next();
                            System.out.println("Your initial string is: " + string + "\nYour substring is: " + dest);
                            System.out.println("Result: " + ReverseString.reverse(string, dest));
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        default:
                            System.exit(0);
                    }
                    break;
                case "3":
                    System.out.println("Task 3 selected! Reverse by index ");
                    System.out.println("Please enter how do u want to check: \n" +
                            "- 1 - Automatically \n" +
                            "- 2 - Input string by yourself \n" +
                            "Input any other number for finish session.");
                    switch (scanner.next()) {
                        case "1": {
                            System.out.println("Your initial string is: " + testString + "\nYour first index is: 2 \nYour first index is: 5 ");
                            System.out.println("Result: " + ReverseString.reverse(testString, 2, 5));
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        case "2": {
                            System.out.println("Enter your string without spaces:");
                            string = scanner.next();
                            int firstIndex;
                            int secondIndex;
                            System.out.println("Enter first index: ");
                            firstIndex = scanner.nextInt();
                            System.out.println("Enter second index: ");
                            secondIndex = scanner.nextInt();
                            System.out.println("Your initial string is: " + string + "\nYour first index is: " + firstIndex
                                    + "\nYour second index is: " + secondIndex);
                            System.out.println("Result: " + ReverseString.reverse(string, firstIndex, secondIndex));
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        default:
                            System.exit(0);
                    }
                    break;
                case "4":
                    System.out.println("Task 4 selected! Reverse by symbol");
                    System.out.println("Please enter how do u want to check: \n" +
                            "- 1 - Automatically \n" +
                            "- 2 - Input string by yourself \n" +
                            "Input any other number for finish session.");
                    switch (scanner.next()) {
                        case "1": {
                            System.out.println("Your initial string is: " + testString + "\nYour first symbol is: e" +
                                    "\nYour second symbol is: i ");
                            System.out.println("Result: " + ReverseString.reverse(testString, 'e', 'i'));
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        case "2": {
                            System.out.println("Enter your string without spaces:");
                            string = scanner.next();
                            System.out.println("Enter first symbol: ");
                            char firstSymbol = scanner.next().charAt(0);
                            System.out.println("Enter second symbol: ");
                            char secondSymbol = scanner.next().charAt(0);
                            System.out.println("Your initial string is: " + string + "\nYour first symbol is: " + firstSymbol +
                                    "\nYour second symbol is: " + secondSymbol);
                            System.out.println("Result: " + ReverseString.reverse(string, firstSymbol, secondSymbol));
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        default:
                            System.exit(0);
                    }
                    break;
                case "5":
                    System.out.println("Task 5 selected! Reverse with boundaries");
                    System.out.println("Please enter how do u want to check: \n" +
                            "- 1 - Automatically \n" +
                            "- 2 - Input string by yourself \n" +
                            "Input any other number for finish session.");
                    switch (scanner.next()) {
                        case "1": {
                            System.out.println("Your initial string is: " + testString + "\nFirst boundary is : es" +
                                    "\nSecond boundary is: in");
                            System.out.println("Result: " + ReverseString.reverse(testString, "es", "in"));
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        case "2": {
                            System.out.println("Enter your string without spaces:");
                            string = scanner.next();
                            System.out.println("Enter first boundary: ");
                            String firstBoundary = scanner.next();
                            System.out.println("Enter second boundary: ");
                            String secondBoundary = scanner.next();
                            System.out.println("Your initial string is: " + string + "\nFirst boundary is : " + firstBoundary +
                                    "\nSecond boundary is: " + secondBoundary);
                            System.out.println("Result: " + ReverseString.reverse(string, firstBoundary, secondBoundary));
                            System.out.println("___________________________________________________\n");
                            break;
                        }
                        default:
                            System.exit(0);
                    }
                default:
                    System.out.println("Thank you for using this application! \nGood Bye ;)");
                    System.exit(0);
            }
        }
    }
}
