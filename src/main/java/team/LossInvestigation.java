package team;

import customer.Customer;
import dao.AccidentDAOImpl;
import dao.EmployeeDAOImpl;
import dao.InjuryDAOImpl;
import exception.DatabaseException;
import insurance.AccidentReport;
import insurance.InjuryReport;
import team.LossAssessment;

import java.util.List;

public class LossInvestigation {

    private int compensationAmount;
    private Customer customer;
    private int faultPercentage;


    public boolean retrieveLossEvaluationTeam(String employeeName) throws DatabaseException {
        return new EmployeeDAOImpl().retrieveLossEvaluationTeam(employeeName);
    }

    public List<AccidentReport> retrieveAccidentList(int i) throws DatabaseException {
        return new AccidentDAOImpl().retrieveAccidentList(i);
    }

    public boolean updateAccidentReport(AccidentReport accidentReport, String employeeName, int i) throws DatabaseException {
        return new AccidentDAOImpl().updateAccidentReport(accidentReport, employeeName, i);
    }

    public List<InjuryReport> retrieveInjuryList(int i) throws DatabaseException {
        return new InjuryDAOImpl().retrieveInjuryList(i);
    }

    public boolean updateInjuryReport(InjuryReport injuryReport, String employeeName, int i) throws DatabaseException {
        return new InjuryDAOImpl().updateInjuryReport(injuryReport, employeeName, i);
    }
}