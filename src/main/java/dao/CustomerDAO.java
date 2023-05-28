package dao;

import connector.Database;
import domain.customer.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO extends Database {

    public CustomerDAO() {
        super.initConnection();
    }

    // 고객이 보험회사에 가입을 한다.
    public boolean register(Customer customer) {
        String query = "INSERT INTO Customer values ('"
                        + customer.getName() + "', " + customer.getAge() + ", " + customer.getSex() + ", '" + customer.getRegistraionNumber() + "', '"
                        + customer.getPhoneNum() + "', '" + customer.getDriverLicense() + "', '" + customer.getCarModel() + "', '"
                        + customer.getCarNum() + "', '" + customer.getCarModelYear() + "', '" + customer.getCustomerID() + "', '" + customer.getPassword() + "');";
        if(super.create(query)) return true;
        return false;
    }

    // 고객이 로그인을 한다.
    public boolean login(String[] user) {
        String query = "SELECT CustomerID, CustomerPassword FROM Customer WHERE CustomerID = ? AND CustomerPassword = ?";
        if(super.authentication(query, user)) return true;
        return false;
    }


    // 고객이 조회된 보험에 가입을 신청한다.
    public boolean registerCarInsuranceProduct(Customer customer, String productID, int price) {
        String query = "SELECT CustomerID FROM TmpCarInsuranceProduct WHERE CustomerID = ? ";
        if(super.isRegister(query, customer.getCustomerID())) return false;
        query = "SELECT CustomerID FROM CarInsuranceProduct WHERE CustomerID = ? ";
        if(super.isRegister(query, customer.getCustomerID())) return false;
        query = "INSERT INTO TmpCarInsuranceProduct (ProductID, CustomerID, CustomerName, PhoneNum, DriverLicense, CarModel, CarNum, Price) VALUES ('"
                + productID + "', '" + customer.getCustomerID() + "', '" + customer.getName() + "', '"
                + customer.getPhoneNum() + "', '" + customer.getDriverLicense() + "', '" + customer.getCarModel() + "', '"
                + customer.getCarNum() + "', " + price + ")";
        if(super.create(query)) return true;
        return false;
    }

    public Customer getUserData(String userName) {
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
            e.printStackTrace();
        }
        return null;
    }

    public boolean registerDriverInsuranceProduct(Customer customer, String productID, int price) {
        String query = "SELECT CustomerID FROM TmpDriverInsuranceProduct WHERE CustomerID = ? ";
        if(super.isRegister(query, customer.getCustomerID())) return false;
        query = "SELECT CustomerID FROM DriverInsuranceProduct WHERE CustomerID = ? ";
        if(super.isRegister(query, customer.getCustomerID())) return false;
        query = "INSERT INTO TmpDriverInsuranceProduct (ProductID, CustomerID, CustomerName, PhoneNum, DriverLicense, Price) VALUES ('"
                + productID + "', '" + customer.getCustomerID() + "', '" + customer.getName() + "', '"
                + customer.getPhoneNum() + "', '" + customer.getDriverLicense() + "', " + price + ")";
        if(super.create(query)) return true;
        return false;
    }

    public String getAllInsuranceProduct(String userName) {
        String line = "";
        String query = "SELECT * FROM CarInsuranceProduct WHERE CustomerID = '" + userName + "';";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                line += " 자동차 보험 ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query = "SELECT * FROM DriverInsuranceProduct WHERE CustomerID = '" + userName + "';";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                line += " 운전자 보험 ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return line;
    }
}
