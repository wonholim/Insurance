package dao;

import customer.Customer;
import insurance.Car;
import insurance.Driver;
import team.Team;

import java.util.List;

public interface EmployeeDAO {
    boolean insertEmployee(Team employee);
    boolean retrieveEmployee(String[] employee);
    boolean insertInsuranceProduct(Customer customer, String productID, int price);

    boolean retrieveEmployeeUnderWriting(String employeeName);
    List<Car> retrieveCarUnderWritingList();
    List<Driver> retrieveDriverUnderWritingList();
    boolean deleteCarUnderWriting(Car car);
    boolean deleteDriverUnderWriting(Driver driver);
    boolean updateCarUnderWriting(Car car);
    boolean updateDriverUnderWriting(Driver driver);

    boolean retrieveAccidentTeam(String employeeName);
    boolean retrieveLossEvaluationTeam(String employeeName);
    boolean retrieveClaimProcessingTeam(String employeeName);
}
