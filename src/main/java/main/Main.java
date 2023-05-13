package main;

import contract.Contract;
import contract.ContractListImpl;
import controller.CustomerRegisterController;
import customer.Customer;
import customer.CustomerListImpl;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.io.*;
public class Main {

    public static void main(String[] args) throws NotBoundException, IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                printFirstMenu();
                String select = br.readLine().trim();
                System.out.println(select);
                switch (select) {
                    case "1":
                        One:
                        while(true) {
                            printCustomerLoginMenu();
                            select = br.readLine().trim();
                            System.out.println(select);
                            switch (select) {
                                case "1":
                                    printCustomerLogin();
                                    break;
                                case "2":
                                    registerCustomer();
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
                                    printEmployeeLoginMenu();
                                    break;
                                case "2":
                                    registerCustomer();
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
                                    printCustomerLogin();
                                    break;
                                case "2":
                                    registerCustomer();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void insuranceContract() {
        ContractListImpl contractListImpl = new ContractListImpl();
        Contract contract = new Contract();
    }

    private static void registerCustomer() {
        ContractListImpl customerListImpl = new ContractListImpl();
        Customer customer = new Customer();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("이름 : ");
            String name = br.readLine().trim();
            customer.setName(name);
            System.out.println("나이 : ");
            int age = Integer.parseInt(br.readLine().trim());
            customer.setAge(age);
            System.out.println("주민등록번호 : ");
            String registraionNumber = br.readLine().trim();
            customer.setRegistraionNumber(registraionNumber);
            System.out.println("전화번호 : ");
            String phoneNum = br.readLine().trim();
            customer.setPhoneNum(phoneNum);
            System.out.println("운전 면허 번호 : ");
            String driverLicense = br.readLine().trim();
            customer.setDriverLicense(driverLicense);
            System.out.println("차종 : ");
            String carModel = br.readLine().trim();
            customer.setCarModel(carModel);
            System.out.println("차 번호 : ");
            String carNum = br.readLine().trim();
            customer.setCarNum(carNum);
            System.out.println("아이디 : ");
            String customerID = br.readLine().trim();
            customer.setCustomerID(customerID);
            System.out.println("비밀번호 : ");
            String password = br.readLine().trim();
            customer.setPassword(password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CustomerRegisterController customerRegisterController = new CustomerRegisterController();
        if (customerRegisterController.register(customer)) {
            System.out.println("회원가입이 완료되었습니다.");
        } else {
            System.out.println("이미 등록된 회원입니다.");
        }
    }
    private static void printCustomerLogin() {

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
    private static void printCustomerMenu() {
        System.out.println("***************** CUSTOMER MENU *****************");
        System.out.println("1. 보험가입");
        System.out.println("2. 보험혜지");
        System.out.println("3. 보험갱신");
        System.out.println("4. 사고접수");
        System.out.println("5. 보험금신청");
        System.out.println("l. 로그아웃");
        System.out.println("r. 뒤로가기");
    }
    private static void printEmployeeMenu() {
        System.out.println("***************** EMPLOYEE MENU *****************");
        System.out.println("1.보험 가입 신청 정보 검증");
        System.out.println("2. 사고처리");
        System.out.println("3. 보험금 청구 내역 조사");
        System.out.println("4. 보험금 청구 내역 심사");
        System.out.println("5. 보험금 지급");
        System.out.println("6. 상품개발 등록");
        System.out.println("l. 로그아웃");
        System.out.println("r. 뒤로가기");
    }
    private static void printFSSMenu() {
        System.out.println("***************** FSS MENU *****************");
        System.out.println("1. 상품판매허가");
        System.out.println("r. 뒤로가기");
    }
    private static void printEmployeeLoginMenu() {
        System.out.println("***************** EMPLOYEE LOGIN MENU *****************");
        System.out.println("1. 로그인");
        System.out.println("2. 등록하기");
        System.out.println("r. 뒤로가기");
    }
    private static void exitProgram() {
        System.out.println("프로그램 종료");
        System.exit(0);
    }
    private static void payment() {
    }

    private static void cancellationInsurance() {
    }

    private static void policyRenewalDate() {
    }

    private static void compensation() {
    }

    private static void accidentReceipt() {
    }
}