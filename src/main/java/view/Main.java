package view;

import customer.Customer;
import dao.CustomerDAOImpl;
import dao.EmployeeDAOImpl;
import team.Team;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                printFirstMenu();
                String select = bufferedReader.readLine().trim();
                switch (select) {
                    case "1":
                        One:
                        while(true) {
                            printCustomerLoginMenu();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1":
                                    String userName = printCustomerLogin(bufferedReader);
                                    if (userName != null) {
                                       CustomerLoginView customerLoginView = new CustomerLoginView(userName);
                                       customerLoginView.customerSelect(bufferedReader);
                                    }
                                    break;
                                case "2": registerCustomer(bufferedReader); break;
                                case "r": break One;
                                default : System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "2":
                        Two:
                        while(true) {
                            printEmployeeLoginMenu();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1":
                                    String employeeName = printEmployeeLogin(bufferedReader);
                                    if (employeeName != null) {
                                        EmployeeLoginView employeeLoginView = new EmployeeLoginView(employeeName);
                                        employeeLoginView.employeeSelect(bufferedReader);
                                    }
                                    break;
                                case "2": registerEmployee(bufferedReader); break;
                                case "r": break Two;
                                default: System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "x": exitProgram(); return;
                    default : System.out.println("입력값이 올바르지 않습니다.");
                }
            }
        } catch (IOException e) {
            // stacktrace로 안찍힘
            System.out.println(e.getCause());
        }
    }

    private static String printEmployeeLogin(BufferedReader br) {
        System.out.println("***************** EMPLOYEE LOGIN MENU *****************");
        String[] employee = new String[2];
        try {
            System.out.print("ID : ");
            employee[0] = br.readLine().trim();
            System.out.print("Password : ");
            employee[1] = br.readLine().trim();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (new EmployeeDAOImpl().retrieveEmployee(employee)) {
            System.out.println(employee[0] + "님 환영합니다.");
            return employee[0];
        } else {
            System.out.println("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        return null;
    }
    private static void registerEmployee(BufferedReader br) {
        Team team = new Team();
        try {
            System.out.print("이름 : ");
            String name = br.readLine().trim();
            team.setName(name);
            System.out.print("나이 : ");
            int age = Integer.parseInt(br.readLine().trim());
            team.setAge(age);
            System.out.print("주민등록번호 : ");
            String registraionNumber = br.readLine().trim();
            team.setRegistraionNumber(registraionNumber);
            System.out.print("전화번호 : ");
            String phoneNum = br.readLine().trim();
            team.setPhoneNum(phoneNum);
            System.out.print("아이디 : ");
            String ID = br.readLine().trim();
            team.setID(ID);
            System.out.print("비밀번호 : ");
            String password = br.readLine().trim();
            team.setPassword(password);
            System.out.print("직급 (1. 사원, 2. 대리, 3. 과장, 4. 부장, 5. 사장) : ");
            int rank = Integer.parseInt(br.readLine().trim());
            team.setEmployeeRank(rank);
            System.out.print("부서 (1. 사고 처리팀, 2. 보상 처리팀, 3. 언더라이팅팀, 4. 손해 사정팀, 5. 상품 개발팀) :");
            int teamNum = Integer.parseInt(br.readLine().trim());
            team.setTeamNumber(teamNum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (new EmployeeDAOImpl().insertEmployee(team)) {
            System.out.println("등록이 완료되었습니다.");
        } else {
            System.out.println("이미 등록되었습니다.");
        }
    }
    private static void registerCustomer(BufferedReader br) {
        Customer customer = new Customer();
        try {
            System.out.print("이름 : ");
            String name = br.readLine().trim();
            customer.setName(name);
            System.out.print("나이 : ");
            int age = Integer.parseInt(br.readLine().trim());
            customer.setAge(age);
            System.out.print("성별 (1. 남자, 2. 여자) : ");
            int sex = Integer.parseInt(br.readLine().trim());
            customer.setSex(sex);
            System.out.print("주민등록번호 : ");
            String registraionNumber = br.readLine().trim();
            customer.setRegistraionNumber(registraionNumber);
            System.out.print("전화번호 : ");
            String phoneNum = br.readLine().trim();
            customer.setPhoneNum(phoneNum);
            System.out.print("운전 면허 번호 : ");
            String driverLicense = br.readLine().trim();
            customer.setDriverLicense(driverLicense);
            System.out.print("차종 : ");
            String carModel = br.readLine().trim();
            customer.setCarModel(carModel);
            System.out.print("차 번호 : ");
            String carNum = br.readLine().trim();
            customer.setCarNum(carNum);
            System.out.print("차 연식 (4자리로 입력해 주세요.) : ");
            String carYear = br.readLine().trim();
            customer.setCarModelYear(carYear);
            System.out.print("아이디 : ");
            String customerID = br.readLine().trim();
            customer.setCustomerID(customerID);
            System.out.print("비밀번호 : ");
            String password = br.readLine().trim();
            customer.setPassword(password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (new CustomerDAOImpl().insertUser(customer)) {
            System.out.println("회원가입이 완료되었습니다.");
        } else {
            System.out.println("이미 등록된 회원입니다.");
        }
    }
    private static String printCustomerLogin(BufferedReader br) {
        System.out.println("***************** CUSTOMER LOGIN MENU *****************");
        String[] user = new String[2];
        try {
            System.out.print("ID : ");
            user[0] = br.readLine().trim();
            System.out.print("Password : ");
            user[1] = br.readLine().trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // DB 중심이 아니게 CRUD 만
        if (new CustomerDAOImpl().retrieveUser(user)) {
            System.out.println(user[0] + "님 환영합니다.");
            return user[0];
        } else {
            System.out.println("아이디 또는 비밀번호가 올바르지 않습니다.");
            return null;
        }
    }
    private static void printFirstMenu() {
        System.out.println("***************** MENU *****************");
        System.out.println("1. 고객");
        System.out.println("2. 직원");
        System.out.println("x. Exit");
    }
    private static void printCustomerLoginMenu() {
        System.out.println("***************** CUSTOMER LOGIN MENU *****************");
        System.out.println("1. 로그인");
        System.out.println("2. 가입하기");
        System.out.println("r. 뒤로가기");
    }
    private static void printEmployeeLoginMenu() {
        System.out.println("***************** EMPLOYEE LOGIN MENU *****************");
        System.out.println("1. 로그인");
        System.out.println("2. 직원 등록하기");
        System.out.println("r. 뒤로가기");
    }
    private static void exitProgram() {
        System.out.println("프로그램 종료");
        System.exit(0);
    }
}