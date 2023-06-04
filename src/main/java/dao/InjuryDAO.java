package dao;

import insurance.Injury;
import insurance.InjuryReport;

import java.util.List;

public interface InjuryDAO {
    List<Injury> retrieveInjuryList();
    boolean deleteInjuryInsurance(Injury injury);
    boolean updateInjuryInsurance(Injury injury);
    List<InjuryReport> retrieveInjuryList(int i);
    boolean updateInjuryReport(InjuryReport injuryReport, String employeeName, int i);
    boolean deleteInjuryReport(InjuryReport injuryReport);
}
