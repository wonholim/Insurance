package dao;

import customer.Customer;

public interface CustomerDAO {
    boolean insertUser(Customer customer);
    boolean retrieveUser(String[] user);
    Customer retrieveUserData(String userName);
}
