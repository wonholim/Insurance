package view;

import domain.contract.Contract;
import domain.contract.ContractListImpl;
import controller.CustomerController;
import domain.customer.Customer;

import java.nio.Buffer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.io.*;
public class Main {
    public static void main(String[] args) throws NotBoundException, IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                printFirstMenu();
                String select = br.readLine().trim();
                switch (select) {
                    case "1":
                        One:
                        while(true) {
                            printCustomerLoginMenu();
                            select = br.readLine().trim();
                            switch (select) {
                                case "1":
                                    String userName = printCustomerLogin(br);
                                    if (userName != null) {
                                       CustomerLoginView customerLoginView = new CustomerLoginView(userName);
                                       customerLoginView.customerSelect(br);
                                    }
                                    break;
                                case "2":
                                    registerCustomer(br);
                                    break;
                                case "r":
                                    break One;
                                default:
                                    System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "2":
                        Two:
                        while(true) {
                            printEmployeeLoginMenu();
                            select = br.readLine().trim();
                            switch (select) {
                                case "1":
                                    String employeeName = printEmployeeLogin(br);
                                    if (employeeName != null) {
                                        EmployeeLoginView employeeLoginView = new EmployeeLoginView(employeeName);
                                        employeeLoginView.employeeSelect(br);
                                    }
                                    break;
                                case "2":
                                    registerEmployee(br);
                                    break;
                                case "r":
                                    break Two;
                                default:
                                    System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "3":
                        Three:
                        while(true) {
                            printFSSMenu();
                            select = br.readLine().trim();
                            switch (select) {
                                case "1":
                                    String FSSName = printFSSLogin(br);
                                    if(FSSName != null) {
                                        FSSLoginView fssLoginView = new FSSLoginView(FSSName);
                                        fssLoginView.fssSelect();
                                    }
                                    break;
                                case "2":
                                    registerFSS(br);
                                    break;
                                case "r":
                                    break Three;
                                default:
                                    System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "x":
                        exitProgram();
                        return;
                    default:
                        System.out.println("입력값이 올바르지 않습니다.");
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static String printFSSLogin(BufferedReader br) {

        return null;
    }
    private static void registerFSS(BufferedReader br) {

    }
    private static String printEmployeeLogin(BufferedReader br) {

        return null;
    }
    private static void registerEmployee(BufferedReader br) {

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
            System.out.print("아이디 : ");
            String customerID = br.readLine().trim();
            customer.setCustomerID(customerID);
            System.out.print("비밀번호 : ");
            String password = br.readLine().trim();
            customer.setPassword(password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CustomerController customerController = new CustomerController();
        if (customerController.register(customer)) {
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
        CustomerController customerController = new CustomerController();
        if (customerController.login(user)) {
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
        System.out.println("3. 금감원");
        System.out.println("x. Exit");
    }
    private static void printCustomerLoginMenu() {
        System.out.println("***************** CUSTOMER LOGIN MENU *****************");
        System.out.println("1. 로그인");
        System.out.println("2. 가입하기");
        System.out.println("r. 뒤로가기");
    }
    private static void printFSSMenu() {
        System.out.println("***************** FSS MENU *****************");
        System.out.println("1. 로그인");
        System.out.println("2. 금감원 등록하기");
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