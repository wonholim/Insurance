package contract;

import customer.Customer;
import dao.ContractDAOImpl;
import dao.InsuranceDAOImpl;

public class MaturityRenewalInsurance extends PolicyRenewalDate {

    public MaturityRenewalInsurance(){

    }


    public boolean retrieveDateCarInsurance(String userName) {
        return new InsuranceDAOImpl().retrieveDateCarInsurance(userName);
    }

    public String[] retrieveCarInsuranceInformations(String userName) {
        return new ContractDAOImpl().retrieveCarInsuranceInformations(userName);
    }

    public String[] retrieveDriverInsuranceInformations(String userName) {
        return new ContractDAOImpl().retrieveDriverInsuranceInformations(userName);
    }

    public boolean retrieveDateDriverInsurance(String userName) {
        return new InsuranceDAOImpl().retrieveDateDriverInsurance(userName);
    }
}