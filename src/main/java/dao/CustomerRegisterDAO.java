package dao;

import connector.Database;
import customer.Customer;

public class CustomerRegisterDAO extends Database {

    public CustomerRegisterDAO() {
        super.initConnection();
    }
    public boolean register(Customer customer) {
        String query = "INSERT INTO Customer values ('"
                        + customer.getName() + "', " + customer.getAge() + ", '" + customer.getRegistraionNumber() + "', '"
                        + customer.getPhoneNum() + "', '" + customer.getDriverLicense() + "', '" + customer.getCarModel() + "', '"
                        + customer.getCarNum() + "', '" + customer.getCustomerID() + "', '" + customer.getPassword() + "');";
        if(super.create(query)) return true;
        return false;
    }
}
