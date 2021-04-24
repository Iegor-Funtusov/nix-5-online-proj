package entity;

import org.apache.commons.text.WordUtils;
import org.apache.commons.lang3.StringUtils;

public class OurCustomers {

    public static void main(String[] args) {
      User.print();
      Customer.print();
      Customer customer = new Customer();
      customer.get_cashback(300);
      String s = "Cashback is available during 3 month!!!";
      s = WordUtils.capitalize(s);
      System.out.println(s);
      String compliment = "Thank you for choosing us!!!";
      compliment = StringUtils.upperCase(compliment);
      System.out.println(compliment);
    }
}