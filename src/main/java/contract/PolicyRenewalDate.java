package contract;

import customer.Customer;
import dao.ContractDAOImpl;
import exception.DatabaseException;

public class PolicyRenewalDate {

    private Customer customer;
    private String InsuranceID;

    public PolicyRenewalDate(){

    }


    public boolean updateDateCarInsurance(String userName) throws DatabaseException {
        return new ContractDAOImpl().updateDateCarInsurance(userName);
    }

    public boolean updateDateDriverInsurance(String userName) throws DatabaseException {
        return new ContractDAOImpl().updateDateDriverInsurance(userName);
    }
}