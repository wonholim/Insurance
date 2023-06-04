package dao;

import customer.Customer;

public interface ContractDAO {
    boolean insertCarInsuranceProduct(Customer customer, String productID, int price);
    boolean insertDriverInsuranceProduct(Customer customer, String productID, int price);
    boolean updateDateCarInsurance(String userName);
    boolean updateDateDriverInsurance(String userName);
    boolean deleteCarInsurance(String userName);

    boolean deleteDriverInsurance(String userName);
    String[] retrieveCarInsuranceInformations(String userName);
    String[] retrieveDriverInsuranceInformations(String userName);
}
