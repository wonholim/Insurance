package controller;

import dao.EmployeeDAO;
import domain.customer.Customer;
import domain.insurance.Car;
import domain.insurance.Driver;
import domain.team.Team;

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
}
