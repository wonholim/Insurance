package org.example.insurance;

import org.example.customer.Customer;

public class Car extends Insurance {

    private int carInsuranceType;

    public Car(){

    }

    public double calculateRate(Customer customer) {
        double carInsuranceRatio = 1.0;
        int age = customer.getAge();
        return 0;
    }

    public double subscribe(){
        return 0;
    }

}