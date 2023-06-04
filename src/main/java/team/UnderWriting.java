package team;

import dao.EmployeeDAOImpl;
import insurance.Car;
import insurance.Driver;

import java.util.List;

public class UnderWriting {
    public boolean retrieveEmployeeUnderWriting(String employeeName) {
        return new EmployeeDAOImpl().retrieveEmployeeUnderWriting(employeeName);
    }

    public List<Car> retrieveCarUnderWritingList() {
        return new EmployeeDAOImpl().retrieveCarUnderWritingList();
    }

    public boolean deleteCarUnderWriting(Car car) {
        return new EmployeeDAOImpl().deleteCarUnderWriting(car);
    }

    public boolean updateCarUnderWriting(Car car) {
        return new EmployeeDAOImpl().updateCarUnderWriting(car);
    }

    public List<Driver> retrieveDriverUnderWritingList() {
        return new EmployeeDAOImpl().retrieveDriverUnderWritingList();
    }

    public boolean deleteDriverUnderWriting(Driver driver) {
        return new EmployeeDAOImpl().deleteDriverUnderWriting(driver);
    }

    public boolean updateDriverUnderWriting(Driver driver) {
        return new EmployeeDAOImpl().updateDriverUnderWriting(driver);
    }
}
