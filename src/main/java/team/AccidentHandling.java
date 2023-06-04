package team;

import dao.AccidentDAOImpl;
import dao.EmployeeDAOImpl;
import dao.InjuryDAOImpl;
import insurance.Accident;
import insurance.Injury;

import java.util.List;

public class AccidentHandling {

	public boolean deleteAccidentInsurance(Accident accident){
		return new AccidentDAOImpl().deleteAccidentInsurance(accident);
	}

	public boolean updateAccidentInsurance(Accident accident) {
		return new AccidentDAOImpl().updateAccidentInsurance(accident);
	}

	public List<Accident> retrieveAccidentList() {
		return new AccidentDAOImpl().retrieveAccidentList();
	}

	public List<Injury> retrieveInjuryList() {
		return new InjuryDAOImpl().retrieveInjuryList();
	}

	public boolean deleteInjuryInsurance(Injury injury) {
		return new InjuryDAOImpl().deleteInjuryInsurance(injury);
	}

	public boolean updateInjuryInsurance(Injury injury) {
		return new InjuryDAOImpl().updateInjuryInsurance(injury);
	}

	public boolean retrieveAccidentTeam(String employeeName) {
		return new EmployeeDAOImpl().retrieveAccidentTeam(employeeName);
	}
}