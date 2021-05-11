package ua.com.nkrasnovoronka.app;

import ua.com.nkrasnovoronka.lib.StringReverser;

public class Main {
    public static void main(String[] args) {
        System.out.println("Some string reverse tests");
        System.out.println("StringReverser.reverse(\"this string will be reversed\") = " + StringReverser.reverse("this string will be reversed"));
        System.out.println("StringReverser.reverse(\"revers hello word\", \"hello\") = " + StringReverser.reverse("revers hello word", "hello"));
        System.out.println("StringReverser.reverse(\"revers by index 2 6\", 2, 6) = " + StringReverser.reverse("revers by index 2 6", 2, 6));
        System.out.println("StringReverser.reverse(\"revers by char e s\", 'e', 's') = " + StringReverser.reverse("revers by char e s", 'e', 's'));
        System.out.println("StringReverser.reverse(\"reverse by string symbols\", \"re\", \"by\") = " + StringReverser.reverse("reverse by string symbols", "re", "by"));
        System.out.println("StringReverser.reverse(\"reverse by CharSequence Ch ce\", \"Ch\",\"ce\") = " + StringReverser.reverse("reverse by CharSequence Ch ce", "Ch", "ce"));
    }
}
