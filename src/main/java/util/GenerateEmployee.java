package util;


import connector.Database;
import exception.DatabaseException;

import java.util.Random;

public class GenerateEmployee extends Database {
    public static void main(String[] args) throws DatabaseException {
        Random random = new Random();
        GenerateEmployee generator = new GenerateEmployee();
        for (int i = 0; i < 100; i++) {
            String employeeName = generateEmployeeName();
            int age = generateAge();
            String registrationNumber = generateRegistrationNumber();
            String phoneNum = generatePhoneNum();
            String employeeID = generateEmployeeID();
            String employeePassword = employeeID;
            int employeeRank = random.nextInt(4) + 1;
            int teamNum = random.nextInt(5) + 1;

            String query = "INSERT INTO Employee (EmployeeName, Age, RegistrationNumber, PhoneNum, EmployeeID, EmployeePassword, EmployeeRank, TeamNum) " +
                    "VALUES ('" + employeeName + "', " + age + ", '" + registrationNumber + "', '" + phoneNum + "', '" +
                    employeeID + "', '" + employeePassword + "', " + employeeRank + ", " + teamNum + ");";
            generator.initConnection();
            generator.create(query);
            System.out.println(query);
        }
    }

    private static String generateEmployeeName() {
        Random random = new Random();
        char[] firstName = { '가', '나', '다', '라', '마', '바', '사', '아', '자', '차', '카', '타', '파', '하' };
        char firstChar = firstName[random.nextInt(firstName.length)];
        char secondChar = (char) (random.nextInt(11172) + 0xAC00);
        return "박" + firstChar + secondChar;
    }

    private static int generateAge() {
        Random random = new Random();
        return random.nextInt(21) + 30;
    }

    private static String generateRegistrationNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        sb.append("-");
        for (int i = 0; i < 7; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static String generatePhoneNum() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("010-");
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        sb.append("-");
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static String generateEmployeeID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int randomValue = random.nextInt(36);
            char randomChar;
            if (randomValue < 10) {
                randomChar = (char) (randomValue + '0');
            } else {
                randomChar = (char) (randomValue - 10 + 'a');
            }
            sb.append(randomChar);
        }
        return sb.toString();
    }
}