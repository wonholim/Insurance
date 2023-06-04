package dao;

import connector.Database;
import insurance.Accident;
import insurance.AccidentReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccidentDAOImpl extends Database implements AccidentDAO {
    public AccidentDAOImpl() {super.initConnection();}

    @Override
    public List<Accident> retrieveAccidentList() {
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
    @Override
    public boolean updateAccidentInsurance(Accident accident) {
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
    @Override
    public boolean deleteAccidentInsurance(Accident accident) {
        String query = "DELETE FROM TmpAccidentInsurance WHERE CustomerID = '" + accident.getCustomerID() + "';";
        return super.delete(query);
    }
    @Override
    public boolean updateAccidentReport(AccidentReport accidentReport, String employeeName, int i) {
        String query = "";
        if(i == 0) query = "UPDATE RequestCarInsurance SET Compensation = '" + accidentReport.getCompensation() + "', EmployeeOne = '" + employeeName + "' WHERE CustomerID = '" + accidentReport.getCustomerID() + "';";
        else if(i == 1) query = "UPDATE RequestCarInsurance SET Compensation = '" + accidentReport.getCompensation() + "', EmployeeTwo = '" + employeeName + "' WHERE CustomerID = '" + accidentReport.getCustomerID() + "';";
        return super.update(query);
    }
    @Override
    public List<AccidentReport> retrieveAccidentList(int i) {
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
    @Override
    public boolean deleteAccidentReport(AccidentReport accidentReport) {
        String query = "DELETE FROM RequestCarInsurance WHERE CustomerID = '" + accidentReport.getCustomerID() + "';";
        return super.delete(query);
    }
}
