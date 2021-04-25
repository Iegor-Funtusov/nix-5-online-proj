import java.util.Arrays;

public class Part6 {

    public void arrayTransposition(int[] args) {
        for (int i = 1; i < args.length; i += 2) {
            int tmp = args[i];
            args[i] = args[i - 1];
            args[i - 1] = tmp;
        }
        System.out.print(Arrays.toString(args));
    }
}
