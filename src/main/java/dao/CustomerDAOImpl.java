package dao;

import connector.Database;
import customer.Customer;
import exception.DatabaseException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl extends Database implements CustomerDAO {

    public CustomerDAOImpl() throws DatabaseException {
        super.initConnection();
    }

    @Override
    public boolean insertUser(Customer customer) throws DatabaseException {
        String query = "INSERT INTO Customer values ('"
                        + customer.getName() + "', " + customer.getAge() + ", " + customer.getSex() + ", '" + customer.getRegistraionNumber() + "', '"
                        + customer.getPhoneNum() + "', '" + customer.getDriverLicense() + "', '" + customer.getCarModel() + "', '"
                        + customer.getCarNum() + "', '" + customer.getCarModelYear() + "', '" + customer.getCustomerID() + "', '" + customer.getPassword() + "');";
        return super.create(query);

    }

    @Override
    public boolean retrieveUser(String[] user) throws DatabaseException {
        String query = "SELECT CustomerID, CustomerPassword FROM Customer WHERE CustomerID = '" + user[0] + "' AND CustomerPassword = '" + user[1] + "';";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
        }
        return false;
    }
    @Override
    public Customer retrieveUserData(String userName) throws DatabaseException {
        String query = "SELECT * FROM Customer WHERE CustomerID = '" + userName + "';";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                Customer customer = new Customer();
                customer.setAge(rs.getInt("Age"));
                customer.setCarModel(rs.getString("CarModel"));
                customer.setCarModelYear(rs.getString("CarModelYear"));
                customer.setCarNum(rs.getString("CarNum"));
                customer.setCustomerID(rs.getString("CustomerID"));
                customer.setDriverLicense(rs.getString("DriverLicense"));
                customer.setName(rs.getString("CustomerName"));
                customer.setPhoneNum(rs.getString("PhoneNum"));
                customer.setRegistraionNumber(rs.getString("RegistrationNumber"));
                customer.setPassword(rs.getString("CustomerPassword"));
                customer.setSex(rs.getInt("Sex"));
                return customer;
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
        }
        return null;
    }

}
