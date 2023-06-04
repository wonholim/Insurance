package customer;

import dao.ContractDAOImpl;
import dao.InsuranceDAOImpl;
import exception.DatabaseException;

public class CancellationInsurance {

    private Customer Customer;
    private String InsuranceID;

    public CancellationInsurance(){

    }


    public boolean cancelInsurance(){
        return false;
    }

    public long retrievePenaltyFeeInsuranceCar(String userName) throws DatabaseException {
        return new InsuranceDAOImpl().retrievePenaltyFeeInsuranceCar(userName);
    }

    public boolean deleteCarInsurance(String userName) throws DatabaseException {
        return new ContractDAOImpl().deleteCarInsurance(userName);
    }
}