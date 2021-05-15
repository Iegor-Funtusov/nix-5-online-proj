package level2.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Random;

public class StringGenerator {

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String backets = "{}[]()";

    public static final String digits = "0123456789";

    public static final String alphanum = upper + backets + digits;

    private final Random random;

    private final char[] symbols;

    private final char[] buf;


    public String nextStringRandom() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public StringGenerator(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    public StringGenerator(int length, Random random) {
        this(length, random, alphanum);
    }

    public static String inputStringRandom() throws IOException {
        Random random = new Random();
        StringGenerator sg = new StringGenerator(10, random);
        System.out.println("Your string is: " + sg.nextStringRandom());
        return sg.nextStringRandom();
    }

    public static String inputStringByUser() throws IOException {
        System.out.println("Please enter a string and press Enter button:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = bufferedReader.readLine();
        System.out.println("Your string is: " + inputString);
        return inputString;
    }

}
