package dao;

import exception.DatabaseException;
import insurance.Injury;
import insurance.InjuryReport;

import java.util.List;

public interface InjuryDAO {
    List<Injury> retrieveInjuryList() throws DatabaseException;
    boolean deleteInjuryInsurance(Injury injury) throws DatabaseException;
    boolean updateInjuryInsurance(Injury injury) throws DatabaseException;
    List<InjuryReport> retrieveInjuryList(int i) throws DatabaseException;
    boolean updateInjuryReport(InjuryReport injuryReport, String employeeName, int i) throws DatabaseException;
    boolean deleteInjuryReport(InjuryReport injuryReport) throws DatabaseException;
}
