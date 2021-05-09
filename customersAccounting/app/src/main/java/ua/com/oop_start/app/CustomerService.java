package ua.com.oop_start.app;

import ua.com.oop_start.lib.CrudProcessFactory;
import ua.com.oop_start.lib.ICrudProcess;

import java.util.Collection;

public class CustomerService {

    ICrudProcess <Customer> customerCrudProcess = CrudProcessFactory.getInstance().getCrudProcess();

public void create(Customer customer){
    customerCrudProcess.create(customer);
}

public void update(Customer customer){
    customerCrudProcess.update(customer);
}

public void delete(String id){
    customerCrudProcess.delete(id);
}

public Customer read(String id){
    return customerCrudProcess.read(id);
}

public Collection<Customer> readAll(){
    return customerCrudProcess.readAll();
}

}
