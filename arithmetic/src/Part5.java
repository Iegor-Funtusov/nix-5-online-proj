import java.util.Arrays;

public class Part5 {

    public void reverseMassive(int[] args) {
        for (int i = 0; i < args.length / 2; i++) {
            int tmp = args[i];
            args[i] = args[args.length - 1 - i];
            args[args.length - 1 - i] = tmp;
        }
        System.out.println(Arrays.toString(args));
    }
}
