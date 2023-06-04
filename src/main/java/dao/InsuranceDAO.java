package dao;

import insurance.Accident;
import insurance.AccidentReport;
import insurance.Injury;
import insurance.InjuryReport;

import java.util.List;

public interface InsuranceDAO {
    String retriveAllInsuranceProduct(String userName);
    long retrievePenaltyFeeInsuranceCar(String userName);
    long retrievePenaltyFeeInsuranceDriver(String userName);
    boolean retrieveDateCarInsurance(String userName);
    boolean retrieveDateDriverInsurance(String userName);
    boolean retrieveAccidentInsurance(String userName);
    boolean retrieveInjuryInsurance(String userName);
    boolean retrieveAccidentCompensation(AccidentReport accidentReport);
    boolean retrieveInjuryCompensation(InjuryReport injuryReports);
    boolean insertAccident(Accident accident);
    boolean insertInjury(Injury injury);
    boolean retrieveAccident(String userName);
    boolean retrieveInjury(String userName);
}
