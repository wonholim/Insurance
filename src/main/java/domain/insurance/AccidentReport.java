package domain.insurance;

public class AccidentReport {
    public String getCustomerID() {
        return customerID;
    }

    public long getCompensation() {
        return compensation;
    }

    public void setCompensation(long compensation) {
        this.compensation = compensation;
    }

    private long compensation;
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getTire() {
        return tire;
    }

    public void setTire(int tire) {
        this.tire = tire;
    }

    public int getFrontBumper() {
        return frontBumper;
    }

    public void setFrontBumper(int frontBumper) {
        this.frontBumper = frontBumper;
    }

    public int getBackBumper() {
        return backBumper;
    }

    public void setBackBumper(int backBumper) {
        this.backBumper = backBumper;
    }

    public int getFrontLight() {
        return frontLight;
    }

    public void setFrontLight(int frontLight) {
        this.frontLight = frontLight;
    }

    public int getBackLight() {
        return backLight;
    }

    public void setBackLight(int backLight) {
        this.backLight = backLight;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public int getDamageCondition() {
        return damageCondition;
    }

    public void setDamageCondition(int damageCondition) {
        this.damageCondition = damageCondition;
    }

    public int getOtherCar() {
        return otherCar;
    }

    public void setOtherCar(int otherCar) {
        this.otherCar = otherCar;
    }

    public String getEmployeeOne() {
        return employeeOne;
    }

    public void setEmployeeOne(String employeeOne) {
        this.employeeOne = employeeOne;
    }

    public String getEmployeeTwo() {
        return employeeTwo;
    }

    public void setEmployeeTwo(String employeeTwo) {
        this.employeeTwo = employeeTwo;
    }

    public String getEmployeeThree() {
        return employeeThree;
    }

    public void setEmployeeThree(String employeeThree) {
        this.employeeThree = employeeThree;
    }

    private String customerID;
    private int tire;
    private int frontBumper;
    private int backBumper;
    private int frontLight;
    private int backLight;
    private int door;
    private int damageCondition;
    private int otherCar;
    private String employeeOne;
    private String employeeTwo;
    private String employeeThree;


}
