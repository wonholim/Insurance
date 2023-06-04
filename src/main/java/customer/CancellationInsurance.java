package customer;

import dao.ContractDAOImpl;
import dao.InsuranceDAOImpl;

public class CancellationInsurance {

    private Customer Customer;
    private String InsuranceID;

    public CancellationInsurance(){

    }


    public boolean cancelInsurance(){
        return false;
    }

    public long retrievePenaltyFeeInsuranceCar(String userName) {
        return new InsuranceDAOImpl().retrievePenaltyFeeInsuranceCar(userName);
    }

    public boolean deleteCarInsurance(String userName) {
        return new ContractDAOImpl().deleteCarInsurance(userName);
    }
}