package team;

import dao.AccidentDAOImpl;
import dao.EmployeeDAOImpl;
import dao.InjuryDAOImpl;
import exception.DatabaseException;
import insurance.Accident;
import insurance.Injury;

import java.util.List;

public class AccidentHandling {

	public boolean deleteAccidentInsurance(Accident accident) throws DatabaseException {
		return new AccidentDAOImpl().deleteAccidentInsurance(accident);
	}

	public boolean updateAccidentInsurance(Accident accident) throws DatabaseException {
		return new AccidentDAOImpl().updateAccidentInsurance(accident);
	}

	public List<Accident> retrieveAccidentList() throws DatabaseException {
		return new AccidentDAOImpl().retrieveAccidentList();
	}

	public List<Injury> retrieveInjuryList() throws DatabaseException {
		return new InjuryDAOImpl().retrieveInjuryList();
	}

	public boolean deleteInjuryInsurance(Injury injury) throws DatabaseException {
		return new InjuryDAOImpl().deleteInjuryInsurance(injury);
	}

	public boolean updateInjuryInsurance(Injury injury) throws DatabaseException {
		return new InjuryDAOImpl().updateInjuryInsurance(injury);
	}

	public boolean retrieveAccidentTeam(String employeeName) throws DatabaseException {
		return new EmployeeDAOImpl().retrieveAccidentTeam(employeeName);
	}
}