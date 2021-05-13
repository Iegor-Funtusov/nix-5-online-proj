package com.nixsolutions.courses.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Level1 {

    static BufferedReader reader;

    public static void countUniqueElements() throws IOException {

    }

    public static void run() {
        System.out.println("Level1.run");
        reader = new BufferedReader((new InputStreamReader(System.in)));
        String input;
        try {
            while (true) {
                System.out.println("Choose task:\n0 - exit\n1 - count unique elements of array");
                input = reader.readLine();

                switch(input) {
                    case "0": System.exit(0);
                    case "1":
                        countUniqueElements();
                        break;
                }
            }
        } catch(IOException e) {
            System.out.println("Something went wrong");
        }
    }

}
