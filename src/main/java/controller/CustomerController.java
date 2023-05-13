package controller;

import domain.customer.Customer;
import dao.CustomerDAO;

public class CustomerController {

    public boolean register(Customer customer) {
        return new CustomerDAO().register(customer);
    }

    public boolean login(String[] user) {
        return new CustomerDAO().login(user);
    }
}
