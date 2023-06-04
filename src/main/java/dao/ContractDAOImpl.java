package dao;

import connector.Database;
import customer.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ContractDAOImpl extends Database implements ContractDAO{
    public ContractDAOImpl() {super.initConnection();}
    @Override
    public boolean insertCarInsuranceProduct(Customer customer, String productID, int price) {
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

    @Override
    public boolean insertDriverInsuranceProduct(Customer customer, String productID, int price) {
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

    @Override
    public boolean updateDateCarInsurance(String userName) {
        String query = "SELECT coverageExpirationDate FROM CarInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String coverageExpirationDate = "";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                coverageExpirationDate = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String lastUpDate = LocalDate.parse(coverageExpirationDate).plusYears(10).toString();
        query = "UPDATE CarInsuranceProduct SET coverageExpirationDate = '" + lastUpDate + "' WHERE CustomerID = '" + userName + "';";
        return super.update(query);
    }

    @Override
    public boolean updateDateDriverInsurance(String userName) {
        String query = "SELECT coverageExpirationDate FROM DriverInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String coverageExpirationDate = "";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                coverageExpirationDate = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String lastUpDate = LocalDate.parse(coverageExpirationDate).plusYears(20).toString();
        query = "UPDATE DriverInsuranceProduct SET coverageExpirationDate = '" + lastUpDate + "' WHERE CustomerID = '" + userName + "';";
        return super.update(query);
    }

    @Override
    public String[] retrieveCarInsuranceInformations(String userName) {
        String query = "SELECT Price ,coverageExpirationDate FROM CarInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String[] informations = new String[2];
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                informations[0] = "" + rs.getInt("Price");
                informations[1] = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informations;
    }

    @Override
    public String[] retrieveDriverInsuranceInformations(String userName) {
        String query = "SELECT Price ,coverageExpirationDate FROM DriverInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String[] informations = new String[2];
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                informations[0] = "" + rs.getInt("Price");
                informations[1] = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informations;
    }
    @Override
    public boolean deleteCarInsurance(String userName) {
        String query = "DELETE FROM carInsuranceProduct WHERE CustomerID = '" + userName + "';";
        if(super.delete(query)) return true;
        return false;
    }

    @Override
    public boolean deleteDriverInsurance(String userName) {
        String query = "DELETE FROM DriverInsuranceProduct WHERE CustomerID = '" + userName + "';";
        if(super.delete(query)) return true;
        return false;
    }
}
