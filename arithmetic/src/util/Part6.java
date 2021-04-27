package util;

import java.util.Arrays;

public class Part6 {

    public void arrayTransposition(int[] args) {
        System.out.print("Reversed neighbor array representation: ");
        for (int i = 1; i < args.length; i += 2) {
            int tmp = args[i];
            args[i] = args[i - 1];
            args[i - 1] = tmp;
        }
        System.out.println(Arrays.toString(args));
    }
}
