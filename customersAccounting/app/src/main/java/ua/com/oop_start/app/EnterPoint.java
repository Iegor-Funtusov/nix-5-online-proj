package ua.com.oop_start.app;
import java.util.Collection;

public class EnterPoint {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        Customer customer = new Customer();
        customer.setName("Вася");
        customer.setSumofpurchase(349);
        customerService.create(customer);

        Customer customer2 = new Customer();
        customer2.setName("Аня");
        customer2.setSumofpurchase(2789);
        customerService.create(customer2);

        Collection<Customer> set = customerService.readAll();
        set.forEach(System.out::println);

        System.out.println(" ");
        for(Customer c : set){
            if(c.getName().equals("Вася")){
                c.setSumofpurchase(c.getSumofpurchase() + 1000);
                customerService.update(c);
            }
        }
        set.forEach(System.out::println);

        System.out.println(" ");
        String fordel = null;
        for(Customer c : set){
            if(c.getName().equals("Вася")){
                fordel = c.getId();
            }
            if (fordel!= null){
                customerService.delete(fordel);
                break;
            }
        }
        set.forEach(System.out::println);

        for(Customer c : set){
            if(c.getName().equals("Аня")) {
                String f = c.getId();
                System.out.println(customerService.read(f));
            }
        }
    }

}
