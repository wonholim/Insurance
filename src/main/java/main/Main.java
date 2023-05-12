package main;

import contract.Contract;
import contract.ContractListImpl;
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

    private static void insuranceContract() {
        ContractListImpl contractListImpl = new ContractListImpl();
        Contract contract = new Contract();
    }

    private static void registerCustomer() {
        CustomerListImpl customerListImpl = new CustomerListImpl();
        Customer customer = new Customer();
        System.out.println("Input Customer Name");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String name = br.readLine();
            customer.setName(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        System.out.println("1. 고객");
        System.out.println("2. 직원");
        System.out.println("3. 금감원");
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
}