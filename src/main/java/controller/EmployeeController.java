package controller;

import dao.EmployeeDAO;
import domain.team.Team;

public class EmployeeController {
    public boolean login(String[] employee) {
        return new EmployeeDAO().login(employee);
    }

    public boolean register(Team team) {
        return new EmployeeDAO().register(team);
    }
}
