package dao;

import connector.Database;
import domain.insurance.*;
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
        if(super.checkTeam(query, new String[]{employeeName, "3"})) return true;
        return false;
    }

    public List<Car> customersCarUnderWriting() {
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

    public boolean customersCarUnderWritingDelete(Car car) {
        String query = "DELETE FROM TmpCarInsuranceProduct WHERE CustomerID = '" + car.getCustomerID() + "';";
        if(super.delete(query)) return true;
        return false;
    }

    public boolean customersCarUnderWritingUpdate(Car car) {
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

    public List<Driver> customersDriverUnderWriting() {
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

    public boolean customersDriverUnderWritingDelete(Driver driver) {
        String query = "DELETE FROM TmpDriverInsuranceProduct WHERE CustomerID = '" + driver.getCustomerID() + "';";
        if(super.delete(query)) return true;
        return false;
    }

    public boolean customersDriverUnderWritingUpate(Driver driver) {
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

    public boolean checkAccidentTeam(String employeeName) {
        String query = "SELECT EmployeeID, EmployeePassword FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        if(super.checkTeam(query, new String[]{employeeName, "1"})) return true;
        return false;
    }

    public List<Accident> getTmpAccidentList() {
        String query = "SELECT * FROM TmpAccidentInsurance;";
        try (ResultSet rs = super.retrieve(query)) {
            List<Accident> accidentList = new ArrayList<>();
            while(rs.next()) {
                Accident accident= new Accident();
                accident.setCustomerID(rs.getString("CustomerID"));
                accident.setCustomerName(rs.getString("CustomerName"));
                accident.setPhoneNum(rs.getString("PhoneNum"));
                accident.setRegistrationNumber(rs.getString("RegistrationNumber"));
                accident.setLocation(rs.getString("Location"));
                accident.setAccidentDate(rs.getString("AccidentDate"));
                accident.setCarNum(rs.getString("CarNum"));
                accident.setService(rs.getInt("Service"));
                accidentList.add(accident);
            }
            return accidentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean accidentInsuranceUpdate(Accident accident) {
        String query = "SELECT EmployeeID FROM Employee WHERE TeamNum = 1 ORDER BY RAND() LIMIT 2";
        List<String> teamID = new ArrayList<>();
        try (ResultSet rs = super.retrieve(query)) {
            while (rs.next()) {
                teamID.add(rs.getString("EmployeeID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LocalDate currentDate = LocalDate.now();
        query = "INSERT INTO AccidentInsurance (CustomerID, CustomerName, RegistrationNumber, PhoneNum, Location, AccidentDate, CarNum,Service , ProcessinDate,EmployeeOne, EmployeeTwo) VALUES ('"
                + accident.getCustomerID() + "', '" + accident.getCustomerName() + "', '" + accident.getRegistrationNumber() + "', '"
                + accident.getPhoneNum() + "', '" + accident.getLocation() + "', '" + accident.getAccidentDate() + "', '"
                + accident.getCarNum() + "' ," + accident.getService() + ", '" + currentDate.toString() +"', '" + teamID.get(0)  + "', '" + teamID.get(1) + "')";
        if(super.create(query)) return true;
        return false;
    }

    public boolean tmpAccidentInsuranceDelete(Accident accident) {
        String query = "DELETE FROM TmpAccidentInsurance WHERE CustomerID = '" + accident.getCustomerID() + "';";
        if(super.delete(query)) return true;
        return false;
    }

    public List<Injury> getTmpInjuryList() {
        String query = "SELECT * FROM TmpDriverInsurance;";
        try (ResultSet rs = super.retrieve(query)) {
            List<Injury> injuries = new ArrayList<>();
            while(rs.next()) {
                Injury injury= new Injury();
                injury.setCustomerID(rs.getString("CustomerID"));
                injury.setCustomerName(rs.getString("CustomerName"));
                injury.setPhoneNum(rs.getString("PhoneNum"));
                injury.setRegistrationNumber(rs.getString("RegistrationNumber"));
                injury.setLocation(rs.getString("Location"));
                injury.setInjuryDate(rs.getString("InjuryDate"));
                injury.setDisease(rs.getString("Disease"));
                injuries.add(injury);
            }
            return injuries;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean tmpInjuryInsuranceDelete(Injury injury) {
        String query = "DELETE FROM TmpDriverInsurance WHERE CustomerID = '" + injury.getCustomerID() + "';";
        return super.delete(query);
    }

    public boolean InjuryInsuranceUpdate(Injury injury) {
        String query = "SELECT EmployeeID FROM Employee WHERE TeamNum = 1 ORDER BY RAND() LIMIT 2";
        List<String> teamID = new ArrayList<>();
        try (ResultSet rs = super.retrieve(query)) {
            while (rs.next()) {
                teamID.add(rs.getString("EmployeeID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LocalDate currentDate = LocalDate.now();
        query = "INSERT INTO DriverInsurance (CustomerID, CustomerName, RegistrationNumber, PhoneNum, Location, InjuryDate, Disease, ProcessinDate , EmployeeOne, EmployeeTwo) VALUES ('"
                + injury.getCustomerID() + "', '" + injury.getCustomerName() + "', '" + injury.getRegistrationNumber() + "', '"
                + injury.getPhoneNum() + "', '" + injury.getLocation() + "', '" + injury.getInjuryDate() + "', '"
                + injury.getDisease() + ", '" + currentDate.toString() +"', '" + teamID.get(0)  + "', '" + teamID.get(1) + "')";
        if(super.create(query)) return true;
        return false;
    }

    public boolean checkLossEvaluationTeam(String employeeName) {
        String query = "SELECT EmployeeID FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        if(super.checkTeam(query, new String[]{employeeName, "4"})) return true;
        return false;
    }

    public List<AccidentReport> getAccidentList(int i) {
        String query = "";
        if(i == 0) query = "SELECT * FROM RequestCarInsurance WHERE EmployeeOne IS NULL;";
        else if(i == 1) query = "SELECT * FROM RequestCarInsurance WHERE EmployeeOne IS NOT NULL AND EmployeeTwo IS NULL;";
        else  query = "SELECT * FROM RequestCarInsurance WHERE EmployeeOne IS NOT NULL AND EmployeeTwo IS NOT NULL AND EmployeeThree IS NULL;";
        try (ResultSet rs = super.retrieve(query)) {
            List<AccidentReport> accidentReportList = new ArrayList<>();
            while(rs.next()) {
                AccidentReport accidentReports= new AccidentReport();
                accidentReports.setCustomerID(rs.getString("CustomerID"));
                accidentReports.setTire(rs.getInt("Tire"));
                accidentReports.setFrontBumper(rs.getInt("FrontBumper"));
                accidentReports.setBackBumper(rs.getInt("BackBumper"));
                accidentReports.setFrontLight(rs.getInt("FrontLight"));
                accidentReports.setBackLight(rs.getInt("BackLight"));
                accidentReports.setDoor(rs.getInt("Door"));
                accidentReports.setDamageCondition(rs.getInt("DamageCondition"));
                accidentReports.setOtherCar(rs.getInt("OtherCar"));
                accidentReports.setCompensation(rs.getInt("Compensation"));
                accidentReports.setEmployeeOne(rs.getString("EmployeeOne"));
                accidentReports.setEmployeeTwo(rs.getString("EmployeeTwo"));
                accidentReports.setEmployeeThree(rs.getString("EmployeeThree"));
                accidentReportList.add(accidentReports);
            }
            return accidentReportList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean accidentReportUpdate(AccidentReport accidentReport, String employeeName) {
        String query = "UPDATE RequestCarInsurance SET Compensation = '" + accidentReport.getCompensation() + "', EmployeeOne = '" + employeeName + "' WHERE CustomerID = '" + accidentReport.getCustomerID() + "';";
        return super.update(query);
    }

    public boolean checkClaimProcessingTeam(String employeeName) {
        String query = "SELECT EmployeeID FROM Employee WHERE EmployeeID = ? AND TeamNum = ?";
        return super.checkTeam(query, new String[]{employeeName, "2"});
    }

    public boolean accidentReportDelete(AccidentReport accidentReport) {
        String query = "DELETE FROM RequestCarInsurance WHERE CustomerID = '" + accidentReport.getCustomerID() + "';";
        return super.delete(query);
    }
}
