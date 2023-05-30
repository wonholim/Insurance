package insurance;

import customer.Customer;

public class Driver extends Insurance {
    public String getSubcriptionDate() {
        return subcriptionDate;
    }

    public void setSubcriptionDate(String subcriptionDate) {
        this.subcriptionDate = subcriptionDate;
    }

    public String getCoverageExpirationDate() {
        return coverageExpirationDate;
    }

    public void setCoverageExpirationDate(String coverageExpirationDate) {
        this.coverageExpirationDate = coverageExpirationDate;
    }
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getDriverLicense() {
        return DriverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        DriverLicense = driverLicense;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getEmployeeOne() {
        return EmployeeOne;
    }

    public void setEmployeeOne(String employeeOne) {
        EmployeeOne = employeeOne;
    }

    public String getEmployeeTwo() {
        return EmployeeTwo;
    }

    public void setEmployeeTwo(String employeeTwo) {
        EmployeeTwo = employeeTwo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private String ProductID;
    private String CustomerID;
    private String CustomerName;
    private String PhoneNum;
    private String DriverLicense;
    private int Price;
    private String EmployeeOne;
    private String EmployeeTwo;
    private Customer customer;
    private String subcriptionDate;
    private String coverageExpirationDate;
    public Driver(Customer customer){
        this.customer = customer;
    }

    public double calculateRate(Customer customer){
        double carInsuranceRatio = 1.0;
        int age = customer.getAge();
        int sex = customer.getSex();
        String year = customer.getCarModelYear();
        if(sex == 1) carInsuranceRatio += 0.4;
        else carInsuranceRatio += 0.2;
        if(age <= 25) carInsuranceRatio += 0.5;
        else if(age <= 30) carInsuranceRatio += 0.7;
        else if(age <= 40) carInsuranceRatio += 0.9;
        else if(age <= 50) carInsuranceRatio += 1.1;
        else if(age <= 60) carInsuranceRatio += 1.15;
        else if(age <= 70) carInsuranceRatio += 2.0;
        else carInsuranceRatio += 3.0;
        return carInsuranceRatio;
    }

    public double subscribe(){
        return 10000;
    }

    public String printProduct() {
        String line = customer.getName() + "님의 운전자 보험 가입 안내서" + "\n"
                + "성별 : " + customer.getSex() + "\n"
                + "나이 : " + customer.getAge() + "\n"
                + "월 납입료 : " + (int)(subscribe() * calculateRate(customer)) + "\n"
                + "계약 기간 : 20년";
        return line;
    }
}