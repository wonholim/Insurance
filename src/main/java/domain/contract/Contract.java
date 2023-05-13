package domain.contract;

import domain.customer.Customer;

public class Contract extends ContractListImpl{

    private Customer customer;
    private String date;
    private String insuranceID;
    private String staffID;

    public Contract(){

    }


    public boolean contract(){

        return false;
    }

}