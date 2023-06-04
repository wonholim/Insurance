package dao;

import exception.DatabaseException;
import insurance.Accident;
import insurance.AccidentReport;
import insurance.Injury;
import insurance.InjuryReport;

public interface InsuranceDAO {
    String retriveAllInsuranceProduct(String userName) throws DatabaseException;
    long retrievePenaltyFeeInsuranceCar(String userName) throws DatabaseException;
    long retrievePenaltyFeeInsuranceDriver(String userName) throws DatabaseException;
    boolean retrieveDateCarInsurance(String userName) throws DatabaseException;
    boolean retrieveDateDriverInsurance(String userName) throws DatabaseException;
    boolean retrieveAccidentInsurance(String userName) throws DatabaseException;
    boolean retrieveInjuryInsurance(String userName) throws DatabaseException;
    boolean retrieveAccidentCompensation(AccidentReport accidentReport) throws DatabaseException;
    boolean retrieveInjuryCompensation(InjuryReport injuryReports) throws DatabaseException;
    boolean insertAccident(Accident accident, String userName) throws DatabaseException;
    boolean insertInjury(Injury injury, String userName) throws DatabaseException;
    boolean retrieveAccident(String userName) throws DatabaseException;
    boolean retrieveInjury(String userName) throws DatabaseException;
}
