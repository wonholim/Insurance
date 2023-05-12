package org.example.customer;

import org.example.contract.Contract;

import java.util.List;

public class Customer {
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCarModelYear() {
        return carModelYear;
    }

    public void setCarModelYear(int carModelYear) {
        this.carModelYear = carModelYear;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getDriverLicense() {
        return DriverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        DriverLicense = driverLicense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getRegistraionNumber() {
        return RegistraionNumber;
    }

    public void setRegistraionNumber(String registraionNumber) {
        RegistraionNumber = registraionNumber;
    }

    public Contract getM_Contract() {
        return m_Contract;
    }

    public void setM_Contract(Contract m_Contract) {
        this.m_Contract = m_Contract;
    }

    private int age;
    private String carModel;
    private int carModelYear;
    private int carNum;
    private int customerID;
    private String DriverLicense;
    private String name;
    private String phoneNum;
    private String RegistraionNumber;
    public Contract m_Contract;


    public Customer(){

    }

    public boolean appayInsurancePremium(){
        return false;
    }

    public boolean applyInsurance(){
        return false;
    }

    public boolean claimCompensation(){
        return false;
    }

    public List<Contract> inquiryInsurance(){
        return null;
    }

    public boolean reportAccident(){
        return false;
    }

}