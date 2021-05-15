import java.util.InputMismatchException;
import java.util.Scanner;

public class Triangle {
    public static void triangle(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose the way you want to input data (random, by hand)");
            String input = scanner.nextLine();
            input = input.toLowerCase();
            switch (input){
                case "by hand":{
                    System.out.println("Input \"x\" and \"y\" for each point:");
                    System.out.println("First point:");
                    System.out.print("x = ");
                    double Ax = number();
                    System.out.print("y = ");
                    double Ay = number();
                    System.out.println("Second point:");
                    System.out.print("x = ");
                    double Bx = number();
                    System.out.print("y = ");
                    double By = number();
                    System.out.println("Third point:");
                    System.out.print("x = ");
                    double Cx = number();
                    System.out.print("y = ");
                    double Cy = number();

                    double AB = Math.sqrt(Math.pow(Bx - Ax, 2) + Math.pow(By - Ay, 2));
                    double AC = Math.sqrt(Math.pow(Cx - Ax, 2) + Math.pow(Cy - Ay, 2));
                    double BC = Math.sqrt(Math.pow(Cx - Bx, 2) + Math.pow(Cy - By, 2));

                    double P = (AB + AC + BC) / 2;
                    double S = Math.sqrt(P * (P - AB) * (P - AC) * (P - BC));
                    System.out.printf("Area: %.2f\n", S);
                }break;
                case "random":{
                    double Ax = numberRandom();
                    double Ay = numberRandom();
                    double Bx = numberRandom();
                    double By = numberRandom();
                    double Cx = numberRandom();
                    double Cy = numberRandom();
                    double AB = Math.sqrt(Math.pow(Bx - Ax, 2) + Math.pow(By - Ay, 2));
                    double AC = Math.sqrt(Math.pow(Cx - Ax, 2) + Math.pow(Cy - Ay, 2));
                    double BC = Math.sqrt(Math.pow(Cx - Bx, 2) + Math.pow(Cy - By, 2));
                    System.out.println("Length of the sides: AB = " + AB + "AC = " + AC + "BC = " + BC);
                    double P = (AB + AC + BC) / 2;
                    double S = Math.sqrt(P * (P - AB) * (P - AC) * (P - BC));
                    System.out.printf("Area: %.2f\n", S);
                }break;
                default:{
                    System.out.println("Incorrect input. Input again.");
                }break;
            }

            System.out.println("Do you want to continue? (yes, no)");
            String choice = scanner.nextLine();
            switch (choice){
                case "no":
                {
                    return;
                }
            }
            System.out.println();
        }
    }

    private static double numberRandom(){
        int num = (int) Math.round((Math.random() * 200) % 200);
        return num;
    }

    private static double number (){
        Scanner scanner = new Scanner(System.in);
        double input;
        while(true) {
            try {
                input = scanner.nextDouble();
                return input;
            } catch (InputMismatchException ex) {
                System.out.println("Wrong input. Input numbers (if there is a floating point then input numbers with comma)");
                return number();
            }
        }
    }
}
