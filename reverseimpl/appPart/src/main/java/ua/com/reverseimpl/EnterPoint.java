package ua.com.reverseimpl;

public class EnterPoint {
    public static void main(String[] args) {
        String s = "hello world";
        System.out.println(s);
        System.out.println("Usual reverse:  " + reverseString.reverse(s));
        System.out.println("Reverse 'word' in a string: "
                + reverseString.reverse(s, "worl"));
        System.out.println("Reverse symbols between 3 and 7 indexes in a string: "
                + reverseString.reverse(s, 3, 7));
        System.out.println(" ");
        ReverseUsersString.processUserString();
    }
}

