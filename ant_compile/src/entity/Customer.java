package entity;

public class Customer extends User{
    private double sum;
    public static void print(){
        System.out.println("Hi, I`m a customer now!");
    }

    public void get_cashback(double sum){
        double a = Math.sqrt(sum);
        System.out.println("I have cashback: " + String.format("%.2f", a));
    }
}
