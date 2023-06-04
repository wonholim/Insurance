package team;

import customer.Customer;
import dao.AccidentDAOImpl;
import dao.EmployeeDAOImpl;
import dao.InjuryDAOImpl;
import insurance.AccidentReport;
import insurance.InjuryReport;

import java.util.List;

public class LossAssessment {

    private int compensationAmount;
    private Customer customer;
    private int faultPercentage;


    public boolean retrieveLossEvaluationTeam(String employeeName) {
        return new EmployeeDAOImpl().retrieveLossEvaluationTeam(employeeName);
    }

    public List<AccidentReport> retrieveAccidentList(int i) {
        return new AccidentDAOImpl().retrieveAccidentList(i);
    }

    public boolean updateAccidentReport(AccidentReport accidentReport, String employeeName, int i) {
        return new AccidentDAOImpl().updateAccidentReport(accidentReport, employeeName, i);
    }

    public List<InjuryReport> retrieveInjuryList(int i) {
        return new InjuryDAOImpl().retrieveInjuryList(i);
    }

    public boolean updateInjuryReport(InjuryReport injuryReport, String employeeName, int i) {
        return new InjuryDAOImpl().updateInjuryReport(injuryReport, employeeName, i);
    }
}