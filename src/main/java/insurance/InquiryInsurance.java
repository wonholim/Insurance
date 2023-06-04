package insurance;

import dao.InsuranceDAOImpl;
import exception.DatabaseException;

public class InquiryInsurance {

    public String retriveAllInsuranceProduct(String userName) throws DatabaseException {
        return new InsuranceDAOImpl().retriveAllInsuranceProduct(userName);
    }
}