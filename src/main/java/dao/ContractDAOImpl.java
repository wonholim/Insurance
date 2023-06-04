package dao;

import connector.Database;
import customer.Customer;
import exception.DatabaseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ContractDAOImpl extends Database implements ContractDAO{
    public ContractDAOImpl() throws DatabaseException {super.initConnection();}

    @Override
    public boolean retrieveInsuranceProduct(String userName, String productID) throws DatabaseException {
        String query = "SELECT CustomerID FROM InsuranceProduct WHERE CustomerID = '" + userName + "' AND ProductID = '" + productID + "';";
        try (ResultSet rs = super.retrieve(query)){
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
        }
        return false;
    }

    @Override
    public boolean insertInsuranceProduct(Customer customer, String productID, int price) throws DatabaseException {
        String query = "INSERT INTO InsuranceProduct (ProductID, CustomerID, Price) VALUES ('"
                + productID + "', '" + customer.getCustomerID() + "', " + price + ")";
        return super.create(query);
    }


    @Override
    public boolean updateDateCarInsurance(String userName) throws DatabaseException {
        String query = "SELECT Number ,coverageExpirationDate FROM InsuranceProduct WHERE CustomerID = '" + userName + "' AND ProductID = '1';";
        String coverageExpirationDate = "";
        int number = -1;
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                coverageExpirationDate = rs.getString("coverageExpirationDate");
                number = rs.getInt("Number");
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
        }
        String lastUpDate = LocalDate.parse(coverageExpirationDate).plusYears(10).toString();
        query = "UPDATE InsuranceProduct SET coverageExpirationDate = '" + lastUpDate + "' WHERE Number = " + number + ";";
        return super.update(query);
    }

    @Override
    public boolean updateDateDriverInsurance(String userName) throws DatabaseException {
        String query = "SELECT Number ,coverageExpirationDate FROM InsuranceProduct WHERE CustomerID = '" + userName + "' AND ProductID = '2';";
        int number = -1;
        String coverageExpirationDate = "";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                number = rs.getInt("Number");
                coverageExpirationDate = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
        }
        String lastUpDate = LocalDate.parse(coverageExpirationDate).plusYears(20).toString();
        query = "UPDATE InsuranceProduct SET coverageExpirationDate = '" + lastUpDate + "' WHERE Number = " + number + ";";
        return super.update(query);
    }

    @Override
    public String[] retrieveCarInsuranceInformations(String userName) throws DatabaseException {
        String query = "SELECT Price, coverageExpirationDate FROM InsuranceProduct WHERE CustomerID = '" + userName + "' AND ProductID = '1';";
        String[] informations = new String[2];
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                informations[0] = "" + rs.getInt("Price");
                informations[1] = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
        }
        return informations;
    }

    @Override
    public String[] retrieveDriverInsuranceInformations(String userName) throws DatabaseException {
        String query = "SELECT Price ,coverageExpirationDate FROM InsuranceProduct WHERE CustomerID = '" + userName + "' AND ProductID = '2';";
        String[] informations = new String[2];
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                informations[0] = "" + rs.getInt("Price");
                informations[1] = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
        }
        return informations;
    }
    @Override
    public boolean deleteCarInsurance(String userName) throws DatabaseException {
        String query = "DELETE FROM InsuranceProduct WHERE CustomerID = '" + userName + "' AND ProductID = '1';";
        return super.delete(query);
    }

    @Override
    public boolean deleteDriverInsurance(String userName) throws DatabaseException {
        String query = "DELETE FROM InsuranceProduct WHERE CustomerID = '" + userName + "' AND ProductID = '2';";
        return super.delete(query);
    }
}
