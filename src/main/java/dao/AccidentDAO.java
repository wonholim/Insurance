package dao;

import insurance.Accident;
import insurance.AccidentReport;

import java.util.List;

public interface AccidentDAO {
    List<Accident> retrieveAccidentList();
    boolean updateAccidentInsurance(Accident accident);
    boolean deleteAccidentInsurance(Accident accident);
    boolean updateAccidentReport(AccidentReport accidentReport, String employeeName, int i);
    List<AccidentReport> retrieveAccidentList(int i);
    boolean deleteAccidentReport(AccidentReport accidentReport);
}
