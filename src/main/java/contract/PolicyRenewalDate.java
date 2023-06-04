package contract;

import customer.Customer;
import dao.ContractDAOImpl;

public class PolicyRenewalDate {

    private Customer customer;
    private String InsuranceID;

    public PolicyRenewalDate(){

    }


    public boolean updateDateCarInsurance(String userName) {
        return new ContractDAOImpl().updateDateCarInsurance(userName);
    }

    public boolean updateDateDriverInsurance(String userName) {
        return new ContractDAOImpl().updateDateDriverInsurance(userName);
    }
}