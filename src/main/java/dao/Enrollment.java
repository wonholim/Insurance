package dao;

import customer.Customer;

public class Enrollment extends Customer {

    private int drvingExperience;
    private String occupation;

    public Enrollment(){

    }


    public boolean cancelInsurance(){
        return false;
    }

    public boolean enrollInsurance(){
        return false;
    }

    public boolean makePayment(){
        return false;
    }

    public boolean receiveReward(){
        return false;
    }

}