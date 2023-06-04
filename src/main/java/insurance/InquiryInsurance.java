package insurance;

import dao.InsuranceDAOImpl;

public class InquiryInsurance {

    public String retriveAllInsuranceProduct(String userName) {
        return new InsuranceDAOImpl().retriveAllInsuranceProduct(userName);
    }
}