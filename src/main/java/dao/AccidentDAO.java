package dao;

import exception.DatabaseException;
import insurance.Accident;
import insurance.AccidentReport;

import java.util.List;

public interface AccidentDAO {
    List<Accident> retrieveAccidentList() throws DatabaseException;
    boolean updateAccidentInsurance(Accident accident) throws DatabaseException;
    boolean deleteAccidentInsurance(Accident accident) throws DatabaseException;
    boolean updateAccidentReport(AccidentReport accidentReport, String employeeName, int i) throws DatabaseException;
    List<AccidentReport> retrieveAccidentList(int i) throws DatabaseException;
    boolean deleteAccidentReport(AccidentReport accidentReport) throws DatabaseException;
}
