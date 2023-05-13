package controller;

import customer.Customer;
import dao.CustomerRegisterDAO;

public class CustomerRegisterController {
    public boolean register(Customer customer) {
        CustomerRegisterDAO customerRegisterDAO = new CustomerRegisterDAO();
        return customerRegisterDAO.register(customer);
    }
}
