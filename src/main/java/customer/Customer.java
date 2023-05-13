package customer;

import contract.Contract;

import java.util.List;

public class Customer {
    private int age;
    private String carModel;
    private int carModelYear;
    private String carNum;
    private String customerID;
    private String driverLicense;
    private String name;
    private String phoneNum;
    private String registraionNumber;
    private String password;
    private int sex;

    public int getSex() {return sex;}
    public void setSex(int sex) {this.sex = sex;}
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
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
    public String getCarNum() {
        return carNum;
    }
    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
    public String getCustomerID() {
        return customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public String getDriverLicense() {
        return driverLicense;
    }
    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
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
        return registraionNumber;
    }
    public void setRegistraionNumber(String registraionNumber) {
        this.registraionNumber = registraionNumber;
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