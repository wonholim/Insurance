package dao;

import connector.Database;
import insurance.Accident;
import insurance.AccidentReport;
import insurance.Injury;
import insurance.InjuryReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class InsuranceDAOImpl extends Database implements InsuranceDAO {

    public InsuranceDAOImpl() {super.initConnection();}
    @Override
    public String retriveAllInsuranceProduct(String userName) {
        String line = "";
        String query = "SELECT * FROM CarInsuranceProduct WHERE CustomerID = '" + userName + "';";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                line += " 자동차 보험(Car)";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query = "SELECT * FROM DriverInsuranceProduct WHERE CustomerID = '" + userName + "';";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                line += " 운전자 보험(Driver)";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public long retrievePenaltyFeeInsuranceCar(String userName) {
        String query = "SELECT subcriptionDate, coverageExpirationDate FROM CarInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String subcriptionDate = "";
        String coverageExpirationDate = "";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                subcriptionDate = rs.getString("subcriptionDate");
                coverageExpirationDate = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LocalDate signUpDate = LocalDate.parse(subcriptionDate);
        LocalDate lastUpDate = LocalDate.parse(coverageExpirationDate);
        LocalDate currentDate = LocalDate.now();
        if(ChronoUnit.DAYS.between(signUpDate, currentDate) <= 15){
            return 0;
        }else{
            return (long) 1000000 * Math.toIntExact(ChronoUnit.YEARS.between(currentDate, lastUpDate));
        }
    }

    @Override
    public long retrievePenaltyFeeInsuranceDriver(String userName) {
        String query = "SELECT subcriptionDate, coverageExpirationDate FROM DriverInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String subcriptionDate = "";
        String coverageExpirationDate = "";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                subcriptionDate = rs.getString("subcriptionDate");
                coverageExpirationDate = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LocalDate signUpDate = LocalDate.parse(subcriptionDate);
        LocalDate lastUpDate = LocalDate.parse(coverageExpirationDate);
        LocalDate currentDate = LocalDate.now();
        if(ChronoUnit.DAYS.between(signUpDate, currentDate) <= 15){
            return 0;
        }else{
            return (long) 1000000 * Math.toIntExact(ChronoUnit.YEARS.between(currentDate, lastUpDate));
        }
    }

    @Override
    public boolean retrieveDateCarInsurance(String userName) {
        String query = "SELECT coverageExpirationDate FROM CarInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String coverageExpirationDate = "";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                coverageExpirationDate = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LocalDate lastUpDate = LocalDate.parse(coverageExpirationDate);
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(lastUpDate);
    }

    @Override
    public boolean retrieveDateDriverInsurance(String userName) {
        String query = "SELECT coverageExpirationDate FROM DriverInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String coverageExpirationDate = "";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                coverageExpirationDate = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LocalDate lastUpDate = LocalDate.parse(coverageExpirationDate);
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(lastUpDate);
    }

    @Override
    public boolean retrieveAccidentInsurance(String userName) {
        String query = "SELECT CustomerID FROM TmpAccidentInsurance WHERE CustomerID = '" + userName + "';";
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
    public boolean retrieveInjuryInsurance(String userName) {
        String query = "SELECT CustomerID FROM TmpDriverInsurance WHERE CustomerID = '" + userName + "';";
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
    public boolean retrieveAccidentCompensation(AccidentReport accidentReport) {
        String query = "INSERT INTO RequestCarInsurance (CustomerID, Tire, FrontBumper, BackBumper, FrontLight, BackLight, Door, DamageCondition, OtherCar, Compensation) VALUES ('"
                + accidentReport.getCustomerID() + "', " + accidentReport.getTire() + ", " + accidentReport.getFrontBumper() + ", "
                + accidentReport.getBackBumper() + ", " + accidentReport.getFrontLight() + ", " + accidentReport.getBackLight() + ", " + accidentReport.getDoor() + ", "
                + accidentReport.getDamageCondition() + ", " + accidentReport.getOtherCar() + ", 0)";
        return super.create(query);
    }

    @Override
    public boolean retrieveInjuryCompensation(InjuryReport injuryReports) {
        String query = "INSERT INTO RequestInjuryInsurance (CustomerID, Sprain, SimpleFracture, OpenFracture, Cut, Compensation) VALUES ('"
                + injuryReports.getCustomerID() + "', " + injuryReports.getSprain() + ", " + injuryReports.getSimpleFracture() + ", "
                + injuryReports.getOpenFracture() + ", " + injuryReports.getCut() + ", " + injuryReports.getCompensation() + ");";
        return super.create(query);
    }

    @Override
    public boolean insertAccident(Accident accident) {
        String query = "INSERT INTO TmpAccidentInsurance (CustomerID, CustomerName, RegistrationNumber, PhoneNum, Location, AccidentDate, CarNum, Service) VALUES ('"
                + accident.getCustomerID() + "', '" + accident.getCustomerName() + "', '" + accident.getRegistrationNumber() + "', '"
                + accident.getPhoneNum() + "', '" + accident.getLocation() + "', '" + accident.getAccidentDate() + "', '" + accident.getCarNum() + "'," + accident.getService() + ")";
        return super.create(query);
    }

    @Override
    public boolean insertInjury(Injury injury) {
        String query = "INSERT INTO TmpDriverInsurance (CustomerID, CustomerName, RegistrationNumber, PhoneNum, Location, InjuryDate, Disease) VALUES ('"
                + injury.getCustomerID() + "', '" + injury.getCustomerName() + "', '" + injury.getRegistrationNumber() + "', '"
                + injury.getPhoneNum() + "', '" + injury.getLocation() + "', '" + injury.getInjuryDate() + "', '" + injury.getDisease() + "')";
        return super.create(query);
    }

    @Override
    public boolean retrieveAccident(String userName) {
        String query = "SELECT CustomerID FROM CarInsuranceProduct WHERE CustomerID = '" + userName + "';";
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
    public boolean retrieveInjury(String userName) {
        String query = "SELECT CustomerID FROM DriverInsuranceProduct WHERE CustomerID = '" + userName + "';";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}