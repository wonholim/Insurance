package dao;

import customer.Customer;
import exception.DatabaseException;

public interface CustomerDAO {
    boolean insertUser(Customer customer) throws DatabaseException;
    boolean retrieveUser(String[] user) throws DatabaseException;
    Customer retrieveUserData(String userName) throws DatabaseException;
}
