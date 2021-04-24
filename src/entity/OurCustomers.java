package entity;

import org.apache.commons.text.WordUtils;

public class OurCustomers {

    public static void main(String[] args) {
      User.print();
      Customer.print();
      Customer customer = new Customer();
      customer.get_cashback(300);
      String s = "Cashback is available during 3 month!!!";
      s = WordUtils.capitalize(s);
      System.out.println(s);
    }
}