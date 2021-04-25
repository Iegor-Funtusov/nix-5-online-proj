public class Part2 {

    public void quantityPositiveNumbers(int [] args) {
        System.out.println("Output quantity of positive numbers:");
        int quantity = 0;
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                quantity++;
            }
        }
        System.out.println(quantity);
    }
}