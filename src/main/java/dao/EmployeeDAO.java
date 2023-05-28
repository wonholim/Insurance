package dao;

import connector.Database;
import domain.insurance.Car;
import domain.insurance.Driver;
import domain.team.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends Database {
    public EmployeeDAO() {
        super.initConnection();
    }
    public boolean register(Team employee) {
        String query = "INSERT INTO Employee values ('"
                + employee.getName() + "', " + employee.getAge() + ", '" + employee.getRegistraionNumber() + "', '"
                + employee.getPhoneNum() + "', '" + employee.getID() + "', '" + employee.getPassword() + "', "
                + employee.getEmployeeRank() + ", " + employee.getTeamNumber() + ");";
        if(super.create(query)) return true;
        return false;
    }

    public boolean login(String[] employee) {
        String query = "SELECT EmployeeID, EmployeePassword FROM Employee WHERE EmployeeID = ? AND EmployeePassword = ?";
        if(super.authentication(query, employee)) return true;
        return false;
    }

    public boolean checkUnderWriting(String employeeName) {
        String query = "SELECT EmployeeID, EmployeePassword FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        if(super.checkUnderWriting(query, new String[]{employeeName, "3"})) return true;
        return false;
    }

    public List<Car> customersCarUnderWriting() {
            String query = "SELECT * FROM TmpCarInsuranceProduct;";
            try (ResultSet rs = super.retrieve(query)) {
                List<Car> carList = new ArrayList<>();
                if(rs.next()) {
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

    public boolean customersCarUnderWritingDelete(Car car) {
        String query = "DELETE FROM TmpCarInsuranceProduct WHERE CustomerID = '" + car.getCustomerID() + "';";
        if(super.delete(query)) return true;
        return false;
    }

    public boolean customersCarUnderWritingUpdate(Car car) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateIn10Years = currentDate.plusYears(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String query = "INSERT INTO CarInsuranceProduct (ProductID, CustomerID, CustomerName, PhoneNum, DriverLicense, CarModel, CarNum, Price, EmployeeOne, EmployeeTwo, subcriptionDate, coverageExpirationDate) VALUES ('"
                + car.getProductID() + "', '" + car.getCustomerID() + "', '" + car.getCustomerName() + "', '"
                + car.getPhoneNum() + "', '" + car.getDriverLicense() + "', '" + car.getCarModel() + "', '" + car.getCarNum() + "', " + car.getPrice() + ", null, null, '"
                + currentDate.format(formatter) + "', '" +  dateIn10Years.format(formatter) + "')";
        if(super.create(query)) return true;
        return false;
    }

    public List<Driver> customersDriverUnderWriting() {
        String query = "SELECT * FROM TmpDriverInsuranceProduct;";
        try (ResultSet rs = super.retrieve(query)) {
            List<Driver> driverList = new ArrayList<>();
            if(rs.next()) {
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

    public boolean customersDriverUnderWritingDelete(Driver driver) {
        String query = "DELETE FROM TmpDriverInsuranceProduct WHERE CustomerID = '" + driver.getCustomerID() + "';";
        if(super.delete(query)) return true;
        return false;
    }

    public boolean customersDriverUnderWritingUpate(Driver driver) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateIn20Years = currentDate.plusYears(20);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String query = "INSERT INTO DriverInsuranceProduct (ProductID, CustomerID, CustomerName, PhoneNum, DriverLicense, Price, EmployeeOne, EmployeeTwo, subcriptionDate, coverageExpirationDate) VALUES ('"
                + driver.getProductID() + "', '" + driver.getCustomerID() + "', '" + driver.getCustomerName() + "', '"
                + driver.getPhoneNum() + "', '" + driver.getDriverLicense() + "', " + driver.getPrice() + ", null, null, '"
                + currentDate.format(formatter) + "', '" +  dateIn20Years.format(formatter) + "')";
        if(super.create(query)) return true;
        return false;
    }
}
