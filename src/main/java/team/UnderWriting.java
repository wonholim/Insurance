package team;

import dao.EmployeeDAOImpl;
import exception.DatabaseException;
import insurance.Car;
import insurance.Driver;

import java.util.List;

public class UnderWriting {
    public boolean retrieveEmployeeUnderWriting(String employeeName) throws DatabaseException {
        return new EmployeeDAOImpl().retrieveEmployeeUnderWriting(employeeName);
    }

    public List<Car> retrieveCarUnderWritingList() throws DatabaseException {
        return new EmployeeDAOImpl().retrieveCarUnderWritingList();
    }

    public boolean updateCarUnderWriting(Car car, String employeeName) throws DatabaseException {
        return new EmployeeDAOImpl().updateCarUnderWriting(car, employeeName);
    }

    public List<Driver> retrieveDriverUnderWritingList() throws DatabaseException {
        return new EmployeeDAOImpl().retrieveDriverUnderWritingList();
    }

    public boolean updateDriverUnderWriting(Driver driver, String employeeName) throws DatabaseException {
        return new EmployeeDAOImpl().updateDriverUnderWriting(driver, employeeName);
    }
}
