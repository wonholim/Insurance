package contract;

import customer.Customer;
import dao.ContractDAOImpl;
import dao.InsuranceDAOImpl;
import exception.DatabaseException;

public class MaturityRenewalInsurance extends PolicyRenewalDate {

    public MaturityRenewalInsurance(){

    }


    public boolean retrieveDateCarInsurance(String userName) throws DatabaseException {
        return new InsuranceDAOImpl().retrieveDateCarInsurance(userName);
    }

    public String[] retrieveCarInsuranceInformations(String userName) throws DatabaseException {
        return new ContractDAOImpl().retrieveCarInsuranceInformations(userName);
    }

    public String[] retrieveDriverInsuranceInformations(String userName) throws DatabaseException {
        return new ContractDAOImpl().retrieveDriverInsuranceInformations(userName);
    }

    public boolean retrieveDateDriverInsurance(String userName) throws DatabaseException {
        return new InsuranceDAOImpl().retrieveDateDriverInsurance(userName);
    }
}