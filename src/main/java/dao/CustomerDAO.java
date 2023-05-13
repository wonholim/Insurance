package dao;

import connector.Database;
import domain.customer.Customer;

public class CustomerDAO extends Database {

    public CustomerDAO() {
        super.initConnection();
    }
    public boolean register(Customer customer) {
        String query = "INSERT INTO Customer values ('"
                        + customer.getName() + "', " + customer.getAge() + ", " + customer.getSex() + ", '" + customer.getRegistraionNumber() + "', '"
                        + customer.getPhoneNum() + "', '" + customer.getDriverLicense() + "', '" + customer.getCarModel() + "', '"
                        + customer.getCarNum() + "', '" + customer.getCarModelYear() + "', '" + customer.getCustomerID() + "', '" + customer.getPassword() + "');";
        if(super.create(query)) return true;
        return false;
    }

    public boolean login(String[] user) {
        String query = "SELECT CustomerID, CustomerPassword FROM Customer WHERE CustomerID = ? AND CustomerPassword = ?";
        if(super.authentication(query, user)) return true;
        return false;
    }
}
