package dao;

import exception.DatabaseException;
import insurance.Accident;
import insurance.AccidentReport;
import insurance.Injury;
import insurance.InjuryReport;

import java.util.List;

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
    boolean insertAccident(Accident accident) throws DatabaseException;
    boolean insertInjury(Injury injury) throws DatabaseException;
    boolean retrieveAccident(String userName) throws DatabaseException;
    boolean retrieveInjury(String userName) throws DatabaseException;
}
