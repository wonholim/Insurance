package team;

import customer.Customer;
import dao.AccidentDAOImpl;
import dao.EmployeeDAOImpl;
import dao.InjuryDAOImpl;
import insurance.AccidentReport;
import insurance.InjuryReport;

import java.util.List;

public class Compensation {

	private int compensation;
	private Customer customer;
	private String insuranceID;

	public boolean retrieveClaimProcessingTeam(String employeeName) {
		return new EmployeeDAOImpl().retrieveClaimProcessingTeam(employeeName);
	}

	public List<AccidentReport> retrieveAccidentList(int i) {
		return new AccidentDAOImpl().retrieveAccidentList(i);
	}

	public boolean deleteAccidentReport(AccidentReport accidentReport) {
		return new AccidentDAOImpl().deleteAccidentReport(accidentReport);
	}

	public List<InjuryReport> retrieveInjuryList(int i) {
		return new InjuryDAOImpl().retrieveInjuryList(i);
	}

	public boolean deleteInjuryReport(InjuryReport injuryReport) {
		return new InjuryDAOImpl().deleteInjuryReport(injuryReport);
	}
}