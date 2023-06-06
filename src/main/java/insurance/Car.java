package insurance;

import customer.Customer;

public class Car extends Insurance {

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    private int Number;
    private int carInsuranceType;
    private String ProductID;
    private String CustomerID;
    private String CustomerName;
    private String PhoneNum;
    private String DriverLicense;
    private String CarModel;
    private String CarNum;
    private int price;
    private String EmployeeOne;
    private String EmployeeTwo;
    private Customer customer;

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

    private String subcriptionDate;
    private String coverageExpirationDate;
    public Car(Customer customer) {
        this.customer =customer;
    }

    public double calculateRate(Customer customer) {
        double carInsuranceRatio = 1.0;
        int age = customer.getAge();
        int sex = customer.getSex();
        String year = customer.getCarModelYear();
        if(sex == 1) carInsuranceRatio -= 0.1;
        else carInsuranceRatio += 0.2;
        if(age <= 25) carInsuranceRatio += 2.0;
        else if(age <= 30) carInsuranceRatio += 0.2;
        else if(age <= 40) carInsuranceRatio += 0.15;
        else if(age <= 50) carInsuranceRatio += 0.1;
        else if(age <= 60) carInsuranceRatio += 0.15;
        else if(age <= 70) carInsuranceRatio += 3.0;
        else carInsuranceRatio += 5.0;

        return carInsuranceRatio;
    }

    public double subscribe(){
        return 1000000;
    }

    public String printProduct() {
        String line = customer.getName() + "님의 자동차 보험 가입 안내서" + "\n"
                + "차종 : " + customer.getCarModel() + "\n"
                + "성별 : " + (customer.getSex() == 1 ? "남자" : "여자") + "\n"
                + "나이 : " + customer.getAge() + "\n"
                + "월 납입료 : " + (int)(subscribe() * calculateRate(customer)) + "\n"
                + "계약 기간 : 10년";
        return line;
    }
    public int getCarInsuranceType() {
        return carInsuranceType;
    }

    public void setCarInsuranceType(int carInsuranceType) {
        this.carInsuranceType = carInsuranceType;
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
        this.CustomerID = customerID;
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

    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String carModel) {
        CarModel = carModel;
    }

    public String getCarNum() {
        return CarNum;
    }

    public void setCarNum(String carNum) {
        this.CarNum = carNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getEmployeeOne() {
        return EmployeeOne;
    }

    public void setEmployeeOne(String employeeOne) {
        this.EmployeeOne = employeeOne;
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

}