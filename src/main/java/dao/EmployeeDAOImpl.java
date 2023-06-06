package dao;

import connector.Database;
import customer.Customer;
import exception.DatabaseException;
import insurance.*;
import team.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl extends Database implements EmployeeDAO {
    public EmployeeDAOImpl() throws DatabaseException {
        super.initConnection();
    }

    @Override
    public boolean insertEmployee(Team employee) throws DatabaseException {
        String query = "INSERT INTO Employee values ('"
                + employee.getName() + "', " + employee.getAge() + ", '" + employee.getRegistraionNumber() + "', '"
                + employee.getPhoneNum() + "', '" + employee.getID() + "', '" + employee.getPassword() + "', "
                + employee.getEmployeeRank() + ", " + employee.getTeamNumber() + ");";
        return super.create(query);
    }
    @Override
    public boolean retrieveEmployee(String[] employee) throws DatabaseException {
        String query = "SELECT EmployeeID, EmployeePassword FROM Employee WHERE EmployeeID = '" + employee[0] + "' AND EmployeePassword = '" + employee[1] + "';";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
        }
        return false;
    }
    @Override
    public boolean insertInsuranceProduct(Customer customer, String productID, int price) throws DatabaseException {
        String query = "SELECT CustomerID FROM TmpCarInsuranceProduct WHERE CustomerID = ? ";
        if(super.retrieveCustomer(query, customer.getCustomerID())) return false;
        query = "SELECT CustomerID FROM CarInsuranceProduct WHERE CustomerID = ? ";
        if(super.retrieveCustomer(query, customer.getCustomerID())) return false;
        query = "INSERT INTO TmpCarInsuranceProduct (ProductID, CustomerID, CustomerName, PhoneNum, DriverLicense, CarModel, CarNum, Price) VALUES ('"
                + productID + "', '" + customer.getCustomerID() + "', '" + customer.getName() + "', '"
                + customer.getPhoneNum() + "', '" + customer.getDriverLicense() + "', '" + customer.getCarModel() + "', '"
                + customer.getCarNum() + "', " + price + ")";
        return super.create(query);
    }
    @Override
    public boolean retrieveEmployeeUnderWriting(String employeeName) {
        String query = "SELECT EmployeeID, EmployeePassword FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        if(super.retrieveTeam(query, new String[]{employeeName, "3"})) return true;
        return false;
    }
    @Override
    public List<Car> retrieveCarUnderWritingList() throws DatabaseException {
            String query = "SELECT * FROM Customer " +
                    "JOIN InsuranceProduct " +
                    "ON Customer.CustomerID = InsuranceProduct.CustomerID " +
                    "WHERE InsuranceProduct.ProductID = '1' AND InsuranceProduct.EmployeeUnderWriting IS NULL " +
                    "ORDER BY InsuranceProduct.Number";
            try (ResultSet rs = super.retrieve(query)) {
                List<Car> carList = new ArrayList<>();
                while(rs.next()) {
                    Car car = new Car(null);
                    car.setNumber(rs.getInt("Number"));
                    car.setProductID(rs.getString("ProductID"));
                    car.setCustomerID(rs.getString("CustomerID"));
                    car.setCustomerName(rs.getString("CustomerName"));
                    car.setPhoneNum(rs.getString("PhoneNum"));
                    car.setDriverLicense(rs.getString("DriverLicense"));
                    car.setCarModel(rs.getString("CarModel"));
                    car.setCarNum(rs.getString("CarNum"));
                    car.setPrice(rs.getInt("Price"));
                    carList.add(car);
                }
                return carList;
            } catch (SQLException e) {
                throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
            }
    }
    @Override
    public boolean deleteCarUnderWriting(Car car) throws DatabaseException {
        String query = "DELETE FROM TmpCarInsuranceProduct WHERE CustomerID = '" + car.getCustomerID() + "';";
        return super.delete(query);
    }
    @Override
    public boolean updateCarUnderWriting(Car car, String employeeName) throws DatabaseException {
        String query = "SELECT EmployeeID FROM Employee WHERE TeamNum = 2 ORDER BY RAND() LIMIT 2";
        List<String> teamID = new ArrayList<>();
        try (ResultSet rs = super.retrieve(query)) {
            while (rs.next()) {
                teamID.add(rs.getString("EmployeeID"));
            }
        } catch (SQLException e) {
            throw new DatabaseException("직원이 2명이상 존재하지 않습니다.");
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate dateIn10Years = currentDate.plusYears(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        query = "UPDATE InsuranceProduct SET ProductID = '" + car.getProductID()
                + "', CustomerID = '" + car.getCustomerID()
                + "', Price = " + car.getPrice()
                + ", EmployeeUnderWriting = '" + employeeName
                + "', EmployeeInChargeOne = '" + teamID.get(0)
                + "', EmployeeInChargeTwo = '" + teamID.get(1)
                + "', SubcriptionDate = '" + currentDate.format(formatter)
                + "', CoverageExpirationDate = '" + dateIn10Years.format(formatter)
                + "' WHERE Number = " + car.getNumber();
        return super.update(query);
    }
    @Override
    public List<Driver> retrieveDriverUnderWritingList() throws DatabaseException {
        String query = "SELECT * FROM Customer " +
                "JOIN InsuranceProduct " +
                "ON Customer.CustomerID = InsuranceProduct.CustomerID " +
                "WHERE InsuranceProduct.ProductID = '2' AND InsuranceProduct.EmployeeUnderWriting IS NULL " +
                "ORDER BY InsuranceProduct.Number";
        try (ResultSet rs = super.retrieve(query)) {
            List<Driver> driverList = new ArrayList<>();
            while(rs.next()) {
                Driver driver = new Driver(null);
                driver.setNumber(rs.getInt("Number"));
                driver.setProductID(rs.getString("ProductID"));
                driver.setCustomerID(rs.getString("CustomerID"));
                driver.setCustomerName(rs.getString("CustomerName"));
                driver.setPhoneNum(rs.getString("PhoneNum"));
                driver.setDriverLicense(rs.getString("DriverLicense"));
                driver.setPrice(rs.getInt("Price"));
                driverList.add(driver);
            }
            return driverList;
        } catch (SQLException e) {
            throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
        }
    }
    @Override
    public boolean deleteDriverUnderWriting(Driver driver) throws DatabaseException {
        String query = "DELETE FROM TmpDriverInsuranceProduct WHERE CustomerID = '" + driver.getCustomerID() + "';";
        return super.delete(query);
    }
    @Override
    public boolean updateDriverUnderWriting(Driver driver, String employeeName) throws DatabaseException {
        String query = "SELECT EmployeeID FROM Employee WHERE TeamNum = 2 ORDER BY RAND() LIMIT 2";
        List<String> teamID = new ArrayList<>();
        try (ResultSet rs = super.retrieve(query)) {
            while (rs.next()) {
                teamID.add(rs.getString("EmployeeID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate dateIn20Years = currentDate.plusYears(20);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        query = "UPDATE InsuranceProduct SET ProductID = '" + driver.getProductID()
                + "', CustomerID = '" + driver.getCustomerID()
                + "', Price = " + driver.getPrice()
                + ", EmployeeUnderWriting = '" + employeeName
                + "', EmployeeInChargeOne = '" + teamID.get(0)
                + "', EmployeeInChargeTwo = '" + teamID.get(1)
                + "', SubcriptionDate = '" + currentDate.format(formatter)
                + "', CoverageExpirationDate = '" + dateIn20Years.format(formatter)
                + "' WHERE Number = " + driver.getNumber();
        return super.create(query);
    }
    @Override
    public boolean retrieveAccidentTeam(String employeeName) {
        String query = "SELECT EmployeeID, EmployeePassword FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        if(super.retrieveTeam(query, new String[]{employeeName, "1"})) return true;
        return false;
    }

    @Override
    public boolean retrieveLossEvaluationTeam(String employeeName) {
        String query = "SELECT EmployeeID FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        if(super.retrieveTeam(query, new String[]{employeeName, "4"})) return true;
        return false;
    }

    @Override
    public boolean retrieveClaimProcessingTeam(String employeeName) {
        String query = "SELECT EmployeeID FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        return super.retrieveTeam(query, new String[]{employeeName, "2"});
    }
}
