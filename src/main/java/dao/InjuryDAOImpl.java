package dao;

import connector.Database;
import exception.DatabaseException;
import insurance.Injury;
import insurance.InjuryReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InjuryDAOImpl extends Database implements InjuryDAO {
    public InjuryDAOImpl() throws DatabaseException {super.initConnection();}

    @Override
    public List<Injury> retrieveInjuryList() throws DatabaseException {
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
            throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
        }
    }

    @Override
    public boolean deleteInjuryInsurance(Injury injury) throws DatabaseException {
        String query = "DELETE FROM TmpDriverInsurance WHERE CustomerID = '" + injury.getCustomerID() + "';";
        return super.delete(query);
    }

    @Override
    public boolean updateInjuryInsurance(Injury injury) throws DatabaseException {
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
                + injury.getDisease() + "', '" + currentDate.toString() +"', '" + teamID.get(0)  + "', '" + teamID.get(1) + "')";
        return super.create(query);
    }
    @Override
    public List<InjuryReport> retrieveInjuryList(int i) throws DatabaseException {
        String query = "";
        if(i == 0) query = "SELECT * FROM RequestInjuryInsurance WHERE EmployeeOne IS NULL;";
        else if(i == 1) query = "SELECT * FROM RequestInjuryInsurance WHERE EmployeeOne IS NOT NULL AND EmployeeTwo IS NULL;";
        else  query = "SELECT * FROM RequestInjuryInsurance WHERE EmployeeOne IS NOT NULL AND EmployeeTwo IS NOT NULL AND EmployeeThree IS NULL;";
        try (ResultSet rs = super.retrieve(query)) {
            List<InjuryReport> injuryReports = new ArrayList<>();
            while(rs.next()) {
                InjuryReport injuryReport= new InjuryReport();
                injuryReport.setCustomerID(rs.getString("CustomerID"));
                injuryReport.setSprain(rs.getInt("Sprain"));
                injuryReport.setSimpleFracture(rs.getInt("SimpleFracture"));
                injuryReport.setOpenFracture(rs.getInt("OpenFracture"));
                injuryReport.setCut(rs.getInt("Cut"));
                injuryReport.setCompensation(rs.getInt("Compensation"));
                injuryReport.setEmployeeOne(rs.getString("EmployeeOne"));
                injuryReport.setEmployeeTwo(rs.getString("EmployeeTwo"));
                injuryReport.setEmployeeThree(rs.getString("EmployeeThree"));
                injuryReports.add(injuryReport);
            }
            return injuryReports;
        } catch (SQLException e) {
            throw new DatabaseException("DB 조회에 오류가 발생했습니다.");
        }
    }
    @Override
    public boolean updateInjuryReport(InjuryReport injuryReport, String employeeName, int i) throws DatabaseException {
        String query = "";
        if (i == 0) query = "UPDATE RequestInjuryInsurance SET Compensation = '" + injuryReport.getCompensation() + "', EmployeeOne = '" + employeeName + "' WHERE CustomerID = '" + injuryReport.getCustomerID() + "';";
        else if(i == 1) query = "UPDATE RequestInjuryInsurance SET Compensation = '" + injuryReport.getCompensation() + "', EmployeeTwo = '" + employeeName + "' WHERE CustomerID = '" + injuryReport.getCustomerID() + "';";
        return super.update(query);
    }
    @Override
    public boolean deleteInjuryReport(InjuryReport injuryReport) throws DatabaseException {
        String query = "DELETE FROM RequestInjuryInsurance WHERE CustomerID = '" + injuryReport.getCustomerID() + "';";
        return super.delete(query);
    }
}
