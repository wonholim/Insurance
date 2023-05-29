package domain.team;


import service.*;

import java.util.List;

public class Team{

	private int teamNumber;
	private String name;
	private String ID;
	private String password;
	private String registraionNumber;
	private String phoneNum;
	private int age;
	private int employeeRank;

	public int getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegistraionNumber() {
		return registraionNumber;
	}

	public void setRegistraionNumber(String registraionNumber) {
		this.registraionNumber = registraionNumber;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getEmployeeRank() {
		return employeeRank;
	}

	public void setEmployeeRank(int employeeRank) {
		this.employeeRank = employeeRank;
	}
}