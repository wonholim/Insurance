package dao;

import customer.Customer;
import exception.DatabaseException;
import insurance.Car;
import insurance.Driver;
import team.Team;

import java.util.List;

public interface EmployeeDAO {
    boolean insertEmployee(Team employee) throws DatabaseException;
    boolean retrieveEmployee(String[] employee) throws DatabaseException;
    boolean insertInsuranceProduct(Customer customer, String productID, int price) throws DatabaseException;

    boolean retrieveEmployeeUnderWriting(String employeeName);
    List<Car> retrieveCarUnderWritingList() throws DatabaseException;
    List<Driver> retrieveDriverUnderWritingList() throws DatabaseException;
    boolean deleteCarUnderWriting(Car car) throws DatabaseException;
    boolean deleteDriverUnderWriting(Driver driver) throws DatabaseException;
    boolean updateCarUnderWriting(Car car) throws DatabaseException;
    boolean updateDriverUnderWriting(Driver driver) throws DatabaseException;

    boolean retrieveAccidentTeam(String employeeName);
    boolean retrieveLossEvaluationTeam(String employeeName);
    boolean retrieveClaimProcessingTeam(String employeeName);
}
