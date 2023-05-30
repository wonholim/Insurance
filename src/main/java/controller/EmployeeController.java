package controller;

import dao.EmployeeDAO;
import insurance.*;
import team.Team;


import java.util.List;

public class EmployeeController {
    public boolean login(String[] employee) {
        return new EmployeeDAO().login(employee);
    }

    public boolean register(Team team) {
        return new EmployeeDAO().register(team);
    }

    public boolean checkUnderWriting(String employeeName) {
        return new EmployeeDAO().checkUnderWriting(employeeName);
    }

    public List<Car> customersCarUnderWriting() {
        return new EmployeeDAO().customersCarUnderWriting();
    }
    public List<Driver> customersDriverUnderWriting() {
        return new EmployeeDAO().customersDriverUnderWriting();
    }

    public boolean customersCarUnderWritingDelete(Car car) {
        return new EmployeeDAO().customersCarUnderWritingDelete(car);
    }

    public boolean customersCarUnderWritingUpdate(Car car) {
        return new EmployeeDAO().customersCarUnderWritingUpdate(car);
    }

    public boolean customersDriverUnderWritingDelete(Driver driver) {
        return new EmployeeDAO().customersDriverUnderWritingDelete(driver);
    }

    public boolean customersDriverUnderWritingUpdate(Driver driver) {
        return new EmployeeDAO().customersDriverUnderWritingUpate(driver);
    }

    public boolean checkAccidentTeam(String employeeName) {
        return new EmployeeDAO().checkAccidentTeam(employeeName);
    }

    public List<Accident> getTmpAccidentList() {
        return new EmployeeDAO().getTmpAccidentList();
    }

    public boolean tmpAccidentInsuranceDelete(Accident accident) {
        return new EmployeeDAO().tmpAccidentInsuranceDelete(accident);
    }

    public boolean accidentInsuranceUpdate(Accident accident) {
        return new EmployeeDAO().accidentInsuranceUpdate(accident);
    }

    public List<Injury> getTmpInjuryList() {
        return new EmployeeDAO().getTmpInjuryList();
    }

    public boolean tmpInjuryInsuranceDelete(Injury injury) {
        return new EmployeeDAO().tmpInjuryInsuranceDelete(injury);
    }

    public boolean InjuryInsuranceUpdate(Injury injury) {
        return new EmployeeDAO().InjuryInsuranceUpdate(injury);
    }

    public boolean checkLossEvaluationTeam(String employeeName) {
        return new EmployeeDAO().checkLossEvaluationTeam(employeeName);
    }

    public boolean accidentReportUpdate(AccidentReport accidentReport, String employeeName) {
        return new EmployeeDAO().accidentReportUpdate(accidentReport, employeeName);
    }

    public List<AccidentReport> getAccidentList(int i) {
        return new EmployeeDAO().getAccidentList(i);
    }

    public boolean checkClaimProcessingTeam(String employeeName) {
        return new EmployeeDAO().checkClaimProcessingTeam(employeeName);
    }

    public boolean accidentReportDelete(AccidentReport accidentReport) {
        return new EmployeeDAO().accidentReportDelete(accidentReport);
    }

    public List<InjuryReport> getInjuryList(int i) {
        return new EmployeeDAO().getInjuryList(i);
    }

    public boolean injuryReportUpdate(InjuryReport injuryReport, String employeeName) {
        return new EmployeeDAO().injuryReportUpdate(injuryReport, employeeName);
    }

    public boolean injuryReportDelete(InjuryReport injuryReport) {
        return new EmployeeDAO().injuryReportDelete(injuryReport);
    }
}
