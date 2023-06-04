package dao;

import connector.Database;
import customer.Customer;
import insurance.*;
import team.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl extends Database implements EmployeeDAO {
    public EmployeeDAOImpl() {
        super.initConnection();
    }

    @Override
    public boolean insertEmployee(Team employee) {
        String query = "INSERT INTO Employee values ('"
                + employee.getName() + "', " + employee.getAge() + ", '" + employee.getRegistraionNumber() + "', '"
                + employee.getPhoneNum() + "', '" + employee.getID() + "', '" + employee.getPassword() + "', "
                + employee.getEmployeeRank() + ", " + employee.getTeamNumber() + ");";
        if(super.create(query)) return true;
        return false;
    }
    @Override
    public boolean retrieveEmployee(String[] employee) {
        String query = "SELECT EmployeeID, EmployeePassword FROM Employee WHERE EmployeeID = '" + employee[0] + "' AND EmployeePassword = '" + employee[1] + "';";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean insertInsuranceProduct(Customer customer, String productID, int price) {
        String query = "SELECT CustomerID FROM TmpCarInsuranceProduct WHERE CustomerID = ? ";
        if(super.isRegister(query, customer.getCustomerID())) return false;
        query = "SELECT CustomerID FROM CarInsuranceProduct WHERE CustomerID = ? ";
        if(super.isRegister(query, customer.getCustomerID())) return false;
        query = "INSERT INTO TmpCarInsuranceProduct (ProductID, CustomerID, CustomerName, PhoneNum, DriverLicense, CarModel, CarNum, Price) VALUES ('"
                + productID + "', '" + customer.getCustomerID() + "', '" + customer.getName() + "', '"
                + customer.getPhoneNum() + "', '" + customer.getDriverLicense() + "', '" + customer.getCarModel() + "', '"
                + customer.getCarNum() + "', " + price + ")";
        if(super.create(query)) return true;
        return false;
    }
    @Override
    public boolean retrieveEmployeeUnderWriting(String employeeName) {
        String query = "SELECT EmployeeID, EmployeePassword FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        if(super.checkTeam(query, new String[]{employeeName, "3"})) return true;
        return false;
    }
    @Override
    public List<Car> retrieveCarUnderWritingList() {
            String query = "SELECT * FROM TmpCarInsuranceProduct;";
            try (ResultSet rs = super.retrieve(query)) {
                List<Car> carList = new ArrayList<>();
                while(rs.next()) {
                    Car car = new Car(null);
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
                e.printStackTrace();
            }
            return null;
    }
    @Override
    public boolean deleteCarUnderWriting(Car car) {
        String query = "DELETE FROM TmpCarInsuranceProduct WHERE CustomerID = '" + car.getCustomerID() + "';";
        if(super.delete(query)) return true;
        return false;
    }
    @Override
    public boolean updateCarUnderWriting(Car car) {
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
        LocalDate dateIn10Years = currentDate.plusYears(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        query = "INSERT INTO CarInsuranceProduct (ProductID, CustomerID, CustomerName, PhoneNum, DriverLicense, CarModel, CarNum, Price, EmployeeOne, EmployeeTwo, subcriptionDate, coverageExpirationDate) VALUES ('"
                + car.getProductID() + "', '" + car.getCustomerID() + "', '" + car.getCustomerName() + "', '"
                + car.getPhoneNum() + "', '" + car.getDriverLicense() + "', '" + car.getCarModel() + "', '" + car.getCarNum() + "', " + car.getPrice() + ", '" + teamID.get(0)  + "', '" + teamID.get(1) +"', '"
                + currentDate.format(formatter) + "', '" +  dateIn10Years.format(formatter) + "')";
        if(super.create(query)) return true;
        return false;
    }
    @Override
    public List<Driver> retrieveDriverUnderWritingList() {
        String query = "SELECT * FROM TmpDriverInsuranceProduct;";
        try (ResultSet rs = super.retrieve(query)) {
            List<Driver> driverList = new ArrayList<>();
            while(rs.next()) {
                Driver driver = new Driver(null);
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
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean deleteDriverUnderWriting(Driver driver) {
        String query = "DELETE FROM TmpDriverInsuranceProduct WHERE CustomerID = '" + driver.getCustomerID() + "';";
        if(super.delete(query)) return true;
        return false;
    }
    @Override
    public boolean updateDriverUnderWriting(Driver driver) {
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
        query = "INSERT INTO DriverInsuranceProduct (ProductID, CustomerID, CustomerName, PhoneNum, DriverLicense, Price, EmployeeOne, EmployeeTwo, subcriptionDate, coverageExpirationDate) VALUES ('"
                + driver.getProductID() + "', '" + driver.getCustomerID() + "', '" + driver.getCustomerName() + "', '"
                + driver.getPhoneNum() + "', '" + driver.getDriverLicense() + "', " + driver.getPrice() + ", '" + teamID.get(0)  + "', '" + teamID.get(1) +"', '"
                + currentDate.format(formatter) + "', '" +  dateIn20Years.format(formatter) + "')";
        if(super.create(query)) return true;
        return false;
    }
    @Override
    public boolean retrieveAccidentTeam(String employeeName) {
        String query = "SELECT EmployeeID, EmployeePassword FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        if(super.checkTeam(query, new String[]{employeeName, "1"})) return true;
        return false;
    }

    @Override
    public boolean retrieveLossEvaluationTeam(String employeeName) {
        String query = "SELECT EmployeeID FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        if(super.checkTeam(query, new String[]{employeeName, "4"})) return true;
        return false;
    }

    @Override
    public boolean retrieveClaimProcessingTeam(String employeeName) {
        String query = "SELECT EmployeeID FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        return super.checkTeam(query, new String[]{employeeName, "2"});
    }





}