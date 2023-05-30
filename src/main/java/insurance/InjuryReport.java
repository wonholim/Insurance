package insurance;

public class InjuryReport {

    private String customerID;
    private String customerName;
    private int sprain;
    private int simpleFracture;
    private int openFracture;
    private int cut;
    private int compensation;
    private String employeeOne;
    private String employeeTwo;
    private String employeeThree;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getSprain() {
        return sprain;
    }

    public void setSprain(int sprain) {
        this.sprain = sprain;
    }

    public int getSimpleFracture() {
        return simpleFracture;
    }

    public void setSimpleFracture(int simpleFracture) {
        this.simpleFracture = simpleFracture;
    }

    public int getOpenFracture() {
        return openFracture;
    }

    public void setOpenFracture(int openFracture) {
        this.openFracture = openFracture;
    }

    public int getCut() {
        return cut;
    }

    public void setCut(int cut) {
        this.cut = cut;
    }

    public int getCompensation() {
        return compensation;
    }

    public void setCompensation(int compensation) {
        this.compensation = compensation;
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
}
