package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerList implements Serializable {
    private ArrayList<Customer> customers;

    public CustomerList() {
        customers = new ArrayList<Customer>();
    }


    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomer(int index) {
        return customers.get(index);
    }

    public int size() {
        return customers.size();
    }

    public void set(int index, Customer customer) throws IndexOutOfBoundsException{
        customers.set(index,customer);
    }

    public int indexOf(Customer customer) {
        return  customers.indexOf(customer);
    }


    public ArrayList<Customer> getCustomerList() {
        return customers;
    }
}
