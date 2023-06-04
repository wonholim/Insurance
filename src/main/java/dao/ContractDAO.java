package dao;

import customer.Customer;
import exception.DatabaseException;

public interface ContractDAO {
    boolean retrieveInsuranceProduct(String userName, String productID) throws DatabaseException;
    boolean insertInsuranceProduct(Customer customer, String productID, int price) throws DatabaseException;
    boolean updateDateCarInsurance(String userName) throws DatabaseException;
    boolean updateDateDriverInsurance(String userName) throws DatabaseException;
    boolean deleteCarInsurance(String userName) throws DatabaseException;

    boolean deleteDriverInsurance(String userName) throws DatabaseException;
    String[] retrieveCarInsuranceInformations(String userName) throws DatabaseException;
    String[] retrieveDriverInsuranceInformations(String userName) throws DatabaseException;
}
