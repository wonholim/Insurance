package dao;

import connector.Database;
import domain.customer.Customer;
import domain.team.Team;

public class EmployeeDAO extends Database {
    public EmployeeDAO() {
        super.initConnection();
    }
    public boolean register(Team employee) {
        String query = "INSERT INTO Employee values ('"
                + employee.getName() + "', " + employee.getAge() + ", '" + employee.getRegistraionNumber() + "', '"
                + employee.getPhoneNum() + "', '" + employee.getID() + "', '" + employee.getPassword() + "', "
                + employee.getEmployeeRank() + ", " + employee.getTeamNumber() + ");";
        if(super.create(query)) return true;
        return false;
    }

    public boolean login(String[] employee) {
        String query = "SELECT EmployeeID, EmployeePassword FROM Employee WHERE EmployeeID = ? AND EmployeePassword = ?";
        if(super.authentication(query, employee)) return true;
        return false;
    }
}
