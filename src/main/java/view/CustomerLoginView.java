package view;

import controller.CustomerController;

import java.io.BufferedReader;
import java.io.IOException;

public class CustomerLoginView {
    private String userName;
    private CustomerController customerController;
    public CustomerLoginView(String userName) {
        this.userName = userName;
        this.customerController = new CustomerController();
    }
    public void customerSelect(BufferedReader bufferedReader) {
        try {
            OUT:
            while (true) {
                printCustomerMenu();
                String select = bufferedReader.readLine().trim();
                System.out.println(select);
                switch (select) {
                    case "1":
                        One:
                        while(true) {
                            printInsuranceProduct();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1":
                                    String product = this.customerController.getInsuranceProduct(this.userName, "1");
                                    if (product == null) {System.out.println("해당 보험의 세부 내역을 불러오는데 실패하였습니다.");}
                                    else System.out.println(product);
                                    OneOut:
                                    while(true) {
                                        printregisterInsuranceProduct();
                                        select = bufferedReader.readLine().trim();
                                        switch (select) {
                                            case "1":
                                                if(this.customerController.registerInsuranceProduct(this.userName, "1")){
                                                    System.out.println("자동차 보험 가입 신청이 완료되었습니다. 검증 후 3일 뒤 가입 신청 결과를 확인할 수 있습니다.");
                                                }else{
                                                    System.out.println("이미 등록된 보험거나, 보험 가입이 거절 되었습니다.");
                                                }
                                                break OneOut;
                                            case "r": break OneOut;
                                            default : System.out.println("입력값이 올바르지 않습니다.");
                                        }
                                    }
                                    break;
                                case "2":
                                    String driverProduct = this.customerController.getInsuranceProduct(this.userName, "2");
                                    if(driverProduct == null) {System.out.println("해당 보험의 세부 내역을 불러오는데 실패하였습니다.");}
                                    else System.out.println(driverProduct);
                                    TwoOut:
                                    while(true) {
                                        printregisterInsuranceProduct();
                                        select = bufferedReader.readLine().trim();
                                        switch (select) {
                                            case "1":
                                                if(this.customerController.registerInsuranceProduct(this.userName, "2")){
                                                    System.out.println("운전자 보험 가입 신청이 완료되었습니다. 검증 후 3일 뒤 가입 신청 결과를 확인할 수 있습니다.");
                                                }else{
                                                    System.out.println("이미 등록된 보험거나, 보험 가입이 거절 되었습니다.");
                                                }
                                                break TwoOut;
                                            case "r":
                                                break TwoOut;
                                            default : System.out.println("입력값이 올바르지 않습니다.");
                                        }
                                    }
                                    break;
                                case "r": break One;
                                default : System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "2":
                        String allInsurance = new CustomerController().getAllInsuranceProduct(userName);
                        if(!allInsurance.equals("")) {
                            System.out.println("가입된 보험 : " + allInsurance);
                        } else {
                            System.out.println("가입된 보험이 존재하지 않습니다.");
                        }
                        break;
                    case "3":

                        break;
                    case "4":
                        break;
                    case "5":
                        break;
                    case "6":
                        break;
                    case "l":
                        break OUT;
                    default:
                        System.out.println("입력값이 올바르지 않습니다.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printregisterInsuranceProduct() {
        System.out.println("1. 가입하기");
        System.out.println("r. 뒤로가기");
    }

    private static void printInsuranceProduct() {
        System.out.println("***************** PRODUCT MENU *****************");
        System.out.println("1. 자동차 보험 조회");
        System.out.println("2. 운전자 보험 조회");
        System.out.println("r. 뒤로가기");
    }

    private static void printCustomerMenu() {
        System.out.println("***************** CUSTOMER MENU *****************");
        System.out.println("1. 보험 상품 조회");
        System.out.println("2. 가입한 보험 상품 조회");
        System.out.println("3. 보험 혜지");
        System.out.println("4. 보험 갱신");
        System.out.println("5. 사고 접수");
        System.out.println("6. 보험금 신청");
        System.out.println("l. 로그아웃");
    }

}
