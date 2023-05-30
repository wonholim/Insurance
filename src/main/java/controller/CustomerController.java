package controller;

import customer.Customer;
import dao.CustomerDAO;
import insurance.*;

public class CustomerController {
    private Car car;
    private Driver driver;
    public boolean register(Customer customer) {
        return new CustomerDAO().register(customer);
    }

    public boolean login(String[] user) {
        return new CustomerDAO().login(user);
    }

    public String getInsuranceProduct(String userName, String productID) {
        Customer customer = new CustomerDAO().getUserData(userName);
        if(customer == null) return null;
        if(productID.equals("1")){
            this.car = new Car(customer);
            return this.car.printProduct();
        }else{
            this.driver = new Driver(customer);
            return this.driver.printProduct();
        }
    }

    public boolean registerInsuranceProduct(String userName, String productID) {
        Customer customer = new CustomerDAO().getUserData(userName);
        if(productID.equals("1")){
            this.car = new Car(customer);
            return new CustomerDAO().registerCarInsuranceProduct(customer, productID, (int)(this.car.subscribe() * this.car.calculateRate(customer)));
        }else{
            this.driver = new Driver(customer);
            return new CustomerDAO().registerDriverInsuranceProduct(customer, productID, (int)(this.driver.subscribe() * this.driver.calculateRate(customer)));
        }
    }

    public String getAllInsuranceProduct(String userName) {
        return new CustomerDAO().getAllInsuranceProduct(userName);
    }

    public long getPenaltyFeeInsuranceCar(String userName) {
        return new CustomerDAO().getPenaltyFeeInsuranceCar(userName);
    }

    public long getPenaltyFeeInsuranceDriver(String userName) {
        return new CustomerDAO().getPenaltyFeeInsuranceDriver(userName);
    }

    public boolean cancellationCarInsurance(String userName) {
        return new CustomerDAO().cancellationCarInsurance(userName);
    }

    public boolean cancellationDriverInsurance(String userName) {
        return new CustomerDAO().cancellationDriverInsurance(userName);
    }

    public boolean checkForExpiredCarInsuran(String userName) {
        return new CustomerDAO().checkForExpriedCarInsurance(userName);
    }

    public boolean renewExpirationCarInsurance(String userName) {
        return new CustomerDAO().renewExpiredCarInsurance(userName);
    }

    public boolean checkForExpiredDriverInsuran(String userName) {
        return new CustomerDAO().checkForExpriedDriverInsurance(userName);
    }

    public boolean renewExpirationDriverInsurance(String userName) {
        return new CustomerDAO().renewExpiredDriverInsurance(userName);
    }

    public String[] getCarInsuranceInformations(String userName) {
        return new CustomerDAO().getCarInsuranceInfromations(userName);
    }

    public String[] getDriverInsuranceInformations(String userName) {
        return new CustomerDAO().getDriverInsuranceInformations(userName);
    }

    public boolean isAccidentAdd(String userName) {
        return new CustomerDAO().isAccidentAdd(userName);
    }

    public boolean isInjuryAdd(String userName) {
        return new CustomerDAO().isInjuryAdd(userName);
    }

    public boolean AccidentAdd(Accident accident) {
        return new CustomerDAO().AccidentAdd(accident);
    }

    public boolean injuryAdd(Injury injury) {
        return new CustomerDAO().injuryAdd(injury);
    }

    public boolean isAccidentAddProcess(String userName) {
        return new CustomerDAO().isAccidentAddProcess(userName);
    }

    public boolean requestCarInsurancePayout(AccidentReport accidentReport) {
        return new CustomerDAO().requestCarInsurancePayout(accidentReport);
    }

    public boolean isInjuryAddProcess(String userName) {
        return new CustomerDAO().isInjuryAddProcess(userName);
    }

    public boolean requestInjuryInsurancePayout(InjuryReport injuryReports) {
        return new CustomerDAO().requestInjuryInsurancePayout(injuryReports);
    }
}
