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

    public boolean deleteCarUnderWriting(Car car) throws DatabaseException {
        return new EmployeeDAOImpl().deleteCarUnderWriting(car);
    }

    public boolean updateCarUnderWriting(Car car) throws DatabaseException {
        return new EmployeeDAOImpl().updateCarUnderWriting(car);
    }

    public List<Driver> retrieveDriverUnderWritingList() throws DatabaseException {
        return new EmployeeDAOImpl().retrieveDriverUnderWritingList();
    }

    public boolean deleteDriverUnderWriting(Driver driver) throws DatabaseException {
        return new EmployeeDAOImpl().deleteDriverUnderWriting(driver);
    }

    public boolean updateDriverUnderWriting(Driver driver) throws DatabaseException {
        return new EmployeeDAOImpl().updateDriverUnderWriting(driver);
    }
}
