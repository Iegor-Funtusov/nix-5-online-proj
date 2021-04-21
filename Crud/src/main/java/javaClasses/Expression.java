package javaClasses;

import java.util.Scanner;

public class Expression {
    public void expression() {
        Scanner sc = new Scanner(System.in);
        Warning warning = new Warning();
        int number1, number2, result, swap;
        String sign;
        System.out.println("Input 2 numbers and +, -, / or *");
        number1 = sc.nextInt();
        number2 = sc.nextInt();
        sign = sc.next();
        if (number1 < number2) {
            swap = number1;
            number1 = number2;
            number2 = swap;
        }
        switch (sign) {
            case "+":
                result = number1 + number2;
                System.out.println(result);
                break;
            case "-":
                result = number1 - number2;
                System.out.println(result);
                break;
            case "/":
                result = number1 / number2;
                System.out.println(result);
                break;
            case "*":
                result = number1 * number2;
                System.out.println(result);
                break;
            default:
                warning.fail();
                return;
        }
        warning.success();
    }
}