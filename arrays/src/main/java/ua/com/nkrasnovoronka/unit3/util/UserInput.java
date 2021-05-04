package ua.com.nkrasnovoronka.unit3.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public final class UserInput {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public int readUserOption() {
        int option = 0;
        try {
            option = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            System.err.println("Oops!! Please check your input");
        }
        return option;
    }

    public String readFigureStartPosition() {
        System.out.println("Pleas enter start position like (a1)");
        String position = "";
        try {
            position = bufferedReader.readLine().toLowerCase(Locale.ROOT);
        } catch (IOException e) {
            System.err.println("Oops!! Please check your input");
        }
        return position;
    }

    public String readMovePosition() {
        System.out.println("Pleas enter move position like(a1 a2)");
        String position = "";
        try {
            position = bufferedReader.readLine().toLowerCase(Locale.ROOT);
        } catch (IOException e) {
            System.err.println("Oops!! Please check your input");
        }
        return position;
    }

    public boolean isValidPosition(String position, String regexp) {
        return position.matches(regexp);
    }

}
