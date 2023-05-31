package dao;

import connector.Database;
import customer.Customer;
import insurance.Accident;
import insurance.AccidentReport;
import insurance.Injury;
import insurance.InjuryReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CustomerDAO extends Database {

    public CustomerDAO() {
        super.initConnection();
    }

    // 고객이 보험회사에 가입을 한다.
    public boolean register(Customer customer) {
        String query = "INSERT INTO Customer values ('"
                        + customer.getName() + "', " + customer.getAge() + ", " + customer.getSex() + ", '" + customer.getRegistraionNumber() + "', '"
                        + customer.getPhoneNum() + "', '" + customer.getDriverLicense() + "', '" + customer.getCarModel() + "', '"
                        + customer.getCarNum() + "', '" + customer.getCarModelYear() + "', '" + customer.getCustomerID() + "', '" + customer.getPassword() + "');";
        if(super.create(query)) return true;
        return false;
    }

    // 고객이 로그인을 한다.
    public boolean login(String[] user) {
        String query = "SELECT CustomerID, CustomerPassword FROM Customer WHERE CustomerID = ? AND CustomerPassword = ?";
        if(super.authentication(query, user)) return true;
        return false;
    }


    // 고객이 조회된 보험에 가입을 신청한다.
    public boolean registerCarInsuranceProduct(Customer customer, String productID, int price) {
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

    public Customer getUserData(String userName) {
        String query = "SELECT * FROM Customer WHERE CustomerID = '" + userName + "';";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                Customer customer = new Customer();
                customer.setAge(rs.getInt("Age"));
                customer.setCarModel(rs.getString("CarModel"));
                customer.setCarModelYear(rs.getString("CarModelYear"));
                customer.setCarNum(rs.getString("CarNum"));
                customer.setCustomerID(rs.getString("CustomerID"));
                customer.setDriverLicense(rs.getString("DriverLicense"));
                customer.setName(rs.getString("CustomerName"));
                customer.setPhoneNum(rs.getString("PhoneNum"));
                customer.setRegistraionNumber(rs.getString("RegistrationNumber"));
                customer.setPassword(rs.getString("CustomerPassword"));
                customer.setSex(rs.getInt("Sex"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registerDriverInsuranceProduct(Customer customer, String productID, int price) {
        String query = "SELECT CustomerID FROM TmpDriverInsuranceProduct WHERE CustomerID = ? ";
        if(super.isRegister(query, customer.getCustomerID())) return false;
        query = "SELECT CustomerID FROM DriverInsuranceProduct WHERE CustomerID = ? ";
        if(super.isRegister(query, customer.getCustomerID())) return false;
        query = "INSERT INTO TmpDriverInsuranceProduct (ProductID, CustomerID, CustomerName, PhoneNum, DriverLicense, Price) VALUES ('"
                + productID + "', '" + customer.getCustomerID() + "', '" + customer.getName() + "', '"
                + customer.getPhoneNum() + "', '" + customer.getDriverLicense() + "', " + price + ")";
        if(super.create(query)) return true;
        return false;
    }

    public String getAllInsuranceProduct(String userName) {
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

    public boolean checkInsuranceProduct(String[] user) {
        String query = "SELECT CustomerID, CustomerPassword FROM Customer WHERE CustomerID = ? AND CustomerPassword = ?";
        if(super.authentication(query, user)) return true;
        return false;
    }

    public long getPenaltyFeeInsuranceCar(String userName) {
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

    public long getPenaltyFeeInsuranceDriver(String userName) {
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

    public boolean cancellationCarInsurance(String userName) {
        String query = "DELETE FROM carInsuranceProduct WHERE CustomerID = '" + userName + "';";
        if(super.delete(query)) return true;
        return false;
    }

    public boolean cancellationDriverInsurance(String userName) {
        String query = "DELETE FROM DriverInsuranceProduct WHERE CustomerID = '" + userName + "';";
        if(super.delete(query)) return true;
        return false;
    }

    public boolean checkForExpriedCarInsurance(String userName) {
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
    public boolean checkForExpriedDriverInsurance(String userName) {
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

    public boolean renewExpiredCarInsurance(String userName) {
        String query = "SELECT coverageExpirationDate FROM CarInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String coverageExpirationDate = "";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                coverageExpirationDate = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String lastUpDate = LocalDate.parse(coverageExpirationDate).plusYears(10).toString();
        query = "UPDATE CarInsuranceProduct SET coverageExpirationDate = '" + lastUpDate + "' WHERE CustomerID = '" + userName + "';";
        return super.update(query);
    }

    public boolean renewExpiredDriverInsurance(String userName) {
        String query = "SELECT coverageExpirationDate FROM DriverInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String coverageExpirationDate = "";
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                coverageExpirationDate = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String lastUpDate = LocalDate.parse(coverageExpirationDate).plusYears(20).toString();
        query = "UPDATE DriverInsuranceProduct SET coverageExpirationDate = '" + lastUpDate + "' WHERE CustomerID = '" + userName + "';";
        return super.update(query);
    }

    public String[] getCarInsuranceInfromations(String userName) {
        String query = "SELECT Price ,coverageExpirationDate FROM CarInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String[] informations = new String[2];
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                informations[0] = "" + rs.getInt("Price");
                informations[1] = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informations;
    }

    public String[] getDriverInsuranceInformations(String userName) {
        String query = "SELECT Price ,coverageExpirationDate FROM DriverInsuranceProduct WHERE CustomerID = '" + userName + "';";
        String[] informations = new String[2];
        try (ResultSet rs = super.retrieve(query)) {
            if(rs.next()) {
                informations[0] = "" + rs.getInt("Price");
                informations[1] = rs.getString("coverageExpirationDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informations;
    }

    public boolean isAccidentAdd(String userName) {
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

    public boolean isInjuryAdd(String userName) {
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

    public boolean AccidentAdd(Accident accident) {
        String query = "INSERT INTO TmpAccidentInsurance (CustomerID, CustomerName, RegistrationNumber, PhoneNum, Location, AccidentDate, CarNum, Service) VALUES ('"
                + accident.getCustomerID() + "', '" + accident.getCustomerName() + "', '" + accident.getRegistrationNumber() + "', '"
                + accident.getPhoneNum() + "', '" + accident.getLocation() + "', '" + accident.getAccidentDate() + "', '" + accident.getCarNum() + "'," + accident.getService() + ")";
        if(super.create(query)) return true;
        return false;
    }

    public boolean injuryAdd(Injury injury) {
        String query = "INSERT INTO TmpDriverInsurance (CustomerID, CustomerName, RegistrationNumber, PhoneNum, Location, InjuryDate, Disease) VALUES ('"
                + injury.getCustomerID() + "', '" + injury.getCustomerName() + "', '" + injury.getRegistrationNumber() + "', '"
                + injury.getPhoneNum() + "', '" + injury.getLocation() + "', '" + injury.getInjuryDate() + "', '" + injury.getDisease() + "')";
        if(super.create(query)) return true;
        return false;
    }

    public boolean isAccidentAddProcess(String userName) {
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

    public boolean requestCarInsurancePayout(AccidentReport accidentReport) {
        String query = "INSERT INTO RequestCarInsurance (CustomerID, Tire, FrontBumper, BackBumper, FrontLight, BackLight, Door, DamageCondition, OtherCar, Compensation) VALUES ('"
                + accidentReport.getCustomerID() + "', " + accidentReport.getTire() + ", " + accidentReport.getFrontBumper() + ", "
                + accidentReport.getBackBumper() + ", " + accidentReport.getFrontLight() + ", " + accidentReport.getBackLight() + ", " + accidentReport.getDoor() + ", "
                + accidentReport.getDamageCondition() + ", " + accidentReport.getOtherCar() + ", 0)";
        if(super.create(query)) return true;
        return false;
    }

    public boolean isInjuryAddProcess(String userName) {
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

    public boolean requestInjuryInsurancePayout(InjuryReport injuryReports) {
        String query = "INSERT INTO RequestInjuryInsurance (CustomerID, Sprain, SimpleFracture, OpenFracture, Cut, Compensation) VALUES ('"
                + injuryReports.getCustomerID() + "', " + injuryReports.getSprain() + ", " + injuryReports.getSimpleFracture() + ", "
                + injuryReports.getOpenFracture() + ", " + injuryReports.getCut() + ", " + injuryReports.getCompensation() + ");";
        return super.create(query);
    }
}
