package team;

import customer.Customer;
import dao.AccidentDAOImpl;
import dao.EmployeeDAOImpl;
import dao.InjuryDAOImpl;
import exception.DatabaseException;
import insurance.AccidentReport;
import insurance.InjuryReport;

import java.util.List;

public class Compensation {

	private int compensation;
	private Customer customer;
	private String insuranceID;

	public boolean retrieveClaimProcessingTeam(String employeeName) throws DatabaseException {
		return new EmployeeDAOImpl().retrieveClaimProcessingTeam(employeeName);
	}

	public List<AccidentReport> retrieveAccidentList(int i) throws DatabaseException {
		return new AccidentDAOImpl().retrieveAccidentList(i);
	}

	public boolean deleteAccidentReport(AccidentReport accidentReport) throws DatabaseException {
		return new AccidentDAOImpl().deleteAccidentReport(accidentReport);
	}

	public List<InjuryReport> retrieveInjuryList(int i) throws DatabaseException {
		return new InjuryDAOImpl().retrieveInjuryList(i);
	}

	public boolean deleteInjuryReport(InjuryReport injuryReport) throws DatabaseException {
		return new InjuryDAOImpl().deleteInjuryReport(injuryReport);
	}
}