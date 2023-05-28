package controller;

import domain.customer.Customer;
import dao.CustomerDAO;
import domain.insurance.Driver;
import domain.insurance.Car;

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
}
